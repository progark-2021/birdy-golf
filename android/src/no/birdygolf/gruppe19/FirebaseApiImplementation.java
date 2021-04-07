package no.birdygolf.gruppe19;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import no.birdygolf.gruppe19.api.FirebaseApi;
import no.birdygolf.gruppe19.api.FirebaseStatus;
import no.birdygolf.gruppe19.api.ScoreDto;
import no.birdygolf.gruppe19.api.ScoreObserver;

public class FirebaseApiImplementation implements FirebaseApi {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final Set<ScoreObserver> observers = new HashSet<>();

    @Override
    public void postScore(ScoreDto score) {
        Map<String, Object> dbScore = new HashMap<>();
        dbScore.put("score", score.getScore());

        db.collection("scores").document(score.getPlayer()).set(dbScore);
    }

    @Override
    public void listen(ScoreObserver observer) {
        observers.add(observer);
    }

    @Override
    public void mute(ScoreObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void getScores() {
        List<ScoreDto> scores = new ArrayList<>();
        observers.forEach(observer -> observer.receiveUpdate(scores, FirebaseStatus.LOADING));
        db.collection("scores").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        scores.addAll(task.getResult().getDocuments().stream().map(document -> {
                            return new ScoreDto(
                                    document.getId(),
                                    document.getLong("score")
                            );
                        }).collect(Collectors.toList()));
                        scores.sort((s1, s2) -> (int) (s1.getScore() - s2.getScore()));
                        observers.forEach(observer -> observer.receiveUpdate(scores, FirebaseStatus.SUCCESS));
                    } else {
                        task.getException().printStackTrace();
                        observers.forEach(observer -> observer.receiveUpdate(scores, FirebaseStatus.ERROR));
                    }
                });
    }


}

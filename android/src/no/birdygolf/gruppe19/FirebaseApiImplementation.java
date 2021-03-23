package no.birdygolf.gruppe19;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import no.birdygolf.gruppe19.api.FirebaseApi;
import no.birdygolf.gruppe19.api.FirebaseStatus;
import no.birdygolf.gruppe19.api.ScoreDto;
import no.birdygolf.gruppe19.screen.HighScoreScreen;

public class FirebaseApiImplementation implements FirebaseApi {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void postScore(ScoreDto score) {
        Map<String, Object> dbScore = new HashMap<>();
        dbScore.put("score", score.getScore());

        db.collection("scores").document(score.getPlayer()).set(dbScore);
    }

    @Override
    public void getScores(HighScoreScreen highScoreScreen) {
        List<ScoreDto> scores = new ArrayList<>();
        highScoreScreen.createHighScoreList(scores, FirebaseStatus.LOADING);
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
                        highScoreScreen.createHighScoreList(scores, FirebaseStatus.SUCCESS);
                    } else {
                        task.getException().printStackTrace();
                        highScoreScreen.createHighScoreList(scores, FirebaseStatus.ERROR);
                    }
                });
    }
}

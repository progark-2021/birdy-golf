package no.birdygolf.gruppe19.api;


import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class FirebaseApi {
    public static final FirebaseApi INSTANCE = new FirebaseApi();
    private static Firestore db;

    private FirebaseApi() {
        GoogleCredentials credentials = null;
        try {
            credentials = GoogleCredentials.fromStream(new FileInputStream("gcloud-auth.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirestoreOptions firestoreOptions = null;
        firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId("birdy-golf")
                .setCredentials(credentials)
                .build();

        db = firestoreOptions.getService();
    }

    /**
     * Post a new score to firebase
     *
     * @param score The {@link ScoreDto} containing a player's name and their score.
     */
    public void postScore(ScoreDto score) {
        DocumentReference docRef = db.collection("scores").document(score.getPlayer());

        Map<String, Long> data = new HashMap<>();
        data.put("score", score.getScore());

        ApiFuture<WriteResult> result = docRef.set(data);
    }

    /**
     * Gets all saved scores from the database, sorted from highest to lowest
     *
     * @return The sorted list of {@link ScoreDto}
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<ScoreDto> getScores() throws ExecutionException, InterruptedException {
        CollectionReference colRef = db.collection("scores");

        QuerySnapshot snapshot = colRef.get().get();

        List<ScoreDto> output = snapshot.getDocuments().stream().map(doc -> new ScoreDto(doc.getId(), (long) doc.get("score"))).collect(Collectors.toList());
        output.sort((s1, s2) -> (int) (s2.getScore() - s1.getScore()));
        return output;
    }
}

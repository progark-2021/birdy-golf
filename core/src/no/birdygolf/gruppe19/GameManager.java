package no.birdygolf.gruppe19;

import java.util.ArrayList;
import java.util.List;

import no.birdygolf.gruppe19.api.FirebaseApi;
import no.birdygolf.gruppe19.api.ScoreDto;

public class GameManager {
    public static final GameManager INSTANCE = new GameManager();
    public final List<String> playerNames = new ArrayList<>();
    public final List<Integer> playerHits = new ArrayList<>();
    public FirebaseApi firebaseApi;
    public int currentLevel = -1;
    public int playerTurn = 0;
    public int currentScore = 0;

    private GameManager() {
    }

    public void nextPlayer() {
        currentScore = 0;
        playerTurn++;
        playerTurn %= playerNames.size();
    }

    public void increaseHits() {
        currentScore++;
        playerHits.set(playerTurn, playerHits.get(playerTurn) + 1);
    }

    public void resetGame() {
        // Post scores to firebase
        for (int i = 0; i < playerNames.size(); i++) {
            firebaseApi.postScore(
                    new ScoreDto(
                            playerNames.get(i) == ""
                                    ? "Anonymous"
                                    : playerNames.get(i),
                            playerHits.get(i)
                    )
            );
        }

        playerTurn = 0;
        playerNames.clear();
        playerHits.clear();
        currentLevel = -1;
    }

}

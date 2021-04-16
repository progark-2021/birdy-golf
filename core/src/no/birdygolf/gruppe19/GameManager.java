package no.birdygolf.gruppe19;

import java.util.ArrayList;
import java.util.List;

import no.birdygolf.gruppe19.levels.Level;

public class GameManager {
    public static final GameManager INSTANCE = new GameManager();


    private final no.birdygolf.gruppe19.levels.Level[] levels = Level.values();
    public int currentLevel = -1;

    public int playerTurn = 0;
    public final List<String> playerNames = new ArrayList<>();
    public final List<Integer> playerHits = new ArrayList<>();


    private GameManager() {
    }

    public void nextPlayer() {
        playerTurn++;
        playerTurn %= playerNames.size();
    }

    public void increaseHits() {
        playerHits.set(playerTurn, playerHits.get(playerTurn) + 1);
    }

    public void resetGame(){
        playerTurn = 0;
        playerNames.clear();
        playerHits.clear();
        currentLevel = -1;
    }

}

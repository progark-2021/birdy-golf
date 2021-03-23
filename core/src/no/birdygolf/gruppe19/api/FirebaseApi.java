package no.birdygolf.gruppe19.api;

import no.birdygolf.gruppe19.screen.HighScoreScreen;

public interface FirebaseApi {
    /**
     * Post a new score to firebase
     *
     * @param score The {@link ScoreDto} containing a player's name and their score.
     */
    void postScore(ScoreDto score);

    /**
     * Gets all saved scores from the database, sorted from highest to lowest, and renders them in the supplied {@link HighScoreScreen}
     */
    void getScores(HighScoreScreen highScoreScreen);
}

package no.birdygolf.gruppe19.api;

public interface FirebaseApi {
    /**
     * Post a new score to firebase
     *
     * @param score The {@link ScoreDto} containing a player's name and their score.
     */
    void postScore(ScoreDto score);

    /**
     * Gets all saved scores from the database, sorted from highest to lowest, and renders notifies listeners of the update.
     */
    void getScores();

    /**
     * Add a new {@link ScoreObserver} to the set of listeners.
     * @param observer The listener to add.
     */
    void listen(ScoreObserver observer);

    /**
     * Remove the given {@link ScoreObserver} from the set of listeners.
     * @param observer The listenr to remove.
     */
    void mute(ScoreObserver observer);
}

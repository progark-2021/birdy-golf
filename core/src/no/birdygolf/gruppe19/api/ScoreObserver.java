package no.birdygolf.gruppe19.api;

import java.util.List;

public interface ScoreObserver {
    void receiveUpdate(List<ScoreDto> highScores, FirebaseStatus status);
}

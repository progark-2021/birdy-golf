package no.birdygolf.gruppe19.desktop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import no.birdygolf.gruppe19.api.FirebaseApi;
import no.birdygolf.gruppe19.api.FirebaseStatus;
import no.birdygolf.gruppe19.api.ScoreDto;
import no.birdygolf.gruppe19.api.ScoreObserver;

/**
 * A mock implementation of {@link FirebaseApi}.
 * This class does nothing except enable the use of {@link DesktopLauncher} for testing.
 * <p>
 * Run the project in Android to get access to Firebase features.
 */
public class FirebaseApiMock implements FirebaseApi {
    Set<ScoreObserver> observers = new HashSet<>();

    @Override
    public void postScore(ScoreDto score) {
        // Do nothing
    }

    @Override
    public void getScores() {
        List<ScoreDto> scores = new ArrayList<>();
        observers.forEach(observer -> observer.receiveUpdate(scores, FirebaseStatus.ERROR));
    }

    @Override
    public void mute(ScoreObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void listen(ScoreObserver observer) {
        observers.add(observer);
    }


}
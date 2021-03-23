package no.birdygolf.gruppe19.desktop;

import java.util.ArrayList;
import java.util.List;

import no.birdygolf.gruppe19.api.FirebaseApi;
import no.birdygolf.gruppe19.api.FirebaseStatus;
import no.birdygolf.gruppe19.api.ScoreDto;
import no.birdygolf.gruppe19.screen.HighScoreScreen;

/**
 * A mock implementation of {@link FirebaseApi}.
 * This class does nothing except enable the use of {@link DesktopLauncher} for testing.
 * <p>
 * Run the project in Android to get access to Firebase features.
 */
public class FirebaseApiMock implements FirebaseApi {

    @Override
    public void postScore(ScoreDto score) {
        // Do nothing
    }

    @Override
    public void getScores(HighScoreScreen highScoreScreen) {
        List<ScoreDto> scores = new ArrayList<>();
        highScoreScreen.createHighScoreList(scores, FirebaseStatus.ERROR);
    }
}
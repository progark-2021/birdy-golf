package no.birdygolf.gruppe19.levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Arrays;
import java.util.List;

public enum Level {
    LEVEL_1(
            new Vector2(20, 100),
            new Vector2(300, 600),
            new Vector3(30, 30, 0)),
    LEVEL_2(
            new Vector2(300, 600),
            new Vector2(20, 100),
            new Vector3(100, 100, 45),
            new Vector3(400, 200, 0),
            new Vector3(10, 300, 45)
    ),
    LEVEL_3(
            new Vector2(20, 100),
            new Vector2(20, 600),
            new Vector3(300, 400, 0));


    public final Vector2 holePosition;
    public final Vector2 startPosition;
    public final List<Vector3> obstacles;

    Level(Vector2 holePosition, Vector2 startPosition, Vector3... obstacles) {
        this.holePosition = holePosition;
        this.startPosition = startPosition;
        this.obstacles = Arrays.asList(obstacles);
    }
}

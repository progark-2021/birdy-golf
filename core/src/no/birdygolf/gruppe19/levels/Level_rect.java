package no.birdygolf.gruppe19.levels;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Arrays;
import java.util.List;

public enum Level_rect {
    LEVEL_1(
            new Vector2(240, 400), //hull
            new Vector2(240, 100), //ball
            new Rectangle(100, 100, 100, 100),
            new Rectangle(0, 0, 100, 100)
    ),

    LEVEL_2(
            new Vector2(-150, 400),
            new Vector2(0, -150),
            //Buttom wall
            new Rectangle(100, 0, 100, 100));


    public final Vector2 holePosition;
    public final Vector2 startPosition;
    public final List<Rectangle> obstacles;

    Level_rect(Vector2 holePosition, Vector2 startPosition, Rectangle... rectangles) {
        this.holePosition = holePosition;
        this.startPosition = startPosition;
        this.obstacles = Arrays.asList(rectangles);
    }
}

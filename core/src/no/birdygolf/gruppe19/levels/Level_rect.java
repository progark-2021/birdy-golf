package no.birdygolf.gruppe19.levels;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Arrays;
import java.util.List;

public enum Level_rect {
    LEVEL_1(
            new Vector2(0, 400), //hull
            new Vector2(200, 100), //ball
            //Bottom wall
            new Rectangle(20, 0, 440, 40),
            //Side walls
            new Rectangle(20, 0, 40, 300),
            new Rectangle(420, 0, 40, 300),
            //Midde walls
            new Rectangle(20, 300, 120, 40),
            new Rectangle(340, 300, 120, 40),
            //Side walls
            new Rectangle(140, 300, 40, 300),
            new Rectangle(300, 300, 40, 300),
            //Midde walls
            new Rectangle(60, 600, 120, 40),
            new Rectangle(300, 600, 120, 40),
            //Side walls
            new Rectangle(20, 600, 40, 150),
            new Rectangle(420, 600, 40, 150),
            //Top wall
            new Rectangle(20, 750, 440, 40)
    ),

    LEVEL_2(
            new Vector2(-150, 400), //hull
            new Vector2(100, 100), //ball
            //Bottom wall
            new Rectangle(20, 0, 440, 40),
            //Side walls
            new Rectangle(20, 0, 40, 250),
            new Rectangle(420, 0, 40, 750),
            //Middle vertical wall
            new Rectangle(20, 250, 200, 40),
            //Side wall
            new Rectangle(180, 250, 40, 250),
            //Middle vertical wall
            new Rectangle(20, 500, 200, 40),
            //Side wall
            new Rectangle(20, 500, 40, 250),

            /*//Side walls
            new Rectangle(140, 300, 40, 300),
            new Rectangle(300, 300, 40, 300),
            //Midde walls
            new Rectangle(60, 600, 120, 40),
            new Rectangle(300, 600, 120, 40),
            //Side walls
            new Rectangle(20, 600, 40, 150),
            new Rectangle(420, 600, 40, 150),*/
            //Top wall
            new Rectangle(20, 750, 440, 40)
    );


    public final Vector2 holePosition;
    public final Vector2 startPosition;
    public final List<Rectangle> obstacles;

    Level_rect(Vector2 holePosition, Vector2 startPosition, Rectangle... rectangles) {
        this.holePosition = holePosition;
        this.startPosition = startPosition;
        this.obstacles = Arrays.asList(rectangles);
    }
}

package no.birdygolf.gruppe19.level;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.List;

public enum Level {
    LEVEL_1(
            new Vector2(240, 700), //hull
            new Vector2(240, 100), //ball
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
            new Vector2(120, 600), //hull
            new Vector2(120, 100), //ball
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
            //Top wall
            new Rectangle(20, 750, 440, 40)
    ),
    LEVEL_3(
            new Vector2(120, 680), //hull
            new Vector2(100, 120), //ball

            new Rectangle(20, 0, 440, 40),
            //Side walls
            new Rectangle(20, 0, 40, 250),
            new Rectangle(420, 0, 40, 600),
            //Middle vertical wall
            new Rectangle(20, 210, 240, 40),
            //Middle wall
            new Rectangle(260, 210, 40, 240),
            new Rectangle(20, 410, 240, 40),
            //Left wall
            new Rectangle(20, 450, 40, 270),
            new Rectangle(180, 600, 280, 40),
            new Rectangle(180, 600, 40, 120),
            //Top wall
            new Rectangle(20, 720, 200, 40)

    ),
    LEVEL_4(
            new Vector2(370, 710), //hull
            new Vector2(240, 100), //ball
            //Bottom wall
            new Rectangle(120, 0, 200, 40),
            //Side walls small
            new Rectangle(120, 0, 40, 200),
            new Rectangle(320, 0, 40, 200),
            new Rectangle(120, 550, 40, 230),
            new Rectangle(320, 550, 40, 100),
            //Middle vertical wall
            new Rectangle(20, 200, 140, 40),
            new Rectangle(320, 200, 140, 40),
            //Side wall big
            new Rectangle(20, 230, 40, 320),
            new Rectangle(420, 230, 40, 320),
            new Rectangle(420, 650, 40, 140),
            //Middle vertical wall
            new Rectangle(20, 550, 140, 40),
            new Rectangle(320, 550, 140, 40),
            new Rectangle(320, 630, 140, 40),
            //Top vertical
            new Rectangle(120, 750, 330, 40),
            //middle sand
            new Rectangle(180, 345, 120, 100)
    );


    public final Vector2 holePosition;
    public final Vector2 startPosition;
    public final List<Rectangle> obstacles;

    Level(Vector2 holePosition, Vector2 startPosition, Rectangle... rectangles) {
        this.holePosition = holePosition;
        this.startPosition = startPosition;
        this.obstacles = Arrays.asList(rectangles);
    }
}

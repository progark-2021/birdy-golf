package no.birdygolf.gruppe19.levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Arrays;
import java.util.List;

public enum Level {
    LEVEL_1(
            new Vector2(20, 100),
            new Vector2(300, 600),
            new Vector3(80, 60, 90),
            new Vector3(30, 30, 0)),
            /*new Vector2(300, 1500),
            new Vector2(300, 50),
            new Vector3(-200, -100, 0),
            new Vector3(0, -100, 0),
            new Vector3(200, -100, 0),
            new Vector3(400, -100, 0),
            new Vector3(-350, -50, 90),
            new Vector3(-350, 150, 90),
            new Vector3(-350, 350, 90),
            new Vector3(-350, 550, 90),
            new Vector3(-350, 750, 90),
            new Vector3(550, -50, 90),
            new Vector3(550, -50, 90),
            new Vector3(550, 150, 90),
            new Vector3(550, 350, 90),
            new Vector3(550, 550, 90),
            new Vector3(550, 750, 90),
            new Vector3(400, 800, 0),
            new Vector3(-200, 800, 0),
            new Vector3(-150, 950, 90),
            new Vector3(350, 950, 90),
            new Vector3(350, 1150, 90),
            new Vector3(-150, 1150, 90),
            new Vector3(350, 1350, 90),
            new Vector3(-150, 1350, 90),
            new Vector3(450, 1400, 0),
            new Vector3(-250, 1400, 0),
            new Vector3(500, 1550, 90),
            new Vector3(-300, 1550, 90),
            new Vector3(500, 1650, 90),
            new Vector3(-300, 1650, 90),
            new Vector3(450, 1700, 0),
            new Vector3(-250, 1700, 0),
            new Vector3(250, 1700, 0),
            new Vector3(-50, 1700, 0),
            new Vector3(50, 1700, 0)),*/
    LEVEL_2(
            new Vector2(0, 1600),
            new Vector2(300, 50),
            new Vector3(-200, -100, 0),
            new Vector3(0, -100, 0),
            new Vector3(200, -100, 0),
            new Vector3(400, -100, 0),
            new Vector3(-350, -50, 90),
            new Vector3(-350, 150, 90),
            new Vector3(-350, 350, 90),
            new Vector3(-290, 500, 45),
            new Vector3(-200, 590, 45),
            new Vector3(-110, 680, 45),
            new Vector3(550, -50, 90),
            new Vector3(550, -50, 90),
            new Vector3(550, 150, 90),
            new Vector3(400, 200, 0),
            new Vector3(350, 350, 90),
            new Vector3(350, 550, 90),
            new Vector3(350, 750, 90),
            new Vector3(350, 950, 90),
            new Vector3(350, 1150, 90),
            new Vector3(350, 1350, 90),
            new Vector3(350, 1550, 90),
            new Vector3(350, 1750, 90),
            new Vector3(300, 1850, 0),
            new Vector3(100, 1850, 0),
            new Vector3(-100, 1850, 0),
            new Vector3(-200, 1850, 0),
            new Vector3(-50, 830, 90),
            new Vector3(-50, 1000, 90),
            new Vector3(-50, 1200, 90),
            new Vector3(-100, 1350, 0),
            new Vector3(-300, 1350, 0),
            new Vector3(-350, 1400, 90),
            new Vector3(-350, 1600, 90),
            new Vector3(-350, 1800, 90)
    ),
    LEVEL_3(
            new Vector2(100, 1200),
            new Vector2(20, 600),
            new Vector3(300, 400, 0),
            new Vector3(600, 900, 70));


    public final Vector2 holePosition;
    public final Vector2 startPosition;
    public final List<Vector3> obstacles;

    Level(Vector2 holePosition, Vector2 startPosition, Vector3... obstacles) {
        this.holePosition = holePosition;
        this.startPosition = startPosition;
        this.obstacles = Arrays.asList(obstacles);
    }
}

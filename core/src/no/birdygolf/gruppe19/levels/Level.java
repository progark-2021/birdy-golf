package no.birdygolf.gruppe19.levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Arrays;
import java.util.List;

public enum Level {
    LEVEL_1(
            new Vector2(20, 100),
            new Vector2(30, 60),
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
            new Vector2(-150, 400),
            new Vector2(0, -150),
            //Buttom wall
            new Vector3(-360, -200, 0),
            new Vector3(-280, -200, 0),
            new Vector3(-200, -200, 0),
            new Vector3(-120, -200, 0),
            new Vector3(-50, -200, 0),
            //Left side wall
            new Vector3(-382, -135, 90),
            new Vector3(-382, -55, 90),
            //Diagonal wall
            new Vector3(-358, 5, 45),
            new Vector3(-300, 62, 45),
            //Left side wall
            new Vector3(-275, 120, 90),
            new Vector3(-275, 200, 90),
            new Vector3(-275, 280, 90),
            //Left corner
            new Vector3(-297, 340, 0),
            new Vector3(-370, 340, 0),
            new Vector3(-392, 390, 90),
            new Vector3(-392, 470, 90),
            //Top wall
            new Vector3(-370, 530, 0),
            new Vector3(-290, 530, 0),
            new Vector3(-210, 530, 0),
            new Vector3(-130, 530, 0),
            new Vector3(-50, 530, 0),
            //Right wall
            new Vector3(-28, 470, 90),
            new Vector3(-28, 390, 90),
            new Vector3(-28, 310, 90),
            new Vector3(-28, 230, 90),
            new Vector3(-28, 150, 90),
            new Vector3(-28, 70, 90),
            new Vector3(-28, -10, 90),
            new Vector3(-28, -90, 90),
            new Vector3(-28, -170, 90)
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

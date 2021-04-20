package no.birdygolf.gruppe19.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Arrays;
import java.util.List;

public enum Level {
    LEVEL_1(
            new Vector2(-20, 400), //hull
            new Vector2(-20, -100), //ball
            //side wall big
            new Vector3(-23, 53, 90), //obstacle 80
            new Vector3(-382, 53, 90), //obstacle 91
            new Vector3(-23, -27, 90),
            new Vector3(-382, -27, 90),
            new Vector3(-23, -107, 90),
            new Vector3(-382, -107, 90),
            new Vector3(-23, 447, 90),
            new Vector3(-382, 447, 90),
            //side wall small
            new Vector3(-107, 187, 90),
            new Vector3(-298, 187, 90),
            new Vector3(-107, 275, 90),
            new Vector3(-298, 275, 90),
            new Vector3(-107, 355, 90),
            new Vector3(-298, 355, 90),
            //middle wall
            new Vector3(-320, 120, 0),
            new Vector3(-360, 120, 0),
            new Vector3(-320, -170, 0),
            new Vector3(-360, -170, 0),
            new Vector3(-85, 120, 0),
            new Vector3(-45, 120, 0),
            new Vector3(-85, -170, 0),
            new Vector3(-45, -170, 0),
            new Vector3(-170, -170, 0),
            new Vector3(-250, -170, 0),
            new Vector3(-320, 380, 0),
            new Vector3(-360, 380, 0),
            new Vector3(-85, 380, 0),
            new Vector3(-45, 380, 0),
            new Vector3(-85, 510, 0),
            new Vector3(-45, 510, 0),
            new Vector3(-320, 510, 0),
            new Vector3(-360, 510, 0),
            new Vector3(-170, 510, 0),
            new Vector3(-250, 510, 0)),
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
            new Vector2(20, 600));


    public final Vector2 holePosition;
    public final Vector2 startPosition;
    public final List<Vector3> obstacles;

    Level(Vector2 holePosition, Vector2 startPosition, Vector3... obstacles) {
        this.holePosition = holePosition;
        this.startPosition = startPosition;
        this.obstacles = Arrays.asList(obstacles);
    }
}

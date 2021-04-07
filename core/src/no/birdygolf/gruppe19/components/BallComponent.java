package no.birdygolf.gruppe19.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class BallComponent implements Component {
    //public CircleShape CIRCLE_SHAPE = new CircleShape();
    public float HEIGHT = 154; //512 pixler scalet med 0.3f
    public float WIDTH = 154;
    public int hits = 0; //counting the number the user hits the ball in one level
    public int score = 0; //the user's score

}

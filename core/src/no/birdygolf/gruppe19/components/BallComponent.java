package no.birdygolf.gruppe19.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class BoundsComponent implements Component {

    public static final CircleShape CIRCLE_SHAPE = new CircleShape();
    public static final float RADIUS = 6f;
    public static final float DENSITY = 0.5f;
    public static final float FRICTION = 0.4f;
    public static final float RESTITUTION = 0.6f;

}

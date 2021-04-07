package no.birdygolf.gruppe19.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

// Describing the balls rotation
public class TransformComponent implements Component {
    public Vector3 pos = new Vector3();
    public Vector2 scale = new Vector2();
    public float rotation = 0.0f;
}




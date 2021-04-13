package no.birdygolf.gruppe19.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Fixture;


public class MovementComponent implements Component {

    Fixture fixture;
    public MovementComponent(Fixture fixture){
        this.fixture = fixture;


    }




}

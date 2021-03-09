package no.birdygolf.gruppe19.factory;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import no.birdygolf.gruppe19.components.PhysicsComponent;

public class BallFactory {


    public void addGolfBall(){
        Entity golfball = new Entity();
        //egt parametere som sendes inn slik at physicscomponent ikke er like hardkodet som nå
        PhysicsComponent circularDynamic = new PhysicsComponent();
        golfball.add(circularDynamic);

    }
}

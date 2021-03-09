package no.birdygolf.gruppe19.factory;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import no.birdygolf.gruppe19.components.PhysicsComponent;

public class BallFactory {


    public void addGolfBall(){
        Entity golfball = new Entity();
        //skal egt ha flere parametere slik at physicscomponent ikke er like hardkodet som nå
        PhysicsComponent circularDynamic = new PhysicsComponent(100,300);
        //adding components to the entity
        golfball.add(circularDynamic);

    }
}

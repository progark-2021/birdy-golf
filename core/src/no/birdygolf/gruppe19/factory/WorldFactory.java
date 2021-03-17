package no.birdygolf.gruppe19.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import no.birdygolf.gruppe19.components.PhysicsComponent;
import no.birdygolf.gruppe19.components.TextureComponent;

// Creates the ball entity and adds all the comnponents belonging to this entity
public class WorldFactory {

    private PooledEngine engine;

    public WorldFactory(PooledEngine engine){
        this.engine = engine;
    }

    private Entity createGolfBall(){
        Entity golfball = engine.createEntity();

        //skal egt ha flere parametere slik at physicscomponent ikke er like hardkodet som nå
        PhysicsComponent circularDynamic = engine.createComponent(PhysicsComponent.class);
        TextureComponent ball = engine.createComponent(TextureComponent.class);

        //adding components to the entity
        golfball.add(circularDynamic);
        golfball.add(ball);

        return golfball;
    }
}

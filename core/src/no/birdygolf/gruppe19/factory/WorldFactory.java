package no.birdygolf.gruppe19.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import no.birdygolf.gruppe19.components.BallComponent;
import no.birdygolf.gruppe19.components.BoundsComponent;
import no.birdygolf.gruppe19.components.MovementComponent;
import no.birdygolf.gruppe19.components.PhysicsComponent;
import no.birdygolf.gruppe19.components.TextureComponent;
import no.birdygolf.gruppe19.components.TransformComponent;

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
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        BallComponent ball = engine.createComponent(BallComponent.class);

        position.pos.set(100, 300, 0.0f);

        //adding components to the entity
        golfball.add(circularDynamic);
        golfball.add(texture);
        golfball.add(movement);
        golfball.add(bounds);
        golfball.add(ball);

        engine.addEntity(golfball);

        return golfball;
    }
}

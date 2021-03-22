package no.birdygolf.gruppe19.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Sprite;

import no.birdygolf.gruppe19.Assets;
import no.birdygolf.gruppe19.components.BallComponent;
import no.birdygolf.gruppe19.components.BoundsComponent;
import no.birdygolf.gruppe19.components.MovementComponent;
import no.birdygolf.gruppe19.components.PhysicsComponent;
import no.birdygolf.gruppe19.components.SpriteComponent;
import no.birdygolf.gruppe19.components.TextureComponent;
import no.birdygolf.gruppe19.components.TransformComponent;

// Creates the ball entity and adds all the comnponents belonging to this entity
public class WorldFactory {

    private PooledEngine engine;

    public WorldFactory(PooledEngine engine){
        this.engine = engine;
    }

    public void create() {
        createGolfBall();
        createObstacle(300, 300, 0);
        createObstacle(10, 500, 45);
    }


    private void createGolfBall(){
        Entity golfball = engine.createEntity();

        //skal egt ha flere parametere slik at physicscomponent ikke er like hardkodet som nå
        //TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        //BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        // BallComponent ball = engine.createComponent(BallComponent.class);

        spriteComponent.sprite = Assets.ball;
        spriteComponent.sprite.setScale(0.3f);

        position.pos.set(275, 0, 0.0f);

        //adding components to the entity
        golfball.add(spriteComponent);
        golfball.add(position);
        golfball.add(movement);
        //golfball.add(bounds);
        // golfball.add(ball);

        engine.addEntity(golfball);
    }

    private void createObstacle(int posX, int posY, float degrees){
        Entity obstacle = engine.createEntity();

        SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);

        transformComponent.pos.set(posX, posY, 0.0f);
        transformComponent.pos.rotate(degrees, transformComponent.pos.x, transformComponent.pos.y, transformComponent.pos.z);

        //transformComponent.scale.set(posX, posY);
        //transformComponent.scale.setAngleDeg(degrees);

        spriteComponent.sprite = Assets.obstacle;
        spriteComponent.sprite.setScale(0.3f);

        obstacle.add(spriteComponent);
        obstacle.add(transformComponent);


        engine.addEntity(obstacle);

    }
}

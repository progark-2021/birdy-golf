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
import no.birdygolf.gruppe19.components.StateComponent;
import no.birdygolf.gruppe19.components.TextureComponent;
import no.birdygolf.gruppe19.components.TransformComponent;

// Creates the ball entity and adds all the comnponents belonging to this entity
public class WorldFactory {

    private PooledEngine engine;
    public int currentLevel;

    public WorldFactory(PooledEngine engine){
        this.engine = engine;
    }

    public void create() {
        createGolfBall(); // create player 1

        System.out.println("The next level to play is: " + currentLevel);
        if (currentLevel == 0){
            createLevel1();
        }
        else if (currentLevel == 1){
            createLevel2();
        }

    }

    private void createGolfBall(){
        Entity golfball = engine.createEntity();

        //skal egt ha flere parametere slik at physicscomponent ikke er like hardkodet som nå
        //TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
        TransformComponent positionComponent = engine.createComponent(TransformComponent.class);
        MovementComponent movementComponent = engine.createComponent(MovementComponent.class);
        BoundsComponent boundsComponent = engine.createComponent(BoundsComponent.class);
        BallComponent ballComponent = engine.createComponent(BallComponent.class);
        StateComponent stateComponent = engine.createComponent(StateComponent.class);

        spriteComponent.sprite = Assets.ball;
        spriteComponent.sprite.setScale(0.3f);

        positionComponent.pos.set(275, 0, 0.0f);

        //OBS ikke riktige bounds på grunn av scale
        //boundsComponent.bounds.width = spriteComponent.sprite.getWidth();
        boundsComponent.bounds.width = ballComponent.WIDTH;
        System.out.println("width bounds: " + ballComponent.WIDTH);
        //boundponent.bounds.height = spriteComponent.sprite.getHeight();
        boundsComponent.bounds.height = ballComponent.HEIGHT;
        System.out.println("height bounds " + ballComponent.HEIGHT);

        currentLevel = stateComponent.getLevel();
        System.out.println("current level: " + currentLevel);

        //adding components to the entity
        golfball.add(spriteComponent);
        golfball.add(positionComponent);
        golfball.add(movementComponent);
        golfball.add(boundsComponent);
        golfball.add(ballComponent);
        golfball.add(stateComponent);

        engine.addEntity(golfball);
    }

    // Creating the first level
    private void createLevel1() {
        createObstacle(300, 300, 100);
        createObstacle(10, 500, 45);
        createObstacle(100, 520, 45);
    }

    private void createLevel2() {
        createObstacle(400, 10, 200);
        createObstacle(600, 520, 45);
    }

    private void createObstacle(int posX, int posY, float degrees){
        Entity obstacle = engine.createEntity();

        SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);

        transformComponent.pos.set(posX, posY, 0.0f);
        transformComponent.rotation = degrees;

        spriteComponent.sprite = Assets.obstacle;
        spriteComponent.sprite.setScale(0.3f);

        obstacle.add(spriteComponent);
        obstacle.add(transformComponent);

        engine.addEntity(obstacle);
    }
}

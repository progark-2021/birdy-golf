package no.birdygolf.gruppe19.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

import no.birdygolf.gruppe19.Assets;
import no.birdygolf.gruppe19.components.BallComponent;
import no.birdygolf.gruppe19.components.BoundsComponent;
import no.birdygolf.gruppe19.components.MovementComponent;
import no.birdygolf.gruppe19.components.SpriteComponent;
import no.birdygolf.gruppe19.components.TransformComponent;
import no.birdygolf.gruppe19.levels.Level;

// Creates the ball entity and adds all the comnponents belonging to this entity
public class WorldFactory {

    private final PooledEngine engine;
    public int currentLevel;

    public WorldFactory(PooledEngine engine) {
        this.engine = engine;
    }

    private void createGolfBall(Vector2 position) {
        Entity golfball = engine.createEntity();

        //skal egt ha flere parametere slik at physicscomponent ikke er like hardkodet som nå
        //TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
        SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
        TransformComponent positionComponent = engine.createComponent(TransformComponent.class);
        MovementComponent movementComponent = engine.createComponent(MovementComponent.class);
        BoundsComponent boundsComponent = engine.createComponent(BoundsComponent.class);
        BallComponent ballComponent = engine.createComponent(BallComponent.class);

        spriteComponent.sprite = Assets.ball;
        spriteComponent.sprite.setScale(0.3f);

        positionComponent.pos.set(position, 0f);

        //OBS ikke riktige bounds på grunn av scale
        //boundsComponent.bounds.width = spriteComponent.sprite.getWidth();
        boundsComponent.bounds.width = ballComponent.WIDTH;
        System.out.println("width bounds: " + ballComponent.WIDTH);
        //boundponent.bounds.height = spriteComponent.sprite.getHeight();
        boundsComponent.bounds.height = ballComponent.HEIGHT;
        System.out.println("height bounds " + ballComponent.HEIGHT);

        //currentLevel = stateComponent.getLevel();
        System.out.println("current level: " + currentLevel);

        //adding components to the entity
        golfball.add(spriteComponent);
        golfball.add(positionComponent);
        golfball.add(movementComponent);
        golfball.add(boundsComponent);
        golfball.add(ballComponent);

        engine.addEntity(golfball);
    }

    public void createLevel(Level level) {
        createGolfBall(level.startPosition);
        createHole(level.holePosition);
        level.obstacles.forEach(obstacle -> createObstacle(obstacle.x, obstacle.y, obstacle.z));
    }

    private void createObstacle(float posX, float posY, float degrees) {
        Entity obstacle = engine.createEntity();

        SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);

        transformComponent.pos.set(posX, posY, 0.0f);
        transformComponent.rotation = degrees;

        spriteComponent.sprite = Assets.obstacle;
        spriteComponent.sprite.setScale(0.25f);

        obstacle.add(spriteComponent);
        obstacle.add(transformComponent);

        engine.addEntity(obstacle);
    }

    private void createHole(Vector2 position) {
        Entity hole = engine.createEntity();

        SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
        TransformComponent positionComponent = engine.createComponent(TransformComponent.class);

        spriteComponent.sprite = Assets.hole;
        spriteComponent.sprite.setScale(0.3f);

        positionComponent.pos.set(position, 0f);

        //adding components to the entity
        hole.add(spriteComponent);
        hole.add(positionComponent);

        engine.addEntity(hole);
    }
}

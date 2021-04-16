package no.birdygolf.gruppe19.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import no.birdygolf.gruppe19.Assets;
import no.birdygolf.gruppe19.components.BallComponent;
import no.birdygolf.gruppe19.components.MovementComponent;
import no.birdygolf.gruppe19.components.PhysicsComponent;
import no.birdygolf.gruppe19.components.SpriteComponent;
import no.birdygolf.gruppe19.levels.Level;

// Creates the ball entity and adds all the comnponents belonging to this entity
public class WorldFactory {

    private final World world;
    private final PooledEngine engine;
    public int currentLevel;

    CircleShape ballShape = new CircleShape();
    CircleShape holeShape = new CircleShape();
    PolygonShape rectangle = new PolygonShape();

    public WorldFactory(PooledEngine engine, World world) {
        this.world = world;
        this.engine = engine;

        ballShape.setRadius(0.25f);
        holeShape.setRadius(0.25f);
        rectangle.setAsBox(0.88f, 0.44f);
    }

    public void createLevel(Level level) {
        createGolfBall(level.startPosition);
        createHole(level.holePosition);
        level.obstacles.forEach(obstacle -> createObstacle(obstacle.x, obstacle.y, obstacle.z));
    }

    private void createGolfBall(Vector2 position) {
        Entity golfball = engine.createEntity();

        SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
        MovementComponent movementComponent = engine.createComponent(MovementComponent.class);
        BallComponent ballComponent = engine.createComponent(BallComponent.class);

        Sprite sprite = spriteComponent.sprite;
        sprite.set(Assets.ball);
        sprite.setSize(50f, 50f);
        sprite.setCenter(25f, 25f);
        sprite.setOrigin(25f, 25f);

        BodyDef golfBallBodyDef = new BodyDef();
        golfBallBodyDef.type = BodyDef.BodyType.DynamicBody;
        golfBallBodyDef.position.set(position.scl(0.01f));

        Body golfBallBody = world.createBody(golfBallBodyDef);
        golfBallBody.setLinearDamping(0.4f);
        golfBallBody.setAngularDamping(0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = ballShape;
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        Fixture fixture = golfBallBody.createFixture(fixtureDef);

        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.fixture = fixture;

        movementComponent.distance = Vector2.Zero;

        //adding components to the entity
        golfball.add(spriteComponent);
        golfball.add(movementComponent);
        golfball.add(ballComponent);
        golfball.add(physicsComponent);

        engine.addEntity(golfball);
    }

    private void createObstacle(float posX, float posY, float radians) {
        Entity obstacle = engine.createEntity();

        SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
        PhysicsComponent physicsComponent = engine.createComponent(PhysicsComponent.class);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(posX * 0.01f, posY * 0.01f);
        bodyDef.angle = MathUtils.degreesToRadians * radians;

        Body body = world.createBody(bodyDef);

        Fixture fixture = body.createFixture(rectangle, 0f);
        physicsComponent.fixture = fixture;

        spriteComponent.sprite = Assets.obstacle;
        spriteComponent.sprite.setScale(0.1f);

        obstacle.add(spriteComponent);
        obstacle.add(physicsComponent);

        engine.addEntity(obstacle);
    }

    private void createHole(Vector2 position) {
        Entity hole = engine.createEntity();

        SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
        PhysicsComponent physicsComponent = engine.createComponent(PhysicsComponent.class);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position.scl(0.01f));

        Body body = world.createBody(bodyDef);

        Fixture fixture = body.createFixture(holeShape, 0f);
        fixture.setSensor(true);

        spriteComponent.sprite = Assets.hole;
        spriteComponent.sprite.setScale(0.1f);


        physicsComponent.fixture = fixture;

        //adding components to the entity
        hole.add(spriteComponent);
        hole.add(physicsComponent);

        engine.addEntity(hole);
    }
}

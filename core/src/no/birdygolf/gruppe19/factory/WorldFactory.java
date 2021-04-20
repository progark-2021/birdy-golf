package no.birdygolf.gruppe19.factory;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
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
import no.birdygolf.gruppe19.components.HoleComponent;
import no.birdygolf.gruppe19.components.MovementComponent;
import no.birdygolf.gruppe19.components.PhysicsComponent;
import no.birdygolf.gruppe19.components.RectangleComponent;
import no.birdygolf.gruppe19.components.SpriteComponent;
import no.birdygolf.gruppe19.levels.Level_rect;

// Creates the ball entity and adds all the comnponents belonging to this entity
public class WorldFactory {

    private final Engine engine;
    private World world;

    public WorldFactory(Engine engine) {
        this.engine = engine;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void createLevel(Level_rect level) {
        createGolfBall(level.startPosition);
        createHole(level.holePosition);
        level.obstacles.forEach(obstacle -> createObstacle(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight()));
    }

    private void createGolfBall(Vector2 position) {
        Entity golfBall = engine.createEntity();

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
        golfBallBodyDef.position.set(position.cpy().scl(0.01f));

        Body body = world.createBody(golfBallBodyDef);
        body.setLinearDamping(0.8f);
        body.setAngularDamping(0.6f);

        CircleShape ballShape = new CircleShape();
        ballShape.setRadius(0.25f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = ballShape;
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.5f;

        Fixture fixture = body.createFixture(fixtureDef);
        ballShape.dispose();
        PhysicsComponent physicsComponent = engine.createComponent(PhysicsComponent.class);
        physicsComponent.fixture = fixture;

        movementComponent.distance = Vector2.Zero;

        //adding components to the entity
        golfBall.add(spriteComponent);
        golfBall.add(movementComponent);
        golfBall.add(ballComponent);
        golfBall.add(physicsComponent);

        engine.addEntity(golfBall);
    }

    private void createHole(Vector2 position) {
        Entity hole = engine.createEntity();

        SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
        PhysicsComponent physicsComponent = engine.createComponent(PhysicsComponent.class);
        HoleComponent holeComponent = engine.createComponent(HoleComponent.class);

        Sprite sprite = spriteComponent.sprite;
        sprite.set(Assets.hole);
        sprite.setSize(50f, 50f);
        sprite.setCenter(25f, 25f);
        sprite.setOrigin(25f, 25f);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position.cpy().scl(0.01f));
        Body body = world.createBody(bodyDef);

        CircleShape holeShape = new CircleShape();
        holeShape.setRadius(0.25f);
        Fixture fixture = body.createFixture(holeShape, 0f);
        fixture.setSensor(true);
        physicsComponent.fixture = fixture;
        holeShape.dispose();

        //adding components to the entity
        hole.add(spriteComponent);
        hole.add(physicsComponent);
        hole.add(holeComponent);

        engine.addEntity(hole);
    }


    private void createObstacle(float posX, float posY, float width, float height) {
        Entity obstacle = engine.createEntity();

        // Store the obstacle's shape for rendering in a component.
        RectangleComponent rectangleComponent = engine.createComponent(RectangleComponent.class);
        rectangleComponent.rectangle = new Rectangle(posX, posY, width, height);
        obstacle.add(rectangleComponent);

        // Calculate and store the obstacle's physics properties for simulation.
        PhysicsComponent physicsComponent = engine.createComponent(PhysicsComponent.class);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(posX * 0.01f + width * 0.01f / 2, posY * 0.01f + height * 0.01f / 2);
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width * 0.005f, height * 0.005f);
        physicsComponent.fixture = body.createFixture(shape, 0f);
        obstacle.add(physicsComponent);
        shape.dispose();

        // Add obstacle to engine
        engine.addEntity(obstacle);
    }
}

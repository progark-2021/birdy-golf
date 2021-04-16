package no.birdygolf.gruppe19.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import java.util.List;

import no.birdygolf.gruppe19.components.MovementComponent;
import no.birdygolf.gruppe19.components.PhysicsComponent;
import no.birdygolf.gruppe19.components.RectangleComponent;
import no.birdygolf.gruppe19.components.SpriteComponent;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;

public class RenderingObsSystem extends IteratingSystem {
    // Entity level
    private static final Family family = Family.all(PhysicsComponent.class, RectangleComponent.class).get();
    //private SpriteBatch batch;
    private ShapeRenderer shape;
    private Camera camera;

    // Component level
    private final ComponentMapper<PhysicsComponent> physicsMapper;
    private final ComponentMapper<RectangleComponent> rectangleMapper;
    private List<Entity> drawQueue;

    public RenderingObsSystem(Camera camera) {
        super(family);

        this.camera = camera;
        //this.batch = batch;
        this.shape = new ShapeRenderer();
        shape.setProjectionMatrix(camera.combined);
        physicsMapper = ComponentMapper.getFor(PhysicsComponent.class);
        rectangleMapper = ComponentMapper.getFor(RectangleComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //can only get entities that satisfies the family restriction

        RectangleComponent rectangleComponent = rectangleMapper.get(entity);


        shape.begin(ShapeType.Filled);
        shape.setColor(Color.TAN); //Change color
        shape.rect(rectangleComponent.rectangle.getX(), rectangleComponent.rectangle.getY(), rectangleComponent.rectangle.getWidth(), rectangleComponent.rectangle.getHeight());
        shape.end();

    }

}
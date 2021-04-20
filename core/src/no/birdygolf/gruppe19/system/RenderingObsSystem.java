package no.birdygolf.gruppe19.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.List;

import no.birdygolf.gruppe19.component.PhysicsComponent;
import no.birdygolf.gruppe19.component.RectangleComponent;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;

public class RenderingObsSystem extends IteratingSystem {
    // Entity level
    private static final Family family = Family.all(PhysicsComponent.class, RectangleComponent.class).get();
    //private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Camera camera;

    // Component level
    private final ComponentMapper<PhysicsComponent> physicsMapper;
    private final ComponentMapper<RectangleComponent> rectangleMapper;
    private List<Entity> drawQueue;

    public RenderingObsSystem(Camera camera) {
        super(family);

        this.camera = camera;
        this.shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        physicsMapper = ComponentMapper.getFor(PhysicsComponent.class);
        rectangleMapper = ComponentMapper.getFor(RectangleComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RectangleComponent rectangleComponent = rectangleMapper.get(entity);


        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.TAN); //Change color
        shapeRenderer.rect(rectangleComponent.rectangle.getX(), rectangleComponent.rectangle.getY(), rectangleComponent.rectangle.getWidth(), rectangleComponent.rectangle.getHeight());
        shapeRenderer.end();
    }

}
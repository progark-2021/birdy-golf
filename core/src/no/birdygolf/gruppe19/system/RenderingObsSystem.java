package no.birdygolf.gruppe19.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import no.birdygolf.gruppe19.component.PhysicsComponent;
import no.birdygolf.gruppe19.component.RectangleComponent;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class RenderingObsSystem extends IteratingSystem {
    private static final Family family = Family.all(PhysicsComponent.class, RectangleComponent.class).get();
    private final ComponentMapper<RectangleComponent> rectangleMapper;
    private final ShapeRenderer shapeRenderer;

    public RenderingObsSystem(Camera camera) {
        super(family);

        this.shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        rectangleMapper = ComponentMapper.getFor(RectangleComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RectangleComponent rectangleComponent = rectangleMapper.get(entity);


        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.TAN);
        shapeRenderer.rect(rectangleComponent.rectangle.getX(), rectangleComponent.rectangle.getY(), rectangleComponent.rectangle.getWidth(), rectangleComponent.rectangle.getHeight());
        shapeRenderer.end();
    }

}
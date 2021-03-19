package no.birdygolf.gruppe19.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

import no.birdygolf.gruppe19.components.TextureComponent;
import no.birdygolf.gruppe19.components.TransformComponent;

public class RenderingSystem extends IteratingSystem {
    // Entity level
    private static final Family family = Family.all(TextureComponent.class, TransformComponent.class).get();
    private SpriteBatch batch;

    // Component level
    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<TransformComponent> transformMapper;
    private List<Entity> drawQueue;

    public RenderingSystem(SpriteBatch batch) {
        super(family);

        this.batch = batch;

        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        batch.begin();
        // calls IteratingSystem and for each entity it calls the processEntity method
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //can only get entities that satisfies the family restriction
        TextureComponent texture = textureMapper.get(entity);
        TransformComponent transform = transformMapper.get(entity);
        texture.getComponent().setPosition(transform.pos.x, transform.pos.y);
        texture.getComponent().draw(batch);
        //batch.draw(texture.getComponent(), transform.pos.x, transform.pos.y);
    }
}
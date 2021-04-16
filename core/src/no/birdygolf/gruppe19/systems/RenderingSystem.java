package no.birdygolf.gruppe19.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import java.util.List;

import no.birdygolf.gruppe19.components.MovementComponent;
import no.birdygolf.gruppe19.components.PhysicsComponent;
import no.birdygolf.gruppe19.components.RectangleComponent;
import no.birdygolf.gruppe19.components.SpriteComponent;

public class RenderingSystem extends IteratingSystem {
    // Entity level
    private static final Family family = Family.all(SpriteComponent.class, PhysicsComponent.class).get();
    private SpriteBatch batch;

    // Component level
    private final ComponentMapper<SpriteComponent> spriteMapper;
    private final ComponentMapper<PhysicsComponent> physicsMapper;

    public RenderingSystem(SpriteBatch batch) {
        super(family);

        this.batch = batch;

        spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
        physicsMapper = ComponentMapper.getFor(PhysicsComponent.class);
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

        SpriteComponent spriteComponent = spriteMapper.get(entity);
        PhysicsComponent physicsComponent = physicsMapper.get(entity);

        spriteComponent.sprite.setPosition(
                physicsComponent.fixture.getBody().getPosition().x * 100,
                physicsComponent.fixture.getBody().getPosition().y * 100
        );
        spriteComponent.sprite.setRotation(MathUtils.radiansToDegrees * physicsComponent.fixture.getBody().getAngle());
        spriteComponent.sprite.draw(batch);
    }
}
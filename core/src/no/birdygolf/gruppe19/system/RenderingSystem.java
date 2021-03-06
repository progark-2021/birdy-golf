package no.birdygolf.gruppe19.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import no.birdygolf.gruppe19.component.PhysicsComponent;
import no.birdygolf.gruppe19.component.SpriteComponent;

public class RenderingSystem extends IteratingSystem {
    private static final Family family = Family.all(SpriteComponent.class, PhysicsComponent.class).get();
    private final ComponentMapper<SpriteComponent> spriteMapper;
    private final ComponentMapper<PhysicsComponent> physicsMapper;
    private final SpriteBatch batch;

    public RenderingSystem(SpriteBatch batch) {
        super(family);

        this.batch = batch;

        spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
        physicsMapper = ComponentMapper.getFor(PhysicsComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        batch.begin();
        // calls IteratingSystem's update, in turn calling the processEntity method for each entity in family.
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SpriteComponent spriteComponent = spriteMapper.get(entity);
        PhysicsComponent physicsComponent = physicsMapper.get(entity);

        // Calculates the sprite position from the physics object's properties.
        spriteComponent.sprite.setPosition(
                physicsComponent.fixture.getBody().getPosition().x * 100 - spriteComponent.sprite.getWidth() / 2,
                physicsComponent.fixture.getBody().getPosition().y * 100 - spriteComponent.sprite.getHeight() / 2
        );
        spriteComponent.sprite.setRotation(MathUtils.radiansToDegrees * physicsComponent.fixture.getBody().getAngle());
        spriteComponent.sprite.draw(batch);
    }
}
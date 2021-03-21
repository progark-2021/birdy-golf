package no.birdygolf.gruppe19.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;


import no.birdygolf.gruppe19.components.MovementComponent;
import no.birdygolf.gruppe19.components.TransformComponent;

public class MovementSystem extends IteratingSystem {
    private Vector2 tmp = new Vector2();

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<MovementComponent> mm;

    public MovementSystem() {
        super(Family.all(TransformComponent.class, MovementComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent pos = tm.get(entity);
        MovementComponent mov = mm.get(entity);;

        tmp.set(mov.accel).scl(deltaTime);
        mov.velocity.add(tmp);

        tmp.set(mov.velocity).scl(deltaTime);
        pos.pos.add(tmp.x, tmp.y, 0.0f);
    }

    public void setPressed(MovementSystem movementSystem, int screenX, int screenY) {
        tm.get(entity).pressedPosition.set(new Vector2(screenX, Gdx.graphics.getHeight() - screenY));
        pm.get(physicsEntity).wasPressed = true;
    }

    public void unPressed(MovementSystem movementSystem) {
    }

    public void dragged(MovementSystem movementSystem, int screenX, int screenY) {
    }
}

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
    private Object ControllerLogic;

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

    /***
    * Draws a circle around the current player and checks iff the touchDown was within the range of that circle
    * If so it sets the flag to true
     */
    public void setPressed(MovementSystem movementSystem, int screenX, int screenY) {
        tm.get(entity).pressedPosition.set(new Vector2(screenX, Gdx.graphics.getHeight() - screenY));
        pm.get(physicsEntity).wasPressed = true;
    }

    /***
     * IF the player has been pressed and is currently beeing dragged its sets the currentposition
     * to the position of the finger at the screen.
     * It then calculates the vector between the pressed position (where the player is) and where the last dragged position is.
     * The distance and angle is so calculated.
     */
    public void dragged(MovementSystem movementSystem, int screenX, int screenY) {
        if(pm.get(physicsEntity).wasPressed) {
            pm.get(physicsEntity).currentPosition.set(screenX, Gdx.graphics.getHeight() - screenY);
            pm.get(physicsEntity).velocityVector.set(pm.get(physicsEntity).currentPosition).sub(pm.get(physicsEntity).pressedPosition);
            pm.get(physicsEntity).distance = pm.get(physicsEntity).velocityVector.len()/22;
            pm.get(physicsEntity).angle = MathUtils.atan2(pm.get(physicsEntity).velocityVector.y,pm.get(physicsEntity).velocityVector.x);
            pm.get(physicsEntity).angle %= 2 * MathUtils.PI;
        }
    }

    /***
     * When the touch is let go of this method will calculate the velocities in the different directions and will
     * set the players bullet to this so it shoots.
     */
    public void unPressed(MovementSystem movementSystem) {
        if(ControllerLogic.charging) {
            float velX = (2.25f * -MathUtils.cos(pm.get(physicsEntity).angle) * pm.get(physicsEntity).distance);
            float velY = (2.25f * -MathUtils.sin(pm.get(physicsEntity).angle) * pm.get(physicsEntity).distance);
            Vector2 velvec = new Vector2(velX, velY);
            ControllerLogic.charging = false;
            pm.get(physicsEntity).wasPressed = false;
            pm.get(physicsEntity).currentPlayer.showBullet();
            pm.get(physicsEntity).bullet.b2Body.setLinearVelocity(velvec);
        }
    }
}

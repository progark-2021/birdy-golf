package no.birdygolf.gruppe19.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
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
        pos.currentPos.add(tmp.x, tmp.y);
    }

    /***
    * Draws a circle around the current player and checks iff the touchDown was within the range of that circle
    * If so it sets the flag to true
     */
    public void setPressed(Entity entity, int screenX, int screenY) {
        tm.get(entity).pressedPosition.set(new Vector2(screenX, Gdx.graphics.getHeight() - screenY));
        tm.get(entity).wasPressed = true;
    }

    /***
     * IF the player has been pressed and is currently beeing dragged its sets the currentposition
     * to the position of the finger at the screen.
     * It then calculates the vector between the pressed position (where the player is) and where the last dragged position is.
     * The distance and angle is so calculated.
     */
    public void dragged(Entity entity, int screenX, int screenY) {
        if(tm.get(entity).wasPressed) {
            tm.get(entity).currentPos.set(screenX, Gdx.graphics.getHeight() - screenY);
            mm.get(entity).velocity.set(tm.get(entity).currentPos).sub(tm.get(entity).currentPos);
            mm.get(entity).distance = mm.get(entity).velocity.len()/22;
            mm.get(entity).angle = MathUtils.atan2(mm.get(entity).velocity.y,mm.get(entity).velocity.x);
            mm.get(entity).angle %= 2 * MathUtils.PI;
        }
    }

    /***
     * When the touch is let go of this method will calculate the velocities in the different directions
     */
    public void unPressed(Entity entity) {
        float velX = (2.25f * -MathUtils.cos(mm.get(entity).angle) * mm.get(entity).distance);
        float velY = (2.25f * -MathUtils.sin(mm.get(entity).angle) * mm.get(entity).distance);
        Vector2 velvec = new Vector2(velX, velY);
        tm.get(entity).wasPressed = false;
    }
}

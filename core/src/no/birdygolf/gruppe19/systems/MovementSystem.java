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
    private int currentScreenX;
    private int currentScreenY;

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
        System.out.println("set pressed");
    }

    /***
     * IF the player has been pressed and is currently beeing dragged its sets the currentposition
     * to the position of the finger at the screen.
     * It then calculates the vector between the pressed position (where the player is) and where the last dragged position is.
     * The distance and angle is so calculated.
     */
    public void dragged(Entity entity, int screenX, int screenY) {
        if(tm.get(entity).wasPressed) {
            System.out.println("Dragged function");
            this.currentScreenX = screenX;
            this.currentScreenY = screenY;
            System.out.println("currentX:" + currentScreenX);
            System.out.println("currentY:" + currentScreenY);

            /*tm.get(entity).currentPos.set(screenX, Gdx.graphics.getHeight() - screenY);
            mm.get(entity).velocity.set(tm.get(entity).currentPos).sub(tm.get(entity).currentPos);
            mm.get(entity).distance = mm.get(entity).velocity.len()/22;
            System.out.println("Distance:" + mm.get(entity).distance);
            mm.get(entity).angle = MathUtils.atan2(mm.get(entity).velocity.y,mm.get(entity).velocity.x);
            System.out.println("angle1:" +mm.get(entity).angle);
            mm.get(entity).angle %= 2 * MathUtils.PI;
            System.out.println("angle2:" +mm.get(entity).angle);
            System.out.println("pos:" +tm.get(entity).currentPos);*/
        }
    }

    /***
     * When the touch is let go of this method will calculate the velocities in the different directions
     */
    public void unPressed(Entity entity) {
        /*float velX = (2.25f * -MathUtils.cos(mm.get(entity).angle) * mm.get(entity).distance);
        float velY = (2.25f * -MathUtils.sin(mm.get(entity).angle) * mm.get(entity).distance);*/
        System.out.println("position:" + tm.get(entity).currentPos.y);
        float diffX = currentScreenX - tm.get(entity).currentPos.x ;
        System.out.println("diffX:" + diffX);
        float diffY = tm.get(entity).currentPos.y - currentScreenY;
        System.out.println("diffY:" + diffY);
        tm.get(entity).currentPos.set(tm.get(entity).currentPos.x + diffX*0.1f, tm.get(entity).currentPos.y - diffY*0.1f);
        float velocityX = mm.get(entity).velocity.x - (tm.get(entity).currentPos.x-currentScreenX)*0.2f ;
        float velocityY = mm.get(entity).velocity.y - (tm.get(entity).currentPos.y-currentScreenY)*0.2f;
        Vector2 velvec = new Vector2(velocityX, velocityY);
        tm.get(entity).wasPressed = false;
        mm.get(entity).velocity.set(velvec).sub(velvec);
        System.out.println("unpressed");
    }
}

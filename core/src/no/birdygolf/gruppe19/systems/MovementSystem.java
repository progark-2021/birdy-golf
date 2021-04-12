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
    public static boolean charging = false;
    public Vector2 startDrag;

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
        MovementComponent mov = mm.get(entity);

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
        //tm.get(entity).pressedPosition.set(new Vector2(screenX, Gdx.graphics.getHeight() - screenY));
        tm.get(entity).wasPressed = true;
        System.out.println("set pressed");
        System.out.println("current xXXXX "+tm.get(entity).currentPos.x);
        System.out.println("current yYYYY "+tm.get(entity).currentPos.y);
        System.out.println("SCREEN X "+ screenX);
        System.out.println("SCREEN Y "+ screenY);
        if (!charging && tm.get(entity).wasPressed){
            startDrag = new Vector2(screenX,screenY);
        }
        System.out.print("setPressed  " + tm.get(entity).wasPressed);
    }


    public void dragged(Entity entity, int screenX, int screenY) {
        System.out.print("dragged  " + tm.get(entity).wasPressed);
        if(tm.get(entity).wasPressed) {
            System.out.println("Dragged function");
            this.currentScreenX = screenX;
            this.currentScreenY = screenY;
            System.out.println("currentX:" + currentScreenX);
            System.out.println("currentY:" + currentScreenY);
            if(tm.get(entity).wasPressed){
                charging = true;
                tm.get(entity).distance = ((float)Math.abs( Math.sqrt(Math.pow((tm.get(entity).currentPos.x - screenX),2f) + (Math.pow((tm.get(entity).currentPos.y - screenY),2)))));
                System.out.println("distance"+tm.get(entity).distance);

            }

        }
    }


    public void unPressed(Entity entity){
        System.out.print("unpressed  " + tm.get(entity).wasPressed);
        if (charging) {
            System.out.println("position:" + tm.get(entity).currentPos.y);
            float diffX = startDrag.x - currentScreenX;
            System.out.println("diffX:" + diffX);
            float diffY = currentScreenY - startDrag.y;
            System.out.println("diffY:" + diffY);
            float velocityX = Math.abs(diffX) * 0.1f;
            float velocityY = Math.abs(diffY) * 0.1f;
            mm.get(entity).accel.x += mm.get(entity).accel.x * 0.1f;
            mm.get(entity).accel.y += mm.get(entity).accel.y * 0.1f;
            velocityX += mm.get(entity).accel.x;
            velocityY += mm.get(entity).accel.y;
            tm.get(entity).currentPos.x += velocityX * 0.1f;
            tm.get(entity).currentPos.y += velocityY * 0.1f;
            System.out.println("velX:" +velocityX);
            System.out.println("velY:" +velocityY);
            Vector2 velvec = new Vector2(velocityX, velocityY);
            tm.get(entity).wasPressed = false;
            mm.get(entity).velocity.set(velvec);
            System.out.println("velvec:" + mm.get(entity).velocity);
        }
        tm.get(entity).wasPressed = false;
        charging = false;
        System.out.print("unpressed  " + tm.get(entity).wasPressed);

    }
}

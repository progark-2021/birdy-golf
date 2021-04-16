package no.birdygolf.gruppe19.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;

import no.birdygolf.gruppe19.components.BallComponent;
import no.birdygolf.gruppe19.components.MovementComponent;
import no.birdygolf.gruppe19.components.PhysicsComponent;

public class MovementSystem extends EntitySystem {
    private Vector2 tmp = new Vector2();
    private int currentScreenX;
    private int currentScreenY;
    public static boolean charging = false;
    public Vector2 startDrag;
    private Entity golfball;
    boolean wasPressed = false;

    public void refreshGolfball(){
        golfball = getEngine().getEntitiesFor(Family.all(BallComponent.class).get()).get(0);
    }

    public void setPressed(int screenX, int screenY) {
        wasPressed = true;
        startDrag = new Vector2(screenX,screenY);
    }

    public void dragged(int screenX, int screenY) {
        if(wasPressed) {
            this.currentScreenX = screenX;
            this.currentScreenY = screenY;
            if(wasPressed){
                charging = true;
            }
        }
    }

    public void unPressed(){
        MovementComponent movementComp = golfball.getComponent(MovementComponent.class);
        if (charging) {
            movementComp.distance.x = startDrag.x - currentScreenX;
            movementComp.distance.y = currentScreenY - startDrag.y;
            Vector2 force = movementComp.distance.scl(0.02f);

            PhysicsComponent physicsComponent = golfball.getComponent(PhysicsComponent.class);
            physicsComponent.fixture.getBody().setLinearVelocity(force);

            wasPressed = false;
        }
        wasPressed = false;
        charging = false;
    }
}

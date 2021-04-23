package no.birdygolf.gruppe19.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;

import no.birdygolf.gruppe19.GameManager;
import no.birdygolf.gruppe19.component.BallComponent;
import no.birdygolf.gruppe19.component.MovementComponent;
import no.birdygolf.gruppe19.component.PhysicsComponent;

public class MovementSystem extends EntitySystem {
    public static boolean charging = false;
    public Vector2 startDrag;
    private boolean wasPressed = false;
    private int currentScreenX;
    private int currentScreenY;
    private Entity golfBall;

    public void fetchGolfBall() {
        golfBall = getEngine().getEntitiesFor(Family.all(BallComponent.class).get()).get(0);
    }

    public void setPressed(int screenX, int screenY) {
        wasPressed = true;
        startDrag = new Vector2(screenX, screenY);
    }

    public void dragged(int screenX, int screenY) {
        if (wasPressed) {
            this.currentScreenX = screenX;
            this.currentScreenY = screenY;
            charging = true;
        }
    }

    public void unPressed() {
        MovementComponent movementComp = golfBall.getComponent(MovementComponent.class);
        if (charging) {
            GameManager.INSTANCE.increaseHits();

            movementComp.distance.x = startDrag.x - currentScreenX;
            movementComp.distance.y = currentScreenY - startDrag.y;
            Vector2 force = movementComp.distance.scl(0.00025f);

            PhysicsComponent physicsComponent = golfBall.getComponent(PhysicsComponent.class);
            physicsComponent.fixture.getBody().applyLinearImpulse(force, physicsComponent.fixture.getBody().getWorldCenter(), true);
        }
        wasPressed = false;
        charging = false;
    }
}

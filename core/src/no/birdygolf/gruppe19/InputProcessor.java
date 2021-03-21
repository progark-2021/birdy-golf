package no.birdygolf.gruppe19;

import no.birdygolf.gruppe19.systems.MovementSystem;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.InputAdapter;

public class InputProcessor extends InputAdapter {

    private MovementSystem movementSystem;
    private Entity movementEntity;

    /***
     *
     * @param movementSystem: The system that controls the physics of the gameworld
     */
    public InputProcessor(MovementSystem movementSystem) {
        this.movementSystem = movementSystem;
        movementEntity = movementSystem.getEntities().get(0);
    }

    /***
     * Methods that defines the event of touches on screen.
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        movementSystem.setPressed(movementEntity, screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        movementSystem.unPressed(movementEntity);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        movementSystem.dragged(movementEntity, screenX, screenY);
        return false;
    }
}

package no.birdygolf.gruppe19.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import no.birdygolf.gruppe19.components.BallComponent;
import no.birdygolf.gruppe19.components.BoundsComponent;
import no.birdygolf.gruppe19.components.MovementComponent;
import no.birdygolf.gruppe19.components.StateComponent;
import no.birdygolf.gruppe19.components.TransformComponent;
import no.birdygolf.gruppe19.factory.WorldFactory;

//Detecting the
public class BallSystem extends IteratingSystem {

    private static final Family family = Family.all(BallComponent.class, TransformComponent.class, MovementComponent.class, StateComponent.class).get();

    private ComponentMapper<BallComponent> ballMapper;
    private ComponentMapper<TransformComponent> transformMapper;
    private ComponentMapper<MovementComponent> movementMapper;
    private ComponentMapper<StateComponent> stateMapper;

    private WorldFactory world;
    private boolean reachedGoal = false; // sets to true when the ball reaches goal

    public BallSystem(WorldFactory world) {
        super(family);

        this.world = world;

        ballMapper = ComponentMapper.getFor(BallComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        movementMapper = ComponentMapper.getFor(MovementComponent.class);
        stateMapper = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }

    // Detects when ball hits obstacles
    public void hitObstacle(Entity entity) {

    }

    // Detects when ball reach the hole
    public void intoHole(Entity entity) {
        // sets currentLevel in WorldFactory in "createGolfBall" to the next level

        StateComponent stateComponent = stateMapper.get(entity);

        //kan sjekke om vært innom fire leveler og dersom det er tilfellet får man videre til neste Screen som er SummaryScreen
        if (reachedGoal) {
            stateComponent.setLevel(world.currentLevel += 1); // Going to the next level when reaching goal
            //world.currentLevel += 1;
        }

    }
}

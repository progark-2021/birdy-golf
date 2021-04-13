package no.birdygolf.gruppe19.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import no.birdygolf.gruppe19.components.BallComponent;
import no.birdygolf.gruppe19.components.MovementComponent;
import no.birdygolf.gruppe19.factory.WorldFactory;

//Detecting the
public class BallSystem extends IteratingSystem {

    private static final Family family = Family.all(BallComponent.class, MovementComponent.class).get();

    private final ComponentMapper<BallComponent> ballMapper;
    private final ComponentMapper<MovementComponent> movementMapper;

    private final WorldFactory world;
    private final boolean reachedGoal = false; // sets to true when the ball reaches goal

    public BallSystem(WorldFactory world) {
        super(family);

        this.world = world;

        ballMapper = ComponentMapper.getFor(BallComponent.class);
        movementMapper = ComponentMapper.getFor(MovementComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }

    // Detects when ball reach the hole
    public void intoHole(Entity entity) {
        // sets currentLevel in WorldFactory in "createGolfBall" to the next level


        //kan sjekke om vært innom fire leveler og dersom det er tilfellet får man videre til neste Screen som er SummaryScreen
        if (reachedGoal) {
            //world.currentLevel += 1;
        }

    }
}

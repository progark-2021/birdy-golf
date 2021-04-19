package no.birdygolf.gruppe19.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;

import no.birdygolf.gruppe19.components.BallComponent;
import no.birdygolf.gruppe19.components.HoleComponent;
import no.birdygolf.gruppe19.components.PhysicsComponent;

public class HoleSystem extends EntitySystem {
    private Fixture ball;
    private Fixture hole;
    public boolean inHole;

    public void fetchEntities(){
        ball = getEngine().getEntitiesFor(Family.all(BallComponent.class).get()).get(0).getComponent(PhysicsComponent.class).fixture;
        hole = getEngine().getEntitiesFor(Family.all(HoleComponent.class).get()).get(0).getComponent(PhysicsComponent.class).fixture;
    }

    public void clearEntities() {
        ball = null;
        hole = null;
    }

    @Override
    public void update(float deltaTime) {
        if (ball == null || hole == null) {
            return;
        }
        Vector2 ballPos = ball.getBody().getWorldCenter();
        Vector2 holePos = hole.getBody().getWorldCenter();
        float holeRadius = hole.getShape().getRadius();

        if (Vector2.dst(ballPos.x, ballPos.y, holePos.x, holePos.y) < holeRadius) {
            inHole = true;
        }
    }
}

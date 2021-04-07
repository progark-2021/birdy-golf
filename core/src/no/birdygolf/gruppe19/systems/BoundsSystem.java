package no.birdygolf.gruppe19.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import no.birdygolf.gruppe19.components.BoundsComponent;
import no.birdygolf.gruppe19.components.TransformComponent;

//Updating the position of the bounds 
public class BoundsSystem extends IteratingSystem {

    private static final Family family = Family.all(BoundsComponent.class, TransformComponent.class).get();

    private ComponentMapper<BoundsComponent> boundsMapper;
    private ComponentMapper<TransformComponent> transformMapper;

    public BoundsSystem() {
        super(family);

        boundsMapper = ComponentMapper.getFor(BoundsComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent boundsComponent = boundsMapper.get(entity);
        TransformComponent transformComponent = transformMapper.get(entity);

        //updating the position of the ball
        boundsComponent.bounds.setPosition(transformComponent.currentPos.x, transformComponent.currentPos.y);
    }
}

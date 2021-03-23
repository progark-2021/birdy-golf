package no.birdygolf.gruppe19.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import no.birdygolf.gruppe19.components.StateComponent;

public class StateSystem extends IteratingSystem {
	private ComponentMapper<StateComponent> stateComponent;

	public StateSystem() {
		super(Family.all(StateComponent.class).get());

		stateComponent = ComponentMapper.getFor(StateComponent.class);
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		//updating current level ??
	}
}
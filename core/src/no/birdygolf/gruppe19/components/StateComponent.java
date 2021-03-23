package no.birdygolf.gruppe19.components;

import com.badlogic.ashley.core.Component;

public class StateComponent implements Component {

	private int level = 0;

	public int getLevel() {
		return level;
	}

	public void setLevel(int newLevel) {
		level = newLevel;
	}
}
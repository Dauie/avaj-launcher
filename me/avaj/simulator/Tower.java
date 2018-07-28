package me.avaj.simulator;

import me.avaj.simulator.vehicles.Flyable;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Tower {
	private List<Flyable> observers;

	Tower() {
		this.observers = new ArrayList<>();
	}
	public void register(Flyable aircraft) {
		observers.add(aircraft);
	}

	public void unregister(Flyable aircraft) {
		Iterator<Flyable> i = this.observers.iterator();
		Iterator<Flyable> rm;
		while (i.hasNext()) {
			Flyable craft = i.next();
			if (craft == aircraft) {
				i.remove();
			}
		}
	}

	protected void conditionsChanged() {
		for (int i = 0; i < this.observers.size(); i++) {
			this.observers.get(i).updateConditions();
		}
	}
}
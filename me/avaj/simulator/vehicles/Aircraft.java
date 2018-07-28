package me.avaj.simulator.vehicles;
import me.avaj.weather.Coordinates;

public class				Aircraft {

	/**Members**/
	protected long			id;
	protected String		name;
	protected Coordinates	coordinates;
	private static long		idCounter;

	/**Constructors**/
	protected 				Aircraft(String name, Coordinates coordinates) {
		this.coordinates = coordinates;
		this.name = name;
		this.id = nextId();
	}

	/**Methods**/
	private long 			nextId() {
		return (++idCounter);
	}
}
package me.avaj.simulator.vehicles;

import me.avaj.weather.Coordinates;

public class AircraftFactory {

	public static Flyable newAircraft(String type, String name, int lat, int lon, int height) {
		height = height > 100 ? 100 : height;
		switch (type) {
			case "Baloon":
				return (new Baloon(name, new Coordinates(lat, lon, height)));
			case "Helicopter":
				return (new Helicopter(name, new Coordinates(lat, lon, height)));
			case "JetPlane":
				return (new JetPlane(name, new Coordinates(lat, lon, height)));
			default: {
				System.out.println("Invalid aircraft type: " + type);
				return (null);
			}
		}
	}
}
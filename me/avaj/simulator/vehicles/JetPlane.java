package me.avaj.simulator.vehicles;
import me.avaj.simulator.WeatherTower;
import me.avaj.weather.Coordinates;
import me.avaj.simulator.Logger;

public class JetPlane extends Aircraft implements Flyable {

	/**Members**/
	private WeatherTower weatherTower;

	/**Constructors**/
	JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	/**Methods**/
	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		Logger.writeToLog("Tower says: JETPLANE#" + this.name + "(" + this.id + ")" + " Jet plane registered." );
	}

	/**
	 * ◦ SUN - Latitude increases with 10, Height increases with 2
	 * ◦ RAIN - Latitude increases with 5
	 * ◦ FOG - Latitude increases with 1
	 * ◦ SNOW - Height decreases with 7
	 **/
	@Override
	public void updateConditions() {
		int lat, height;
		String cweather = this.weatherTower.getWeather(this.coordinates);
		String logstr = "JETPLANE#" + this.name + "(" + this.id + ") : ";
		switch (cweather) {
			case "SUN": {
				lat = this.coordinates.getLatitude();
				height = this.coordinates.getHeight();
				height = height + 10 > 100 ? 100 : height + 10;
				this.coordinates.setLatitude(lat + 10);
				this.coordinates.setHeight(height);
				Logger.writeToLog(logstr + "Bright out. Increasing altitude!");
				break;
			}
			case "RAIN": {
				lat = this.coordinates.getLatitude();
				this.coordinates.setLatitude(lat + 5);
				Logger.writeToLog(logstr + "Started raining here. Heading east!");
				break;
			}
			case "FOG": {
				lat = this.coordinates.getLatitude();
				this.coordinates.setLatitude(lat + 2);
				Logger.writeToLog(logstr + "I see fog below. Gonna see how far it goes!");
				break;
			}
			case "SNOW": {
				height = this.coordinates.getHeight();
				height = height - 7 < 0 ? 0 : height - 7;
				this.coordinates.setLatitude(height);
				Logger.writeToLog(logstr + "It started snowing. Headed back to base.");
				break;
			}
			default:
				break;
		}
		if (this.coordinates.getHeight() == 0) {
			Logger.writeToLog(logstr + "landing.");
			Logger.writeToLog("Tower says:" + logstr + "unregistered from weather tower.");
			this.weatherTower.unregister(this);
		}
	}
}
package me.avaj.simulator.vehicles;
import me.avaj.simulator.WeatherTower;
import me.avaj.weather.Coordinates;
import me.avaj.simulator.Logger;

public class Helicopter extends Aircraft implements Flyable {

	/**Members**/
	private WeatherTower weatherTower;

	/**Constructors**/
	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	/**Methods**/
	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		Logger.writeToLog("Tower says: HELICOPTER#" + this.name + "(" + this.id + ")" + "  Helicopter registered." );
	}

	/**
	 * ◦ SUN - Longitude increases with 10, Height increases with 2
	 * ◦ RAIN - Longitude increases with 5
	 * ◦ FOG - Longitude increases with 1
	 * ◦ SNOW - Height decreases with 12
	 **/
	@Override
	public void updateConditions() {
		int lon, height;
		String cweather = this.weatherTower.getWeather(this.coordinates);
		String logstr = "HELICOPTER#" + this.name + "(" + this.id + ") : ";
		switch (cweather) {
			case "SUN": {
				lon = this.coordinates.getLongitude();
				height = this.coordinates.getHeight();
				height = height + 2 > 100 ? 100 : height + 2;
				this.coordinates.setLongitude(lon + 10);
				this.coordinates.setHeight(height);
				Logger.writeToLog(logstr + "Getting sunny. Going forward and up.");
				break;
			}
			case "RAIN": {
				lon = this.coordinates.getLongitude();
				this.coordinates.setLatitude(lon + 5);
				Logger.writeToLog(logstr + "Its pouring. Getting out of here.");
				break;
			}
			case "FOG": {
				lon = this.coordinates.getLatitude();
				this.coordinates.setLatitude(lon + 1);
				Logger.writeToLog(logstr + "Its foggy. Taking it easy");
				break;
			}
			case "SNOW": {
				height = this.coordinates.getHeight();
				height = height - 12 < 0 ? 0 : height - 12;
				this.coordinates.setLatitude(height);
				Logger.writeToLog(logstr + "Its snowing. Taking this bird down.");
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
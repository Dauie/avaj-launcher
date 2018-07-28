package me.avaj.simulator.vehicles;
import me.avaj.simulator.Logger;
import me.avaj.simulator.WeatherTower;
import me.avaj.weather.Coordinates;

public class Baloon extends Aircraft implements Flyable {

	/**Members**/
	private WeatherTower weatherTower;

	/**Constructors**/
	Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	/**Methods**/
	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		Logger.writeToLog("Tower says: BALOON#" + this.name + "(" + this.id + ")" + " Baloon registered.");
	}

	/**
	 * ◦ SUN - Longitude increases with 2, Height increases with 4
	 * ◦ RAIN - Height decreases with 5
	 * ◦ FOG - Height decreases with 3
	 * ◦ SNOW - Height decreases with 15
	 **/
	@Override
	public void updateConditions() {
		String cweather = this.weatherTower.getWeather(this.coordinates);
		int height, lon;
		String logstr = "BALOON#" + this.name + "(" + this.id + ") : ";
		switch (cweather) {
			case "SUN": {
				lon = this.coordinates.getLongitude();
				height = this.coordinates.getHeight();
				height = height + 4 > 100 ? 100 : height + 4;
				this.coordinates.setLongitude(lon + 2);
				this.coordinates.setHeight(height);
				Logger.writeToLog(logstr + "Sun is out. Baloon to the moon!");
				break;
			}
			case "RAIN": {
				height = this.coordinates.getHeight();
				height = height - 5 < 0 ? 0 : height - 5;
				this.coordinates.setHeight(height);
				Logger.writeToLog(logstr + "Rain coming down hard. Baloon swoon!");
				break;
			}
			case "FOG": {
				height = this.coordinates.getHeight();
				height = height - 3 < 0 ? 0 : height - 3;
				this.coordinates.setHeight(height);
				Logger.writeToLog(logstr + "Oooh O.o its foggy. Going to see the ground clouds.");
				break;
			}
			case "SNOW": {
				height = this.coordinates.getHeight();
				height = height - 15 < 0 ? 0 : height - 15;
				this.coordinates.setHeight(height);
				Logger.writeToLog(logstr + "Shit its snowing. A baloon cannot abide.");
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
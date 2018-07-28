package me.avaj.simulator;

import me.avaj.weather.Coordinates;
import me.avaj.weather.WeatherProvider;

public class WeatherTower extends Tower {

	WeatherTower() {
		super();
	}
	public String getWeather(Coordinates cords) {
		return (WeatherProvider.getCurrentWeather(cords));
	}

	public void changeWeather() {
		this.conditionsChanged();
	}
}
package me.avaj.weather;

import me.avaj.weather.Noise;

public class WeatherProvider {
	private static WeatherProvider weatherProvider = new WeatherProvider();
	private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

	private	WeatherProvider() {

	}

	public static String getCurrentWeather(Coordinates cords) {
		double x = cords.getLongitude();
		double y = cords.getLatitude();
		double z = cords.getHeight();
		System.out.println(x + " " + y + " " + z);
		long res = Noise.noise(x, y, z);
		System.out.println(res);
		System.out.println(weather[(int)res % 4]);
		return (weather[(int)res % 4]);
	}

	public static WeatherProvider getProvider() {
		return(weatherProvider);
	}
}

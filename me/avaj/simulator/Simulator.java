package me.avaj.simulator;

import me.avaj.simulator.vehicles.AircraftFactory;
import me.avaj.simulator.vehicles.Flyable;
import me.avaj.weather.WeatherProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Simulator {

	private static List<Flyable> flyList = new ArrayList<>();

	public static void main(String[] av) throws InterruptedException {
		try {
			WeatherTower wt;
			int simAmount;
			BufferedReader read;
			String line;
			String[] desc;

			Logger.createLogfile();
			wt = new WeatherTower();
			read = new BufferedReader(new FileReader(av[0]));
			line = read.readLine();
			if (line != null) {
				simAmount = Integer.parseInt(line);
				if (simAmount < 0) {
					System.out.println("Invalid simulation count");
					System.exit(-1);
				}
				while ((line = read.readLine()) != null) {
					desc = line.split(" ");
					if (desc.length != 5) {
						System.out.println("Line not formatted correctly " + line);
						System.exit(-1);
					}
					int lon, lat, height;

					lon = Integer.parseInt(desc[2]);
					lat = Integer.parseInt(desc[3]);
					height = Integer.parseInt(desc[4]);
					if (lat < 0 && lon < 0 && height < 0) {
						System.out.println("Coordinates must be positive");
						System.exit(-1);
					}
					Flyable craft = AircraftFactory.newAircraft(desc[0], desc[1], lon, lat, height);
					if (craft != null)
						flyList.add(craft);
					else
						System.exit(-1);
				}
				for (Flyable flyable : flyList) {
					flyable.registerTower(wt);
				}
				for (int i = 0; i < simAmount; i++) {
					wt.changeWeather();
				}
			}
			Logger.closeLogFile();
		}
		catch (java.io.FileNotFoundException e) {
			System.out.println("No file " + av[0]);
		}
		catch (java.io.IOException e) {
			System.out.println("Error reading file " + av[0]);
		}
		catch (java.lang.NumberFormatException e) {
			System.out.println("Invalid file format");
		}
	}
}


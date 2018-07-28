package me.avaj.simulator.vehicles;
import me.avaj.simulator.WeatherTower;

public interface Flyable {
    public void registerTower(WeatherTower weatherTower);
    public void updateConditions();
}
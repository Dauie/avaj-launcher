package me.avaj.weather;

public class Coordinates {

	/**Members**/
	private int longitude;
	private int latitude;
	private int height;

	/**Constructors**/
	public Coordinates(int longitude, int latitude, int height) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
	}

	/**Methods**/
	public int getLongitude() {
		return (this.longitude);
	}

	public int getLatitude() {
		return (this.latitude);
	}

	public int getHeight() {
		return (this.height);
	}

	public void setLongitude(int lon) {
		this.longitude = lon;
	}

	public void setLatitude(int lat) {
		this.latitude = lat;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
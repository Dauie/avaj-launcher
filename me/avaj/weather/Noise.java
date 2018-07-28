package me.avaj.weather;

import java.util.Random;
import java.util.Date;

public class Noise {
	static public long noise(double x, double y, double z) {
		int seed = (int)(x + y + z);
		Random rand = new Random(seed + new Date().getTime());
		int ret = rand.nextInt();
		if (ret < 0)
			ret *= -1;
		return (ret);
	}
}

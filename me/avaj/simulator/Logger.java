package me.avaj.simulator;


import java.io.BufferedWriter;
import java.io.FileWriter;

public class Logger {

	private static BufferedWriter	logfile;

	public static void createLogfile() {
		try {
			logfile = new BufferedWriter(new FileWriter("simLog.txt", false));
		} catch (java.io.IOException e) {
			System.out.println("Error creating file");
			System.exit(-1);
		}
	}
	public static void closeLogFile() {
		try {
			logfile.close();
		} catch (java.io.IOException e) {
			System.out.println("Error closing file after write");
			System.exit(-1);
		}
	}

	public static void writeToLog(String logstr) {
		try {
			logfile.write(logstr + "\n");
		} catch (java.io.IOException e) {
			System.out.println("Error writing to log file");
			System.exit(-1);
		}
	}
}

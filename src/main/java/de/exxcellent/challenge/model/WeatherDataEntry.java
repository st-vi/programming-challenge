package de.exxcellent.challenge.model;


/**
 * A record representing weather data for a specific day.
 * This record holds the day of the weather entry, the maximum temperature,
 * and the minimum temperature for that day.
 */
public record WeatherDataEntry(int day, int maxTemp, int minTemp) {
}

package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.model.WeatherDataEntry;

import java.util.List;

/**
 * A class responsible for analyzing weather data and providing various (currently only one) statistics.
 */
public class WeatherAnalyzer {

    /**
     * Returns the day with the minimum temperature spread (the difference between max and min temperatures).
     * If there are multiple days with the same minimum temperature spread, the first one encountered in the list will be returned.
     *
     * @param weatherDataEntries A list of {@link WeatherDataEntry} objects containing weather data for each day.
     * @return The day ID with the minimum temperature spread.
     * @throws IllegalArgumentException if the list is empty.
     */
    public int getDayWithMinTemperatureSpread(List<WeatherDataEntry> weatherDataEntries){
        if (weatherDataEntries.isEmpty()) {
            throw new IllegalArgumentException("At least one WeatherDataEntry must be present for analyzing it.");
        }
        int minTempSpread = weatherDataEntries.get(0).maxTemp() - weatherDataEntries.get(0).minTemp();
        int dayIdOfMinTempSpread = weatherDataEntries.get(0).day();
        for(int i=1;i< weatherDataEntries.size();i++){
            WeatherDataEntry weatherDataEntry = weatherDataEntries.get(i);
            int currentTempSpread = weatherDataEntry.maxTemp() - weatherDataEntry.minTemp();
            if(currentTempSpread < minTempSpread) {
                minTempSpread = currentTempSpread;
                dayIdOfMinTempSpread = weatherDataEntry.day();
            }
        }
        return dayIdOfMinTempSpread;
    }
}

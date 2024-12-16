package de.exxcellent.challenge.analyzer;

import de.exxcellent.challenge.model.WeatherDataEntry;

import java.util.List;

public class WeatherAnalyzer {
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

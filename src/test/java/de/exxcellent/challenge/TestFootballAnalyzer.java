package de.exxcellent.challenge;

import de.exxcellent.challenge.analyzer.WeatherAnalyzer;
import de.exxcellent.challenge.io.WeatherReader;
import de.exxcellent.challenge.model.FootballDataEntry;
import de.exxcellent.challenge.model.WeatherDataEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFootballAnalyzer {
    @Test
    void testSuccessfulGetTeamNameWithMinGoalDifference() throws IOException {
        List<Map<String,String>> mockData = new LinkedList<>();
        Map<String,String> entry = new HashMap<>();
        entry.put("Team", "a");
        entry.put("Goals", "1");
        entry.put("Goals Allowed", "2");
        mockData.add(entry);
        entry = new HashMap<>();
        entry.put("Team", "b");
        entry.put("Goals", "1");
        entry.put("Goals Allowed", "3");
        mockData.add(entry);

        MockDataSourceConnector<FootballDataEntry> mockDataSourceConnector = new MockDataSourceConnector<>(mockData);
        F weatherReader = new WeatherReader(mockDataSourceConnector);

        List<WeatherDataEntry> weatherDataEntries = weatherReader.readData();
        WeatherAnalyzer weatherAnalyzer = new WeatherAnalyzer();
        int resultDayId = weatherAnalyzer.getDayWithMinTemperatureSpread(weatherDataEntries);
        assertEquals(2, resultDayId);
    }
}

package de.exxcellent.challenge;

import de.exxcellent.challenge.analyzer.WeatherAnalyzer;
import de.exxcellent.challenge.io.WeatherReader;
import de.exxcellent.challenge.model.WeatherDataEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestWeatherAnalyzer {
    @Test
    void testSuccessGetDayWithMinTemperatureSpread() throws IOException {
        List<Map<String,String>> mockData = new LinkedList<>();
        Map<String,String> entry = new HashMap<>();
        entry.put("Day", "1");
        entry.put("MxT", "88");
        entry.put("MnT", "59");
        mockData.add(entry);
        entry = new HashMap<>();
        entry.put("Day", "2");
        entry.put("MxT", "79");
        entry.put("MnT", "63");
        mockData.add(entry);

        MockDataSourceConnector<WeatherDataEntry> mockDataSourceConnector = new MockDataSourceConnector<>(mockData);
        WeatherReader weatherReader = new WeatherReader(mockDataSourceConnector);

        List<WeatherDataEntry> weatherDataEntries = weatherReader.readData();
        WeatherAnalyzer weatherAnalyzer = new WeatherAnalyzer();
        int resultDayId = weatherAnalyzer.getDayWithMinTemperatureSpread(weatherDataEntries);
        assertEquals(1, resultDayId);
    }

    @Test
    void testGetDayWithMinTemperatureSpreadWithMultipleMinimums() throws IOException {
        List<Map<String,String>> mockData = new LinkedList<>();
        Map<String,String> entry = new HashMap<>();
        entry.put("Day", "1");
        entry.put("MxT", "1");
        entry.put("MnT", "2");
        mockData.add(entry);
        entry = new HashMap<>();
        entry.put("Day", "2");
        entry.put("MxT", "2");
        entry.put("MnT", "3");
        mockData.add(entry);

        MockDataSourceConnector<WeatherDataEntry> mockDataSourceConnector = new MockDataSourceConnector<>(mockData);
        WeatherReader weatherReader = new WeatherReader(mockDataSourceConnector);

        List<WeatherDataEntry> weatherDataEntries = weatherReader.readData();
        WeatherAnalyzer weatherAnalyzer = new WeatherAnalyzer();
        int resultDayId = weatherAnalyzer.getDayWithMinTemperatureSpread(weatherDataEntries);
        assertEquals(1, resultDayId);
    }



}

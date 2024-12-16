package de.exxcellent.challenge;

import de.exxcellent.challenge.io.CSVSourceConnector;
import de.exxcellent.challenge.io.WeatherReader;
import de.exxcellent.challenge.model.WeatherDataEntry;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWeatherReader {

    void testSuccessfulWeatherReading() throws IOException {
        List<Map<String,String>> mockData = new LinkedList<>();
        Map<String,String> entry = new HashMap<>();
        entry.put("Day", "1");
        entry.put("MxT", "88");
        entry.put("MnT", "59");
        mockData.add(entry);

        MockDataSourceConnector<WeatherDataEntry> mockDataSourceConnector = new MockDataSourceConnector<>(mockData);
        WeatherReader weatherReader = new WeatherReader(mockDataSourceConnector);

        WeatherDataEntry expectedEntry = new WeatherDataEntry(1, 88,59);
        List<WeatherDataEntry> result = weatherReader.readData();
        assertEquals(1, result.size());
        assertEquals(expectedEntry, weatherReader.readData().get(0));
    }
}

package de.exxcellent.challenge;

import de.exxcellent.challenge.analyzer.FootballAnalyzer;
import de.exxcellent.challenge.io.FootballReader;
import de.exxcellent.challenge.model.FootballDataEntry;
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
        FootballReader footballReader = new FootballReader(mockDataSourceConnector);

        List<FootballDataEntry> footballDataEntries = footballReader.readData();
        FootballAnalyzer footballAnalyzer = new FootballAnalyzer();
        String teamName = footballAnalyzer.getTeamNameWithMinGoalDifference(footballDataEntries);
        assertEquals("a", teamName);
    }

    @Test
    void testSuccessfulGetTeamNameWithMinGoalDifferenceWithNegativeDifferences() throws IOException {
        List<Map<String,String>> mockData = new LinkedList<>();
        Map<String,String> entry = new HashMap<>();
        entry.put("Team", "a");
        entry.put("Goals", "1");
        entry.put("Goals Allowed", "2");
        mockData.add(entry);
        entry = new HashMap<>();
        entry.put("Team", "b");
        entry.put("Goals", "3");
        entry.put("Goals Allowed", "1");
        mockData.add(entry);

        MockDataSourceConnector<FootballDataEntry> mockDataSourceConnector = new MockDataSourceConnector<>(mockData);
        FootballReader footballReader = new FootballReader(mockDataSourceConnector);

        List<FootballDataEntry> footballDataEntries = footballReader.readData();
        FootballAnalyzer footballAnalyzer = new FootballAnalyzer();
        String teamName = footballAnalyzer.getTeamNameWithMinGoalDifference(footballDataEntries);
        assertEquals("a", teamName);
    }
}

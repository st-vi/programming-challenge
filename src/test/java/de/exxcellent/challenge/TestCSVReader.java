package de.exxcellent.challenge;

import de.exxcellent.challenge.io.CSVSourceConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class TestCSVReader {

    private Path validPath;
    private Path invalidPath;
    private Path nonCsvPath;
    private Path malformedCSVPath;

    @BeforeEach
    void setUp() {
        validPath = Paths.get("validFile.csv");
        malformedCSVPath = Paths.get("malformed.csv");
        invalidPath = Paths.get("invalidFile.txt");
        nonCsvPath = Paths.get("nonCsvFile.csv");
    }

    @Test
    void testFileDoesNotExist() {
        CSVSourceConnector<String> reader = new CSVSourceConnector<>(invalidPath);
        assertThrows(FileNotFoundException.class, () -> reader.readData(data -> "test"));
    }

    @Test
    void testInvalidFileType() {
        CSVSourceConnector<String> reader = new CSVSourceConnector<>(nonCsvPath);
        assertThrows(IllegalArgumentException.class, () -> reader.readData(data -> "test"));
    }

    @Test
    void testSuccessfulReading() throws IOException {
        CSVSourceConnector<Map<String, String>> reader = new CSVSourceConnector<>(validPath);
        Function<Map<String, String>, Map<String, String>> dataMapping = map -> map;
        List<Map<String, String>> result = reader.readData(dataMapping);

        assertEquals(1, result.size());
        assertEquals("a", result.get(0).get("a"));
        assertEquals("b", result.get(0).get("b"));
    }

    @Test
    void testMalformedCSV() throws IOException {
        CSVSourceConnector<Map<String, String>> reader = new CSVSourceConnector<>(malformedCSVPath);
        Function<Map<String, String>, Map<String, String>> dataMapping = map -> map;
        assertThrows(IllegalArgumentException.class, () -> reader.readData(dataMapping));
    }
}

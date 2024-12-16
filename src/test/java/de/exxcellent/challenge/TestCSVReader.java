package de.exxcellent.challenge;

import de.exxcellent.challenge.io.CSVSourceConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class TestCSVReader {

    private Path validPath;
    private Path invalidPath;
    private Path nonCsvPath;

    @BeforeEach
    void setUp() {
        validPath = Paths.get("validFile.csv");
        invalidPath = Paths.get("invalidFile.txt");
        nonCsvPath = Paths.get("nonCsvFile.csv"); // Assume this is a non-existent file for some cases
    }

    @Test
    void testFileDoesNotExist() {
        CSVSourceConnector<String> reader = new CSVSourceConnector<>(invalidPath);
        assertThrows(FileNotFoundException.class, () -> reader.readData(data -> "test"));
    }
}

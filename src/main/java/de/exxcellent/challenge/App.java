package de.exxcellent.challenge;

import de.exxcellent.challenge.analyzer.FootballAnalyzer;
import de.exxcellent.challenge.analyzer.WeatherAnalyzer;
import de.exxcellent.challenge.io.CSVSourceConnector;
import de.exxcellent.challenge.io.FootballReader;
import de.exxcellent.challenge.io.IDataReader;
import de.exxcellent.challenge.io.WeatherReader;
import de.exxcellent.challenge.model.FootballDataEntry;
import de.exxcellent.challenge.model.WeatherDataEntry;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        try {
            IDataReader<WeatherDataEntry> weatherReader = new WeatherReader(
                    new CSVSourceConnector<WeatherDataEntry>(
                            Paths.get("./src/main/resources/de/exxcellent/challenge/weather.csv")
                    )
            );

            List<WeatherDataEntry> weatherDataEntryList = weatherReader.readData();
            WeatherAnalyzer weatherAnalyzer = new WeatherAnalyzer();
            int dayWithSmallestTempSpread = weatherAnalyzer.getDayWithMinTemperatureSpread(weatherDataEntryList);     // Your day analysis function call …
            System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

            IDataReader<FootballDataEntry> footballReader = new FootballReader(
                    new CSVSourceConnector<FootballDataEntry>(
                            Paths.get("./src/main/resources/de/exxcellent/challenge/football.csv")
                    )
            );
            List<FootballDataEntry> footballDataEntryList = footballReader.readData();
            FootballAnalyzer footballAnalyzer = new FootballAnalyzer();
            String teamWithSmallestGoalSpread = footballAnalyzer.getTeamNameWithMinGoalDifference(footballDataEntryList); // Your goal analysis function call …
            System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
        }catch (FileNotFoundException e) {
            System.out.println("Could not find the specified files, please check the paths");
            e.printStackTrace();
        }

    }
}

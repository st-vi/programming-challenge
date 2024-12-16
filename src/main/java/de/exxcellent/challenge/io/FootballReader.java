package de.exxcellent.challenge.io;

import de.exxcellent.challenge.model.FootballDataEntry;
import de.exxcellent.challenge.model.WeatherDataEntry;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FootballReader  implements IDataReader<FootballDataEntry> {
    public final IDataSourceConnector<FootballDataEntry> dataSourceConnector;

    public FootballReader(IDataSourceConnector<FootballDataEntry> dataSourceConnector) {
        this.dataSourceConnector = dataSourceConnector;
    }

    @Override
    public List<FootballDataEntry> readData() throws FileNotFoundException {
        Function<Map<String, String>, FootballDataEntry> mapToFootballDataEntry = rawData -> {
            String teamName = rawData.get("Team");
            int goals = Integer.parseInt(rawData.get("Goals"));
            int goalsAllowed = Integer.parseInt(rawData.get("Goals Allowed"));
            return new FootballDataEntry(teamName, goals, goalsAllowed);
        };
        try {
            return dataSourceConnector.readData(mapToFootballDataEntry);
        }catch (NumberFormatException n){
            throw new IllegalArgumentException("The provided data does not represent a FootballDataEntry");
        }
    }
}

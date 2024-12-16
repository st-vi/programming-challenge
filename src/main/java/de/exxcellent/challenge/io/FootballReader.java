package de.exxcellent.challenge.io;

import de.exxcellent.challenge.model.FootballDataEntry;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * A class responsible for reading football match data from a data source and converting it
 * into a list of {@link FootballDataEntry} objects.
 * This class implements the {@link IDataReader} interface to standardize how data is read from a source.
 */
public class FootballReader  implements IDataReader<FootballDataEntry> {
    /**
     * The data source connector responsible for fetching raw football data from a specific source.
     * It provides a method to read and map the raw data into a list of {@link FootballDataEntry}.
     */
    public final IDataSourceConnector<FootballDataEntry> dataSourceConnector;

    /**
     * Constructs a new instance of {@link FootballReader} with the specified data source connector.
     *
     * @param dataSourceConnector The data source connector used to fetch raw football data.
     */
    public FootballReader(IDataSourceConnector<FootballDataEntry> dataSourceConnector) {
        this.dataSourceConnector = dataSourceConnector;
    }

    /**
     * Reads raw football match data from the data source and converts it into a list of {@link FootballDataEntry} objects.
     * This method uses a {@link Function} to map each raw data entry into a {@link FootballDataEntry} object.
     *
     * @return A list of {@link FootballDataEntry} objects representing football match data.
     * @throws FileNotFoundException If the data source is not found.
     * @throws IllegalArgumentException If the raw data cannot be parsed into a valid {@link FootballDataEntry}.
     */
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

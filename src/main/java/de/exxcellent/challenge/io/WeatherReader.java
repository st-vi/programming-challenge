package de.exxcellent.challenge.io;

import de.exxcellent.challenge.model.WeatherDataEntry;

import java.util.List;

/**
 * A class for reading weather data. This class implements the {@link IDataReader} interface
 * to handle weather data specifically and uses a provided {@link IDataSourceConnector}
 * to retrieve the data.
 */
public class WeatherReader implements IDataReader<WeatherDataEntry>{
    /**
     * The connector responsible for accessing the weather data source.
     */
    public final IDataSourceConnector<WeatherDataEntry> dataSourceConnector;

    /**
     * Constructs a {@code WeatherReader} with the specified data source connector.
     *
     * @param dataSourceConnector The {@link IDataSourceConnector} to use for retrieving weather data.
     *                            Must not be {@code null}.
     */
    public WeatherReader(IDataSourceConnector<WeatherDataEntry> dataSourceConnector){
        this.dataSourceConnector = dataSourceConnector;

    }

    /**
     * Reads and retrieves all {@link WeatherDataEntry} from the data source.
     *
     * @return A list of {@link WeatherDataEntry} objects containing the weather data.
     */
    @Override
    public List<WeatherDataEntry> readData() {
        return null;
    }
}

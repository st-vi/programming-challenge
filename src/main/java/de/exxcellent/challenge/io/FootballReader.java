package de.exxcellent.challenge.io;

import de.exxcellent.challenge.model.FootballDataEntry;
import de.exxcellent.challenge.model.WeatherDataEntry;

import java.io.FileNotFoundException;
import java.util.List;

public class FootballReader  implements IDataReader<FootballDataEntry> {
    public final IDataSourceConnector<FootballDataEntry> dataSourceConnector;

    public FootballReader(IDataSourceConnector<FootballDataEntry> dataSourceConnector) {
        this.dataSourceConnector = dataSourceConnector;
    }

    @Override
    public List<FootballDataEntry> readData() throws FileNotFoundException {
        return null;
    }
}

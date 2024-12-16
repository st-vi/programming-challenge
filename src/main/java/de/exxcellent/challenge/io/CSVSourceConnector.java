package de.exxcellent.challenge.io;

import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;


/**
 * A connector class for reading data from a CSV file. This class implements the
 * {@link IDataSourceConnector} interface to allow generic data reading and mapping
 * from a CSV source.
 *
 * @param <T> The type of the data to be returned after reading and mapping.
 */
public class CSVSourceConnector<T> implements IDataSourceConnector<T> {

    /**
     * The path to the CSV file that this connector will read.
     */
    public final Path CSV_PATH;

    /**
     * Constructs a new {@code CSVSourceConnector} with the given CSV file path.
     *
     * @param csvPath The {@link Path} to the CSV file. This path must point to an
     *                existing file that the connector can read.
     */
    public CSVSourceConnector(Path csvPath){
        this.CSV_PATH = csvPath;
    }

    /**
     * Reads data from the CSV file and maps it to the specified type using the
     * provided mapping function.
     *
     * @param dataMapping A {@link Function} that maps a {@link Map} of column names
     *                    and values (representing a single row of the CSV file) to
     *                    an instance of type {@code T}.
     * @return The mapped data of type {@code T}.
     */
    @Override
    public T readData(Function<Map<String, String>, T> dataMapping) {
        return null;
    }
}

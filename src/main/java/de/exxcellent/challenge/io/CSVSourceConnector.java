package de.exxcellent.challenge.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

    public final String SEPARATOR = ";";

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
    public List<T> readData(Function<Map<String, String>, T> dataMapping) throws FileNotFoundException {
        List<T> resultList = new LinkedList<>();

        // check if the CSV file exists and is readable
        if (!Files.exists(CSV_PATH)) {
            throw new FileNotFoundException("CSV file does not exist: " + CSV_PATH.toString());
        }

        // check if the file is a CSV
        if (!CSV_PATH.toString().endsWith(".csv")) {
            throw new IllegalArgumentException("File is not a valid CSV: " + CSV_PATH);
        }

        try (BufferedReader reader = Files.newBufferedReader(CSV_PATH)) {
            String headerLine = reader.readLine();

            if (headerLine == null) {
                throw new IOException("CSV file is empty: " + CSV_PATH.toString());
            }

            // split the header line to get column names
            String[] headers = headerLine.split(SEPARATOR);

            // process each line in csv file
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(SEPARATOR);
                Map<String, String> rowData = new HashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    if (i < values.length) {
                        rowData.put(headers[i], values[i]);
                    } else {
                        rowData.put(headers[i], "");
                    }
                }

                T mappedData = dataMapping.apply(rowData);
                resultList.add(mappedData);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the CSV file: " + CSV_PATH.toString(), e);
        }

        return resultList;
    }
}

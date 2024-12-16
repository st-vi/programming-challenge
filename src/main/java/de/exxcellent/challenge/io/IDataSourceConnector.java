package de.exxcellent.challenge.io;

import java.util.Map;
import java.util.function.Function;

/**
 * Represents a generic data source connector that reads data from an input source
 * and maps it into a specified format.
 *
 * @param <T> the type of object that the data will be mapped to
 */
public interface IDataSourceConnector<T> {
    /**
     * Reads data from a data source and maps it into an object of type {@code T}.
     * The mapping is performed using the provided {@link Function}.
     *
     * @param dataMapping a function that maps a {@link Map} of key-value pairs
     *                    (representing raw data) into an object of type {@code T}
     * @return an object of type {@code T} created from the mapped data
     */
    public T readData(Function<Map<String,String>, T> dataMapping);
}

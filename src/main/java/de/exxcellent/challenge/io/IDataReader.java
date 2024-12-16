package de.exxcellent.challenge.io;

import java.util.List;

/**
 * A generic interface for reading data from a source.
 *
 * @param <T> The type of the data to be read and returned.
 */
public interface IDataReader<T> {

    /**
     * Reads data from a data source and returns it.
     *
     * @return A list of type {@code T} read from the source.
     */
    public List<T> readData();
}

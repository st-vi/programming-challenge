package de.exxcellent.challenge.io;

/**
 * A generic interface for reading data from a source.
 *
 * @param <T> The type of the data to be read and returned.
 */
public interface IDataReader<T> {

    /**
     * Reads data from a data source and returns it.
     *
     * @return The data of type {@code T} read from the source. The returned object
     *         may vary depending on the implementation of the data reader.
     */
    public T readData();
}

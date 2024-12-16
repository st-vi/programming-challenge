package de.exxcellent.challenge;

import de.exxcellent.challenge.io.IDataSourceConnector;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MockDataSourceConnector<T> implements IDataSourceConnector<T> {

    private final List<Map<String,String>> mockData;
    public MockDataSourceConnector(List<Map<String,String>> mockData){
        this.mockData = mockData;
    }
    @Override
    public List<T> readData(Function<Map<String, String>, T> dataMapping) throws FileNotFoundException {
        List<T> result = new LinkedList<>();
        for (var element : mockData){
            result.add(dataMapping.apply(element));
        }
        return result;
    }
}

package org.syphr.puzzle.antikythera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Ring
{
    public static Ring empty(int columns)
    {
        List<Integer> columnList = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            columnList.add(null);
        }
        
        return new Ring(columnList);
    }
    
    private final LinkedList<Integer> columns;
    
    public Ring(Integer... columns)
    {
        this(Arrays.asList(columns));
    }
    
    public Ring(List<Integer> columns)
    {
        this.columns = new LinkedList<>(columns);
    }
    
    public void rotate()
    {
        columns.offerFirst(columns.removeLast());
    }
    
    public Integer value(int column)
    {
        return columns.get(column);
    }
    
    public int columnCount()
    {
        return columns.size();
    }
}

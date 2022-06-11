package org.syphr.puzzle.antikythera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@EqualsAndHashCode
public class Wheel
{
    private final List<Ring> rings;
    
    public Wheel(Ring... rings)
    {
        this(Arrays.asList(rings));
    }

    public Wheel(List<Ring> rings)
    {
        int lastColumnCount = -1;
        for (Ring ring : rings) {
            log.trace("Adding ring {}", ring);
            
            int ringColumnCount = ring.columnCount();
            if (lastColumnCount != -1 && lastColumnCount != ringColumnCount) {
                throw new IllegalArgumentException("All rings must have the same number of columns");
            }
            lastColumnCount = ringColumnCount;
        }

        this.rings = new ArrayList<>(rings);
    }

    public void rotate()
    {
        for (Ring ring : rings) {
            ring.rotate();
        }
    }

    public Integer value(int ring, int column)
    {
        return rings.get(ring).value(column);
    }

    public int ringCount()
    {
        return rings.size();
    }
    
    public int columnCount()
    {
        return rings.get(0).columnCount();
    }
}

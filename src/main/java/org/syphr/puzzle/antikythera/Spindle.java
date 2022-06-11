package org.syphr.puzzle.antikythera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString(onlyExplicitlyIncluded = true)
public class Spindle
{
    @ToString.Include
    private final List<Wheel> wheels;
    private final int ringCount;
    private final int columnCount;
    private final int maxRotations;
    
    private int rotations = 0;
    
    public Spindle(Wheel... wheels)
    {
        this(Arrays.asList(wheels));
    }
    
    public Spindle(List<Wheel> wheels)
    {
        int lastRingCount = -1;
        for (Wheel wheel : wheels) {
            log.trace("Adding wheel {}", wheel);
            
            int wheelRingCount = wheel.ringCount();
            if (lastRingCount != -1 && lastRingCount != wheelRingCount) {
                throw new IllegalArgumentException("All wheels must have the same number of rings");
            }
            lastRingCount = wheelRingCount;
        }

        this.wheels = new ArrayList<>(wheels);
        ringCount = this.wheels.get(0).ringCount();
        columnCount = this.wheels.get(0).columnCount();
        maxRotations = (int)Math.pow(columnCount, this.wheels.size()) - 1;
    }
    
    public void rotate()
    {
        if (rotations >= maxRotations) {
            throw new IllegalStateException("Rotations have exceeded the maximum");
        }
        
        rotations++;
        
        for (int wheel = 0; wheel < wheels.size(); wheel++) {
            log.debug("Rotating wheel {}", wheel);
            wheels.get(wheel).rotate();
        
            if (rotations % (int)Math.pow(columnCount, wheel + 1) != 0) {
                break;
            }
        }
    }

    public int value(int column)
    {
        int value = 0;

        for (int ring = 0; ring < ringCount; ring++) {
            value += ringValue(ring, column);
        }
        
        return value;
    }

    private int ringValue(int ring, int column)
    {
        for (Wheel wheel : wheels) {
            Integer wheelValue = wheel.value(ring, column);
            if (wheelValue != null) {
                return wheelValue;
            }
        }

        throw new IllegalStateException("No value found for ring " + ring + ", column " + column);
    }
    
    public int columnCount()
    {
        return columnCount;
    }
    
    public int rotations()
    {
        return rotations;
    }
    
    public int maxRotations()
    {
        return maxRotations;
    }
}

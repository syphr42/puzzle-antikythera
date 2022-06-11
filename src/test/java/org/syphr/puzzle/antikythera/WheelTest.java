package org.syphr.puzzle.antikythera;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WheelTest
{
    @Test
    void rotate()
    {
        Ring ring0 = new Ring(1, 2, 3);
        Ring ring1 = new Ring(4, 5, 6);
        Wheel wheel = new Wheel(ring0, ring1);
        
        wheel.rotate();
        
        assertEquals(new Wheel(new Ring(3, 1, 2), new Ring(6, 4, 5)), wheel);
    }
    
    @Test
    void value()
    {
        Ring ring0 = new Ring(1, 2);
        Ring ring1 = new Ring(3, 4);
        Wheel wheel = new Wheel(ring0, ring1);
        
        assertAll(() -> assertEquals(1, wheel.value(0, 0)),
                  () -> assertEquals(2, wheel.value(0, 1)),
                  () -> assertEquals(3, wheel.value(1, 0)),
                  () -> assertEquals(4, wheel.value(1, 1)));
    }
}

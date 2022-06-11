package org.syphr.puzzle.antikythera;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RingTest
{
    @Test
    void rotate()
    {
        Ring ring = new Ring(1, 2, 3);
        ring.rotate();
        
        assertEquals(new Ring(3, 1, 2), ring);
    }
}

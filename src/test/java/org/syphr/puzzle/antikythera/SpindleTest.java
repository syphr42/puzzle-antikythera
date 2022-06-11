package org.syphr.puzzle.antikythera;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpindleTest
{
    @ParameterizedTest
    @CsvSource({ "0, 12, 15, 18",
                 "1, 14, 14, 17",
                 "2, 13, 16, 16",
                 "3, 14, 14, 17",
                 "4, 16, 13, 16",
                 "5, 15, 15, 15",
                 "6, 13, 16, 16",
                 "7, 15, 15, 15",
                 "8, 14, 17, 14" })
    void value_rotated_twoWheels(int rotations, int column1, int column2, int column3)
    {
        Spindle spindle = spindle_full();
        for (int i = 0; i < rotations; i++) {
            spindle.rotate();
        }

        assertAll(() -> assertEquals(column1, spindle.value(0)),
                  () -> assertEquals(column2, spindle.value(1)),
                  () -> assertEquals(column3, spindle.value(2)));
    }

    @Test
    void maxRotations()
    {
        Spindle spindle = spindle_full();
        for (int i = 0; i < 26; i++) {
            spindle.rotate();
        }

        assertThrows(IllegalStateException.class, () -> spindle.rotate());
    }

    Spindle spindle_full()
    {
        Ring w0ring0 = new Ring(1, 2, 3);
        Ring w0ring1 = Ring.empty(3);
        Ring w0ring2 = Ring.empty(3);
        Wheel wheel0 = new Wheel(w0ring0, w0ring1, w0ring2);

        Ring w1ring0 = Ring.empty(3);
        Ring w1ring1 = new Ring(4, 5, 6);
        Ring w1ring2 = Ring.empty(3);
        Wheel wheel1 = new Wheel(w1ring0, w1ring1, w1ring2);
        
        Ring w2ring0 = Ring.empty(3);
        Ring w2ring1 = Ring.empty(3);
        Ring w2ring2 = new Ring(7, 8, 9);
        Wheel wheel2 = new Wheel(w2ring0, w2ring1, w2ring2);

        return new Spindle(wheel0, wheel1, wheel2);
    }
}

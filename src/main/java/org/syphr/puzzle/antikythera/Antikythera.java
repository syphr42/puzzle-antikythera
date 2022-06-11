package org.syphr.puzzle.antikythera;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Antikythera
{
    private final Spindle spindle;
    private final int target;

    public void play()
    {
        System.out.println("Starting Antikythera game...");

        while (!targetFound()) {
            spindle.rotate();
            System.out.println("Tested " + spindle.rotations() + " rotations (" + percentRotationsCompleted() + "%)");
        }

        System.out.println("Target found!");
        System.out.println(spindle);
    }

    private boolean targetFound()
    {
        for (int column = 0; column < spindle.columnCount(); column++) {
            if (spindle.value(column) != target) {
                return false;
            }
        }

        return true;
    }
    
    private int percentRotationsCompleted()
    {
        return (int)Math.floor((double)spindle.rotations() / (double)spindle.maxRotations() * 100.0);
    }

    public static void main(String[] args)
    {
        Spindle spindle = new Spindle(buildWheel0(), buildWheel1(), buildWheel2(), buildWheel3(), buildWheel4());

        Antikythera antikythera = new Antikythera(spindle, 42);
        antikythera.play();
    }

    private static Wheel buildWheel0()
    {
        log.trace("Building wheel 0");
        
        Ring ring0 = new Ring(3, null, 6, null, 10, null, 7, null, 15, null, 8, null);

        return new Wheel(ring0, Ring.empty(12), Ring.empty(12), Ring.empty(12));
    }

    private static Wheel buildWheel1()
    {
        log.trace("Building wheel 1");
        
        Ring ring0 = new Ring(7, 3, null, 6, null, 11, 11, 6, 11, null, 6, 17);
        Ring ring1 = new Ring(4, null, 7, 15, null, null, 14, null, 9, null, 12, null);

        return new Wheel(ring0, ring1, Ring.empty(12), Ring.empty(12));
    }

    private static Wheel buildWheel2()
    {
        log.trace("Building wheel 2");
        
        Ring ring0 = new Ring(17, 4, 5, null, 7, 8, 9, 13, 9, 7, 13, 21);
        Ring ring1 = new Ring(11, 26, 14, 1, 12, null, 21, 6, 15, 4, 9, 18);
        Ring ring2 = new Ring(22, null, 16, null, 9, null, 5, null, 10, null, 8, null);

        return new Wheel(ring0, ring1, ring2, Ring.empty(12));
    }

    private static Wheel buildWheel3()
    {
        log.trace("Building wheel 3");
        
        Ring ring0 = new Ring(7, null, 9, null, 7, 14, 11, null, 8, null, 16, 2);
        Ring ring1 = new Ring(9, 20, 12, 3, 6, null, 14, 12, 3, 8, 9, null);
        Ring ring2 = new Ring(3, 26, 6, null, 2, 13, 9, null, 17, 19, 3, 12);
        Ring ring3 = new Ring(1, null, 9, null, 12, null, 6, null, 10, null, 10, null);

        return new Wheel(ring0, ring1, ring2, ring3);
    }

    private static Wheel buildWheel4()
    {
        log.trace("Building wheel 4");
        
        Ring ring0 = new Ring(11, 14, 14, 11, 14, 11, 14, 11, 11, 14, 11, 14);
        Ring ring1 = new Ring(9, 10, 11, 12, 13, 14, 15, 4, 5, 6, 7, 8);
        Ring ring2 = new Ring(3, 14, 14, 21, 21, 9, 9, 4, 4, 6, 6, 3);
        Ring ring3 = new Ring(5, 10, 7, 16, 8, 7, 8, 8, 3, 4, 12, 2);

        return new Wheel(ring0, ring1, ring2, ring3);
    }
}

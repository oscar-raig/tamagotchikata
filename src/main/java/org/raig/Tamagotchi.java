package org.raig;

import java.util.Observable;
import java.util.Observer;



public class Tamagotchi implements Observer {

    public static final   int  MAX_HAPPINESS = 50;
    private static final  int DEFAULT_INIT_HAPPINESS = 0;



    private int happiness;
    private final ClockTamagotchi clock;

    public Tamagotchi(int happiness, ClockTamagotchi clock) {
        this.happiness = happiness;
        this.clock = clock;
    }
    public Tamagotchi(ClockTamagotchi clock) {
        this.happiness = DEFAULT_INIT_HAPPINESS;
        this.clock = clock;
    }

    public int getHappiness() {
        return happiness;
    }

    public void feed() {
        increaseHappiness();
    }

    private void increaseHappiness() {
        if (happiness < MAX_HAPPINESS) {
            happiness++;
        }
    }

    private void decreaseHappiness() {
        if (happiness > 0) {
            happiness--;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        decreaseHappiness();
    }
}

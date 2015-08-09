package org.raig;

import java.util.Observable;
import java.util.Observer;



public class Tamagotchi implements Observer {

    public static final   int  MAX_HAPPINESS = 50;
    private static final  int DEFAULT_INIT_HAPPINESS = 0;



    private int happiness;

    public Tamagotchi(int happiness) {
        this.happiness = happiness;
    }
    public Tamagotchi() {
        this.happiness = DEFAULT_INIT_HAPPINESS;
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

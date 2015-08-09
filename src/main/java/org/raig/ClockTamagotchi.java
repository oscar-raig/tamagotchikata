package org.raig;

import org.apache.log4j.Logger;

import java.util.Observable;

import static java.lang.Thread.sleep;

public class ClockTamagotchi extends Observable implements Runnable {

    public  static final  long DEFAULT_MILLISECONDS_PERIOD = 5000;
    private long millisecondsPeriodAlert = DEFAULT_MILLISECONDS_PERIOD;
    private static final  long BASE_FREQUENCY = 100;
    private static final  long INITIAL_COUNTER_VALUE = 1;
    private long maxCount = 0;

    private  final  Logger logger = Logger.getLogger(ClockTamagotchi.class);

    private boolean stopClock = false;
    private long count = INITIAL_COUNTER_VALUE;


    public ClockTamagotchi(long millisecondsPeriodAlert) {
        this.millisecondsPeriodAlert = millisecondsPeriodAlert;
        maxCount = millisecondsPeriodAlert / BASE_FREQUENCY;
    }

    @Override
    public void run() {
        while (true) {
            if (shouldResumeClock()) {
                return;
            }
            sleepBaseFreqluency();
            if (shouldPublish()) {
                logger.debug("notify Observers");
                setChanged();
                notifyObservers();
                resetPublishCondition();

            }
            count++;
        }
    }

    public void die() {
        stopClock = true;
    }


    private void sleepBaseFreqluency() {
        try {
            sleep(BASE_FREQUENCY);
        } catch (InterruptedException e) {
            logger.error("Error Sleeping");
        }
    }

    private boolean shouldResumeClock() {
        if (stopClock) {
            System.out.println("Dying");
        }
        return stopClock;
    }

    private boolean shouldPublish() {
        return (count >=  maxCount);
    }

    private void resetPublishCondition() {
        count = INITIAL_COUNTER_VALUE;
    }
}

package org.raig;

import org.apache.log4j.Logger;

import java.util.Observable;

import static java.lang.Thread.sleep;

public class ClockTamagotchi extends Observable implements Runnable {

    final static Logger logger = Logger.getLogger(ClockTamagotchi.class);

    private boolean stopClock = false;
    private int Count = 0;
    @Override
    public void run() {
        while(true) {
            if ( shouldResumeClock() ) {
                return;
            }
            logger.debug("Sleeping Clock");
            SleepOneSecond();
            Count++;
            if (shouldPublish() ) {
                logger.debug("notify Observers");
                setChanged();
                resetPublishCondition();
                notifyObservers();
            }
        }
    }

    public void die() {
        stopClock = true;
    }


    private void SleepOneSecond() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            logger.error("Error Sleeping");
            return;
        }
    }

    public boolean shouldResumeClock() {
        if ( stopClock ) {
            System.out.println("Dying");
        }
        return stopClock;
    }

    public boolean shouldPublish() {
        return ( Count ==  5 );
    }

    public void resetPublishCondition() {
        Count = 0;
    }
}

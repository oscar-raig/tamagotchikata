package org.raig.tamagotchi.domain.model;

import org.apache.log4j.Logger;

import java.util.Observable;

import static java.lang.Thread.sleep;

public class Alarm extends Observable implements Runnable {

    private  final  Logger logger = Logger.getLogger(Alarm.class);

    public  static final  long DEFAULT_MILLISECONDS_PERIOD = 500;
    private static final  long BASE_FREQUENCY = 100;
    private static final  long INITIAL_COUNTER_VALUE = 1;


    private boolean clockRunning = true;
    private long count = INITIAL_COUNTER_VALUE;
    private long maxCount = DEFAULT_MILLISECONDS_PERIOD / BASE_FREQUENCY;

    private DateProvider dateProvider;


    public Alarm(long millisecondsPeriodAlert, DateProvider dateProvider) {
        maxCount = millisecondsPeriodAlert / BASE_FREQUENCY;
        this.dateProvider = dateProvider;
    }

    @Override
    public void run() {
        while (clockIsRunning()) {
            sleepBaseFrequency();
            if (shouldPublish()) {
                publish();
                resetPublishCondition();
            }
            incrementClock();
        }
    }

    private void publish() {
        logger.debug("Notify Observers");
        setChanged();
        notifyObservers(dateProvider.now());
    }

    public void die() {
        clockRunning = false;
        logger.info("Clock is Stopped");
    }


    private void sleepBaseFrequency() {
        try {
            sleep(BASE_FREQUENCY);
        } catch (InterruptedException e) {
            logger.error("Error Sleeping");
        }
    }

    private boolean clockIsRunning() {
        return clockRunning;
    }

    private boolean shouldPublish() {
        return (count >=  maxCount);
    }

    private void resetPublishCondition() {
        count = INITIAL_COUNTER_VALUE;
    }

    private void incrementClock() {
        count++;
    }
}

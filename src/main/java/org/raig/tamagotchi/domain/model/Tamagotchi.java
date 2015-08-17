package org.raig.tamagotchi.domain.model;

import org.apache.log4j.Logger;
import org.raig.tamagotchi.domain.model.Feeling.FeelingRepository;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;



public class Tamagotchi implements Observer {

    private static final Logger  LOGGER  = Logger.getLogger(Tamagotchi.class);

    public static final long MILLISECONDS  = 1000;
    public static final int PERIOD_FOR_UPDATING_FEELINGS_IN_SECONDS = 8;

    private FeelingRepository feelingRepository;

    private Command feed;
    private Command timePasses;
    private Command play;

    Date lastUpdateDate;

    public Tamagotchi(FeelingRepository feelingRepository, Command feed, Command play,
                      Command timePasses) {
        this.feed = feed;
        this.play = play;
        this.feelingRepository = feelingRepository;
        this.timePasses = timePasses;
        this.lastUpdateDate = new Date();
        LOGGER.debug("First update: " + lastUpdateDate.toString());

    }

    public int getTiredness() {
        return getFeelingValue("tiredness");
    }
    public int getHappiness() {
        return getFeelingValue("happiness");
    }
    public int getFullness() {
        return getFeelingValue("fullness");
    }

    public int getHungriness() {
        return getFeelingValue("hungriness");
    }

    private int getFeelingValue(String nameOfFeeling) {
        return feelingRepository.getFeeling(nameOfFeeling).getValue();
    }
    public void feed() {
        feed.execute();
    }

    public void play() {
        play.execute();
    }

    @Override
    public void update(Observable o, Object arg) {
        Date dateOfEvent = (Date) arg;

        if (shouldUpdateFeelings(dateOfEvent)) {
            timePasses.execute();
            updateDateFromLastUpdateFeelings(dateOfEvent);
            LOGGER.debug("Updating - last update - to" + lastUpdateDate);
        }
    }

    private boolean shouldUpdateFeelings(Date dateNow) {
        long milliseconds = (dateNow.getTime() - lastUpdateDate.getTime()); // Make a function
        return (milliseconds > (PERIOD_FOR_UPDATING_FEELINGS_IN_SECONDS * MILLISECONDS));
    }

    private void updateDateFromLastUpdateFeelings(Date dateOfEvent) {
        lastUpdateDate = dateOfEvent;
    }
}

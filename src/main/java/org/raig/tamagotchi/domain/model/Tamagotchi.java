package org.raig.tamagotchi.domain.model;

import org.apache.log4j.Logger;
import org.raig.tamagotchi.domain.model.Feeling.FeelingRepository;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;



public class Tamagotchi implements Observer {

    private static final Logger  LOGGER  = Logger.getLogger(Tamagotchi.class);
    public static final   int  MAX_HAPPINESS = 50;
    public static final  int DEFAULT_INIT_FEELING = 0;
    private FeelingRepository feelingRepository;
    private static final long MILLISECONDS  = 1000;

    private Command feed;
    private Command timePasses;
    private Command play;


    private final DateProvider dateTamagotchi;
    java.util.Date lastUpdateDate;

    public Tamagotchi(FeelingRepository feelingRepository, Command feed, Command play,
                      Command timePasses, DateProvider dateTamagotchi) {
        this.feed = feed;
        this.play = play;
        this.feelingRepository = feelingRepository;
        this.dateTamagotchi = dateTamagotchi;
        this.timePasses = timePasses;
        this.lastUpdateDate = dateTamagotchi.now();
        LOGGER.info("First update: " + lastUpdateDate.toString());

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
        Date now = dateTamagotchi.now();
        long seconds = (now.getTime() - lastUpdateDate.getTime()) / MILLISECONDS;
        LOGGER.info("Last updated was: " + seconds + "seconds");
        timePasses.execute();
    }
}

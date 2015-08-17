package org.raig.tamagotchi.application;


import org.apache.log4j.Logger;

import org.raig.tamagotchi.domain.model.Alarm;
import org.raig.tamagotchi.domain.model.DateProvider;
import org.raig.tamagotchi.domain.model.Feeling.Feeling;
import org.raig.tamagotchi.domain.model.Tamagotchi;

import static java.lang.Thread.sleep;

public final class Main {
    private static   Logger logger = Logger.getLogger(Main.class);

    private static final long SLEEP_TIME = 15000;

    private Main() {
    }

    public static void main(String[] argv) {

        logger.debug("Indian Pale Ale is the best beer you have tasted!");

        DateProvider dateProvider = new DateProvider();
        Alarm clock = new Alarm(Alarm.DEFAULT_MILLISECONDS_PERIOD, dateProvider);
        Tamagotchi tamagotchi = TamagotchiFactory.createTamagotchi(
                Feeling.DEFAULT_INIT_FEELING, dateProvider);
        clock.addObserver(tamagotchi);

        new Thread(clock).start();
        logger.debug("Init Fullness :" + tamagotchi.getFullness());

        tamagotchi.feed();
        logger.debug("After feeding the tamagotchi Fullness has been Increased " + tamagotchi.getFullness());


        logger.debug("Now we are going to play to increase happiness " + tamagotchi.getHappiness());

        tamagotchi.play();
        tamagotchi.play();

        logger.debug("We played twice so we are happy " + tamagotchi.getHappiness());

        logger.debug("Now we are going to sleep");
        sleeping();
        logger.debug("After Sleeping more " + SLEEP_TIME / Tamagotchi.MILLISECONDS
          + " seconds happiness has decreased, and the new value of Happiness is : " + tamagotchi.getHappiness());

        clock.die();
    }

    private static void sleeping() {
        try {
            sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            logger.error("Error Sleeping!!");
            return;
        }
    }

}

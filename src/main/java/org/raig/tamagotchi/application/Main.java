package org.raig.tamagotchi.application;


import org.apache.log4j.Logger;

import org.raig.tamagotchi.domain.model.Alarm;
import org.raig.tamagotchi.domain.model.Tamagotchi;

import static java.lang.Thread.sleep;

public final class Main {
    private static   Logger logger = Logger.getLogger(Main.class);
    private static final long SLEEP_TIME = Alarm.DEFAULT_MILLISECONDS_PERIOD * 3;

    private Main() {
    }

    public static void main(String[] argv) {
        logger.debug("Indian Pale Ale is the best beer you have tasted!");


        Alarm clock = new Alarm(Alarm.DEFAULT_MILLISECONDS_PERIOD);

        Tamagotchi tamagotchi = TamagotchiFactory.createTamagotchi(Tamagotchi.DEFAULT_INIT_FEELING);
        clock.addObserver(tamagotchi);

        new Thread(clock).start();
        logger.debug("Init happiness :" + tamagotchi.getHappiness());

        tamagotchi.feed();
        logger.debug("After feeding the tamagotchi happiness has been Increased " + tamagotchi.getHappiness());

        sleeping();
        logger.debug("After Sleeping more " + SLEEP_TIME
          + " seconds happiness has decreased : " + tamagotchi.getHappiness());

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

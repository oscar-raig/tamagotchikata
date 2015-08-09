package org.raig;


import org.apache.log4j.Logger;


import static java.lang.Thread.sleep;

public final class  IndianPaleAle {
    private static   Logger logger = Logger.getLogger(IndianPaleAle.class);
    private static final long SLEEP_TIME = ClockTamagotchi.DEFAULT_MILLISECONDS_PERIOD * 3;
    private static final int INITIAL_HAPPINESS = 6;

    private IndianPaleAle() {
    }

    public static void main(String[] argv) {
        logger.debug("Indian pale Ale is the best!");


        ClockTamagotchi clock = new ClockTamagotchi(ClockTamagotchi.DEFAULT_MILLISECONDS_PERIOD);


        Tamagotchi tamagotchi =  new Tamagotchi(INITIAL_HAPPINESS);
        clock.addObserver(tamagotchi);

        new Thread(clock).start();
        logger.debug("Init happiness :" + tamagotchi.getHappiness());

        tamagotchi.feed();
        logger.debug("After feeding the tamagotchi happiness has been Increased " + tamagotchi.getHappiness());

        try {
            sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            logger.error("Error Sleeping!!");
            return;
        }

        logger.debug("After Sleeping more " + SLEEP_TIME + " seconds happiness has decreased : " + tamagotchi.getHappiness());

        clock.die();
    }

}

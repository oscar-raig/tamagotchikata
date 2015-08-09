package org.raig;


import org.apache.log4j.Logger;


import static java.lang.Thread.sleep;

public final class  IndianPaleAle {
    private static   Logger logger = Logger.getLogger(IndianPaleAle.class);
    private static final long SLEEP_TIME = ClockTamagotchi.DEFAULT_MILLISECONDS_PERIOD * 10;

    private IndianPaleAle() {
    }

    public static void main(String[] argv) {
        logger.debug("Indian pale Ale is the best!");

        ClockTamagotchi clock = new ClockTamagotchi(ClockTamagotchi.DEFAULT_MILLISECONDS_PERIOD);

        new Thread(clock).start();
        Tamagotchi tamagotchi =  new Tamagotchi(clock);

        logger.debug("Init happiness :" + tamagotchi.getHappiness());

        tamagotchi.feed();
        logger.debug("After feeding the tamagotchi happiness has been Increased " + tamagotchi.getHappiness());

        try {
            sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            logger.error("Error Sleeping!!");
            return;
        }

        logger.debug("After Sleeping more 5 seconds happiness has decreased : " + tamagotchi.getHappiness());

        clock.die();
    }

}

package org.raig;


import org.apache.log4j.Logger;

import static java.lang.Thread.sleep;

public final class Main {
    private static   Logger logger = Logger.getLogger(Main.class);
    private static final long SLEEP_TIME = ClockTamagotchi.DEFAULT_MILLISECONDS_PERIOD * 3;
    private static final int INITIAL_HAPPINESS = 6;

    private Main() {
    }

    public static void main(String[] argv) {
        logger.debug("Indian Pale Ale is the best beer you have tasted!");




        ClockTamagotchi clock = new ClockTamagotchi(ClockTamagotchi.DEFAULT_MILLISECONDS_PERIOD);
        FeelingRepository feelingRepository = new FeelingRepository();
        Feeling happiness = new Feeling("happiness");
        feelingRepository.insertFeeling(happiness);
        MacroCommand macroCommand = new MacroCommand();
        IncrementCommand incrementCommand = new IncrementCommand(feelingRepository,"happiness");
        macroCommand.add(incrementCommand);
        DateTamagochi dateTamagochi = new DateTamagochi();
        TimePassesCommand timePassesCommand =
          new TimePassesCommand(feelingRepository,"happiness",dateTamagochi);

        Tamagotchi tamagotchi =  new Tamagotchi(feelingRepository,
            macroCommand,timePassesCommand,INITIAL_HAPPINESS);
        clock.addObserver(tamagotchi);

        new Thread(clock).start();
        logger.debug("Init happiness :" + tamagotchi.getHappiness());

        tamagotchi.feed();
        logger.debug("After feeding the tamagotchi happiness has been Increased " + tamagotchi.getHappiness());

        sleeping();
        logger.debug("After Sleeping more " + SLEEP_TIME + " seconds happiness has decreased : " + tamagotchi.getHappiness());

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

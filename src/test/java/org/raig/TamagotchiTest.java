package org.raig;

import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;

import static java.lang.Thread.sleep;
import org.junit.Assert;


public class TamagotchiTest {

    private static  Logger logger = Logger.getLogger("TamagotchiTest");

    private Tamagotchi tamagotchi;
    private ClockTamagotchi clockTamagotchi;
    private static final long TEST_MILLISECONS_PERIOD = 1000;
    private static final long TEST_DELAY = TEST_MILLISECONS_PERIOD / 2;

    @Before
    public void setup() {
        clockTamagotchi = new ClockTamagotchi(TEST_MILLISECONS_PERIOD);
        final int initialValueOfHappiness  = 5;
        tamagotchi = new Tamagotchi(initialValueOfHappiness);
        new Thread(clockTamagotchi).start();
    }

    @Test
    public void feedShouldIncreaseHappiness() {
        int initialHappiness = tamagotchi.getHappiness();
        tamagotchi.feed();
        Assert.assertTrue(initialHappiness < tamagotchi.getHappiness());
    }

    @Test
    public void feedShouldIncrease1Unit() {
        int initialHappiness = tamagotchi.getHappiness();
        tamagotchi.feed();
        Assert.assertEquals(initialHappiness + 1, tamagotchi.getHappiness());
    }

    @Test
    public void happinessAtMaxLevelShouldNotAboveMaxLevelHappinessAfterFeed() {

        ClockTamagotchi clockTamagotchi = new ClockTamagotchi(ClockTamagotchi.DEFAULT_MILLISECONDS_PERIOD);
        Tamagotchi tamagotchiVeryHappy = new Tamagotchi(Tamagotchi.MAX_HAPPINESS);
        int initialHappiness = tamagotchiVeryHappy.getHappiness();
        tamagotchiVeryHappy.feed();
        Assert.assertEquals(initialHappiness, tamagotchiVeryHappy.getHappiness());
    }

    @Test
    public void happinessShouldDecreaseAfter10Seconds() throws InterruptedException {

        int initHappiness = tamagotchi.getHappiness();
        logger.debug("Testing happiness Value of Initial Happiness " + initHappiness);
        clockTamagotchi.addObserver(tamagotchi);
        logger.debug("Sleeping a little bit");
        sleep(TEST_MILLISECONS_PERIOD + TEST_DELAY);
        logger.debug("Testing happiness Value of actual Happiness " + tamagotchi.getHappiness());
        Assert.assertTrue(initHappiness > tamagotchi.getHappiness());

    }


}

package org.raig;

import org.apache.log4j.Logger;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.raig.tamagotchi.application.TamagotchiFactory;

import org.raig.tamagotchi.domain.model.Tamagotchi;

import static java.lang.Thread.sleep;


public class TamagotchiTest {

    private static  final Logger LOGGER = Logger.getLogger(TamagotchiTest.class);

    private Tamagotchi tamagotchi;
    private static final long TEST_MILLISECONS_PERIOD = 1000;
    private static final long TEST_DELAY = TEST_MILLISECONS_PERIOD / 2;
    private static final int INIT_FEELING_TEST  = 5;

    @Before
    public void setup() {
        tamagotchi = TamagotchiFactory.createTamagotchi(INIT_FEELING_TEST);

    }

    @Test
    public void feedShouldDecreasedHungriness() {
        int initialHungriness = tamagotchi.getHungriness();
        tamagotchi.feed();

        Assert.assertEquals(initialHungriness , tamagotchi.getHungriness() + 1);
    }

    @Test
    public void feedShouldIncreaseFullness() {
        int initialFullness = tamagotchi.getFullness();
        tamagotchi.feed();
        Assert.assertEquals(initialFullness + 1 , tamagotchi.getFullness());
    }


    @Test
    public void playShouldIncreaseTiredness() {
        int initialTiredness = tamagotchi.getTiredness();
        tamagotchi.play();
        Assert.assertEquals(initialTiredness + 1 , tamagotchi.getTiredness());
    }

    @Test
    public void playShouldIncreaseHappiness() {
        int initialHappiness = tamagotchi.getHappiness();
        tamagotchi.play();
        Assert.assertEquals(initialHappiness + 1 , tamagotchi.getHappiness());
    }



    @Test
    public void happinessShouldDecreaseAfter10Seconds() throws InterruptedException {

        int initHappiness = tamagotchi.getHappiness();
        sleep(TEST_MILLISECONS_PERIOD + TEST_DELAY);
        Assert.assertTrue(initHappiness > tamagotchi.getHappiness());

    }


}

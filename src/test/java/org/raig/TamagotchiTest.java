package org.raig;

import org.apache.log4j.Logger;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.raig.tamagotchi.application.TamagotchiFactory;

import org.raig.tamagotchi.domain.model.DateProvider;
import org.raig.tamagotchi.domain.model.Tamagotchi;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.when;


public class TamagotchiTest {

    private  static final int TIMEINSECONDSFORTEST = 61;
    private static  final Logger LOGGER = Logger.getLogger(TamagotchiTest.class);
    private static final long TEST_MILLISECONS_PERIOD = 1000;
    private static final long TEST_DELAY = TEST_MILLISECONS_PERIOD / 2;
    private static final int INIT_FEELING_TEST  = 5;

    private Tamagotchi tamagotchi;
    private DateProvider dateProvider;
    private Date dateInitTest;

    @Before
    public void setup() {
        dateProvider = Mockito.mock(DateProvider.class);
        dateInitTest = new Date();

        when(dateProvider.now()).thenReturn(dateInitTest);
        tamagotchi = TamagotchiFactory.createTamagotchi(INIT_FEELING_TEST, dateProvider);

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
    public void happinessShouldNotDecreaseAfter0Seconds() throws InterruptedException {
        int initHappiness = tamagotchi.getHappiness();
        tamagotchi.update(null, dateInitTest);
        Assert.assertEquals(initHappiness, tamagotchi.getHappiness());
    }

    @Test
    public void happinessShouldDecreaseAftert61Seconds() throws InterruptedException {

        // TODO make a private function
        Date dateInitTestFor61Seconds = dateInitTest;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateInitTestFor61Seconds);
        calendar.add(Calendar.SECOND, TIMEINSECONDSFORTEST);

        Date dateForTest = calendar.getTime();
        // end TODO
        LOGGER.debug("Date Init Test" + dateInitTestFor61Seconds.toString());
        LOGGER.debug("Mocked date " + dateForTest.toString());



        int initHappiness = tamagotchi.getHappiness();

        tamagotchi.update(null, dateForTest);
        Assert.assertEquals(initHappiness, tamagotchi.getHappiness() + 1);
    }


}

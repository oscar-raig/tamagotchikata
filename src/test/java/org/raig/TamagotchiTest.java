package org.raig;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.raig.tamagotchi.domain.model.Alarm;
import org.raig.tamagotchi.domain.model.DateProvider;
import org.raig.tamagotchi.domain.model.Feeling.Feeling;
import org.raig.tamagotchi.domain.model.Feeling.FeelingRepository;
import org.raig.tamagotchi.domain.model.Tamagotchi;

import static java.lang.Thread.sleep;


public class TamagotchiTest {

    private static  final Logger LOGGER = Logger.getLogger(TamagotchiTest.class);

    private Tamagotchi tamagotchi;
    private Alarm clockTamagotchi;
    private static final long TEST_MILLISECONS_PERIOD = 1000;
    private static final long TEST_DELAY = TEST_MILLISECONS_PERIOD / 2;

    @Before
    public void setup() {

        clockTamagotchi = new Alarm(TEST_MILLISECONS_PERIOD);
        final int initialValueOfHappiness  = 5;
        FeelingRepository feelingRepository = new FeelingRepository();
        Feeling happiness = new Feeling("happiness");
        feelingRepository.insertFeeling(happiness);

        IncrementCommand incrementHappiness = new IncrementCommand(feelingRepository,"happiness");
        MacroCommand feed = new MacroCommand();
        feed.add(incrementHappiness);
        DateProvider dateTamagochi = new DateProvider();
        TimePassesCommand timePassesCommand =
          new TimePassesCommand(feelingRepository,"happiness", dateTamagochi);
        tamagotchi = new Tamagotchi(feelingRepository, feed,
          timePassesCommand,initialValueOfHappiness);
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

        Alarm clockTamagotchi = new Alarm(Alarm.DEFAULT_MILLISECONDS_PERIOD);
        FeelingRepository feelingRepository = new FeelingRepository();
        Feeling happiness = new Feeling("happiness");
        feelingRepository.insertFeeling(happiness);
        MacroCommand feed = new MacroCommand();
        IncrementCommand incrementCommand = new IncrementCommand(feelingRepository,"happiness");
        feed.add(incrementCommand);
        DateProvider dateTamagochi = new DateProvider();
        TimePassesCommand timePassesCommand =
          new TimePassesCommand(feelingRepository,"happiness",dateTamagochi);
        Tamagotchi tamagotchiVeryHappy = new Tamagotchi(feelingRepository,feed,
          timePassesCommand,Tamagotchi.MAX_HAPPINESS);
        clockTamagotchi.addObserver(tamagotchiVeryHappy);
        int initialHappiness = tamagotchiVeryHappy.getHappiness();
        tamagotchiVeryHappy.feed();
        Assert.assertEquals(initialHappiness, tamagotchiVeryHappy.getHappiness());
    }

    @Test
    public void happinessShouldDecreaseAfter10Seconds() throws InterruptedException {

        FeelingRepository feelingRepository = new FeelingRepository();
        Feeling feeling = new Feeling("happiness");
        feelingRepository.insertFeeling(feeling);
        DateProvider dateTamagochi = new DateProvider();
        TimePassesCommand timePassesCommand =
          new TimePassesCommand(feelingRepository,"happiness",dateTamagochi);
        Tamagotchi tamagotchi = new Tamagotchi(feelingRepository,null,
          timePassesCommand,Tamagotchi.MAX_HAPPINESS);
        int initHappiness = tamagotchi.getHappiness();
        LOGGER.debug("Testing happiness Value of Initial Happiness " + initHappiness);
        clockTamagotchi.addObserver(tamagotchi);
        LOGGER.debug("Sleeping a little bit");
        sleep(TEST_MILLISECONS_PERIOD + TEST_DELAY);
        LOGGER.debug("Testing happiness Value of actual Happiness " + tamagotchi.getHappiness());
        Assert.assertTrue(initHappiness > tamagotchi.getHappiness());

    }


}

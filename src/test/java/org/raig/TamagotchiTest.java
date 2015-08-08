package org.raig;

import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;



import static java.lang.Thread.sleep;
import static org.junit.Assert.*;


public class TamagotchiTest {
    static final Logger logger = Logger.getLogger("TamagotchiTest");
    private Tamagotchi tamagotchi;
    private ClockTamagotchi clockTamagotchi;
	@Before
	public void Setup() {
        clockTamagotchi = new ClockTamagotchi();
		tamagotchi = new Tamagotchi(5,clockTamagotchi);
        new Thread( clockTamagotchi).start();
	}
	@Test
	public void feedShouldIncreaseHappiness() {
		int initialHappiness = tamagotchi.getHappiness();
		tamagotchi.feed();
		assertTrue(initialHappiness < tamagotchi.getHappiness());
	}

	@Test
	public void feedShouldIncreas1Unit() {

		int initialHappiness = tamagotchi.getHappiness();

		tamagotchi.feed();

		assertEquals(initialHappiness + 1, tamagotchi.getHappiness());
	}

	@Test
	public void happinessAtMaxLevelShouldNotAboveMaxLeveHappinesAfterfeed() {

		ClockTamagotchi clockTamagotchi = new ClockTamagotchi();
		Tamagotchi tamagotchiVeryHappy = new Tamagotchi(Tamagotchi.MAX_HAPPINESS,clockTamagotchi);

		int initialHappiness = tamagotchiVeryHappy.getHappiness();

		tamagotchiVeryHappy.feed();

		assertEquals(initialHappiness,tamagotchiVeryHappy.getHappiness() );
	}

    @Test
    public void happinessShouldDecreaseAfter10Seconds() throws InterruptedException {

        int initalHappiness = tamagotchi.getHappiness();
        logger.debug("Testing happiness Value of Initial Happiness " + initalHappiness);
        clockTamagotchi.addObserver(tamagotchi);
        logger.debug("Sleeping a little bit");
        sleep(10000);
        logger.debug("Testing happiness Value of actual Happiness " + tamagotchi.getHappiness());
        assertTrue(initalHappiness > tamagotchi.getHappiness());

    }


}

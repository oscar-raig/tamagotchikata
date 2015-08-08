package org.raig;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static java.lang.Thread.sleep;

public class  IndianPaleAle {
	final static Logger logger = Logger.getLogger(IndianPaleAle.class);
	final static int INITIAL_HAPPINESS = 0;

	public static void main(String[] argv) {
		logger.debug("Indian pale Ale is the best!");

		ClockTamagotchi clock = new ClockTamagotchi();

		new Thread(clock).start();
		Tamagotchi tamagotchi =  new Tamagotchi(INITIAL_HAPPINESS, clock);

		logger.debug("Inital hapiness :" + tamagotchi.getHappiness());

		tamagotchi.feed();
		logger.debug("Afterer feeding the tamagotchi hapiness has been Increased " + tamagotchi.getHappiness());

		try {
			sleep(10000);
		} catch (InterruptedException e) {
			logger.error("Error Sleeping!!");
			return;
		}

		logger.debug("After Sleeping more 5 seconds hapiness has decreased : " + tamagotchi.getHappiness());

		clock.die();
	}

}

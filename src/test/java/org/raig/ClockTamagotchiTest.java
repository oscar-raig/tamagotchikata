package org.raig;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class ClockTamagotchiTest {
    static final Logger logger = Logger.getLogger(ClockTamagotchiTest.class);
    boolean updatecalled;
    class TestObserver implements Observer {

        public void update(Observable o, Object arg) {
            logger.debug("Updating observer");
            updatecalled = true;
        }
    }

    @Test
    public void shouldCallUpdateMethod() throws Exception {

        logger.debug("testUpdate");
        updatecalled = false;
        TestObserver observer = new TestObserver();
        ClockTamagotchi clockTamagotchi = new ClockTamagotchi();
        clockTamagotchi.addObserver(observer);
        Thread thread = new Thread(clockTamagotchi);
        thread.start();

        sleep(10000);
        assertEquals(updatecalled, true);
        clockTamagotchi.die();

    }
}

package org.raig.tamagotchi.domain.model;

import org.apache.log4j.Logger;
import org.junit.Test;
import java.util.Observer;
import static java.lang.Thread.sleep;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AlarmTest {
    private static  Logger logger = Logger.getLogger(AlarmTest.class);

    private static final long TEST_MILLISECONS_PERIOD = 100;
    private static final long TEST_DELAY = TEST_MILLISECONS_PERIOD / 2;
    @Test
    public void shouldCallUpdateMethodonce() throws Exception {
        // TODO DRY
        logger.debug("Clock should Call Update Method once");
        DateProvider dateProvider = new DateProvider();
        Alarm clockTamagotchi = new Alarm(TEST_MILLISECONS_PERIOD, dateProvider);
        Observer observer = Mockito.mock(Observer.class);

        clockTamagotchi.addObserver(observer);
        Thread thread = new Thread(clockTamagotchi);
        thread.start();

        sleep(TEST_MILLISECONS_PERIOD * 1 + TEST_DELAY);
        verify(observer, times(1)).update(anyObject(), anyObject());
        clockTamagotchi.die();

    }

    @Test
    public void shouldCallUpdateMethodtwice() throws Exception {
        // TODO DRY
        logger.debug("Clock Should call update Method twice");
        DateProvider dateProvider = new DateProvider();
        Alarm clockTamagotchi = new Alarm(TEST_MILLISECONS_PERIOD, dateProvider);
        Observer observer = Mockito.mock(Observer.class);
        clockTamagotchi.addObserver(observer);
        Thread thread = new Thread(clockTamagotchi);
        thread.start();

        sleep(TEST_MILLISECONS_PERIOD * 2 + TEST_DELAY);
        verify(observer, times(2)).update(anyObject(), anyObject());
        clockTamagotchi.die();

    }

}

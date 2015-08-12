package org.raig;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IncrementCommandTest {

  @Test
  public void incrementCommandShouldAdd1() {

    FeelingRepository feelingRepository = new FeelingRepository ();
    Feeling happiness = new Feeling("happiness");
    feelingRepository.insertFeeling(happiness);
    int initHappiness = happiness.getValue();
    IncrementCommand incrementCommand = new IncrementCommand(feelingRepository,"happiness");

    incrementCommand.execute();

    Feeling happinessAfterIncrease = feelingRepository.getFeeling("happiness");

    int finalHappiness = happiness.getValue();

    assertEquals(initHappiness+1,finalHappiness);

  }
}
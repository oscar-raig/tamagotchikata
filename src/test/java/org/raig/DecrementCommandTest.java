package org.raig;

import org.junit.Test;
import org.raig.tamagotchi.domain.model.Feeling.Feeling;
import org.raig.tamagotchi.domain.model.Feeling.FeelingRepository;

import static org.junit.Assert.assertEquals;

public class DecrementCommandTest {


  @Test
  public void decrementCommandShouldAdd1() {

    FeelingRepository feelingRepository = new FeelingRepository ();
    Feeling happiness = new Feeling("happiness");
    feelingRepository.insertFeeling(happiness);
    int initHappiness = happiness.getValue();
    DecrementCommand incrementCommand = new DecrementCommand(feelingRepository,"happiness");

    incrementCommand.execute();

    Feeling happinessAfterIncrease = feelingRepository.getFeeling("happiness");

    int finalHappiness = happiness.getValue();

    assertEquals(initHappiness,finalHappiness + 1);

  }
}
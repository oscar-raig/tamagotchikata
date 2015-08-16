package org.raig;

import org.junit.Test;
import org.raig.tamagotchi.domain.model.Feeling.Feeling;
import org.raig.tamagotchi.domain.model.Feeling.FeelingRepository;

import static org.junit.Assert.assertEquals;

public class IncrementCommandTest {

  @Test
  public void incrementCommandShouldAdd1() {

    FeelingRepository feelingRepository = new FeelingRepository();
    Feeling happiness = new Feeling("happiness");
    feelingRepository.insertFeeling(happiness);
    int initHappiness = happiness.getValue();
    IncrementCommand incrementCommand = new IncrementCommand(feelingRepository, "happiness");
    incrementCommand.execute();

    Feeling happinessFinal = feelingRepository.getFeeling("happiness");
    assertEquals(initHappiness + 1, happinessFinal.getValue());

  }
}

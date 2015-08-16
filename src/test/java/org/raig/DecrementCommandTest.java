package org.raig;

import org.junit.Test;
import org.raig.tamagotchi.domain.model.Feeling.Feeling;
import org.raig.tamagotchi.domain.model.Feeling.FeelingRepository;


import static org.junit.Assert.assertEquals;

public class DecrementCommandTest {

  private  static final int INITIAL_HAPPINESS_FOR_TEST = 5;

  @Test
  public void decrementCommandShouldDecrement1() {

    FeelingRepository feelingRepository = new FeelingRepository();
    Feeling happiness = new Feeling("happiness", INITIAL_HAPPINESS_FOR_TEST);
    feelingRepository.insertFeeling(happiness);
    DecrementCommand decrementCommand = new DecrementCommand(feelingRepository, "happiness");

    decrementCommand.execute();


    int finalHappiness = happiness.getValue();
    assertEquals(INITIAL_HAPPINESS_FOR_TEST - 1, finalHappiness);

  }
}

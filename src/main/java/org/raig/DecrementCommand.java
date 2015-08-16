package org.raig;

import org.apache.log4j.Logger;
import org.raig.tamagotchi.domain.model.Command;
import org.raig.tamagotchi.domain.model.Feeling.Feeling;
import org.raig.tamagotchi.domain.model.Feeling.FeelingRepository;

public class DecrementCommand implements Command {

  private static final Logger LOGGER = Logger.getLogger(DecrementCommand.class);

  FeelingRepository feelingRepository;
  String feelingName;

  public DecrementCommand(FeelingRepository feelingRepository, String feelingName) {
    this.feelingRepository = feelingRepository;
    this.feelingName = feelingName;
  }

  @Override
  public void execute() {
    LOGGER.debug("Decrementing " + feelingName);
    Feeling feeling = feelingRepository.getFeeling(feelingName);
    int value = feeling.getValue();
    if (value > 0) {
      value--;
      feeling.setValue(value);
    }
  }
}

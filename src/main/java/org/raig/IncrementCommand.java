package org.raig;

import org.raig.tamagotchi.domain.model.Command;
import org.raig.tamagotchi.domain.model.Feeling.Feeling;
import org.raig.tamagotchi.domain.model.Feeling.FeelingRepository;

public class IncrementCommand implements Command {
  FeelingRepository feelingRepository;
  String feelingName;
  private final int MAX_INCREMENT = 50;
  public IncrementCommand(FeelingRepository feelingRepository,String feelingName) {
    this.feelingRepository = feelingRepository;
    this.feelingName = feelingName;
  }
  @Override
  public void execute() {

    Feeling feeling = feelingRepository.getFeeling(feelingName);
    int value = feeling.getValue();
    if ( value >= MAX_INCREMENT) {
      value = MAX_INCREMENT;
      return;
    }
    value++;
    feeling.setValue(value);

  }
}

package org.raig;

public class DecrementCommand implements Command {

  FeelingRepository feelingRepository;
  String feelingName;

  public DecrementCommand(FeelingRepository feelingRepository,String feelingName) {
    this.feelingRepository = feelingRepository;
    this.feelingName = feelingName;
  }

  @Override
  public void execute() {
    Feeling feeling = feelingRepository.getFeeling(feelingName);
    int value = feeling.getValue();
    value--;
    feeling.setValue(value);
  }
}

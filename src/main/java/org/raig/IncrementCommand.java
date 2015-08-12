package org.raig;

public class IncrementCommand implements Command {
  FeelingRepository feelingRepository;
  String feelingName;
  public IncrementCommand(FeelingRepository feelingRepository,String feelingName) {
    this.feelingRepository = feelingRepository;
    this.feelingName = feelingName;
  }
  @Override
  public void execute() {

    Feeling feeling = feelingRepository.getFeeling(feelingName);
    int value = feeling.getValue();
    value++;
    feeling.setValue(value);

  }
}

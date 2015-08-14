package org.raig.tamagotchi.domain.model.Feeling;

public class Feeling {

  private final String name;
  private int value;

  public void setValue(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  public Feeling(String name) {
    this.name = name;
    this.value = 0;
  }


}

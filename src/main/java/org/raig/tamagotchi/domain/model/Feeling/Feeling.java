package org.raig.tamagotchi.domain.model.Feeling;

public class Feeling {

  public static final int DEFAULT_INIT_FEELING = 5;

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

  public Feeling(String name, int initialValue) {
    this.name = name;
    this.value = initialValue;
  }

  public Feeling(String name) {
    this.name = name;
    this.value = 0;
  }

}

package org.raig;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FeelingTest {
  Feeling feeling;

  @Before
  public void setUp() {
    feeling = new Feeling("happiness");
  }

  @Test
  public void getNameShouldReturnNameoTheFiling(){
    String nameOfFeeling = feeling.getName();
    assertEquals("happiness" , nameOfFeeling);
  }

  @Test
  public void getValueShouldReturn0IfIsNotInitialized() {
    int feelingValue = feeling.getValue();
    assertEquals(0, feelingValue);
  }



}
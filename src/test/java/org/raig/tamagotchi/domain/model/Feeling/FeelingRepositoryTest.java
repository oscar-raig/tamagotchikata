package org.raig.tamagotchi.domain.model.Feeling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FeelingRepositoryTest {
  FeelingRepository feelingRepository;
  private static final int INITIAL_FEELING = 5;

  @Before
  public void setUp() {
    feelingRepository = new FeelingRepository();
  }
  @Test
  public void getValueVoidRepositoryShourldReturnNull() {
    Feeling feeling = feelingRepository.getFeeling("happiness");
    assertNull(feeling);
  }

  @Test
  public void getSameFeelingAfterInsertingShouldNotReturnNull() {
    Feeling feeling = new Feeling("happiness");
    feelingRepository.insertFeeling(feeling);
    Feeling feelingRead = feelingRepository.getFeeling("happiness");
    assertNotNull(feelingRead);
  }

  @Test
  public void getDifferentFeelingAfterInsertingShouldNotReturnNull() {
    Feeling feeling = new Feeling("happiness");
    feelingRepository.insertFeeling(feeling);
    Feeling feelingNotRead = feelingRepository.getFeeling("dummy");
    assertNull(feelingNotRead);
  }

  @Test
  public void getValueAfterupdateFeelingShouldReturnUpdatedValue() {
    Feeling feeling = new Feeling("happiness");
    feelingRepository.insertFeeling(feeling);
    Feeling feelingNotRead = feelingRepository.getFeeling("happiness");
    feelingRepository.updateFeeling("happiness", INITIAL_FEELING);

    Feeling afterUpdate = feelingRepository.getFeeling("happiness");

    int valuAfter = afterUpdate.getValue();

    assertEquals(INITIAL_FEELING, valuAfter);
  }
}

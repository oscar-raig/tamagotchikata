package org.raig.tamagotchi.domain.model.Feeling;

import org.junit.Before;
import org.junit.Test;
import org.raig.tamagotchi.domain.model.Feeling.Feeling;
import org.raig.tamagotchi.domain.model.Feeling.FeelingRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FeelingRepositoryTest {
  FeelingRepository feelingRepository;

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
    feelingRepository.updateFeeling("happiness",5);

    Feeling afterUpdate = feelingRepository.getFeeling("happiness");

    int valuAfter = afterUpdate.getValue();

    assertEquals(5,valuAfter);
  }


}
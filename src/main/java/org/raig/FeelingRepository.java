package org.raig;

import java.util.HashMap;

public class FeelingRepository {

  HashMap<String,Feeling> list = new HashMap<>();

  public Feeling getFeeling(String happiness) {
    return list.get(happiness);
  }

  public void insertFeeling(Feeling feeling) {
    list.put(feeling.getName(),feeling);
  }

  public void updateFeeling(String  feelingName,int Value) {
    Feeling feeling = list.get(feelingName);
    feeling.setValue(Value);
  }





}

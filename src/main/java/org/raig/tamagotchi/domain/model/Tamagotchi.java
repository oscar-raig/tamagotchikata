package org.raig.tamagotchi.domain.model;

import org.raig.tamagotchi.domain.model.Feeling.FeelingRepository;

import java.util.Observable;
import java.util.Observer;



public class Tamagotchi implements Observer {

    public static final   int  MAX_HAPPINESS = 50;
    private static final  int DEFAULT_INIT_HAPPINESS = 0;
    private FeelingRepository feelingRepository;

    private Command feed;
    private Command timePasses;


   public Tamagotchi( FeelingRepository feelingRepository) {
     this.feelingRepository = feelingRepository;
   }

    public Tamagotchi(FeelingRepository feelingRepository,Command feed,Command timePasses,int happiness) {
      this.feed = feed;
      this.feelingRepository = feelingRepository;
      feelingRepository.updateFeeling("happiness",happiness);
      this.timePasses = timePasses;

    }
    public Tamagotchi() {
      feelingRepository.updateFeeling("happiness", DEFAULT_INIT_HAPPINESS);
    }

    public int getHappiness() {
        return feelingRepository.getFeeling("happiness").getValue();
    }

    public void feed() {
        feed.execute();
    }



    @Override
    public void update(Observable o, Object arg) {
        timePasses.execute();
    }
}

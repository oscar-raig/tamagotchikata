package org.raig;

import org.apache.log4j.Logger;

import java.util.Date;

public class TimePassesCommand extends DecrementCommand {

  java.util.Date lastUpdateDate;
  private static  final Logger LOGGER = Logger.getLogger(TimePassesCommand.class);

  private final DateTamagochi dateTamagotchi;

  public TimePassesCommand(FeelingRepository feelingRepository,String feeling,
                           DateTamagochi dateTamagotchi){
    super(feelingRepository,feeling);
    this.dateTamagotchi = dateTamagotchi;
    lastUpdateDate =dateTamagotchi.now();

    LOGGER.info("First update: " + lastUpdateDate.toString());
  }

  @Override
  public void execute() {
    LOGGER.info("execute");
    Date now = dateTamagotchi.now();
    long seconds = (now.getTime()-lastUpdateDate.getTime())/1000;
    LOGGER.info("Last updated was: " + seconds + "seconds");
    super.execute();
  }
}

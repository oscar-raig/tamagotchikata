package org.raig;

import org.apache.log4j.Logger;
import org.raig.tamagotchi.domain.model.DateProvider;
import org.raig.tamagotchi.domain.model.Feeling.FeelingRepository;

import java.util.Date;

public class TimePassesCommand extends DecrementCommand {

  java.util.Date lastUpdateDate;
  private static  final Logger LOGGER = Logger.getLogger(TimePassesCommand.class);

  private final DateProvider dateTamagotchi;
  private static final long MILLISECONDS  = 1000;

  public TimePassesCommand(FeelingRepository feelingRepository, String feeling,
                           DateProvider dateTamagotchi) {
    super(feelingRepository, feeling);
    this.dateTamagotchi = dateTamagotchi;
    lastUpdateDate = dateTamagotchi.now();

    LOGGER.info("First update: " + lastUpdateDate.toString());
  }

  @Override
  public void execute() {
    LOGGER.info("execute");
    Date now = dateTamagotchi.now();
    long seconds = (now.getTime() - lastUpdateDate.getTime()) / MILLISECONDS;
    LOGGER.info("Last updated was: " + seconds + "seconds");

    super.execute();
  }
}

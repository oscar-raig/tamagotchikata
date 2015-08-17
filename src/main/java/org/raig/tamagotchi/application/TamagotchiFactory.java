package org.raig.tamagotchi.application;


import org.raig.DecrementCommand;
import org.raig.IncrementCommand;
import org.raig.MacroCommand;
import org.raig.tamagotchi.domain.model.DateProvider;
import org.raig.tamagotchi.domain.model.Feeling.Feeling;
import org.raig.tamagotchi.domain.model.Feeling.FeelingRepository;
import org.raig.tamagotchi.domain.model.Tamagotchi;



public final class TamagotchiFactory {

    private TamagotchiFactory() {
    }


    private static MacroCommand feed;
    private static MacroCommand play;

    private static IncrementCommand incrementCommandHappiness = null;
    private static DecrementCommand decrementCommandHappiness = null;

    private static IncrementCommand incrementCommandHungriness = null;
    private static DecrementCommand decrementCommandHungriness = null;

    private static IncrementCommand incrementCommandTiredness = null;
    private static IncrementCommand incrementCommandFullness = null;

    private static MacroCommand timePassesCommand;
    private static Tamagotchi tamagotchi = null;

    public static Tamagotchi createTamagotchi(int initFeeling, DateProvider dateProvider) {
        if (tamagotchi != null) {
            return tamagotchi;
        }
        FeelingRepository feelingRepository = new FeelingRepository();

        Feeling happiness = new Feeling("happiness", initFeeling);
        feelingRepository.insertFeeling(happiness);

        Feeling hungriness = new Feeling("hungriness", initFeeling);
        feelingRepository.insertFeeling(hungriness);

        Feeling tiredness = new Feeling("tiredness");
        feelingRepository.insertFeeling(tiredness);

        Feeling fullness = new Feeling("fullness");
        feelingRepository.insertFeeling(fullness);





        createFeed(feelingRepository);
        createPlay(feelingRepository);
        createTimePasses(feelingRepository);
        tamagotchi =  new Tamagotchi(feelingRepository,
                feed, play, timePassesCommand);
        return tamagotchi;
    }

    public static MacroCommand  createTimePasses(FeelingRepository feelingRepository) {
        if (decrementCommandHappiness == null) {
            createDecrementCommandHappiness(feelingRepository);
        }

        if (incrementCommandTiredness == null) {
            createIncrementCommandTiredness(feelingRepository);
        }

        if (incrementCommandHungriness == null) {
            createIncrementCommandHungriness(feelingRepository);
        }
        timePassesCommand =
                new MacroCommand();
        timePassesCommand.add(decrementCommandHappiness);
        timePassesCommand.add(incrementCommandTiredness);
        timePassesCommand.add(incrementCommandHungriness);
        return timePassesCommand;
    }

    public static MacroCommand createFeed(FeelingRepository feelingRepository) {
        if (incrementCommandFullness == null) {
            createIncrementCommandFullness(feelingRepository);
        }
        if (decrementCommandHungriness == null) {
            createDecrementCommandHungriness(feelingRepository);
        }
        feed = new MacroCommand();
        feed.add(incrementCommandFullness);
        feed.add(decrementCommandHungriness);
        return feed;
    }

    public static MacroCommand createPlay(FeelingRepository feelingRepository) {
        if (incrementCommandHappiness == null) {
            createIncrementCommandHappiness(feelingRepository);
        }
        if (incrementCommandTiredness == null) {
            createIncrementCommandTiredness(feelingRepository);
        }
        play = new MacroCommand();
        play.add(incrementCommandHappiness);
        play.add(incrementCommandTiredness);
        return play;
    }



    public static DecrementCommand createDecrementCommandHappiness(FeelingRepository feelingRepository) {
        decrementCommandHappiness = new DecrementCommand(feelingRepository, "happiness");
        return decrementCommandHappiness;
    }

    public static IncrementCommand createIncrementCommandHappiness(FeelingRepository feelingRepository) {
        incrementCommandHappiness = new IncrementCommand(feelingRepository, "happiness");
        return incrementCommandHappiness;
    }

    public static IncrementCommand createIncrementCommandTiredness(FeelingRepository feelingRepository) {
        incrementCommandTiredness = new IncrementCommand(feelingRepository, "tiredness");
        return incrementCommandTiredness;
    }

    public static IncrementCommand createIncrementCommandHungriness(FeelingRepository feelingRepository) {
        incrementCommandHungriness = new IncrementCommand(feelingRepository, "hungriness");
        return incrementCommandHungriness;
    }

    public static DecrementCommand createDecrementCommandHungriness(FeelingRepository feelingRepository) {
        decrementCommandHungriness = new DecrementCommand(feelingRepository, "hungriness");
        return decrementCommandHungriness;
    }
    public static IncrementCommand createIncrementCommandFullness(FeelingRepository feelingRepository) {
        incrementCommandFullness = new IncrementCommand(feelingRepository, "fullness");
        return incrementCommandFullness;
    }

}

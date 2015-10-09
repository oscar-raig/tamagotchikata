package org.raig;

import org.raig.tamagotchi.domain.model.Command;

import java.util.ArrayList;
import java.util.List;

public class MacroCommand implements Command {

  List<Command> list;

  public MacroCommand() {
    list = new ArrayList<Command>();
  }

  @Override
  public void execute() {
    for (Command command : list) {
      command.execute();
    }

  }

  public void add(Command command) {
    list.add(command);
  }


}

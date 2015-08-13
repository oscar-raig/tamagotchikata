package org.raig;

import org.raig.tamagotchi.domain.model.Command;

import java.util.ArrayList;

public class MacroCommand implements Command {

  ArrayList<Command> list;

  public MacroCommand() {
    list = new ArrayList<Command>();
  }

  @Override
  public void execute() {
    for(Command command : list ) {
      command.execute();
    }

  }

  public void add(Command command) {
    list.add(command);
  }


}

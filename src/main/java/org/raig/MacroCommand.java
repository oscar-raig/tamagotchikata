package org.raig;

import java.util.ArrayList;

public class MacroCommand implements Command {

  ArrayList<Command> list;

  MacroCommand() {
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

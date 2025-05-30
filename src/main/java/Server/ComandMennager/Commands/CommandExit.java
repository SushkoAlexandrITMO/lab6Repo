package Server.ComandMennager.Commands;

import Server.ComandMennager.CommandMennager;
import Server.transfer.*;

/**
 * CommandExit - класс, реализующий выполнение команды exit
 */
public class CommandExit implements Command{

    @Override
    public Response execute(String arg) {
        CommandMennager.Commands.get("save").execute(null);
        System.exit(1);
        return null;
    }

    @Override
    public String description() {
        return "exit - завершить программу (без сохранения в файл)";
    }
}

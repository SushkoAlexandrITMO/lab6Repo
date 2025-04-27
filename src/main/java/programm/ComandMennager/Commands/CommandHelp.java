package programm.ComandMennager.Commands;

import programm.ComandMennager.CommandMennager;
import programm.IO.transfer.Response;

/**
 * CommandHelp - класс, реализующий выполнение команды help
 */
public class CommandHelp implements Command{
    CommandMennager commandMennager;

    /**
     * CommandHelp() - конструктор класса
     */
    public CommandHelp() {}

    @Override
    public Response execute(String arg) {
        StringBuilder result = new StringBuilder();

        if (arg == null) {
            for (Command c : CommandMennager.Commands.values()) {
                result.append(c.description()).append("\n");
            }
        }
        else {return new Response("После команды help е должно быть аргументов", "error");}

        return new Response(result.toString(), null);
    }

    @Override
    public String description() {
        return "help - вывести справку по доступным командам";
    }
}

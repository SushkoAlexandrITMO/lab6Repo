package programm.ComandMennager;

import programm.ComandMennager.Commands.*;
import programm.Data.Stack.StackMennager;
import programm.IO.transfer.Request;
import programm.IO.transfer.Response;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * CommandMennager - класс, предназначенный для управления вызова и исполнения команд\n
 * Имеет поле - HashMap<String, Command> Commands
 */
public class CommandMennager {
    public static final Map<String, Command> Commands = new HashMap<String, Command>();

    /**
     * CommandMennager - конструктор класса, предназначенный для задания команд в Map и создания экземпляра класса StackMennager
     */
    public CommandMennager(StackMennager stackMennager) {
        Commands.put("help", new CommandHelp());
        Commands.put("info", new CommandInfo(stackMennager));
        Commands.put("show", new CommandShow(stackMennager));
        Commands.put("add", new CommandAdd(stackMennager, stackMennager.getFileMennager()));
        Commands.put("update", new CommandUpdate(stackMennager));
        Commands.put("remove_by_id", new CommandRemove(stackMennager, stackMennager.getFileMennager()));
        Commands.put("clear", new CommandClear(stackMennager));
        Commands.put("save", new CommandSave(stackMennager));
        Commands.put("execute_script", new CommandExecute(stackMennager.getFileMennager(), this));
        Commands.put("exit", new CommandExit());
        Commands.put("shuffle", new CommandShuffle(stackMennager));
        Commands.put("reorder", new CommandReorder(stackMennager));
        Commands.put("sort", new CommandSort(stackMennager));
        Commands.put("average_of_annual_turnover", new CommandAverage(stackMennager));
        Commands.put("count_by_official_address", new CommandCountByAddress(stackMennager));
        Commands.put("filter_starts_with_full_name", new CommandFilterFullName(stackMennager));
    }

    /**
     * useCommand - метод, предназначенный для обработки данных и вызова необходимой команды
     * @param req - запрос из IO Менеджера
     * @return - ответ Response
     */
    public Response useCommand(Request req)  {
        if (req.command().isEmpty() || !Commands.containsKey(req.command())) {
            return new Response("Команда введена не верно / данная команда не поддерживается", "error");
        }
        else {
            return Commands.get(req.command()).execute(req.arg());
        }
    }
}

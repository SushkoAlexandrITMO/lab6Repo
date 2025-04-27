package programm.ComandMennager.Commands;

import programm.IO.transfer.Response;

/**
 * CommandExit - класс, реализующий выполнение команды exit
 */
public class CommandExit implements Command{

    @Override
    public Response execute(String arg) {
        System.exit(1);
        return null;
    }

    @Override
    public String description() {
        return "exit - завершить программу (без сохранения в файл)";
    }
}

package Server.ComandMennager.Commands;

import Server.Model.*;
import Server.Stack.StackMennager;
import Server.transfer.*;

/**
 * CommandAverage - класс, предназначенный для реализации команды average
 */
public class CommandAverage implements Command{
    private final StackMennager stackMennager;

    /**
     * CommandAverage(StackMennager stackMennager) - конструктор класса
     * @param stackMennager - ссылка на стек-менеджер
     */
    public CommandAverage(StackMennager stackMennager) {
        this.stackMennager = stackMennager;
    }

    @Override
    public Response execute(String arg) {
        if (arg != null) {return new Response("После команды average_of_annual_turnover не должно быть аргументов", "error");}

        double count = 0;
        double summ = 0;

        for (Organization org: this.stackMennager.getStack()) {
            summ += org.getAnnualTurnover();
            count += 1;
        }

        return new Response("Средний годовой оборот компаний: " + String.valueOf((summ/count)), null);
    }

    @Override
    public String description() {
        return "average_of_annual_turnover - вывести среднее значение годового оборота для всех элементов коллекции";
    }
}

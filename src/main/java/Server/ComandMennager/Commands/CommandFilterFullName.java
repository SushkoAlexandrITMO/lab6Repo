package Server.ComandMennager.Commands;

import Server.Model.*;
import Server.Stack.StackMennager;
import Server.transfer.*;

import java.util.Stack;

/**
 * CommandFilterFullName - класс, реализующий выполнение команды filter_starts_with_full_name
 */
public class CommandFilterFullName implements Command{
    private final StackMennager stackMennager;

    /**
     * CommandFilterFullName(StackMennager stackMennager) - конструктор класса
     * @param stackMennager - ссылка на стек-менеджер
     */
    public CommandFilterFullName(StackMennager stackMennager) {
        this.stackMennager = stackMennager;
    }

    @Override
    public Response execute(String arg) {
        if (arg == null) {return new Response("", null);}
        String resp = "";

        Stack<Organization> stack = this.stackMennager.getStack();
        for (Organization org: stack) {
            if (org.getFullName().contains(arg)) {
                resp += org.toString() + "\n";
            }
        }

        if (resp.isEmpty()) {return new Response("Нет компаний, чьё полное название содержало в виде подстроки введённую строку", null);}
        return new Response("Список компаний, чьё полное название начинается с " + arg + ": " + resp, null);
    }

    @Override
    public String description() {
        return "filter_starts_with_full_name fullName - вывести элементы, полное название которых начинается с заданной подстроки";
    }
}

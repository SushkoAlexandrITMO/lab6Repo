package programm.IO;

import programm.ComandMennager.CommandMennager;
import programm.IO.transfer.*;

import java.util.Scanner;

/**
 * IOMennager - класс, ответственный за первичную обработку вводимой информации и вывод ответов
 */
public class InputOutMennager {
    CommandMennager commandMennager;
    private boolean isCommandInput;
    private String commandName;
    Response resp;

    /**
     * InputOutMennager(CommandMennager commandMennager) - конструктор класса
     * @param commandMennager - ссылка на стек-менеджер
     */
    public InputOutMennager(CommandMennager commandMennager) {
        this.commandMennager = commandMennager;
        this.isCommandInput = false;
    }

    /**
     * Start() - метод, отвечающий за запуск и функционирования приёма вводимых данных и их первичную обработку и вывод ответов
     */
    public void Start() {
        isCommandInput = false;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Приложение начало работу");

        while (scanner.hasNextLine()) {

            String[] inp = scanner.nextLine().split(" ");
            if (inp.length > 2) {
                System.out.println("Слишком большое количество аргументов!");
                continue;
            }
            else if (this.isCommandInput) {
                this.resp = commandMennager.useCommand(new Request(this.commandName, inp[0]));
            }
            else if (inp.length == 1) {
                this.resp = commandMennager.useCommand(new Request(inp[0], null));
            }
            else {
                this.resp = commandMennager.useCommand(new Request(inp[0], inp[1]));
            }

            System.out.println(resp.message());

            if (resp.args() != null) {
                if (resp.args().equals("input")) {
                    this.isCommandInput = true;
                    this.commandName = inp[0];
                } else if (resp.args().equals("finish")) {
                    this.isCommandInput = false;
                    this.commandName = null;
                }
            }

        }

    }
}

package programm.Data.Stack;

import programm.ComandMennager.Commands.Command;
import programm.Data.FileMennager.FileMenager;
import programm.Data.Model.Organization;
import programm.IO.transfer.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
/**
 * StackMennager - класс, предназначенный для управления стеком и его хранения\n
 * Имеет поле Stack<Organization> stack
 */
public class StackMennager {
    private final FileMenager fileMennager;
    private final Stack<Organization> stack;
    private int count;
    private final LocalDate initDate;

    /**
     * StackMennager - конструктор класса
     */
    public StackMennager(FileMenager fileMennager) {
        HashMap<String, Command> commands = new HashMap<>();
        initDate = LocalDate.now();
        stack = new Stack<>();
        this.fileMennager = fileMennager;
        ArrayList<Organization> result = fileMennager.addOrgsFromEnv(System.getenv("save"));
        if (result == null) {
            System.err.println("При чтении файла из переменной окружения произошла ошибка.\nПрограмма останавливает работу");
            System.exit(1);
        } else {
            setAll(result);
        }
    }

    /**
     * getStackInfo - метод для вывода информации о коллекции
     */
    public Response getStackInfo() {
        return new Response("Тип коллекции: Stack, количество элементов: " + String.valueOf(this.stack.size()) + ", дата инициализации: " + this.initDate + "\n", null);
    }

    /**
     * addOrganization(StackRequest req) - метод, предназначенный для добавления новой организации в вершину стека
     * @param req - запрос, содержащий необходимые данные (в нашем случае новую организацию)
     * @return - ответ о результате операции
     */
    public Response addOrganization(StackRequest req) {
        this.stack.push(req.org());
        return new Response("Данные сохранены", null);
    }

    /**
     * isIDIn(StackRequest req) - метод, предназначенный для проверки на наличие организации с необходимым ID
     * @param req - запрос, содержащий необходимые данные (в нашем случае ID организации)
     * @return - ответ о результате операции
     */
    public Response isIDIn(StackRequest req) {
        for (Organization org: this.stack) {
            if (org.getID() == req.id()) {return new Response("1", null);}
        }
        return new Response("0", null);
    }

    /**
     * setOrgFromID(StackRequest req) - метод, предназначенный для замены организации с необходимым ID
     * @param req - запрос, содержащий необходимые данные (в нашем случае организацию и ID)
     * @return - ответ о результате операции
     */
    public Response setOrgFromID(StackRequest req) {
        for (int ind = 0; ind < this.stack.size(); ind ++) {
            if (ind == req.id()) {
                this.stack.set(ind, req.org());
            }
        }
        return new Response("Данные сохранены", null);
    }

    /**
     * clearStack() - метод, предназначенный для очистки стека
     * @return - ответ о результате операции
     */
    public Response clearStack() {
        this.stack.clear();
        return new Response("Стек очищен", null);
    }

    /**
     * clearStack() - метод, предназначенный для очистки стека
     * @return - ответ о результате операции
     */
    public Stack<Organization> getStack() {
        return this.stack;
    }

    /**
     * removeFromStack - метод, предназначенный удаления элемента стека по ID
     * @return - ответ о результате операции
     */
    public Response removeFromStack(StackRequest req) {
        if (isIDIn(req).args().equals("0")) {return new Response("Организации с данным ID не существует", null);}
        ArrayList<Organization> list = new ArrayList<>();
        for (Organization orgs: this.stack) {
            Organization org = this.stack.pop();
            if (org.getID() == req.id()) {break;}
            else {list.add(org);}
        }
        this.stack.addAll(list);
        this.fileMennager.addNewFreeID(String.valueOf(req.id()));
        return new Response("Организация удалена", null);
    }

    /**
     * setAll(ArrayList<Organization> list) - метод, предназначенный для добавления всех элементов списка в стек
     * @param list - список, из которого загружаются элементы
     */
    public void setAll(ArrayList<Organization> list) {
        this.stack.addAll(list);
    }

    /**
     * getFM() - метод, предназначенный для получения ссылки на файл менеджер, используемый этим стек менеджер
     * @return - ссылка на файл менеджер, используемый этим стек менеджер
     */
    public FileMenager getFileMennager() {
        return this.fileMennager;
    }

    /**
     * saveStack(Request req) - метод, предназначенный для сохранения стека в файл
     * @param req - запрос
     * @return - ответ о статусе сохранения и ошибках (при наличии)
     */
    public Response saveStack(Request req) {
        return this.fileMennager.save(new FileRequest(this.stack));
    }

    /**
     * setFromEnvironment(String arg) - метод, предназначенный для загрузки данных в стек из файла
     * @param arg - имя файла
     * @return - ответ о загрузке данных
     */
    public Response setFromEnvironment(String arg) {
        ArrayList<Organization> res = this.fileMennager.addOrgsFromEnv(arg);
        if (res != null) {this.stack.addAll(res);}
        return new Response("Данные загружены", "success");
    }
}

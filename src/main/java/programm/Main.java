package programm;

import programm.ComandMennager.CommandMennager;
import programm.Data.FileMennager.FileMenager;
import programm.Data.Stack.StackMennager;
import programm.IO.InputOutMennager;

public class Main {
    public static void main(String[] args) {
        FileMenager fileMenager = new FileMenager("Data\\save.xml");
        StackMennager stackMennager = new StackMennager(fileMenager);
        CommandMennager commandMennager= new CommandMennager(stackMennager);
        InputOutMennager inputOutMennager = new InputOutMennager(commandMennager);
        inputOutMennager.Start();
    }
}

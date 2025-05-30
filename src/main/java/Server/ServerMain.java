package Server;

import Server.ComandMennager.CommandMennager;
import Server.FileMennager.FileMenager;
import Server.Stack.StackMennager;
import Server.ConnectMenager.ServerConnectMenager;

public class Main {
    public static void main(String[] args) {
        FileMenager fileMenager = new FileMenager();
        StackMennager stackMennager = new StackMennager(fileMenager);
        CommandMennager commandMennager= new CommandMennager(stackMennager);
        ServerConnectMenager serverConnectMenager = new ServerConnectMenager(commandMennager);
        serverConnectMenager.getSendFromToClient();

    }
}

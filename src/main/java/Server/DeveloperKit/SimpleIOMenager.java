package Server.DeveloperKit;

import Server.transfer.IORequest;

public class SimpleIOMenager {
    boolean fullLogs;
    
    public SimpleIOMenager() {this.fullLogs = true;}
    
    public void sendLog(IORequest request) {
        if (request.code().equals("1")) {System.out.println("Ошибка!");}
        System.out.println(request.message());
    }
}

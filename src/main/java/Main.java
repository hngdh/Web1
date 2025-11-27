import main.*;

public class Main {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Checker checker = new Checker();
        Sender sender = new Sender();
        Server server = new Server(receiver, checker, sender);
        server.transport();
    }
}

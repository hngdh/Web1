package main;

import com.fastcgi.FCGIInterface;
import exceptions.InputError;
import java.util.LinkedList;
import java.util.List;

public class Server {
    private final Receiver receiver;
    private final Checker checker;
    private final Sender sender;
    private final ErrorLogger errorLogger;
    private final String FCGI_PORT = "FCGI_PORT";
    private final String NUM_FCGI_PORT = "40405";
    private final List<Result> history = new LinkedList<>();

    public Server(Receiver receiver, Checker checker, Sender sender, ErrorLogger errorLogger) {
        this.receiver = receiver;
        this.checker = checker;
        this.sender = sender;
        this.errorLogger = errorLogger;
    }

    public void transport() {
        System.setProperty(FCGI_PORT, NUM_FCGI_PORT);
        FCGIInterface fcgi = new FCGIInterface();

        while(fcgi.FCGIaccept() == 0) {
            try {
                List<Float> parameters = this.receiver.readRequest();
                Float X = parameters.get(0);
                Float Y = parameters.get(1);
                Float R = parameters.get(2);
                Result result = this.checker.checkFallIn(X, Y, R);
                this.history.add(result);
                this.sender.sendJson(result);
            } catch (InputError e) {
                this.errorLogger.badRequest(e.getMessage());
            }
        }

    }
}
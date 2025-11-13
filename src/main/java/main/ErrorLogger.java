package main;

import java.io.PrintStream;

public class ErrorLogger {
    public void badRequest(String msg) {
        PrintStream out = System.out;
        out.print("HTTP/1.1 400 Bad Request\r\nContent-Type: text/plain\r\n\r\n" + msg + "\r\n");
        out.flush();
    }
}


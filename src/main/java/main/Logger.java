package main;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private static FileWriter error;
    private static FileWriter response;
    private static FileWriter request;
    private static int i;
    static {
        try {
            error = new FileWriter("lab1/error.txt");
            response = new FileWriter("lab1/response.txt");
            request = new FileWriter("lab1/request.txt");
        } catch (IOException e) {
            i = 1;
        }
    }

    public static void logError(String msg) {
        try {
            error.write(LocalDateTime.now() + ":\n" + msg + "\r\n");
            error.flush();
        } catch (IOException e) {
            i = 1;
        }
    }

    public static void logRequest(String msg) {
        try {
            request.write(LocalDateTime.now() + ":\n" + msg + "\r\n");
            request.flush();
        } catch (IOException e) {
            i = 1;
        }
    }

    public static void logResponse(String msg) {
        try {
            response.write(LocalDateTime.now() + ":\n" + msg + "\r\n");
            response.flush();
        } catch (IOException e) {
            i = 1;
        }
    }
}

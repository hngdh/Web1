package main;

import java.io.PrintStream;

public class Sender {
    public void sendJson(Result res) {
        String element = String.format("{\"x\":%s,\"y\":%s,\"R\":%s,\"hit\":%b,\"calTime\":\"%s\",\"printTime\":\"%s\"}", res.getX(), res.getY(), res.getR(), res.isHit(), res.getCalTime(), res.getPrintTime().toString());
        PrintStream out = System.out;
        out.print("HTTP/1.1 200 OK\r\n");
        out.print("Content-Type: application/json; charset=UTF-8\r\n");
        out.print("Connection: close\r\n");
        out.print("\r\n");
        out.print(element);
        out.flush();
    }
}

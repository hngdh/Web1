package main;

import exceptions.InputError;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Receiver {
    private final String MALFORMED_QUERY_STRING = "Bad Request: malformed query string";

    List<Float> readRequest() throws InputError {
        Properties env = System.getProperties();
        String qs = env.getProperty("QUERY_STRING", "");
        Map<String, String> params = new HashMap();
        if (!qs.isEmpty()) {
            for(String pair : qs.split("&")) {
                if (!pair.isEmpty()) {
                    String[] kv = pair.split("=", 2);
                    if (kv.length != 2) {
                        throw new InputError(MALFORMED_QUERY_STRING);
                    }

                    try {
                        String key = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
                        String val = URLDecoder.decode(kv[1], StandardCharsets.UTF_8);
                        if (key.isEmpty()) {
                            throw new InputError(MALFORMED_QUERY_STRING);
                        }

                        params.put(key, val);
                    } catch (IllegalArgumentException var15) {
                        throw new InputError(MALFORMED_QUERY_STRING);
                    }
                }
            }
        }

        String xStr = params.get("x");
        String yStr = params.get("y");
        String RStr = params.get("R");

        float R;
        float x;
        float y;
        try {
            x = Float.parseFloat(xStr.trim());
            y = Float.parseFloat(yStr.trim());
            R = Float.parseFloat(RStr.trim());
        } catch (Exception var14) {
            throw new InputError(MALFORMED_QUERY_STRING);
        }

        if (!Checker.checkLength(xStr)) {
            throw new InputError("Bad Request: X is too long. Please write only 4 numbers after decimal point.");
        } else if (!Checker.checkLength(yStr)) {
            throw new InputError("Bad Request: Y is too long. Please write only 4 numbers after decimal point.");
        } else if (!Checker.checkLength(RStr)) {
            throw new InputError("Bad Request: Y is too long. Please write only 4 numbers after decimal point.");
        } else {
            return List.of(x, y, R);
        }
    }
}
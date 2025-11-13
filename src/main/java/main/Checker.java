package main;

import exceptions.InputError;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Checker {
    private final List<Float> validX = List.of(-2.0F, -1.5F, -1.0F, -0.5F, 0.0F, 0.5F, 1.0F, 1.5F, 2.0F);
    private final List<Float> validR = List.of(1.0F, 2.0F, 3.0F, 4.0F, 5.0F);

    private boolean checkX(Float X) {
        return this.validX.contains(X);
    }

    private boolean checkY(Float Y) {
        return Y >= -5.0F && Y <= 5.0F;
    }

    private boolean checkR(Float R) {
        return this.validR.contains(R);
    }

    public Result checkFallIn(Float X, Float Y, Float R) throws InputError {
        long start = System.nanoTime();
        boolean hit = this.checkAreas(X, Y, R);
        long end = System.nanoTime();
        String calTime = (end - start) / 1000.0F + " µs";
        return new Result(X, Y, R, hit, calTime, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM HH:mm:ss.SSS")));
    }

    private boolean checkAreas(Float X, Float Y, Float R) throws InputError {
        if (!this.checkX(X)) {
            throw new InputError("Bad Request: undefined X");
        } else if (!this.checkY(Y)) {
            throw new InputError("Bad Request: undefined Y");
        } else if (!this.checkR(R)) {
            throw new InputError("Bad Request: undefined R");
        } else {
            return this.checkSquare(X, Y, R) || this.checkTriangle(X, Y, R) || this.checkCircle(X, Y, R);
        }
    }

    private boolean checkSquare(Float X, Float Y, Float R) {
        return X <= 0.0F && Y <= 0.0F && X >= -R / 2.0F && Y >= -R;
    }

    private boolean checkTriangle(Float X, Float Y, Float R) {
        return X <= 0.0F && Y >= 0.0F && -X + Y <= R / 2.0F;
    }

    private boolean checkCircle(Float X, Float Y, Float R) {
        return X >= 0.0F && Y >= 0.0F && X * X + Y * Y <= R * R / 4.0F;
    }

    public static boolean checkLength(String num) {
        int pos = -1;

        for(int i = 0; i < num.length(); ++i) {
            if (num.charAt(i) == '.') {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            return true;
        } else {
            int cnt = 0;

            for(int i = pos + 1; i < num.length(); ++i) {
                ++cnt;
            }

            return cnt <= 4;
        }
    }
}

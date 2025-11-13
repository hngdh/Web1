package main;

public class Result {
    private Float X;
    private Float Y;
    private Float R;
    private boolean hit;
    private String calTime;
    private String printTime;

    public Result(Float X, Float Y, Float R, boolean hit, String calTime, String curTime) {
        this.X = X;
        this.Y = Y;
        this.R = R;
        this.hit = hit;
        this.calTime = calTime;
        this.printTime = curTime;
    }

    public Float getY() {
        return this.Y;
    }

    public void setY(Float y) {
        this.Y = y;
    }

    public Float getX() {
        return this.X;
    }

    public void setX(Float x) {
        this.X = x;
    }

    public Float getR() {
        return this.R;
    }

    public void setR(Float r) {
        this.R = r;
    }

    public boolean isHit() {
        return this.hit;
    }

    public String getPrintTime() {
        return this.printTime;
    }

    public void setPrintTime(String printTime) {
        this.printTime = printTime;
    }

    public String getCalTime() {
        return this.calTime;
    }

    public void setCalTime(String calTime) {
        this.calTime = calTime;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}

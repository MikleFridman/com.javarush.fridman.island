package utils;

public class Util {
    private static final boolean testMode = true;

    public static void setMsg(String msg) {
        if (testMode) {
            System.out.println(Thread.currentThread() + " " + msg);
        }
    }
}

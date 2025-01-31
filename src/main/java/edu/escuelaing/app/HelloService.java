package edu.escuelaing.app;

public class HelloService {
    public static String process(String path) {
        String name = path.contains("?name=") ? path.substring(path.indexOf("?name=") + 6) : "Unknown";
        return "{\"name\":\"" + name + "\"}";
    }
}
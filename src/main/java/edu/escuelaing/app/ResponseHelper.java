package edu.escuelaing.app;

import java.io.PrintWriter;

public class ResponseHelper {
    public static void sendJsonResponse(PrintWriter out, String response) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: application/json");
        out.println();
        out.println(response);
    }

    public static void sendErrorResponse(PrintWriter out, int statusCode, String message) {
        out.println("HTTP/1.1 " + statusCode + " " + message);
        out.println("Content-Type: text/html");
        out.println();
        out.println("<!DOCTYPE html><html><body><h1>" + statusCode + " " + message + "</h1></body></html>");
    }

    public static String getContentType(String fileName) {
        if (fileName.endsWith(".html")) return "text/html";
        if (fileName.endsWith(".css")) return "text/css";
        if (fileName.endsWith(".js")) return "application/javascript";
        if (fileName.endsWith(".png")) return "image/png";
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) return "image/jpeg";
        return "application/octet-stream";
    }
}
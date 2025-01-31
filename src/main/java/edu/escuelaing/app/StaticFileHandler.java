package edu.escuelaing.app;

import java.io.*;

public class StaticFileHandler {
    private static final String BASE_PATH = "./src/main/resources/static";

    public static void serve(String resourcePath, OutputStream out, PrintWriter writer) {
        File file = new File(BASE_PATH + resourcePath);
        if (file.exists() && file.isFile()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                String contentType = ResponseHelper.getContentType(file.getName());
                out.write(("HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\n\r\n").getBytes());

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                ResponseHelper.sendErrorResponse(writer, 500, "Internal Server Error");
            }
        } else {
            ResponseHelper.sendErrorResponse(writer, 404, "Not Found");
        }
    }
}
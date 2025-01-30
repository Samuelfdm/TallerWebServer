package edu.escuelaing.app;

import java.net.*;
import java.io.*;

public class HttpServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;

        while (running) {
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 OutputStream out = clientSocket.getOutputStream()) {

                System.out.println("Listo para recibir ...");

                String inputLine;
                boolean isFirstLine = true;
                String resourcePath = "";

                while ((inputLine = in.readLine()) != null) {
                    if (isFirstLine) {
                        resourcePath = inputLine.split(" ")[1];
                        isFirstLine = false;
                    }
                    System.out.println("Received: " + inputLine);
                    if (inputLine.isEmpty()) {
                        break;
                    }
                }

                File file = new File("." + resourcePath);

                if (file.exists() && file.isFile()) {
                    try {
                        serveStaticFile(file, out);
                    } catch (IOException e) {
                        System.err.println("Error al servir el archivo: " + e.getMessage());
                        sendErrorResponse(new PrintWriter(out, true), 500, "Internal Server Error");
                    }
                } else if (resourcePath.startsWith("/app/hello")) {
                    String response = helloRestService(resourcePath);
                    sendJsonResponse(new PrintWriter(out, true), response);
                } else {
                    sendErrorResponse(new PrintWriter(out, true), 404, "Not Found");
                }

            } catch (IOException e) {
                System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
            }
        }
        serverSocket.close();
    }

    // Función para servir archivos estáticos como datos binarios
    private static void serveStaticFile(File file, OutputStream out) throws IOException {
        String contentType = getContentType(file.getName());
        out.write(("HTTP/1.1 200 OK\r\n").getBytes());
        out.write(("Content-Type: " + contentType + "\r\n").getBytes());
        out.write(("\r\n").getBytes());

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead); // Escribe los bytes directamente
            }
        }
    }

    // Función para obtener el tipo de contenido
    private static String getContentType(String fileName) {
        if (fileName.endsWith(".html") || fileName.endsWith(".htm")) {
            return "text/html";
        } else if (fileName.endsWith(".css")) {
            return "text/css";
        } else if (fileName.endsWith(".js")) {
            return "text/javascript";
        } else if (fileName.endsWith(".png")) {
            return "image/png";
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else {
            return "application/octet-stream"; // Tipo genérico para otros archivos
        }
    }

    private static void sendJsonResponse(PrintWriter out, String response) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: application/json");
        out.println();
        out.println(response);
    }

    private static void sendErrorResponse(PrintWriter out, int statusCode, String message) {
        out.println("HTTP/1.1 " + statusCode + " " + message);
        out.println("Content-Type: text/html");
        out.println();
        out.println("<!DOCTYPE html><html><body><h1>" + statusCode + " " + message + "</h1></body></html>");
    }

    private static String helloRestService(String path) {
        // Extrae el parámetro 'name' de la URL
        String name = "";
        if (path.contains("?name=")) {
            name = path.substring(path.indexOf("?name=") + 6);
        }
        return "{\"name\":\"" + name + "\"}"; // Devuelve el nombre en formato JSON
    }
}
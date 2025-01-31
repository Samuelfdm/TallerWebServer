package edu.escuelaing.app;

import java.io.*;
import java.net.Socket;

public class RequestHandler {
    private final Socket clientSocket;

    public RequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void handle() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream out = clientSocket.getOutputStream();
             PrintWriter writer = new PrintWriter(out, true)) {

            String requestLine = in.readLine();
            if (requestLine == null) return;

            System.out.println("Received: " + requestLine);
            String resourcePath = requestLine.split(" ")[1];

            if (resourcePath.startsWith("/app/hello")) {
                String response = HelloService.process(resourcePath);
                ResponseHelper.sendJsonResponse(writer, response);
            } else {
                StaticFileHandler.serve(resourcePath, out, writer);
            }
        } catch (IOException e) {
            System.err.println("Error procesando la solicitud: " + e.getMessage());
        }
    }
}
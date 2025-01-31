package edu.escuelaing.app;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.*;
import java.net.Socket;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RequestHandlerTest {

    @Test
    void testHandleHelloRequest() throws IOException {
        Socket mockSocket = mock(Socket.class);
        InputStream mockInputStream = new ByteArrayInputStream("GET /app/hello?name=John HTTP/1.1\n\n".getBytes());
        OutputStream mockOutputStream = new ByteArrayOutputStream();

        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        when(mockSocket.getOutputStream()).thenReturn(mockOutputStream);

        RequestHandler handler = new RequestHandler(mockSocket);
        handler.handle();

        String response = mockOutputStream.toString();
        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.contains("{\"name\":\"John\"}"));
    }

    @Test
    void testHandleStaticFileRequest() throws IOException {
        Socket mockSocket = mock(Socket.class);
        InputStream mockInputStream = new ByteArrayInputStream("GET /index.html HTTP/1.1\n\n".getBytes());
        OutputStream mockOutputStream = new ByteArrayOutputStream();
        PrintWriter mockWriter = new PrintWriter(mockOutputStream, true);

        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        when(mockSocket.getOutputStream()).thenReturn(mockOutputStream);

        RequestHandler handler = new RequestHandler(mockSocket);
        handler.handle();

        assertTrue(mockOutputStream.toString().contains("HTTP/1.1 404 Not Found"));
    }
}

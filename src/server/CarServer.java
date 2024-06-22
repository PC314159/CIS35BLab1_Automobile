package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CarServer {
    public static void main(String[] args) {
        DefaultServerSocket serverSocket = new DefaultServerSocket(4444);
        serverSocket.start();
    }
}

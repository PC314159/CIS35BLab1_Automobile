package client;

import server.DefaultServerSocket;

import java.util.Scanner;

public class ClientApp {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 4444;
    public static void main(String[] args) {
        DefaultSocketClient socketClient = new DefaultSocketClient(SERVER_HOST, SERVER_PORT);
        socketClient.start();
    }
}

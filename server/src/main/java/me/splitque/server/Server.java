package me.splitque.server;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

public class Server {
    private final int PORT;
    private ServerSocketChannel serverSocket;

    public Server(int port) {
        PORT = port;
    }

    public void startServer() {
        try {
            serverSocket = ServerSocketChannel.open();
            serverSocket.configureBlocking(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

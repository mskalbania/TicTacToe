package com.TicTacToe;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final ServerSocket serverSocket;
    private Socket userSocket;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    private void establishConnection() throws IOException {
        userSocket = serverSocket.accept();
        System.out.println("CONNECTED TO " + userSocket.getRemoteSocketAddress());
    }

    public void start() throws IOException {
        System.out.println("SERVER LISTENING ON PORT " + serverSocket.getLocalPort());
        System.out.println("CONNECTING...");
        establishConnection();
    }

    private BufferedReader getInputStream() throws IOException {
        return new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
    }

    private OutputStreamWriter getOutputStream() throws IOException {
        return new OutputStreamWriter(userSocket.getOutputStream());
    }

    public void writeToUser(String string) throws IOException {
        OutputStreamWriter writer = getOutputStream();
        writer.write(string);
        writer.flush();
    }

    public String readFromUser() throws IOException {
        return getInputStream().readLine();
    }

}

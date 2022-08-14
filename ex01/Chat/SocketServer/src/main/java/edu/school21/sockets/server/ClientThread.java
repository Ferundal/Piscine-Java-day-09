package edu.school21.sockets.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {
    Socket threadSocket;

    public ClientThread(Socket threadSocket) {
        this.threadSocket = threadSocket;
    }

    @Override
    public void run() {
        while (true) {
            PrintWriter output;
            BufferedReader input;
            try {
                output = new PrintWriter(threadSocket.getOutputStream(), true);
                input = new BufferedReader(new InputStreamReader(threadSocket.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

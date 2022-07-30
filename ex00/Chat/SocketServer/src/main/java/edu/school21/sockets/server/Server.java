package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.config.SocketsApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final AnnotationConfigApplicationContext context;
    private final ServerSocket serverSocket;

    public Server(int port) {
        this.context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ioException) {
            throw new RuntimeException("Can't create socket at port " + port);
        }
    }

    public void start() {
        UsersService usersService = context.getBean("usersService", UsersService.class);
        Socket clientSocket;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException ioException) {
            throw new RuntimeException("Problems while creating clientSocket");
        }
        BufferedWriter toClient;
        BufferedReader fromClient;
        try {
            toClient = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ioException) {
            throw new RuntimeException("Problems while opening streams with client");
        }
        String login;
        String password;
        try {
            toClient.write("Hello from server!\n");
            toClient.flush();
            login = new String("");
            password = new String("");
            String nextLine;
            boolean isSuccess = false;
            while (!isSuccess) {
                nextLine = fromClient.readLine();
                if (nextLine.equals("signUp")) {
                    toClient.write("Enter username:\n");
                    toClient.flush();
                    do {
                        nextLine = fromClient.readLine();
                    } while (nextLine.isEmpty());
                    login = nextLine;

                    toClient.write("Enter password:\n");
                    toClient.flush();
                    do {
                        nextLine = fromClient.readLine();
                    } while (nextLine.isEmpty());
                    password = nextLine;

                    toClient.write("Successful!\n");
                    toClient.flush();
                    isSuccess = true;
                }
            }
        } catch (IOException ioException) {
            throw new RuntimeException("Connection with client lost");
        }
        usersService.signUp(login, password);

        try {
            if (!clientSocket.isClosed())
                clientSocket.close();
            serverSocket.close();
            context.close();
        } catch (IOException ioException) {
            throw new RuntimeException("Problems while closing connection");
        }
    }
}

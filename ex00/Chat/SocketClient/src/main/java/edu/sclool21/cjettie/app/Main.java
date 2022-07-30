package edu.sclool21.cjettie.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = "--server-port")
    public Integer port;

    public static void main(String[] args) {
        Main game = new Main();
        JCommander.newBuilder()
                .addObject(game)
                .build()
                .parse(args);
        game.run();
    }
    private void run() {
        this.checkArgs();
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", port);
        } catch (IOException ioException) {
            throw new RuntimeException("Can't connect to server");
        }
        BufferedReader fromServer;
        BufferedWriter toServer;
        try {
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            toServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException ioException) {
            throw new RuntimeException("Can't connect to server");
        }
        Scanner console = new Scanner(System.in);
        try {
            String nextLine = fromServer.readLine();
            System.out.println(nextLine);
            while (!nextLine.equals("Successful!")) {
                toServer.write(console.nextLine() + '\n');
                toServer.flush();
                nextLine = fromServer.readLine();
                System.out.println(nextLine);
            }
        } catch (IOException ioException) {
            throw new RuntimeException("Problems during connection with server");
        }
        try {
            socket.close();
            fromServer.close();
            toServer.close();
        } catch (IOException ioException) {
            throw new RuntimeException("Problems during closing connection");
        }
    }

    private boolean checkArgs () {
        if (port == null) {
            throw new RuntimeException("Awaits arguments in format: \"--server-port=[int]\"");
        }
        return true;
    }
}

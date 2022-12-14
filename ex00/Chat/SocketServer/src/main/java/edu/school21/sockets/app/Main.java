package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.server.Server;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = "--port")
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
    }

    private boolean checkArgs () {
        if (port == null) {
            throw new RuntimeException("Awaits arguments in format: \"--port=[int]\"");
        }
        Server server = new Server(port);
        server.start();
        return true;
    }
}

package edu.sclool21.cjettie.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Program {
    @Parameter(names = "--server-port")
    public Integer port;

    public static void main(String[] args) {
        Program game = new Program();
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
            throw new RuntimeException("Awaits arguments in format: \"--server-port=[int]\"");
        }
        return true;
    }
}

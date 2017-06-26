package com.TicTacToe;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        //int port = Integer.parseInt(args[0]);

        try {
            Server server = new Server(8080);
            Game game = new Game();
            TerminalPrinter printer = new TerminalPrinter(game);
            ServerController controller = new ServerController(server, game, printer);
            controller.run();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

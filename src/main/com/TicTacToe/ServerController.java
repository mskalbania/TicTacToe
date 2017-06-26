package com.TicTacToe;

import java.io.*;
import java.util.Scanner;

import static com.TicTacToe.ServerController.State.*;

public class ServerController {

    private final TerminalPrinter printer;
    private final Game game;
    private final Server server;
    private State gameState;
    private Scanner scanner;

    enum State {
        PLAYER_X_MOVE,
        PLAYER_O_MOVE,
        END_OF_GAME,
    }


    public ServerController(Server server, Game game, TerminalPrinter printer) throws IOException {
        this.server = server;
        this.printer = printer;
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    public void run() throws IOException {

        server.start();
        game.initializeNewBoard();
        gameState = PLAYER_X_MOVE;
        while (gameState != END_OF_GAME) {
            switch (gameState) {
                case PLAYER_X_MOVE: {
                    playerXMove();
                    checkForEndOfGame();
                    break;
                }
                case PLAYER_O_MOVE:{
                    playerOMove();
                    checkForEndOfGame();
                    break;
                }
            }
        }
    }

    private void playerOMove() throws IOException {
        printer.informPlayerOMove();
        printer.showWaitingInfo();
        while (true) {
            String serverMoveFeedback = server.readFromUser();
            int field;
            if (serverMoveFeedback.matches("\\d+")) {
                field = Integer.parseInt(serverMoveFeedback);
                if (game.setField(field, "O")) {
                    gameState = PLAYER_X_MOVE;
                    break;
                }
            }
            server.writeToUser("INVALID");
        }
    }

    private void playerXMove() throws IOException {
        printer.showBoard();
        printer.informPlayerXMove();
        if (scanner.hasNextInt()) {
            while (true) {
                int field = scanner.nextInt();
                if (game.setField(field, "X")) {
                    printer.showBoard();
                    server.writeToUser(getBoardAsString());
                    gameState = PLAYER_O_MOVE;
                    break;
                } else {
                    printer.informInvalid();
                }
            }
        }
    }


    private String getBoardAsString() {
        String output = "";
        for (Game.Field field : game.getBoard()) {
            if (field.getContent().equals("X")) {
                output = output.concat("1");
            } else if (field.getContent().equals("O")) {
                output = output.concat("2");
            } else if (field.getContent().equals(" ")) {
                output = output.concat("0");
            }
        }
        return output;
    }

    private void checkForEndOfGame() throws IOException {
        if (checkForDraw() || checkIfAnySideWon()) {
            gameState = END_OF_GAME;
        }
    }

    private boolean checkForDraw() throws IOException {
        if (game.checkForDraw()) {
            printer.informOfDraw();
            server.writeToUser("DRAW");
            return true;
        }
        return false;
    }

    private boolean checkIfAnySideWon() throws IOException {
        String check = game.checkForWinConditions();
        if (check.equals("X")) {
            printer.informPlayerXWin();
            server.writeToUser("WON X");
            return true;
        }
        if (check.equals("O")) {
            printer.informPlayerOWin();
            server.writeToUser("WON O");
            return true;
        }
        return false;
    }
}




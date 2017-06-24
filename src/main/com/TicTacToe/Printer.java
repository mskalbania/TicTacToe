package com.TicTacToe;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.TicTacToe.Game.Field;

public class Printer {

    private Controller controller;

    public Printer() {
        controller = new Controller();
    }

    public void run() {
        System.out.println("\n---TIC TAC TOE---\n");
        printMenu();
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        int action = 0;
        while (!quit) {
            if (sc.hasNextInt()) {
                action = sc.nextInt();
            }
            switch (action) {
                case 1:
                    startGame();
                    printMenu();
                    break;
                case 2:
                    System.out.println("EXITING...");
                    quit = true;
                    break;
                default:
                    System.out.println("\nSELECT DIFFERENT OPTION\nAVAILABLE:");
                    printMenu();
                    sc.next();
            }
        }
    }

    private void printMenu() {
        System.out.println("1.START GAME"
                + "\n2.QUIT");
    }

    private void printBoard() {
        Field[] board = controller.getBoard();
        System.out.println("--BOARD--");
        for (int i = 0; i < 9; i++) {
            System.out.print(board[i].getContent());
            if ((i + 1) % 3 != 0) {
                System.out.print(" | ");
            }
            if (i != 0 && i != 8 && (i + 1) % 3 == 0) {
                System.out.println();
                System.out.println("---------");
            }
        }
        System.out.println();
    }

    private void startGame() {
        printBoard();
        while (true) {
            player1Move();
            if (areWinConditionsReached() || isDrawReached()) {
                break;
            }
            player2Move();
            if (areWinConditionsReached() || isDrawReached()) {
                break;
            }
        }
    }

    private void player1Move() {
        Scanner sc = new Scanner(System.in);
        int field;
        System.out.println("\nPlayer 1 [X] chose your field..");
        do {
            try {
                field = sc.nextInt();
                if (this.controller.putOnBoard(field, "X")) {
                    printBoard();
                    break;
                } else {
                    System.out.println("Wrong field. Again..");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong key. Again...");
                sc.next();
            }
        } while (true);
    }

    private void player2Move() {
        Scanner sc = new Scanner(System.in);
        int field;
        System.out.println("\nPlayer 2 [O] chose your field..");
        do {
            try {
                field = sc.nextInt();
                if (this.controller.putOnBoard(field, "O")) {
                    printBoard();
                    break;
                } else {
                    System.out.println("Wrong field. Again..");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong key. Again...");
                sc.next();
            }
        } while (true);
    }

    private boolean areWinConditionsReached() {
        String winner = this.controller.checkForWinConditions();
        if (winner.equals("X")) {
            System.out.println("\n!!Player 1 wins!!\n");
            return true;
        } else if (winner.equals("O")) {
            System.out.println("\n!!Player 2 wins!!\n");
            return true;
        }
        return false;
    }

    private boolean isDrawReached() {
        if (controller.checkForDraw()) {
            System.out.println("\nDRAW!!");
            return true;
        }
        return false;
    }
}

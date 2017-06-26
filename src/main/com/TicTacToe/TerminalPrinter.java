package com.TicTacToe;

public class TerminalPrinter {

    private Game game;

    public TerminalPrinter(Game game) {
        this.game = game;
    }

    public void showBoard(){
        Game.Field[] board = game.getBoard();
        System.out.println("\n--BOARD--");
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

    public void informPlayerXMove(){
        System.out.println("Player X - chose your field");
    }

    public void showWaitingInfo(){
        System.out.println("Waiting...");
    }

    public void informPlayerOMove(){
        System.out.println("Player O move.");
    }

    public void informPlayerXWin(){
        System.out.println("Player X won.");
    }

    public void informPlayerOWin(){
        System.out.println("Player O won.");
    }

    public void informInvalid(){
        System.out.println("Invalid");
    }

    public void informOfDraw(){
        System.out.println("DRAW!");
    }

}

package com.TicTacToe;

import com.TicTacToe.Game.Field;

public class Controller {

    private final Game game;

    public Controller() {
        this.game = new Game();
    }

    public Field[] getBoard() {
        return game.getBoard();
    }

    public boolean putOnBoard(int position, String field) {
        return game.setField(position, field);
    }

    public String checkForWinConditions(){
        return game.checkForWinConditions();
    }

    public boolean checkForDraw(){
        return game.checkForDraw();
    }

}

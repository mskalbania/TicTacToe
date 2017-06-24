package com.TicTacToe;

public class Game {

    //Board composition
    //1 2 3
    //4 5 6
    //7 8 9

    private Field[] board;

    public Game() {
        initializeNewBoard();
    }

    public Field[] getBoard() {
        return board;
    }

    public boolean setField(int position, String mark) {
        Field tempField = board[position - 1];
        if (tempField.content.equals(" ")) {
            tempField.content = mark;
            return true;
        } else {
            return false;
        }
    }

    public void initializeNewBoard(){
        this.board = new Field[9];
        for (int i = 1; i <= 9; i++) {
            this.board[i - 1] = new Field(i);
        }
    }

    public String checkForWinConditions() {
        //ROW CONDITIONS CHECK
        for (int i = 0; i < 9; i += 3) {
            String currentRow = "";
            for (int j = i; j < i + 3; j++) {
                currentRow = currentRow.concat(this.board[j].getContent());
            }
            if (currentRow.toUpperCase().equals("OOO")) {
                return "O";
            } else if (currentRow.toUpperCase().equals("XXX")) {
                return "X";
            }
        }
        //COLUMN CONDITIONS CHECK
        for (int i = 0; i < 3; i++) {
            String currentColumn = "";
            for (int j = i; j < 9; j += 3) {
                currentColumn = currentColumn.concat(this.board[j].getContent());
            }
            if (currentColumn.toUpperCase().equals("OOO")) {
                return "O";
            } else if (currentColumn.toUpperCase().equals("XXX")) {
                return "X";
            }
        }
        //CROSS CONDITIONS CHECK
        String currentCross = "";
        for (int i = 0; i < 9; i += 4) {
            currentCross = currentCross.concat(this.board[i].content);
        }
        if (currentCross.toUpperCase().equals("OOO")) {
            return "O";
        } else if (currentCross.toUpperCase().equals("XXX")) {
            return "X";
        }
        currentCross = "";
        for(int i = 2; i < 8; i+=2) {
            currentCross = currentCross.concat(this.board[i].content);
        }
        if (currentCross.toUpperCase().equals("OOO")) {
            return "O";
        } else if (currentCross.toUpperCase().equals("XXX")) {
            return "X";
        }
        return "";
    }

    public boolean checkForDraw() {
        for (Field f : this.board) {
            if (f.content.equals(" ")) {
                return false;
            }
        }
        return true;
    }

    class Field {

        private final Integer ID;
        private String content;

        public Field(Integer ID) {
            this.ID = ID;
            this.content = " ";
        }

        public String getContent() {
            return content;
        }

        public Integer getID() {
            return ID;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

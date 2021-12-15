package com.amoba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GameManager {
    public String[][] board;

    public GameManager(int size) {
        board = new String[size][size];

        initBoard(size);
    }


    public String[] GetPlayerMove(Player p) throws IOException {
        System.out.println("[PAYER " + p.icon + "] add meg a lépésed (X,Y) ebben a formában: 0,0");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            String move = reader.readLine();

            String[] coords = move.split(",");
            String xCoord = coords[0];
            String yCoord = coords[1];

            if (board[Integer.parseInt(xCoord)][Integer.parseInt(yCoord)] != " ") {
                throw new Exception("taken");
            }

            board[Integer.parseInt(xCoord)][Integer.parseInt(yCoord)] = p.icon.toString();

            return coords;
        } catch (Exception e) {
            if (e.getMessage() == "taken") {
                System.out.println("Foglalt mező! Kérelek válassz másikat!");
            } else {
                System.out.println("Hibás formátum. (Kérlek pozitív egész számokat adj meg, amik kisebbek, mint " + (board.length - 1));
            }
            GetPlayerMove(p);
        }

        return new String[0];
    }

    public void PrintBoard() {
        System.out.print(" |");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + "|");
        }
        System.out.println("");
        printDivider();
        System.out.println("");

        for (int i = 0; i < board.length; i++) {
            System.out.print(i + "|");

            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + "|");
            }

            System.out.println("");
        }
    }

    public void DetectWin() {
        if (winByPlayer(Main.p1)) {
            System.out.println(Main.p1.icon + " nyert!");
            newGame();
            return;
        }
        if (winByPlayer(Main.p2)) {
            System.out.println(Main.p2.icon + " nyert!");
            newGame();
            return;
        }

        return;
    }

    private boolean winByPlayer(Player p) {
        boolean result = false;
        int win = 3;
        int counter = 0;

        //Row
        for (int row = 0; row < board.length; row++){
          for (int col = 0; col < board[row].length; col++){
              if (board[row][col].equals(String.valueOf(p.icon))) {
                  counter++;
              }
              if (board[row][col] != String.valueOf(p.icon)) {
                      counter = 0;
              }
              if (counter == win) {
                      result = true;
              }
          }
        }

        //Col
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                if (board[col][row].equals(String.valueOf(p.icon))) {
                    counter++;
                }
                if (board[col][row] != String.valueOf(p.icon)) {
                    counter = 0;
                }
                if (counter == win) {
                    result = true;
                }
            }
        }

        //First Diag
        for (int row = 0; row < board.length; row++){
            if (board[row][row].equals(String.valueOf(p.icon))) {
                counter++;
            }
            if (board[row][row] != String.valueOf(p.icon)) {
                counter = 0;
            }
            if (counter == win) {
                result = true;
            }
        }

        //Second Diag
        for (int row = 0; row < board.length; row++){
            if (board[row][board[0].length-1-row].equals(String.valueOf(p.icon))) {
                counter++;
            }
            if (board[row][board[0].length-1-row] != String.valueOf(p.icon)) {
                counter = 0;
            }
            if (counter == win) {
                result = true;
            }
        }

        return result;
    }

    private void newGame () {
            System.out.println("Vége a játéknak, szeretnél új játékot indítani? (y/n)");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));

                // Reading data using readLine
                String input = "";

                while (input != "y" && input != "n") {
                    input = reader.readLine();

                    if (input.toString().toLowerCase().charAt(0) == "y".charAt(0)) {
                        System.out.println("Betöltés...");
                        initBoard(board.length);
                        Main.NewGame();
                    } else if (input.toString().toLowerCase().charAt(0) == "n".charAt(0)) {
                        System.out.println("Köszönjük a játékot!");
                        Main.EndGame();
                    } else {
                        System.out.println("Vége a játéknak, szeretnél új játékot indítani? (y/n)");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }

        private void initBoard ( int size){
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    board[i][j] = " ";
                }
            }
        }

        private void printDivider () {
            for (int i = 0; i < board.length * 2 + 2; i++) {
                System.out.print("-");
            }
        }
}


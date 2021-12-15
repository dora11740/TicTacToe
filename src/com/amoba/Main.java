package com.amoba;

import java.io.IOException;

public class Main {

    /*
        [X] Get user input
        [X] Update table
        [X] Print table data
     */

    public static boolean isPlay = true;
    static Player p1 = new Player(Icon.X,0);
    static Player p2 = new Player(Icon.O,1);
    static GameManager gameManager = new GameManager(10);

    public static void main(String[] args) throws IOException {
        NewGame();
    }

    public static void EndGame(){
        isPlay = false;
        System.exit(0);
    }
    public static void NewGame() throws IOException {
        isPlay = true;
        gameManager.PrintBoard();

        while(isPlay){
            gameManager.GetPlayerMove(p1);
            gameManager.PrintBoard();
            gameManager.GetPlayerMove(p2);
            gameManager.PrintBoard();
            gameManager.DetectWin();
        }
    }
}

import za.nmu.mandela.qwirkle_game.Model.GameState;

import java.util.Scanner;

public class Main {
    GameState gameState;
    Scanner in;
    public static void main(String[] args) {

        new Main();
    }
    public Main(){
        System.out.println("\n\n\tWelcome to the za.nmu.mandela.qwirkle_game_client.Controller.Client.Client.Game of Qwirkle!!\n " +
                "\tHow many players shall take part today?\n" +
                "\t Please Select:\n" +
                "\t 2 players > [PRESS 2]\n" +
                "\t 3 players > [PRESS 3]\n" +
                "\t 4 players > [PRESS 4]\n");

        String choice = "";
        in = new Scanner(System.in);
        do{
            choice = in.nextLine();
            switch(choice){
                case "2":
                    gameState= new GameState(2);

                    break;
                case"3":
                    gameState= new GameState(3);
                    break;
                case "4":
                    gameState= new GameState(4);
                    break;
                default:
                        System.out.println("Invalid entry");
                        break;
            }
        }while(choice!="2"||choice!="3"||choice!="4");
    }
}

package za.nmu.mandela.qwirkle_game.Model.Player;

import za.nmu.mandela.qwirkle_game.Model.GameState;
import za.nmu.mandela.qwirkle_game.Model.Rules.QwirkleRules;
import za.nmu.mandela.qwirkle_game.Model.Tile.QwirkleTile;

import java.util.ArrayList;
import java.util.Scanner;

import static za.nmu.mandela.qwirkle_game.Model.Constant.CONST.BOARD_HEIGHT;
import static za.nmu.mandela.qwirkle_game.Model.Constant.CONST.BOARD_WIDTH;

/**za.nmu.mandela.qwirkle_game_client.Model.Player.Player
 * This class will contain all the necessary information for the za.nmu.mandela.qwirkle_game_client.Model.Player.Player**/
public class Player {
    /**The game state of the current game**/
    GameState gameState;
    /**The Qwirkle tiles in the players hand*/
   ArrayList<QwirkleTile> hand = new ArrayList<>();
    /**The ID for the player**/
    int ID;
/**The Qwirkle rules */
    QwirkleRules rules;
    /**An array of tiles that were played*/
    ArrayList<QwirkleTile> playedTiles = new ArrayList<>();

    /**Score*/
    int score;
    public Player(GameState gameState,int ID) {
        this.gameState=gameState;
        this.ID=ID;
        rules = gameState.getRules();
    }
    public void placeTile(QwirkleTile tileToBePlaced, int x, int y){
        //If it is this player's turn.
        if(!gameState.getCurrentPlayer().equals(this))
        {//It is not this players turn
            System.out.printf("It is not player #%s's turn yet",ID);
            return;
        }


        if(gameState.getGameBoard().countTilesInPlay()==0)
        {x=BOARD_WIDTH/2;
            y=BOARD_HEIGHT/2;}

          if(playedTiles.size()>0)//A za.nmu.mandela.qwirkle_game_client.Model.tile has been played already
              //check that there the za.nmu.mandela.qwirkle_game_client.Model.tile to be placed is in the same row or column as the first played za.nmu.mandela.qwirkle_game_client.Model.tile
          {
              if(playedTiles.get(0).getxPos()==x||playedTiles.get(0).getyPos()==y)
              {
                  if(rules.isValidMove(x,y,tileToBePlaced,gameState.getGameBoard().getBoardgame()))
                  {
                      gameState.placeTile(tileToBePlaced, x, y);
                  playedTiles.add(tileToBePlaced);
                  }
                  else{
                      System.out.println("This is an invalid move");
                  }
              }
              else{
                  System.out.println("This is an invalid move");
              }
          }
          //The first za.nmu.mandela.qwirkle_game_client.Model.tile is being played for this current turn
        if(rules.isValidMove(x,y,tileToBePlaced,gameState.getGameBoard().getBoardgame()))
        {
            gameState.placeTile(tileToBePlaced, x, y);
            playedTiles.add(tileToBePlaced);
        }
        else{
            System.out.println("This is an invalid move");
        }
    }
    /**Accept the move and send it to the Server*/
    public void accept()
    {
        //Send the array of played tiles to the game state and calculate the points
        gameState.getPoints(playedTiles);
    }

    @Override
    public boolean equals(Object obj){
       if(obj.getClass()==this.getClass())
       {
           Player player= (Player)obj;
           if(player.ID==this.ID)
           {return true;}
       }return false;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public void yourTurn() {
        System.out.println("\nIt is now player " + ID + "'s turn.");
        String choice = "";
        while (!choice.equals("1")&&!choice.equals("2")&&!choice.equals("3")) {
            System.out.println("\nOptions:\n" +
                    "\t 1> Place a tile\n" +
                    "\t 2> skip\n" +
                    "\t 3> draw");
            Scanner scan = new Scanner(System.in);
            choice = scan.nextLine();

            switch (choice) {
                case "1": {
                    System.out.println("Indicate which tile you would like to play");
                    //placeTIle()
                    //accept()
                    break;
                }
                case "2":{
                    System.out.println("Your turn is skipped");
                    break;
            }
                case "3": {
                    System.out.println("You have Drawn a tile");
                    break;
                }
                default:
                    System.out.printf("Invalid entry");
                    break;
            }
        }


    }
}

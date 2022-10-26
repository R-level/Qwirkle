package Model.Player;

import Controller.Game.GameState;
import Model.Rules.QwirkleRules;
import Model.Tile.QwirkleTile;

import java.util.ArrayList;
import java.util.Scanner;

import static ui.Constant.BOARD_HEIGHT;
import static ui.Constant.BOARD_WIDTH;

/**Model.Player.Player
 * This class will contain all the necessary information for the Model.Player.Player**/
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

          if(playedTiles.size()>0)//A Model.tile has been played already
              //check that there the Model.tile to be placed is in the same row or column as the first played Model.tile
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
          //The first Model.tile is being played for this current turn
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
    public void Accept()
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

    public void yourTurn(){
        System.out.println("Options:\n" +
                "\t 1> Place a tile\n" +
                "\t 2> skip\n" +
                "\t 3> draw");
        Scanner scan = new Scanner(System.in);
        int choice =
    }
}

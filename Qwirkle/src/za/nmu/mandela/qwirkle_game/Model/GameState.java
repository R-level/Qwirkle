package za.nmu.mandela.qwirkle_game.Model;

import za.nmu.mandela.qwirkle_game.Model.GameBoard;
import za.nmu.mandela.qwirkle_game.Model.Player.Player;
import za.nmu.mandela.qwirkle_game.Model.Rules.QwirkleRules;
import za.nmu.mandela.qwirkle_game.Model.Tile.QwirkleTile;

import java.util.ArrayList;
import java.util.List;

/**The game state class will contain all information for the game**/
public class GameState {
  QwirkleRules rules;
  GameBoard gameBoard;
  Player currentPlayer;
  int noPlayers;

List<Player> players;
  boolean gameOver;
    public GameState(int noPlayers){
this.noPlayers = noPlayers;
gameBoard = new GameBoard();
rules = new QwirkleRules();
gameOver=false;

players = new ArrayList<>();
init();
    }
    //todo Need to keep the game going and changing players while game is not over.
    //Add the players to the game
    public void init(){
        for(int i =0;i<noPlayers;i++){
            players.add(new Player(this,i+1));

        }
        setCurrentPlayer(players.get(0));
while(!gameOver){
    //iterate through players.

    //Player 1 starts the game
            currentPlayer.yourTurn();
    //a turn ends after the player presses the Accept button
    //The next player gets a turn
    changeTurn();
}

        gameBoard.display();
    }

    /**Change the turn of the player*/
    public void changeTurn(){
       int currentIndex = players.indexOf(currentPlayer);
       if((currentIndex+1)==players.size())
       {
           setCurrentPlayer(players.get(0));
       }
       else{
           setCurrentPlayer(players.get(currentIndex+1));
       }
    }
    /**The current player is set*/
    public void setCurrentPlayer(Player player){
currentPlayer=player;
    }

    /**Get the current za.nmu.mandela.qwirkle_game_client.Model.Player.Player*/
public Player getCurrentPlayer(){
    return currentPlayer;
}

/**Get the rules of the Qwirkle game*/
    public QwirkleRules getRules() {
        return rules;
    }

    /**getGameBoard will return the game board  **/
    public GameBoard getGameBoard() {
        return gameBoard;
    }



    /**Place the tile in the gameboard */
    public void placeTile(QwirkleTile tileToBePlaced, int x, int y) {
        gameBoard.placeTile(tileToBePlaced,x,y);
        update();
    }
    /**getPoints is called when player Accepts the tiles being placed */
    public void getPoints(ArrayList<QwirkleTile> playedTiles){
int points = rules.getPoints(playedTiles);
//update the scoreboard
        getCurrentPlayer().setScore(points);
    }
    /**update the gamestate by sending a gamestate object to the clients*/
   private void update()
   {
       for(int i = 0; i< players.size();i++)
       {
           //Update by sending all players the updated game state
       }
   }


}

package za.nmu.mandela.qwirkle_game.Model;

import za.nmu.mandela.qwirkle_game.Model.Tile.QwirkleTile;

import static za.nmu.mandela.qwirkle_game.Model.Constant.CONST.BOARD_HEIGHT;
import static za.nmu.mandela.qwirkle_game.Model.Constant.CONST.BOARD_WIDTH;

/**
 * GameBoard will be used to hold the game pieces and do the validation using the QwirkleRules     **/
public class GameBoard {
    QwirkleTile[][] boardgame;

    public GameBoard(){
        boardgame = new QwirkleTile[BOARD_WIDTH][BOARD_HEIGHT];

    }

    public void placeTile(QwirkleTile tile,int x,int y)
    {
        boardgame[x][y]=tile;

    }

    public QwirkleTile[][] getBoardgame() {
        return boardgame;
    }
public int countTilesInPlay() {
    int count = 0;

    for (int i = 0; i < BOARD_HEIGHT; i++) {
        for (int j = 0; j < BOARD_WIDTH; j++) {
            if (boardgame[i][j] != null) {
                count++;
            }
        }}
        return count;
    }

    public void display(){
        for(int i = 0;i<BOARD_HEIGHT;i++){
            for(int j = 0; j<BOARD_WIDTH;j++){
               if(boardgame[i][j]==null)
                   System.out.print("[ ] ");
                else
                {System.out.print("["+boardgame[i][j].toString()+"] ");}
        }
            System.out.println("\n");
        }
    }
}

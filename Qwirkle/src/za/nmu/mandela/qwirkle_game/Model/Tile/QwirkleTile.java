package za.nmu.mandela.qwirkle_game.Model.Tile;

import java.util.ArrayList;

public class QwirkleTile {
    private int xPos; //x position of the za.nmu.mandela.qwirkle_game_client.Model.tile
    private int yPos; //z position of the za.nmu.mandela.qwirkle_game_client.Model.tile
    private QwirkleShape qwirkleShape; //Shape group of the za.nmu.mandela.qwirkle_game_client.Model.tile
    private QwirkleColour qwirkleColour; //Colour group of the za.nmu.mandela.qwirkle_game_client.Model.tile
    //QwirkleTile Arrays that hold the references to the tiles in the same column and row
    //Used only during point checking
    public ArrayList<QwirkleTile>lineNS = new ArrayList<>();
    public ArrayList<QwirkleTile> lineEW = new ArrayList<>();

    public boolean freshlyPlaced = false;
    public boolean checkNS = false;
    public boolean checkEW = false;

    //Booleans to tell if tiles are on the main board and if selected
    private boolean mainBoard;
    private boolean isSelected;

    /**
     * Constructor: QwirkleTile
     * Use to create a new Tile from scratch
     *
     * @param shape  QwirkleTile shape for bitmap
     * @param colour QwirkleColour for bitmap
     */


    public QwirkleTile(QwirkleShape shape, QwirkleColour colour) {
        qwirkleColour = colour;
        qwirkleShape = shape;

    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public QwirkleShape getQwirkleShape() {
        return qwirkleShape;
    }


    public QwirkleColour getQwirkleColour() {
        return qwirkleColour;
    }


    public boolean isMainBoard() {
        return mainBoard;
    }

    public void setMainBoard(boolean mainBoard) {
        this.mainBoard = mainBoard;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * Set the line pointers to null
     */
    public void setLinesToNull() {
        lineEW = null;
        lineNS = null;
    }

    @Override
    public String toString() {
        return getQwirkleColour().toString().toUpperCase().charAt(0) + " " + getQwirkleShape().toString().toUpperCase().substring(0, 2);
    }
}

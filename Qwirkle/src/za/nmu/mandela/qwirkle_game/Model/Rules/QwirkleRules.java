package za.nmu.mandela.qwirkle_game.Model.Rules;

import za.nmu.mandela.qwirkle_game.Model.Tile.QwirkleColour;
import za.nmu.mandela.qwirkle_game.Model.Tile.QwirkleShape;
import za.nmu.mandela.qwirkle_game.Model.Tile.QwirkleTile;


import java.awt.*;
import java.util.ArrayList;

import static za.nmu.mandela.qwirkle_game.Model.Constant.CONST.BOARD_HEIGHT;
import static za.nmu.mandela.qwirkle_game.Model.Constant.CONST.BOARD_WIDTH;

public class QwirkleRules {
    ArrayList<QwirkleTile> lineNS; //the line north and south.
    ArrayList<QwirkleTile> lineEW; //the line east and west.


    /**
     * Enum Direction
     * Represents North, South, East or West direction.
     **/

    private enum Direction {
        NORTH(0, -1),
        EAST(1, 0),
        SOUTH(0, 1),
        WEST(-1, 0);

        private int x; //The x position in the direction
        private int y; //The y position in the direction


        Direction(int x, int y) {
            this.x = x;
            this.y = y;

        }

        /**
         * Method: getX
         *
         * @return The x value to move by (0, 1, or -1)
         **/
        public int getX() {
            return x;
        }

        /**
         * Method: getY
         *
         * @return The y value to move by (0, 1, or -1)
         **/
        public int getY() {
            return y;
        }
    }

    /**
     * Method: createLine
     * Create the line of tiles.
     *
     * @param x     The x position to start at.
     * @param y     The y position to start at.
     * @param line  The line to create (ArrayList of tiles).
     * @param dir   The direction to go (enum).
     * @param board The board of tiles.
     */
    private void createLine(int x, int y, ArrayList<QwirkleTile> line,
                            Direction dir, QwirkleTile[][] board) {
        // Increment x & y by direction first.
        x += dir.getX();
        y += dir.getY();

        // To prevent array out of bounds.

        if (!(x >= 0 && y >= 0 && x < BOARD_WIDTH && y < BOARD_HEIGHT)) {
            return;
        }

        while (board[x][y] != null) {
            //Add za.nmu.mandela.qwirkle_game_client.Model.tile to the line
            line.add(board[x][y]);
            if (dir == Direction.EAST || dir == Direction.WEST)
                board[x][y].checkEW = false;
            if (dir == Direction.NORTH || dir == Direction.SOUTH)
                board[x][y].checkNS = false;
            // Increment by the direction in x & y.
            x += dir.getX();
            y += dir.getY();

            // To prevent array out of bounds.
            if (!(x >= 0 && y >= 0 && x < BOARD_WIDTH && y < BOARD_HEIGHT)) {
                return;
            }
        }
    }

    /**
     * Method: isSameShape
     * Determines if the tiles in the line should have the same shape or not.
     *
     * @param line The ArrayList of tiles in the line.
     * @return True (should be same animal), otherwise false.
     */
    private boolean isSameShape(ArrayList<QwirkleTile> line) {
        // This function assumes that there are at least two tiles in the line
        // (otherwise must not be called, as it will throw exception)
        QwirkleTile tile1 = line.get(0);
        QwirkleTile tile2 = line.get(1);

        return tile1.getQwirkleShape().equals(tile2.getQwirkleShape());
    }

    /**
     * Method: isValidLine
     * Determines if a given line of tiles is valid
     *
     * @param line        The ArrayList of tiles that represents the line.
     * @param isSameShape Whether they should all have the same animal.
     * @return True (line is valid), otherwise false.
     */
    private boolean isValidLine(ArrayList<QwirkleTile> line,
                                boolean isSameShape) {
        /*
        External Citation
        Date: 11 April 2018
        Problem: Could not get the index of something in an enum.
        Resource:
        https://stackoverflow.com/questions/14769655/
        use-of-ordinal-inside-java-enum-definition
        Solution: Used the ordinal feature of enums.
        */

        /**Is same shape will take the first 2 elements in the  line and compare
         * their shapes to see if they are equal.
         * If this is true See ROUTE 1...
         * If this is false See ROUTE 2
         *
         * ROUTE 1
         * Get the type of shape of the line.#
         * Instantiate an array of type boolean for a maximum of 6
         *    This array will serve to check if there is indeed a
         *    line containing different colors.#
         * Next we need to iterate through the line of QwirkleTiles
         * extract the color and mark true in the boolean array index
         * that matches the ordinal position of the enum color
         *
         * ROUTE 2
         * The line is not the same shape so make sure that there are
         * different shapes but the same color using the same process as above
         * **/

        if (isSameShape) {
            QwirkleShape animal1 = line.get(0).getQwirkleShape();
            boolean[] differentColors = new boolean[6];
            for (QwirkleTile tile : line) {
                if (!tile.getQwirkleShape().equals(animal1)) {
                    return false;
                }

                QwirkleColour color = tile.getQwirkleColour();
                if (differentColors[color.ordinal()]) {
                    return false;
                } else {
                    differentColors[color.ordinal()] = true;
                }
            }
        } else {
            QwirkleColour color1 = line.get(0).getQwirkleColour();
            boolean[] differentShapes = new boolean[6];
            for (QwirkleTile tile : line) {
                if (!tile.getQwirkleColour().equals(color1)) {
                    return false;
                }
                QwirkleShape shape = tile.getQwirkleShape();
                if (differentShapes[shape.ordinal()]) {
                    return false;
                } else {
                    differentShapes[shape.ordinal()] = true;
                }
            }
        }

        return true;
    }

    /**
     * Method: isValidMove
     * When the user is placing a piece on the board,
     * check to see if that move is valid
     *
     * @param x     x position of za.nmu.mandela.qwirkle_game_client.Model.tile being put
     * @param y     y position of za.nmu.mandela.qwirkle_game_client.Model.tile being put
     * @param tile  za.nmu.mandela.qwirkle_game_client.Model.tile being put
     * @param board board to be put
     * @return return true if move is valid
     */
    public boolean isValidMove(int x, int y, QwirkleTile tile,
                               QwirkleTile[][] board) {
        // Step 1: Check to see that x,y is empty spot on board
        if (board[x][y] != null) return false;

        // Step 2: Create lineEW & lineNS. Add za.nmu.mandela.qwirkle_game_client.Model.tile to each line.
        this.lineNS = new ArrayList<>();
        lineNS.add(tile);
        this.lineEW = new ArrayList<>();
        lineEW.add(tile);

        // Step 3: If entire board is empty, return true (any placement ok)
        boolean empty = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    empty = false;
                }
            }
        }
        // Require that the first za.nmu.mandela.qwirkle_game_client.Model.tile be placed in the center of the board.
        if (empty) {
            return x == board.length / 2 && y == board[0].length / 2;
        }

        // Step 4: Find the tiles in line that za.nmu.mandela.qwirkle_game_client.Model.tile is being added to.
        createLine(x, y, lineNS, Direction.NORTH, board);
        createLine(x, y, lineNS, Direction.SOUTH, board);
        createLine(x, y, lineEW, Direction.EAST, board);
        createLine(x, y, lineEW, Direction.WEST, board);

        tile.lineEW = lineEW;
        tile.lineNS = lineNS;
        // Step 5: verify lines are valid
        if (lineNS.size() == 1 && lineEW.size() == 1) {
            //
            return false;
        }
        if (lineNS.size() > 1) {
            boolean sameAnimalNS = isSameShape(lineNS);
            if (!isValidLine(lineNS, sameAnimalNS)) {
                return false;
            }
        }
        if (lineEW.size() > 1) {
            boolean sameAnimalEW = isSameShape(lineEW);
            if (!isValidLine(lineEW, sameAnimalEW)) {
                return false;
            }
        }

        // If all checks passed, then it must be a valid move.
        return true;
    }

    /**
     * Method: getPoints
     * Gets how many points are being earned by placing a za.nmu.mandela.qwirkle_game_client.Model.tile.
     * Assumes that isValidMove is run first.
     * Called after Accept is clicked
     *
     * @param playedTiles array of played tiles during the turn
     * @return The amount of points as an integer.
     */
    public int getPoints(ArrayList<QwirkleTile> playedTiles) {
        // Starts off with 0 points
        int points = 0;
        //iterate through the played tiles
        for (QwirkleTile tile : playedTiles) {
            //each played za.nmu.mandela.qwirkle_game_client.Model.tile will have an East to West line
            // and a North to South line

            // Only applies to the first move.
            if (tile.lineNS.size() == 1 && tile.lineEW.size() == 1) points += 1;

            // Give points for number of tiles in the line.
            if (tile.lineNS.size() > 1) {
                for (QwirkleTile t : tile.lineNS) {
                    //check if za.nmu.mandela.qwirkle_game_client.Model.tile has been checked
                    //if the check is false it has not been counted
                    if (!t.checkNS) {
                        points = points + 1;
                        t.checkNS = true;

                    }

                }

                if (lineEW.size() > 1) {
                    for (QwirkleTile t2 : tile.lineEW) {
                        //if the check is false it has not been counted
                        if (!t2.checkEW) {
                            points += 1;
                            t2.checkEW = true;
                        }
                    }

                }

            }


            // 6 points for a Qwirkle
            if (lineNS.size() == 6) points += 6;
            if (lineEW.size() == 6) points += 6;
        }

            return points;

    }
        /**
         * Method: getLegalMoves
         * Returns the legal moves for a given za.nmu.mandela.qwirkle_game_client.Model.tile as an ArrayList of Points.
         *
         * @param tile  The za.nmu.mandela.qwirkle_game_client.Model.tile for the move.
         * @param board The Qwirkle board.
         * @return The ArrayList of all the possible legal moves.
         */
        public ArrayList<Point> getLegalMoves (QwirkleTile tile,
                QwirkleTile[][]board){
            ArrayList<Point> legalMoves = new ArrayList<>();
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[x].length; y++) {
                    if (isValidMove(x, y, tile, board)) {
                        legalMoves.add(new Point(x, y));
                    }
                }
            }
            return legalMoves;
        }

        /**
         * Method: validMovesExist
         * Check to see whether there are valid moves on the board for given player.
         *
         * @param playerHand The player hand to check.
         * @param board      The board.
         * @return true if there are valid moves on the board for the player.
         */
        public boolean validMovesExist (QwirkleTile[]playerHand,
                QwirkleTile[][]board){
            for (QwirkleTile tileInHand : playerHand) {
                if (tileInHand == null) continue;
                for (int x = 0; x < board.length; x++) {
                    for (int y = 0; y < board[x].length; y++) {
                        if (isValidMove(x, y, tileInHand, board)) {
                            return true;
                        }
                    }
                }
            }

            // return false if there are no more valid moves.
            return false;
        }

        /**
         * Method: getNumQwirkles
         *
         * @return the amount of Qwirkles as an integer.
         */
        public int getNumQwirkles () {
            if (this.lineNS.size() == 6 && this.lineEW.size() == 6) return 2;
            if (this.lineNS.size() == 6) return 1;
            if (this.lineEW.size() == 6) return 1;
            return 0;
        }
    }

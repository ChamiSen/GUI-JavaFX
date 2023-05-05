package senarath_chami.river;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class RiverSim implements PropertyChangeListener {
    private int col, row;
    private int timeReset;
    private int funds;
    private int numOfFilledTiles;
    ArrayList<ArrayList<Tile>> tile2D;

    /**
     * Constructor for RiverSim
     */
    public RiverSim (int col, int row) {
        this.col = col;
        this.row = row;
        timeReset = 0;
        funds = 0;
        numOfFilledTiles = 0;
        this.tile2D = new ArrayList<ArrayList<Tile>>();
        generateRiverGrid();
    }

    /**
     * This function creates a 2D array of tiles
     */
    private void generateRiverGrid() {
        for (int j = 0; j < col; j++)
        {
            ArrayList<Tile> rowTiles = new ArrayList<Tile>();
            for (int i = 0; i < row; i++)
            {
                rowTiles.add(new Tile());
            }
            this.tile2D.add(rowTiles);
        }
    }

    /**
     * The function takes in a column, row, and a string. It then sets the tile at the given column and row to the given
     * string
     *
     * @param col the column of the tile
     * @param row the row of the tile
     * @param selected The type of land area that the user wants to set the tile to.
     */
    public void setTile(int col, int row, String selected) {
        LandArea landArea = switch (selected){
            case "Agriculture" -> {
                funds -= 300;
                yield new Agriculture(timeReset);
            }
            case "Recreation" -> {
                funds -= 10;
                yield new Recreation(timeReset);
            }
            case "Flooded" -> new Flooded();
            default -> new Unused(timeReset);
        };

        this.tile2D.get(col).get(row).setLandType(landArea);
        this.tile2D.get(col).get(row).resizeTiles();
    }

    /**
     * This function returns the tile at the specified column and row.
     *
     * @param col The column of the tile you want to get.
     * @param row The row of the tile you want to get.
     * @return The tile at the specified column and row.
     */
    public Tile getTile(int col, int row) {
        return this.tile2D.get(col).get(row);
    }

    /**
     * This function returns the timeReset variable
     *
     * @return The timeReset variable is being returned.
     */
    public int getTime() {
        return timeReset;
    }

    /**
     * This function returns the amount of funds the player has
     *
     * @return The funds variable is being returned.
     */
    public int getFunds() {
        return funds;
    }

    /**
     * This function counts the number of filled tiles in the board
     *
     * @return The number of filled tiles in the board.
     */
    public int getNumOfFilledTiles() {
        numOfFilledTiles = 0;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (y != col/2)
                    if (!this.tile2D.get(y).get(x).getName().equals("Unused"))
                        numOfFilledTiles++;
            }
        }
        return numOfFilledTiles;
    }

    /**
     * This function returns the column of the current position.
     *
     * @return The column number of the cell.
     */
    public int getCol() {
        return col;
    }

    /**
     * This function returns the row of the current cell.
     *
     * @return The row of the cell.
     */
    public int getRow() {
        return row;
    }

    /**
     * The function iterates through the 2D array of tiles and calls the nextMonth() function of each tile
     */
    public void nextMonth() {
        timeReset++;
        for (int a = 0; a < row; a++) {
            for (int b = 0; b < col; b++) {
                int month = timeReset % 12;
                if (month == 2 && (b == (col/2) - 1 || b == (col/2) + 1)) {
                    setTile(b, a, "Flooded");
                } else if (month == 3 && (b == (col/2) - 1 || b == (col/2) + 1)) {
                    setTile(b, a, "Unused");
                } else if (b != col/2) {
                    funds += this.tile2D.get(b).get(a).nextMonth();
                }
            }
        }
    }

    /**
     * > This function is called whenever a property of the object is changed
     *
     * @param evt The event that was fired.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
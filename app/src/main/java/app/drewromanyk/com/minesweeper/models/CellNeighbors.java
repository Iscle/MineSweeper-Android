package app.drewromanyk.com.minesweeper.models;

/**
 * Created by Drew on 12/14/2014.
 * CellNeighbors
 * Class used to calculate the number of mines and number of flags near a cell
 */
class CellNeighbors {

    private Cell[][] neighboringCells;
    private int rows;
    private int columns;
    private int numMines;
    private int numFlags;


    CellNeighbors(Cell[][] cell, Cell tgtCell) {
        rows = cell.length;
        columns = cell[0].length;
        neighboringCells = new Cell[3][3];

        numMines = 0;
        numFlags = 0;

        findNeighboringCells(cell, tgtCell);
    }

    int getNumMines() {
        return numMines;
    }

    void setNumFlags(int numFlags) {
        this.numFlags = numFlags;
    }

    int getNumFlags() {
        return numFlags;
    }

    //sets the valid neighboring cells to the class
    private void findNeighboringCells(Cell[][] cell, Cell tgtCell) {
        for (int r = tgtCell.getRow() - 1; r <= tgtCell.getRow() + 1; r++) {
            for (int c = tgtCell.getColumn() - 1; c <= tgtCell.getColumn() + 1; c++) {
                if (inbounds(r, c) && !isCell(tgtCell, r, c)) {
                    neighboringCells[r - (tgtCell.getRow() - 1)][c - (tgtCell.getColumn() - 1)] = cell[r][c];
                    if (cell[r][c].isMine()) {
                        numMines++;
                    } else if (cell[r][c].isFlagged()) {
                        numFlags++;
                    }
                }
            }
        }
    }

    //checks if the cell being called is inbounds
    private boolean inbounds(int row, int column) {
        return (0 <= row && row < rows && 0 <= column && column < columns);
    }

    private boolean isCell(Cell cell, int row, int col) {
        return cell.getRow() == row && cell.getColumn() == col;
    }
}

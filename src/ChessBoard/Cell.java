package ChessBoard;


import Figures.AbstractFigure;

public class Cell {
    private boolean Occupied = false;
    private int CellSize;
    private int[] REAL_COORDINATES = new int[2];
    AbstractFigure Occupation;

    Cell(int x, int y, int Size) {
        this.REAL_COORDINATES[0] = x;
        this.REAL_COORDINATES[1] = y;
        this.CellSize = Size;
    }

    public AbstractFigure getOccupation() {
        return Occupation;
    }

    public void setOccupation(AbstractFigure occupation) {
        Occupation = occupation;
    }

    public void setOccupied(boolean occupied) {
        Occupied = occupied;
    }
    public boolean getOccupied(boolean occupied) {
        return Occupied;
    }

    public int getCellSize() {
        return CellSize;
    }


    public int[] getREAL_COORDINATES() {
        return REAL_COORDINATES;
    }


}

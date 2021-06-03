package ChessBoard;


import Figures.AbstractFigure;

public class Cell {
    private String BoardLoc;
    private boolean Occupied = false;
    private int CellSize;
    private int[] REAL_COORDINATES = new int[2];
    AbstractFigure Occupation;

    Cell(int x, int y, int Size, String BoardLoc) {
        this.REAL_COORDINATES[0] = x;
        this.REAL_COORDINATES[1] = y;
        this.CellSize = Size;
        this.BoardLoc = BoardLoc;
    }

    public String getBoardLoc() {
        return BoardLoc;
    }


    public AbstractFigure getOccupation() {
        return Occupation;
    }

    public void setOccupation(AbstractFigure occupation) {
        setOccupied(true);
        Occupation = occupation;
    }

    public void setOccupied(boolean occupied) {
        if (!occupied) setOccupation(null);
        Occupied = occupied;
    }

    public boolean getOccupied() {
        return Occupied;
    }

    public int getCellSize() {
        return CellSize;
    }


    public int[] getREAL_COORDINATES() {
        return REAL_COORDINATES;
    }


}

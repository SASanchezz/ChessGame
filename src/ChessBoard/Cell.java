package ChessBoard;


import Figures.AbstractFigure;

public class Cell {
    private String BoardLoc;
    private String OccupiedBy = null;
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
        if (occupation == null) {

        } else setOccupiedBy(occupation.getColor());

        Occupation = occupation;
    }

    public void setOccupiedBy(String occupiedBy) {
        if (occupiedBy == null) setOccupation(null);
        this.OccupiedBy = occupiedBy;
    }

    public String getOccupiedBy() {
        return OccupiedBy;
    }

    public int getCellSize() {
        return CellSize;
    }


    public int[] getREAL_COORDINATES() {
        return REAL_COORDINATES;
    }


}

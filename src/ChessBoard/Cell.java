package ChessBoard;


import Figures.AbstractFigure;

public class Cell {
    private String BoardLoc;
    private Boolean OccupiedColor;
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
        if (BoardLoc == null) return "no";
        return BoardLoc;
    }


    public AbstractFigure getOccupation() {
        return Occupation;
    }

    public void setOccupation(AbstractFigure occupation) {
        if (occupation == null) {setOccupiedColor(null);}
        else setOccupiedColor(occupation.getColor());
        Occupation = occupation;
    }

    public void setOccupiedColor(Boolean OccupiedColor) {
        this.OccupiedColor = OccupiedColor;
    }

    public Boolean getOccupiedColor() {
        return OccupiedColor;
    }

    public int getCellSize() {
        return CellSize;
    }


    public int[] getREAL_COORDINATES() {
        return REAL_COORDINATES;
    }


    @Override
    public String toString() {
        return BoardLoc;
    }
}

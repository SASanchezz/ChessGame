package ChessBoard;


public class Cell {
    private boolean Occupied = false;
    private int CellSize;
    private int[] REAL_COORDINATES = new int[2];

    Cell(int x, int y, int Size) {
        this.REAL_COORDINATES[0] = x;
        this.REAL_COORDINATES[1] = y;
        this.CellSize = Size;
    }

    public boolean isOccupied() {
        return Occupied;
    }

    public void setOccupied(boolean occupied) {
        Occupied = occupied;
    }

    public int getCellSize() {
        return CellSize;
    }

    public void setCellSize(int cellSize) {
        CellSize = cellSize;
    }

    public int[] getREAL_COORDINATES() {
        return REAL_COORDINATES;
    }

    public void setREAL_COORDINATES(int[] REAL_COORDINATES) {
        this.REAL_COORDINATES = REAL_COORDINATES;
    }


}

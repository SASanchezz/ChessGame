package ChessBoard;

import ChessBoard.Cell;

public abstract class AbstractFigure {
    Cell StartingCell;
    Cell ActualCell;

    AbstractFigure(Cell StartingCell) {
        this.StartingCell = StartingCell;
    }

    public void move (Cell toCell) {
        this.ActualCell = toCell;
    }
}

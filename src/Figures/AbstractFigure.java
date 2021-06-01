package Figures;

import ChessBoard.Cell;

import java.sql.Array;
import java.util.ArrayList;

public abstract class AbstractFigure {
    Cell StartingCell;
    Cell ActualCell;

    AbstractFigure(Cell StartingCell) {
        this.StartingCell = StartingCell;
    }

    ArrayList<String> AllowedMoves = new ArrayList<>();

    public void move (Cell toCell) {
        this.ActualCell = toCell;
    }
}

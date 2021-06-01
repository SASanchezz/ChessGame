package Figures;

import ChessBoard.*;
import java.util.ArrayList;


public abstract class AbstractFigure {
    ArrayList<Cell> AllowedMoves = new ArrayList<>();
    Cell StartingCell;
    Cell ActualCell;

    AbstractFigure(Cell StartingCell) {
        this.StartingCell = StartingCell;
        move(StartingCell);
    }


    public void move (Cell toCell) {
        this.ActualCell = toCell;
        ActualCell.setOccupied(true);
    }
}

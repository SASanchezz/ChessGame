package Figures;

import ChessBoard.*;

import javax.swing.*;
import java.util.ArrayList;


public abstract class AbstractFigure extends JButton {
    ArrayList<Cell> AllowedMoves = new ArrayList<>();
    Cell StartingCell;
    Cell ActualCell;

    AbstractFigure(Cell StartingCell) {
        this.StartingCell = StartingCell;
        move(StartingCell);
    }


    public void move (Cell toCell) {
        ActualCell.setOccupied(false);
        this.ActualCell = toCell;
        ActualCell.setOccupied(true);
    }
}

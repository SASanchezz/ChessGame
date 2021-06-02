package Figures;

import static Auxiliary.DetermineCell.determineCell;
import ChessBoard.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;


public abstract class AbstractFigure extends JButton {
    Cell SomeCell;
    Cell ActualCell;

    AbstractFigure(Cell SomeCell) {
        SomeCell.setOccupation(this);
        setBounds(SomeCell.getREAL_COORDINATES()[0], SomeCell.getREAL_COORDINATES()[1], SomeCell.getCellSize(), SomeCell.getCellSize());
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        this.SomeCell = SomeCell;
        move(SomeCell);


    }

    public ArrayList AllowedMoves(Cell cell) {
        ArrayList<Cell> AllowedMoves = new ArrayList<>();

        return AllowedMoves;
    }

    public void move (Cell toCell) {
        if (AllowedMoves(toCell).contains(toCell)) {
            ActualCell.setOccupied(false);
            this.ActualCell = toCell;
            ActualCell.setOccupied(true);
            setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1], ActualCell.getCellSize(), ActualCell.getCellSize());
        }

    }
}

package Figures;

import static Auxiliary.DetermineCell.determineCell;
import ChessBoard.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;



public abstract class AbstractFigure extends JButton {
    ArrayList<Cell> AllowedMoves = new ArrayList<>();
    Cell StartingCell;
    Cell ActualCell;

    AbstractFigure(Cell StartingCell) {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        this.StartingCell = StartingCell;
        move(StartingCell);

        addMouseListener(new MouseAdapter() {
            Cell RememberedCell;
            @Override
            public void mousePressed(MouseEvent e) {
                int X = e.getX();
                int Y = e.getY();

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Cell FinalCell =

            }



            /*Override other methods*/

        });
    }


    public void move (Cell toCell) {
        if (AllowedMoves.contains(toCell)) {
            ActualCell.setOccupied(false);
            this.ActualCell = toCell;
            ActualCell.setOccupied(true);
        }

    }
}

package Figures;

import static Auxiliary.GetKey.getKey;
import static ChessBoard.ChessBoard.CellSet;
import ChessBoard.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public abstract class AbstractFigure extends JButton {
    Cell SomeCell;
    Cell ActualCell;
    AbstractFigure Figure;
    int StartX;
    int StartY;

    AbstractFigure(Cell SomeCell) {

        setBounds(SomeCell.getREAL_COORDINATES()[0], SomeCell.getREAL_COORDINATES()[1], SomeCell.getCellSize(), SomeCell.getCellSize());
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        this.SomeCell = SomeCell;
        ActualCell = SomeCell;
        ActualCell.setOccupied(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse pressed:  "+ e.getX()+" "+ e.getY());
                StartX = e.getX();
                StartY = e.getY();
                Figure = AbstractFigure.this;
            }


            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("released: " + Figure.ActualCell +"  coords: " +e.getX()+" "+ e.getY());
                if(Figure.ActualCell != null) {
                    String[] StartKey = Figure.ActualCell.getBoardLoc().split("");

                    int xDifference = (int) Math.floor((e.getX() - StartX)/Figure.ActualCell.getCellSize());
                    int yDifference = (int) Math.floor(-(e.getY() - StartY)/Figure.ActualCell.getCellSize());
                    String FCell = Character.toString(StartKey[0].charAt(0) + xDifference) + ((Integer.parseInt(StartKey[1]) + yDifference));
                    System.out.println("Fcell:  "+FCell);
                    Cell FinalCell = CellSet.get(FCell);
                    Figure.move(FinalCell);
                }

            }


        });

    }

    public ArrayList<Cell> AllowedMoves() {
        ArrayList<Cell> AllowedMoves = new ArrayList<>();

        return AllowedMoves;
    }

    public void move (Cell toCell) {
        System.out.println("Moving...");

//        for (Cell cell: AllowedMoves()) {
//            System.out.println("key: "+cell.getBoardLoc());
//        }
        if (AllowedMoves().contains(toCell)) {
            System.out.println("Real moving...");

            ActualCell.setOccupied(false);
            ActualCell = toCell;
            ActualCell.setOccupied(true);
            setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1], ActualCell.getCellSize(), ActualCell.getCellSize());
        }

    }
}

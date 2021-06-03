package Figures;

import static Auxiliary.GetKey.getKey;
import static ChessBoard.ChessBoard.CellSet;
import ChessBoard.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public abstract class AbstractFigure extends JButton {

    Cell ActualCell;
    AbstractFigure Figure;
    int StartX;
    int StartY;

    AbstractFigure(Cell SomeCell) {

        setBounds(SomeCell.getREAL_COORDINATES()[0], SomeCell.getREAL_COORDINATES()[1], SomeCell.getCellSize(), SomeCell.getCellSize());
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

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
                    int xDifference;
                    int yDifference;
                    if (e.getX() > 0 ) {
                        xDifference = (int) Math.floor(Math.abs(e.getX()/(double)Figure.ActualCell.getCellSize()));
                    } else {
                        xDifference = (int) -Math.ceil(Math.abs(e.getX()/(double)Figure.ActualCell.getCellSize()));
                    } if (e.getY() > 0 ) {
                        yDifference = (int) -Math.floor(Math.abs(e.getY()/(double)Figure.ActualCell.getCellSize()));
                    } else {
                        yDifference = (int) Math.ceil(Math.abs(e.getY()/(double)Figure.ActualCell.getCellSize()));
                    }

                    //System.out.println(xDifference+" "+ yDifference);

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

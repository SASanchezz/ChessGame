package Figures;

import static ChessBoard.ChessBoard.getCellSet;
import ChessBoard.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public abstract class AbstractFigure extends JButton {
    private String Color;
    Cell ActualCell;
    AbstractFigure Figure;
    int StartX;
    int StartY;

    AbstractFigure(Cell SomeCell, String Color) {
        this.Color = Color;
        setBounds(SomeCell.getREAL_COORDINATES()[0], SomeCell.getREAL_COORDINATES()[1], SomeCell.getCellSize(), SomeCell.getCellSize());
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        ActualCell = SomeCell;
        ActualCell.setOccupiedBy(Color);
        ActualCell.setOccupation(this);

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

                    String FCell = Character.toString(StartKey[0].charAt(0) + xDifference) + ((Integer.parseInt(StartKey[1]) + yDifference));
                    System.out.println("Fcell:  "+FCell);
                    Cell FinalCell = getCellSet().get(FCell);
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
        System.out.println(toCell.getBoardLoc());
        System.out.println("Abstract move: "+getCellSet().get(toCell.getBoardLoc()).getOccupation() +" " + getCellSet().get(toCell.getBoardLoc()).getOccupiedBy()+" "+ Color);

        if (AllowedMoves().contains(toCell)) {
            if (toCell != null && Color != toCell.getOccupiedBy()) {
                if (toCell.getOccupation() != null) {
                    toCell.getOccupation().setBounds(0,0,0,0);
                }
            }
                ActualCell.setOccupation(null);
                ActualCell = toCell;
                ActualCell.setOccupiedBy(Color);
                ActualCell.setOccupation(this);
            setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1],
                    ActualCell.getCellSize(), ActualCell.getCellSize());
        }

    }

    public String getColor() {
        return Color;
    }
}

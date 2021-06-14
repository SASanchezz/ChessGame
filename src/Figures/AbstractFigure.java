package Figures;

import ChessBoard.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static ChessBoard.ChessBoard.*;


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
        ActualCell.setOccupiedColor(Color);
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
        System.out.println("Abstract move: "+getCellSet().get(toCell.getBoardLoc()).getOccupation() +" " + getCellSet().get(toCell.getBoardLoc()).getOccupiedColor()+" "+ Color);
        Cell OldCell = ActualCell;
        King MainKing = null;
        if (AllowedMoves().contains(toCell) ) {
            // ((!BKing.isDangered() && Color.equals("Black")) || (!WKing.isDangered() && Color.equals("White")) )
            if (Color.equals("White") && WKing.isDangered()) {
                MainKing = WKing;
            } else if (Color.equals("Black") && BKing.isDangered()) {
                MainKing = BKing;
            }
            if(MainKing != null && toCell != MainKing.getDangerFigure().getActualCell()) {

                String OldColorKilled = toCell.getOccupiedColor();
                AbstractFigure OldFigureKilled = toCell.getOccupation();
                ActualCell.setOccupation(null);
                ActualCell = toCell;
                ActualCell.setOccupiedColor(Color);
                ActualCell.setOccupation(this);

                boolean doNextAction = true;
                for (Cell StepCell: MainKing.getDangerFigure().AllowedMoves()) {
                    if (StepCell.getOccupation() != null) {
                        if (StepCell.getOccupation().getClass().getName().equals("Figures.King")) {
                            ActualCell.setOccupation(OldFigureKilled);
                            ActualCell.setOccupiedColor(OldColorKilled);
                            ActualCell = OldCell;
                            ActualCell.setOccupiedColor(Color);
                            ActualCell.setOccupation(this);
                            doNextAction = false;
                        }
                    }
                }
                if (doNextAction) {
                    if (OldFigureKilled != null) {
                        System.out.println("Is gonna kill" + OldFigureKilled);
                        OldFigureKilled.setBounds(0,0,0,0);
                        getBlackFigures().remove(OldFigureKilled);
                        getWhiteFigures().remove(OldFigureKilled);
                    }

                setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1],
                        ActualCell.getCellSize(), ActualCell.getCellSize());
                    MainKing.setDangered(false);

                }


            } else {
                if (MainKing != null) MainKing.setDangered(false);

                if (Color != toCell.getOccupiedColor()) {
                    if (toCell.getOccupation() != null) {
                        toCell.getOccupation().setBounds(0,0,0,0);

                        getBlackFigures().remove(toCell.getOccupation());
                        getWhiteFigures().remove(toCell.getOccupation());
                    }
                }
                ActualCell.setOccupation(null);
                ActualCell = toCell;
                ActualCell.setOccupiedColor(Color);
                ActualCell.setOccupation(this);
                setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1],
                        ActualCell.getCellSize(), ActualCell.getCellSize());
            }
            for (Cell StepCell: AllowedMoves()) {
                if (StepCell.getOccupation() != null) {
                    if (StepCell.getOccupation().getClass().getName().equals("Figures.King") && !StepCell.getOccupiedColor().equals(Color)) {
                        StepCell.getOccupation().setDangered(true);
                        StepCell.getOccupation().setDangerFigure(this);
                    }
                }
            }



        }
    }

    public void setDangerFigure(AbstractFigure abstractFigure) {
    }

    public void setDangered(boolean b) {
    }

    public String getColor() {
        return Color;
    }
    public Cell getActualCell() {
        return ActualCell;
    }
}

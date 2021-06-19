package Figures;

import ChessBoard.*;
import Starting.Config;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static Auxiliary.IconChanger.iconChange;
import static ChessBoard.ChessBoard.*;


public abstract class AbstractFigure extends JButton {
    Boolean FigureColor;
    private Boolean Color;
    Cell ActualCell;
    AbstractFigure Figure;
    int StartX;
    int StartY;
    Boolean Dangered;

    AbstractFigure(Cell SomeCell, Boolean Color) {
        if (Config.COLOR.equals("WHITE")) {
            this.Color = Color;
        } else this.Color = !Color;

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
                StartX = e.getX();
                StartY = e.getY();
                Figure = AbstractFigure.this;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println();
                System.out.println();
                System.out.println("previous cell: " + Figure.ActualCell);
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
                    Cell FinalCell = getCellSet().get(FCell);

                    System.out.println("White to step: "+isWhiteToStep());
                    if (Config.COLOR.equals("WHITE")) {
                        FigureColor = Figure.getColor();
                    } else {
                        FigureColor = !Figure.getColor();
                    }
                    if (isWhiteToStep() && FigureColor) {
                        Figure.move(FinalCell);
                    } else if (!isWhiteToStep() && !FigureColor) {
                        Figure.move(FinalCell);
                    }
                }
            }
        });
    }

    public ArrayList<Cell> AllowedMoves() {
        ArrayList<Cell> AllowedMoves = new ArrayList<>();

        return AllowedMoves;
    }

    public void move (Cell toCell) {
        System.out.println("Finish cell: " +toCell.getBoardLoc());


        Cell OldCell = ActualCell;
        King MainKing = null;
        if (AllowedMoves().contains(toCell) ) {
            if (Color && WKing.isDangered()) {
                MainKing = WKing;
            } else if (!Color && BKing.isDangered()) {
                MainKing = BKing;
            }
            ArrayList<AbstractFigure> FoeFigureSet = null;
            if (Color) {
                if (Config.COLOR.equals("WHITE")) {
                    FoeFigureSet = getBlackFigures();
                } else {
                    FoeFigureSet = getWhiteFigures();
                }

            } else {
                if (Config.COLOR.equals("BLACK")) {
                    FoeFigureSet = getBlackFigures();
                } else {
                    FoeFigureSet = getWhiteFigures();
                }
            }

            if(MainKing != null && toCell != MainKing.getDangerFigure().getActualCell()) {
                System.out.println("Main king is not null");


                Boolean OldColorKilled = toCell.getOccupiedColor();
                AbstractFigure OldFigureKilled = toCell.getOccupation();
                ActualCell.setOccupation(null);
                ActualCell = toCell;
                ActualCell.setOccupiedColor(Color);
                ActualCell.setOccupation(this);



                Boolean doNextAction = true;
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
                        OldFigureKilled.setBounds(0,0,0,0);
                        FoeFigureSet.remove(OldFigureKilled);
                    }

                    setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1],
                        ActualCell.getCellSize(), ActualCell.getCellSize());
                    if (isWhiteToStep() && FigureColor) {
                        setWhiteToStep(false);
                    } else if (!isWhiteToStep() && !FigureColor) {
                        setWhiteToStep(true);
                    }
                    MainKing.setDangered(false);

                }


            } else {
                if (MainKing != null) MainKing.setDangered(false);

                    Boolean OldColorKilled = toCell.getOccupiedColor();
                    AbstractFigure OldFigureKilled = toCell.getOccupation();
                    ActualCell.setOccupation(null);
                    ActualCell = toCell;
                    ActualCell.setOccupiedColor(Color);
                    ActualCell.setOccupation(this);
                    FoeFigureSet.remove(OldFigureKilled);

                    Boolean doNextAction = true;

                    for (AbstractFigure figure: FoeFigureSet) {
                        if(figure != null) {
                            for (Cell AlCell: figure.AllowedMoves()) {

                                if (AlCell.getOccupation() != null && Color == AlCell.getOccupiedColor() && AlCell.getOccupation().getClass().getName().equals("Figures.King")) {
                                    ActualCell.setOccupation(OldFigureKilled);
                                    ActualCell.setOccupiedColor(OldColorKilled);
                                    ActualCell = OldCell;
                                    ActualCell.setOccupiedColor(Color);
                                    ActualCell.setOccupation(this);
                                    doNextAction = false;
                                }
                            }
                        }

                    }
                    System.out.println("Do next action: "+doNextAction);
                    if (doNextAction) {
                        if (OldFigureKilled != null) {
                            OldFigureKilled.setBounds(0, 0, 0, 0);
                            FoeFigureSet.remove(OldFigureKilled);

                        }
                        setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1],
                                ActualCell.getCellSize(), ActualCell.getCellSize());
                        if (isWhiteToStep() && FigureColor) {
                            setWhiteToStep(false);
                        } else if (!isWhiteToStep() && !FigureColor) {
                            setWhiteToStep(true);
                        }

                    } else FoeFigureSet.add(OldFigureKilled);



            }
            for (Cell StepCell: AllowedMoves()) {
                if (StepCell.getOccupation() != null) {
                    if (StepCell.getOccupation().getClass().getName().equals("Figures.King") && !StepCell.getOccupiedColor() == Color) {
                        StepCell.getOccupation().setDangerFigure(this);
                        StepCell.getOccupation().setDangered(true);
                        System.out.println("set dangered: " +StepCell.getOccupation());
                    }
                }
            }



        }
    }

    public void setDangerFigure(AbstractFigure abstractFigure) {
    }

    public void setDangered(Boolean b) {
        Dangered = true;
    }

    public Boolean getColor() {
        return Color;
    }
    public Cell getActualCell() {
        return ActualCell;
    }
}

package Figures;

import ChessBoard.*;
import Starting.Config;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static Bot.Bot.RandomMover;
import static ChessBoard.ChessBoard.*;


public abstract class AbstractFigure extends JButton {
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
                System.out.println("previous cell: " + ActualCell);
                if(ActualCell != null) {
                    String[] StartKey = ActualCell.getBoardLoc().split("");
                    int xDifference;
                    int yDifference;
                    if (e.getX() > 0 ) {
                        xDifference = (int) Math.floor(Math.abs(e.getX()/(double)ActualCell.getCellSize()));
                    } else {
                        xDifference = (int) -Math.ceil(Math.abs(e.getX()/(double)ActualCell.getCellSize()));
                    } if (e.getY() > 0 ) {
                        yDifference = (int) -Math.floor(Math.abs(e.getY()/(double)ActualCell.getCellSize()));
                    } else {
                        yDifference = (int) Math.ceil(Math.abs(e.getY()/(double)ActualCell.getCellSize()));
                    }

                    String FCell = Character.toString(StartKey[0].charAt(0) + xDifference) + ((Integer.parseInt(StartKey[1]) + yDifference));
                    Cell FinalCell = getCellSet().get(FCell);

                    boolean ConfigColor = Config.COLOR.equals("WHITE");


                    if (isWhiteToStep() && Figure.Color && !Config.BOT) {
                        move(FinalCell);
                    } else if (!isWhiteToStep() && !Figure.Color && !Config.BOT) {
                        move(FinalCell);
                    } else if (Figure.Color == ConfigColor && Config.BOT) {
                        System.out.println("Our move");
                        Cell OldCell = ActualCell;
                        move(FinalCell);

                        if(!OldCell.equals(ActualCell)  && isMate()){
                            RandomMover();

                        }
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
                    FoeFigureSet = getBlackFigures();
            } else {
                    FoeFigureSet = getWhiteFigures();
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
                    setWhiteToStep(!isWhiteToStep());
                    MainKing.setDangered(false);

                }


            } else {
                if (MainKing != null) MainKing.setDangered(false);

                    Boolean OldColorKilled = toCell.getOccupiedColor();
                    AbstractFigure OldFigureKilled = toCell.getOccupation();
                    ActualCell.setOccupation(null);
                    ActualCell = toCell;
                    ActualCell.setOccupation(this);
                    FoeFigureSet.remove(OldFigureKilled);

                    Boolean doNextAction = true;

                    for (AbstractFigure figure: FoeFigureSet) {
                        if(figure != null && figure.AllowedMoves().size() > 0) {
                            for (Cell AlCell: figure.AllowedMoves()) {

                                if (AlCell.getOccupation() != null && Color == AlCell.getOccupiedColor() && AlCell.getOccupation().getClass().getName().equals("Figures.King")) {
                                    ActualCell.setOccupation(OldFigureKilled);
                                    ActualCell.setOccupiedColor(OldColorKilled);
                                    ActualCell = OldCell;
                                    ActualCell.setOccupation(this);
                                    doNextAction = false;
                                }
                            }
                        }

                    }
                    if (doNextAction) {
                        if (OldFigureKilled != null) {
                            OldFigureKilled.setBounds(0, 0, 0, 0);
                            FoeFigureSet.remove(OldFigureKilled);

                        }
                        setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1],
                                ActualCell.getCellSize(), ActualCell.getCellSize());
                        setWhiteToStep(!isWhiteToStep());


                    } else FoeFigureSet.add(OldFigureKilled);



            }
            for (Cell StepCell: AllowedMoves()) {
                if (StepCell.getOccupation() != null) {
                    if (StepCell.getOccupation().getClass().getName().equals("Figures.King") && StepCell.getOccupiedColor() != Color) {
                        StepCell.getOccupation().setDangerFigure(this);
                        StepCell.getOccupation().setDangered(true);
                    }
                }
            }



        }
    }
    public ArrayList<Cell> StraightAllowedMoves() {
        return new ArrayList<Cell>();
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

    @Override
    public String toString() {
        return Color + " " + getClass().getName().split("\\.")[1];

    }
}

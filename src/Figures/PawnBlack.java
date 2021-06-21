package Figures;

import ChessBoard.Cell;
import Starting.Config;

import static Auxiliary.IconChanger.iconChange;
import static ChessBoard.ChessBoard.*;

import java.util.ArrayList;


public class PawnBlack extends AbstractFigure {
    Boolean Color ;


    public PawnBlack(Cell SomeCell, int Size, Boolean Color) {
        super(SomeCell, Color);
        if (Config.COLOR.equals("WHITE")) {
            this.Color = Color;
        } else this.Color = !Color;

        ActualCell = SomeCell;
        ActualCell.setOccupation(this);
        if (Config.COLOR.equals("BLACK")) {
            setIcon(iconChange(Size).get("WPawn.png"));
        } else {
            setIcon(iconChange(Size).get("BPawn.png"));
        }

    }

    @Override
    public void move (Cell toCell) {

        King MainKing = null;
        Cell OldCell = ActualCell;


        ArrayList<AbstractFigure> FoeFigureSet = null;

        //Start
        if (Config.COLOR.equals("BLACK")) {
            FoeFigureSet = getBlackFigures();
        } else {
            FoeFigureSet = getWhiteFigures();
        }

        if (UpdatedAllowedMoves().contains(toCell) && (
                !(toCell.getBoardLoc().charAt(0) != ActualCell.getBoardLoc().charAt(0)) ||
                    (toCell.getBoardLoc().charAt(0) != ActualCell.getBoardLoc().charAt(0) && Color != toCell.getOccupiedColor() && toCell.getOccupation() != null)
        )) {
            System.out.println("Moved");

            if (Config.COLOR.equals("BLACK")){
                if (Color && WKing.isDangered()) {
                    MainKing = WKing;
                }
            } else {
                if (!Color && BKing.isDangered()) {
                    MainKing = BKing;
                }
            }

            if (MainKing != null && toCell != MainKing.getDangerFigure().getActualCell()) {

                Boolean OldColorKilled = toCell.getOccupiedColor();
                AbstractFigure OldFigureKilled = toCell.getOccupation();
                ActualCell.setOccupation(null);
                ActualCell = toCell;
                ActualCell.setOccupation(this);

                Boolean doNextAction = true;
                for (Cell StepCell : MainKing.getDangerFigure().AllowedMoves()) {
                    if (StepCell.getOccupation() != null) {
                        if (StepCell.getOccupation().getClass().getName().equals("Figures.King")) {
                            ActualCell.setOccupation(OldFigureKilled);
                            ActualCell = OldCell;
                            ActualCell.setOccupation(this);
                            doNextAction = false;
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

                    MainKing.setDangered(false);

                }


            } else {
                if (MainKing != null) MainKing.setDangered(false);

//            Start implementation here


                //End


                    Boolean OldColorKilled = toCell.getOccupiedColor();
                    AbstractFigure OldFigureKilled = toCell.getOccupation();
                    ActualCell.setOccupation(null);
                    ActualCell = toCell;
                    ActualCell.setOccupation(this);
                    FoeFigureSet.remove(OldFigureKilled);

                    Boolean doNextAction = true;
                    for (AbstractFigure figure : FoeFigureSet) {
                        if(figure != null) {
                            for (Cell AlCell : figure.AllowedMoves()) {
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
        }
//            Finish

        }


    @Override
    public ArrayList<Cell> AllowedMoves() {
        ArrayList<Cell> AllowedMoves = new ArrayList<>();
        String key = ActualCell.getBoardLoc();
        if (key != null) {
            if (key.charAt(0) != 'h') {
                String CellRight = Character.toString(key.charAt(0) + 1) + Character.toString(key.charAt(1) - 1);
                AllowedMoves.add(getCellSet().get(CellRight));
            }
            if (key.charAt(0) != 'a') {
                String CellLeft = Character.toString(key.charAt(0) - 1) + Character.toString(key.charAt(1) - 1);
                AllowedMoves.add(getCellSet().get(CellLeft));
            }
        }

        return AllowedMoves;
    }

    public ArrayList<Cell> UpdatedAllowedMoves() {
        //hit
        ArrayList<Cell> AllowedMoves = new ArrayList<>();
        String key = ActualCell.getBoardLoc();
        if (key != null) {
            if (key.charAt(0) != 'h') {
                String CellRight = Character.toString(key.charAt(0)+1) + Character.toString(key.charAt(1) - 1);
                AllowedMoves.add(getCellSet().get(CellRight));
            }
            if (key.charAt(0) != 'a') {
                String CellLeft = Character.toString(key.charAt(0)-1) + Character.toString(key.charAt(1) - 1);
                AllowedMoves.add(getCellSet().get(CellLeft));
            }
        }
        //ahead
        if (!ActualCell.getBoardLoc().split("")[1].equals("1")) {

            String CellAhead = key.charAt(0) + Character.toString(key.charAt(1) - 1);

            if (getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) - 1)).getOccupation() == null ) {
                AllowedMoves.add(getCellSet().get(CellAhead));
            }
            if (key.split("")[1].equals("7") &&
                    getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) - 1)).getOccupation() == null &&
                    getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) - 2)).getOccupation() == null) {
                CellAhead = key.charAt(0) + Character.toString(key.charAt(1) - 2);
                AllowedMoves.add(getCellSet().get(CellAhead));
            }
        }

        return AllowedMoves;
    }

    @Override
    public ArrayList<Cell> StraightAllowedMoves() {
        System.out.println();

        ArrayList<Cell> AllowedMoves = new ArrayList<>();
        String key = ActualCell.getBoardLoc();

        if (!ActualCell.getBoardLoc().split("")[1].equals("1")) {

            String CellAhead = key.charAt(0) + Character.toString(key.charAt(1) - 1);

            if (getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) - 1)).getOccupation() == null ) {
                AllowedMoves.add(getCellSet().get(CellAhead));
            }
            if (key.split("")[1].equals("7") &&
                    getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) - 1)).getOccupation() == null &&
                    getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) - 2)).getOccupation() == null) {
                CellAhead = key.charAt(0) + Character.toString(key.charAt(1) - 2);
                AllowedMoves.add(getCellSet().get(CellAhead));
            }
        }
        return AllowedMoves;
    }
    @Override
    public String toString() {
        return Color + " " + getClass().getName().split("\\.")[1];

    }
}
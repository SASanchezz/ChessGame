package Figures;

import ChessBoard.Cell;
import static Auxiliary.IconChanger.iconChange;
import static ChessBoard.ChessBoard.*;

import java.util.ArrayList;


public class PawnBlack extends AbstractFigure {
    String Color = "Black";


    public PawnBlack(Cell SomeCell, int Size, String Color) {
        super(SomeCell, Color);
        ActualCell = SomeCell;
        ActualCell.setOccupation(this);
        ActualCell.setOccupiedColor("Black");

        setIcon(iconChange(Size).get("BPawn.png"));

    }

    @Override
    public void move (Cell toCell) {
        King MainKing = null;
        Cell OldCell = ActualCell;

        if (UpdatedAllowedMoves().contains(toCell) && (
                !(toCell.getBoardLoc().charAt(0) != ActualCell.getBoardLoc().charAt(0)) ||
                    (toCell.getBoardLoc().charAt(0) != ActualCell.getBoardLoc().charAt(0) && Color != toCell.getOccupiedColor() && toCell.getOccupation() != null)
        )) {
            System.out.println("Moved");

            if (Color.equals("Black") && BKing.isDangered()) {
                MainKing = BKing;
            }
            if (MainKing != null && toCell != MainKing.getDangerFigure().getActualCell()) {

                String OldColorKilled = toCell.getOccupiedColor();
                AbstractFigure OldFigureKilled = toCell.getOccupation();
                ActualCell.setOccupation(null);
                ActualCell = toCell;
                ActualCell.setOccupiedColor(Color);
                ActualCell.setOccupation(this);

                boolean doNextAction = true;
                for (Cell StepCell : MainKing.getDangerFigure().AllowedMoves()) {
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
                        OldFigureKilled.setBounds(0, 0, 0, 0);
                        getBlackFigures().remove(OldFigureKilled);
                        getWhiteFigures().remove(OldFigureKilled);
                    }

                    setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1],
                            ActualCell.getCellSize(), ActualCell.getCellSize());
                    MainKing.setDangered(false);

                }


            } else {
                if (MainKing != null) MainKing.setDangered(false);

//            Start implementation here
                ArrayList<AbstractFigure> FoeFigureSet = null;

                MainKing = BKing;
                FoeFigureSet = getWhiteFigures();

                if (MainKing != null) {

                    String OldColorKilled = toCell.getOccupiedColor();
                    AbstractFigure OldFigureKilled = toCell.getOccupation();
                    ActualCell.setOccupation(null);
                    ActualCell = toCell;
                    ActualCell.setOccupiedColor(Color);
                    ActualCell.setOccupation(this);
                    FoeFigureSet.remove(OldFigureKilled);

                    boolean doNextAction = true;
                    for (AbstractFigure figure : FoeFigureSet) {
                        if(figure != null) {
                            for (Cell AlCell : figure.AllowedMoves()) {
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
                    System.out.println(doNextAction);
                    if (doNextAction) {
                        if (OldFigureKilled != null) {
                            OldFigureKilled.setBounds(0, 0, 0, 0);
                            FoeFigureSet.remove(OldFigureKilled);

                        }
                        setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1],
                                ActualCell.getCellSize(), ActualCell.getCellSize());
                    } else FoeFigureSet.add(OldFigureKilled);

                }
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

    public ArrayList UpdatedAllowedMoves() {
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

            if (getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) - 1)).getOccupiedColor() == null ) {
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
}
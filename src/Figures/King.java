package Figures;

import static Auxiliary.IconChanger.iconChange;
import static ChessBoard.ChessBoard.*;
import ChessBoard.Cell;


import ChessBoard.Cell;
import Starting.Config;

import java.util.ArrayList;


public class King extends AbstractFigure {
    boolean Mate = true;
    Boolean FigureColor;
    Boolean Color;
    private Boolean Dangered;
    private AbstractFigure DangerFigure;
    ArrayList<AbstractFigure> FoeFigureSet;
    ArrayList<AbstractFigure> AllyFigureSet;


    public King(Cell SomeCell, int Size, Boolean Color) {
        super(SomeCell, Color);

        Dangered = false;
        ActualCell = SomeCell;
        ActualCell.setOccupation(this);
        if (Config.COLOR.equals("WHITE")) {
            this.Color = Color;
        } else this.Color = !Color;


        if (Color) {
            if (Config.COLOR.equals("WHITE")) {
                ActualCell.setOccupiedColor(true);
                setIcon(iconChange(Size).get("WKing.png"));
                FoeFigureSet = getBlackFigures();
                AllyFigureSet = getWhiteFigures();
            } else {
                ActualCell.setOccupiedColor(false);
                setIcon(iconChange(Size).get("BKing.png"));
                FoeFigureSet = getWhiteFigures();
                AllyFigureSet = getBlackFigures();
            }

        } else {
            if (Config.COLOR.equals("BLACK")) {
                ActualCell.setOccupiedColor(true);
                setIcon(iconChange(Size).get("WKing.png"));
                FoeFigureSet = getBlackFigures();
                AllyFigureSet = getWhiteFigures();
            } else {
                ActualCell.setOccupiedColor(false);
                setIcon(iconChange(Size).get("BKing.png"));
                FoeFigureSet = getWhiteFigures();
                AllyFigureSet = getBlackFigures();

            }
        }

    }

    public Boolean isDangered() {
        System.out.println("dangered: "+Dangered);

        return Dangered;
    }
    @Override
    public void setDangered(Boolean dangered) {
        Dangered = dangered;
        if (Dangered) {
            if (isMate()) {
//                Config.MAINBOARD.dispose();
//                Config.MAINBOARD.setVisible(false);
            }
        }
    }

    public AbstractFigure getDangerFigure() {
        return DangerFigure;
    }

    @Override
    public void setDangerFigure(AbstractFigure dangerFigure) {
        DangerFigure = dangerFigure;
    }

    @Override
    public void move (Cell toCell) {
        Cell OldCell = ActualCell;

        if (Config.COLOR.equals("WHITE")) {
            FigureColor = Figure.getColor();
        } else {
            FigureColor = !Figure.getColor();
        }


        if (AllowedMoves().contains(toCell)) {

            Boolean OldColorKilled = toCell.getOccupiedColor();
            AbstractFigure OldFigureKilled = toCell.getOccupation();
            ActualCell.setOccupation(null);
            ActualCell = toCell;
            ActualCell.setOccupiedColor(Color);
            ActualCell.setOccupation(this);

            if (DangerCheck(FoeFigureSet, ActualCell) == true) {
                ActualCell.setOccupation(OldFigureKilled);
                ActualCell.setOccupiedColor(OldColorKilled);
                ActualCell = OldCell;
                ActualCell.setOccupiedColor(Color);
                ActualCell.setOccupation(this);

            } else {
                if (OldColorKilled != null && !Color == (OldColorKilled)) {
                    if (OldFigureKilled != null) {
                        OldFigureKilled.setBounds(0,0,0,0);
                        FoeFigureSet.remove(OldFigureKilled);
                    }
                }
                setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1],
                        ActualCell.getCellSize(), ActualCell.getCellSize());
                if (isWhiteToStep() && FigureColor) {
                    setWhiteToStep(false);
                } else if (!isWhiteToStep() && !FigureColor) {
                    setWhiteToStep(true);
                }
                setDangered(false);
            }
        }

    }

    private ArrayList<Cell> AroundZone(String NewCell) {
        String[] key = NewCell.split("");
        ArrayList<Cell> LocalSet = new ArrayList<>();
        ArrayList<String> StringSet = new ArrayList<>();

        StringSet.add(key[0]+ (Integer.parseInt(key[1]) + 1));
        StringSet.add(Character.toString(key[0].charAt(0)+1) + (Integer.parseInt(key[1]) + 1));
        StringSet.add(Character.toString(key[0].charAt(0)+1) + key[1]);
        StringSet.add(Character.toString(key[0].charAt(0)+1) + (Integer.parseInt(key[1]) - 1));
        StringSet.add(key[0] + (Integer.parseInt(key[1]) - 1));
        StringSet.add(Character.toString(key[0].charAt(0)-1) + (Integer.parseInt(key[1]) - 1));
        StringSet.add(Character.toString(key[0].charAt(0)-1) + key[1]);
        StringSet.add(Character.toString(key[0].charAt(0)-1) + (Integer.parseInt(key[1]) + 1));

        for (String s : StringSet) {
            Cell SpecificCell = getCellSet().get(s);
            if (SpecificCell != null && !ActualCell.getBoardLoc().equals(s)) {
                LocalSet.add(SpecificCell);

            }
        }
        return LocalSet;
    }

    private Boolean IsKingNearby(String NewCell) {
        for (Cell cell: AroundZone(NewCell)) {
            if (cell.getOccupation() != null) {
                if (cell.getOccupation().getClass().getName() == "Figures.King") {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<Cell> AllowedMoves() {
        ArrayList<Cell> AllowedMoves = new ArrayList<>();
        String[] key = ActualCell.getBoardLoc().split("");

//            Up Move
        if (!key[1].equals("8") && getCellSet().get(key[0] + (Integer.parseInt(key[1]) + 1)).getOccupiedColor() != (Color) &&
                !IsKingNearby(key[0] + (Integer.parseInt(key[1])+1)) ) {
            AllowedMoves.add(getCellSet().get(key[0] + (Integer.parseInt(key[1]) + 1)));
        }
//            Down Move
        if (!key[1].equals("1") && getCellSet().get(key[0] + (Integer.parseInt(key[1]) - 1)).getOccupiedColor() != (Color) &&
                !IsKingNearby(key[0] + (Integer.parseInt(key[1])-1)) ) {
            AllowedMoves.add(getCellSet().get(key[0] + (Integer.parseInt(key[1]) - 1)));
        }
//        Move Left
        if (!key[0].equals("a") && getCellSet().get((Character.toString(key[0].charAt(0)-1)) + key[1]).getOccupiedColor() != (Color) &&
                !IsKingNearby(Character.toString(key[0].charAt(0)-1) + key[1]) ) {
            AllowedMoves.add(getCellSet().get(Character.toString(key[0].charAt(0)-1) + key[1]));
        }
//        Move Right
        if (!key[0].equals("h") && getCellSet().get((Character.toString(key[0].charAt(0)+1)) + key[1]).getOccupiedColor() != (Color) &&
                !IsKingNearby(Character.toString(key[0].charAt(0)+1) + key[1]) ) {
            AllowedMoves.add(getCellSet().get(Character.toString(key[0].charAt(0)+1) + key[1]));
        }

//           Up-Right Move
        if (!key[0].equals("h") && !key[1].equals("8") && getCellSet().get((Character.toString(key[0].charAt(0)+1)) + (Integer.parseInt(key[1]) + 1)).getOccupiedColor() != (Color) &&
                !IsKingNearby(Character.toString(key[0].charAt(0)+1) + (Integer.parseInt(key[1]) + 1)) ) {
            AllowedMoves.add(getCellSet().get(Character.toString(key[0].charAt(0)+1) + (Integer.parseInt(key[1]) + 1)));
        }
//          Down-Right Move
        if (!key[0].equals("h") && !key[1].equals("1") && getCellSet().get((Character.toString(key[0].charAt(0)+1)) + (Integer.parseInt(key[1]) - 1)).getOccupiedColor() != (Color) &&
                !IsKingNearby(Character.toString(key[0].charAt(0)+1) + (Integer.parseInt(key[1]) - 1)) ) {
            AllowedMoves.add(getCellSet().get(Character.toString(key[0].charAt(0)+1) + (Integer.parseInt(key[1]) - 1)));
        }
//          Up-Left Move
        if (!key[0].equals("a") && !key[1].equals("8") && getCellSet().get((Character.toString(key[0].charAt(0)-1)) + (Integer.parseInt(key[1]) + 1)).getOccupiedColor() != (Color) &&
                !IsKingNearby(Character.toString(key[0].charAt(0)-1) + (Integer.parseInt(key[1]) + 1)) ) {
            AllowedMoves.add(getCellSet().get(Character.toString(key[0].charAt(0)-1) + (Integer.parseInt(key[1]) + 1)));
        }
//          Down-Left Move
        if (!key[0].equals("a") && !key[1].equals("1") && getCellSet().get((Character.toString(key[0].charAt(0)-1)) + (Integer.parseInt(key[1]) - 1)).getOccupiedColor() != (Color) &&
                !IsKingNearby(Character.toString(key[0].charAt(0)-1) + (Integer.parseInt(key[1]) - 1)) ) {
            AllowedMoves.add(getCellSet().get(Character.toString(key[0].charAt(0)-1) + (Integer.parseInt(key[1]) - 1)));
        }


        return AllowedMoves;
    }

    private Boolean DangerCheck(ArrayList<AbstractFigure> FoeSet, Cell SpecificCell) {
                for (AbstractFigure FoeFigure: FoeSet){
                    for (Cell FoeCell: FoeFigure.AllowedMoves()) {
                        if (SpecificCell.equals(FoeCell)) {
                            return true;
                        }
                    }
                }


        return false;

        }

    public boolean isMate() {
        if (CanBeEscaped()) return false;
        else {
            return true;
        }
    }



    private boolean CanBeEscaped() {
        ArrayList<Cell> CopyAllowedMoves = new ArrayList<>();
        for (Cell copiedCell: AllowedMoves()) {
            CopyAllowedMoves.add(copiedCell);
        }

        for (int i=0; i<CopyAllowedMoves.size(); i++) {
            Cell toCell = CopyAllowedMoves.get(i);
            //remembering
            Cell OldCell = ActualCell;
            AbstractFigure KilledFigure = toCell.getOccupation();

            ActualCell.setOccupation(null);
            ActualCell = toCell;
            ActualCell.setOccupation(this);

            for (AbstractFigure FoeFigure: AllyFigureSet) {
                if (FoeFigure.AllowedMoves().contains(ActualCell)) {
                    CopyAllowedMoves.remove(ActualCell);
//                    back move
                    ActualCell.setOccupation(KilledFigure);
                    ActualCell = OldCell;
                    ActualCell.setOccupation(this);

                    i = -1;
                }
            }
        }
        if (CopyAllowedMoves.size() == 0) {
            System.out.println("King cannot be moved");
            return false;
        } else {
            System.out.println("King can be moved");
            return true;
        }
    }

    private boolean CanBeEliminated() {
        for (AbstractFigure AllyFigure: AllyFigureSet) {
            for (Cell AllyCell : AllyFigure.AllowedMoves()) {
                if (DangerFigure.equals(AllyCell.getOccupation())) {
                    System.out.println();
                    System.out.println("Can be eliminated not by king!");
                    System.out.println();
                    return true;
                }
            }
        }
        if (AroundZone(ActualCell.getBoardLoc()).contains(DangerFigure.getActualCell())) {
            System.out.println("It's around king");
            for (AbstractFigure FoeFigure: FoeFigureSet) {
                    for (Cell FoeCell: FoeFigure.AllowedMoves()) {
                        if (FoeCell.equals(DangerFigure.getActualCell()) ) {
                            System.out.println();
                            System.out.println("Cannot be eliminated around king :c");
                            System.out.println();
                            return false;
                        }
                    }
                }
            System.out.println();
            System.out.println("Can be eliminated by KING !");
            System.out.println();
            return true;
        }

        System.out.println();
        System.out.println("Cannot be eliminated at all :c");
        System.out.println();
        return false;



    }

    private boolean CheckForAroundZone() {
        System.out.println("Dangered by: "+ DangerFigure);
        ArrayList<Cell> CopyAllowedMoves = new ArrayList<>();
        for (Cell copiedCell: AllowedMoves()) {
            CopyAllowedMoves.add(copiedCell);
        }


        for (int i=0; i<CopyAllowedMoves.size(); i++) {
            Cell KingCell = CopyAllowedMoves.get(i);
            if (DangerCheck(FoeFigureSet, KingCell)) {
                CopyAllowedMoves.remove(KingCell);
                System.out.println("deleted: "+KingCell.getBoardLoc());
                i=-1;
            }
        }
        if (CopyAllowedMoves.size() > 0)return false;
        else return true;
    }

}
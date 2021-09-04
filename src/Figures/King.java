package Figures;

import static Auxiliary.IconChanger.iconChange;
import static ChessBoard.ChessBoard.*;
import ChessBoard.Cell;


import Starting.Config;
import Starting.StartingMenu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;


public class King extends AbstractFigure {
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


        if (this.Color) {
            setIcon(iconChange(Size).get("WKing.png"));
            FoeFigureSet = getBlackFigures();
            AllyFigureSet = getWhiteFigures();
        } else {
            setIcon(iconChange(Size).get("BKing.png"));
            FoeFigureSet = getWhiteFigures();
            AllyFigureSet = getBlackFigures();
            }


    }

    public Boolean isDangered() {
        return Dangered;
    }
    @Override
    public void setDangered(Boolean dangered) {
        if (!dangered) setDangerFigure(null);
        String message;
        if (Color) message = "African Americans wins!!!";
        else message = "White wins!!!";
        Dangered = dangered;
        if (Dangered) {
            if (isMate()) {
                Config.FINISHED = true;
                JOptionPane.showMessageDialog(Config.MAINBOARD,
                        message);
                new StartingMenu();
                Config.MAINBOARD.dispose();
                Config.MAINBOARD.setVisible(false);
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


        if (AllowedMoves().contains(toCell)) {

            Boolean OldColorKilled = toCell.getOccupiedColor();
            AbstractFigure OldFigureKilled = toCell.getOccupation();
            ActualCell.setOccupation(null);
            ActualCell = toCell;
            ActualCell.setOccupation(this);

            FoeFigureSet.remove(OldFigureKilled);


            if (DangerCheck(FoeFigureSet, ActualCell)) {
                ActualCell.setOccupation(OldFigureKilled);
                ActualCell = OldCell;
                ActualCell.setOccupation(this);

                FoeFigureSet.add(OldFigureKilled);


            } else {
                if (OldColorKilled != null && !Color == (OldColorKilled)) {
                    if (OldFigureKilled != null) {
                        OldFigureKilled.setBounds(0,0,0,0);
                        FoeFigureSet.remove(OldFigureKilled);
                        System.out.println("OLD FIGURE KILLED: " +OldColorKilled);
                    }
                }
                setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1],
                        ActualCell.getCellSize(), ActualCell.getCellSize());
                setWhiteToStep(!isWhiteToStep());

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
                if (cell.getOccupation().getClass().getName().equals("Figures.King")) {
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
                    if (FoeFigure != null){
                        for (Cell FoeCell : FoeFigure.AllowedMoves()) {

                            if (SpecificCell.equals(FoeCell)) {
                                return true;
                            }
                        }
                    }
                }


        return false;

        }

    private boolean isMate() {
        if (CanBeEscaped() || CanBeEliminatedOrClosed()) return false;
        else return true;
    }



    private boolean CanBeEscaped() {
        ArrayList<Cell> CopyAllowedMoves = new ArrayList<>(AllowedMoves());

        for (int i=0; i<CopyAllowedMoves.size(); i++) {

            Cell toCell = CopyAllowedMoves.get(i);

            //remembering
            Cell OldCell = ActualCell;
            AbstractFigure KilledFigure = toCell.getOccupation();

            ActualCell.setOccupation(null);
            ActualCell = toCell;
            ActualCell.setOccupation(this);
            FoeFigureSet.remove(KilledFigure);

            for (AbstractFigure FoeFigure: FoeFigureSet) {
                if (FoeFigure != null && FoeFigure.AllowedMoves().contains(this.ActualCell)) {
//                    System.out.println("removed: "+ ActualCell + "  by figure: "+ FoeFigure);
                    CopyAllowedMoves.remove(this.ActualCell);
//                    back move
                    i = -1;
                }
            }
            ActualCell.setOccupation(KilledFigure);
            ActualCell = OldCell;
            ActualCell.setOccupation(this);
            FoeFigureSet.add(KilledFigure);

        }
        if (CopyAllowedMoves.size() == 0) {
//            System.out.println("King cannot be moved");
            return false;
        } else {
//            System.out.println();
//
//            System.out.println("King can be moved");
//            System.out.println();

            return true;
        }
    }

    private boolean CanBeEliminatedOrClosed() {
        for (AbstractFigure AllyFigure: AllyFigureSet) {
//            System.out.println();
//            System.out.println("Figure is: " + AllyFigure);
//            System.out.println();

            ArrayList<Cell> MainCellSet;

            if (AllyFigure != null && AllyFigure.getClass().getName().equals("Figures.PawnWhite") || AllyFigure.getClass().getName().equals("Figures.PawnBlack")) {

                MainCellSet = AllyFigure.StraightAllowedMoves();
                System.out.println("PAWN");
            } else MainCellSet = AllyFigure.AllowedMoves();

            ArrayList<Cell> CopyAllyCells = new ArrayList<>(MainCellSet);
//            Get cells of AllyFigure
            Iterator<Cell> itr = CopyAllyCells.iterator();

            for (int i=0; i< CopyAllyCells.size(); i++) {

                Cell AllyCell = CopyAllyCells.get(i);

                if (DangerFigure.equals(AllyCell.getOccupation())) {
//                    System.out.println();
//                    System.out.println("Can be eliminated not by king!");
//                    System.out.println();
                    return true;
                }

                Cell OldCell = AllyFigure.ActualCell;
                AbstractFigure OldFigureKilled = AllyCell.getOccupation();
                AllyFigure.ActualCell.setOccupation(null);
                AllyFigure.ActualCell = AllyCell;
                AllyFigure.ActualCell.setOccupation(AllyFigure);

//                Check if danger figure can kill king
                for (Cell CellOfDanger : DangerFigure.AllowedMoves()) {

//                    if (CellOfDanger.getOccupation() != null) {
//                        System.out.println("Ocupation: "+ CellOfDanger.getOccupation()+"  Color: "+ CellOfDanger.getOccupiedColor()+ "  My Color: "+DangerFigure.getColor()+ "  class: "+CellOfDanger.getOccupation().getClass().getName());
//                    }

                    if (CellOfDanger.getOccupation() != null && CellOfDanger.getOccupiedColor() != DangerFigure.getColor()
                            && CellOfDanger.getOccupation().getClass().getName().equals("Figures.King")) {

//                        System.out.println("Deleted");

                        CopyAllyCells.remove(AllyCell);
                        i=-1;
                    }

                }
                AllyFigure.ActualCell.setOccupation(OldFigureKilled);
                AllyFigure.ActualCell = OldCell;
                AllyFigure.ActualCell.setOccupation(AllyFigure);
            }
//            System.out.println();
//            System.out.println("Can be closed: "+ AllyFigure.getActualCell() + " " + CopyAllyCells);
//            System.out.println();
            if (CopyAllyCells.size() != 0) return true;
        }


//        System.out.println();
//        System.out.println("Cannot be eliminated at all :c");
//        System.out.println();
        return false;



    }


}
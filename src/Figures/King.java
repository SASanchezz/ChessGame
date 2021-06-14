package Figures;

import static ChessBoard.ChessBoard.getCellSet;
import static ChessBoard.ChessBoard.getBlackFigures;
import static ChessBoard.ChessBoard.getWhiteFigures;
import static Auxiliary.IconChanger.iconChange;


import ChessBoard.Cell;

import java.util.ArrayList;


public class King extends AbstractFigure {
    String Color;
    private boolean Dangered;
    private AbstractFigure DangerFigure;

    public King(Cell SomeCell, int Size, String Color) {
        super(SomeCell, Color);
        Dangered = false;
        ActualCell = SomeCell;
        ActualCell.setOccupation(this);
        this.Color = Color;
        if (Color.equals("White")) {
            ActualCell.setOccupiedColor("White");
            setIcon(iconChange(Size).get("WKing.png"));
        } else {
            ActualCell.setOccupiedColor("Black");
            setIcon(iconChange(Size).get("BKing.png"));
        }
    }

    public boolean isDangered() {
        return Dangered;
    }
    @Override
    public void setDangered(boolean dangered) {
        Dangered = dangered;
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
        System.out.println(toCell.getBoardLoc());
        System.out.println("King move: "+getCellSet().get(toCell.getBoardLoc()).getOccupation() +" " + getCellSet().get(toCell.getBoardLoc()).getOccupiedColor()+" "+ Color);

        Cell OldCell = ActualCell;

        if (AllowedMoves().contains(toCell)) {

            String OldColorKilled = toCell.getOccupiedColor();
            AbstractFigure OldFigureKilled = toCell.getOccupation();
            ActualCell.setOccupation(null);
            ActualCell = toCell;
            ActualCell.setOccupiedColor(Color);
            ActualCell.setOccupation(this);

            if (DangerCheck() == true) {
                System.out.println("Dangered");
                ActualCell.setOccupation(OldFigureKilled);
                ActualCell.setOccupiedColor(OldColorKilled);
                ActualCell = OldCell;
                ActualCell.setOccupiedColor(Color);
                ActualCell.setOccupation(this);

            } else {
                System.out.println("Safe");
                System.out.println(Color+" "+OldColorKilled);
                if (!Color.equals(OldColorKilled)) {
                    if (OldFigureKilled != null) {
                        System.out.println("Is gonna kill" + OldFigureKilled);
                        OldFigureKilled.setBounds(0,0,0,0);
                        getBlackFigures().remove(OldFigureKilled);
                        getWhiteFigures().remove(OldFigureKilled);
                    }
                }
                setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1],
                        ActualCell.getCellSize(), ActualCell.getCellSize());
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

    private boolean IsKingNearby(String NewCell) {
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

    private boolean DangerCheck() {
        if (Color.equals("White")) {
            System.out.println("White");
            for (int bf=0; bf<getBlackFigures().size(); bf++) {
                for (int j = 0; j < getBlackFigures().get(bf).AllowedMoves().size(); j++) {
                    if (ActualCell.equals(getBlackFigures().get(bf).AllowedMoves().get(j))) {
                        return true;
                    }
                }
            }

        } else if (Color.equals("Black")) {
            System.out.println("Black");
            for (int bf = 0; bf < getWhiteFigures().size(); bf++) {
                for (int j = 0; j < getWhiteFigures().get(bf).AllowedMoves().size(); j++) {
                    if (ActualCell.equals(getWhiteFigures().get(bf).AllowedMoves().get(j))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
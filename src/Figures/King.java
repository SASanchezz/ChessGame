package Figures;

import static ChessBoard.ChessBoard.getCellSet;
import static Auxiliary.IconChanger.iconChange;
import static ChessBoard.ChessBoard.getLetters;


import ChessBoard.Cell;

import java.util.ArrayList;


public class King extends AbstractFigure {
    String Color;

    public King(Cell SomeCell, int Size, String Color) {
        super(SomeCell, Color);
        ActualCell = SomeCell;
        ActualCell.setOccupation(this);
        this.Color = Color;
        if (Color.equals("White")) {
            ActualCell.setOccupiedBy("White");
            setIcon(iconChange(Size).get("WKing.png"));
        } else {
            ActualCell.setOccupiedBy("Black");
            setIcon(iconChange(Size).get("BKing.png"));
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
        if (!key[1].equals("8") && getCellSet().get(key[0] + (Integer.parseInt(key[1]) + 1)).getOccupiedBy() != (Color) &&
                !IsKingNearby(key[0] + (Integer.parseInt(key[1])+1)) ) {
            AllowedMoves.add(getCellSet().get(key[0] + (Integer.parseInt(key[1]) + 1)));
        }
//            Down Move
        if (!key[1].equals("1") && getCellSet().get(key[0] + (Integer.parseInt(key[1]) - 1)).getOccupiedBy() != (Color) &&
                !IsKingNearby(key[0] + (Integer.parseInt(key[1])-1)) ) {
            AllowedMoves.add(getCellSet().get(key[0] + (Integer.parseInt(key[1]) - 1)));
        }
//        Move Left
        if (!key[0].equals("a") && getCellSet().get((Character.toString(key[0].charAt(0)-1)) + key[1]).getOccupiedBy() != (Color) &&
                !IsKingNearby(Character.toString(key[0].charAt(0)-1) + key[1]) ) {
            AllowedMoves.add(getCellSet().get(Character.toString(key[0].charAt(0)-1) + key[1]));
        }
//        Move Right
        if (!key[0].equals("h") && getCellSet().get((Character.toString(key[0].charAt(0)+1)) + key[1]).getOccupiedBy() != (Color) &&
                !IsKingNearby(Character.toString(key[0].charAt(0)+1) + key[1]) ) {
            AllowedMoves.add(getCellSet().get(Character.toString(key[0].charAt(0)+1) + key[1]));
        }

//           Up-Right Move
        if (!key[0].equals("h") && !key[1].equals("8") && getCellSet().get((Character.toString(key[0].charAt(0)+1)) + (Integer.parseInt(key[1]) + 1)).getOccupiedBy() != (Color) &&
                !IsKingNearby(Character.toString(key[0].charAt(0)+1) + (Integer.parseInt(key[1]) + 1)) ) {
            AllowedMoves.add(getCellSet().get(Character.toString(key[0].charAt(0)+1) + (Integer.parseInt(key[1]) + 1)));
        }
//          Down-Right Move
        if (!key[0].equals("h") && !key[1].equals("1") && getCellSet().get((Character.toString(key[0].charAt(0)+1)) + (Integer.parseInt(key[1]) - 1)).getOccupiedBy() != (Color) &&
                !IsKingNearby(Character.toString(key[0].charAt(0)+1) + (Integer.parseInt(key[1]) - 1)) ) {
            AllowedMoves.add(getCellSet().get(Character.toString(key[0].charAt(0)+1) + (Integer.parseInt(key[1]) - 1)));
        }
//          Up-Left Move
        if (!key[0].equals("a") && !key[1].equals("8") && getCellSet().get((Character.toString(key[0].charAt(0)-1)) + (Integer.parseInt(key[1]) + 1)).getOccupiedBy() != (Color) &&
                !IsKingNearby(Character.toString(key[0].charAt(0)-1) + (Integer.parseInt(key[1]) + 1)) ) {
            AllowedMoves.add(getCellSet().get(Character.toString(key[0].charAt(0)-1) + (Integer.parseInt(key[1]) + 1)));
        }
//          Down-Left Move
        if (!key[0].equals("a") && !key[1].equals("1") && getCellSet().get((Character.toString(key[0].charAt(0)-1)) + (Integer.parseInt(key[1]) - 1)).getOccupiedBy() != (Color) &&
                !IsKingNearby(Character.toString(key[0].charAt(0)-1) + (Integer.parseInt(key[1]) - 1)) ) {
            AllowedMoves.add(getCellSet().get(Character.toString(key[0].charAt(0)-1) + (Integer.parseInt(key[1]) - 1)));
        }

        return AllowedMoves;
    }


}
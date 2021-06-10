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
        System.out.println("Uppercell of king: " + (key[0]+ (Integer.parseInt(key[1]) + 1)));
        StringSet.add(key[0].charAt(0)+1 + String.valueOf(Integer.parseInt(key[1])+1));
        StringSet.add(key[0].charAt(0)+1 + key[1]);
        StringSet.add(key[0].charAt(0)+1 + String.valueOf(Integer.parseInt(key[1])-1));
        StringSet.add(key[0] + (Integer.parseInt(key[1]) - 1));
        StringSet.add(key[0].charAt(0)-1 + String.valueOf(Integer.parseInt(key[1])-1));
        StringSet.add(key[0].charAt(0)-1 + key[1]);
        StringSet.add(key[0].charAt(0)-1 + String.valueOf(Integer.parseInt(key[1])+1));

        for (int i=0; i<StringSet.size(); i++) {
            Cell SpecificCell = getCellSet().get(StringSet.get(i));
            if (SpecificCell != null) {
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
        System.out.println(key[0] + (Integer.parseInt(key[1]) + 1));
        if (key[1] != "8" && getCellSet().get(key[0] + (Integer.parseInt(key[1]) + 1)).getOccupiedBy() != (Color) &&
                !IsKingNearby(key[0] + Integer.parseInt(key[1])) ) {
            System.out.println("King moves");
            AllowedMoves.add(getCellSet().get(key[0] + (Integer.parseInt(key[1]) + 1)));
        }
//            Down Move
        for (int i = Integer.parseInt(key[1])-1; i >= 1; i--) {
            String CellToAdd = key[0] + String.valueOf(i);
            if (getCellSet().get(CellToAdd).getOccupiedBy() != (Color)) {
                AllowedMoves.add(getCellSet().get(CellToAdd));
                if (getCellSet().get(CellToAdd).getOccupiedBy() != null) break;
            } else break;
        }
//        Move Left
        for (int i = (key[0]).charAt(0)-97-1; i >= 0; i--) {
            String CellToAdd = getLetters()[i] + key[1];
            if (getCellSet().get(CellToAdd).getOccupiedBy() != (Color)) {
                AllowedMoves.add(getCellSet().get(CellToAdd));
                if (getCellSet().get(CellToAdd).getOccupiedBy() != null) break;
            } else break;
        }
//        Move Right
        for (int i = (key[0]).charAt(0)-97+1; i < 8; i++) {
            String CellToAdd = getLetters()[i] + key[1];
            if (getCellSet().get(CellToAdd).getOccupiedBy() != (Color)) {
                AllowedMoves.add(getCellSet().get(CellToAdd));
                if (getCellSet().get(CellToAdd).getOccupiedBy() != null) break;
            } else break;
        }

//           Up-Right Move
        int sign = (key[0]).charAt(0)-97+1;
        for (int num = Integer.parseInt(key[1])+1; num <= 8; num++) {
            if (sign < 8) {
                String CellToAdd = getLetters()[sign] + String.valueOf(num);
                sign++;
                if (getCellSet().get(CellToAdd).getOccupiedBy() != (Color)) {
                    AllowedMoves.add(getCellSet().get(CellToAdd));
                    if (getCellSet().get(CellToAdd).getOccupiedBy() != null) break;
                } else break;
            }
        }
//          Down-Right Move
        sign = (key[0]).charAt(0)-97+1;
        for (int num = Integer.parseInt(key[1])-1; num >= 1; num--) {
            if (sign < 8) {
                String CellToAdd = getLetters()[sign] + String.valueOf(num);
                sign++;
                if (getCellSet().get(CellToAdd).getOccupiedBy() != (Color)) {
                    AllowedMoves.add(getCellSet().get(CellToAdd));
                    if (getCellSet().get(CellToAdd).getOccupiedBy() != null) break;
                } else break;
            }
        }
//          Up-Left Move
        sign = (key[0]).charAt(0)-97-1;
        for (int num = Integer.parseInt(key[1])+1; num <= 8; num++) {
            if (sign >= 0) {
                String CellToAdd = getLetters()[sign] + String.valueOf(num);
                sign--;
                if (getCellSet().get(CellToAdd).getOccupiedBy() != (Color)) {
                    AllowedMoves.add(getCellSet().get(CellToAdd));
                    if (getCellSet().get(CellToAdd).getOccupiedBy() != null) break;
                } else break;
            }
        }
//          Down-Left Move
        sign = (key[0]).charAt(0)-97-1;
        for (int num = Integer.parseInt(key[1])-1; num >= 1; num--) {
            if (sign >= 0) {
                String CellToAdd = getLetters()[sign] + String.valueOf(num);
                sign--;
                if (getCellSet().get(CellToAdd).getOccupiedBy() != (Color)) {
                    AllowedMoves.add(getCellSet().get(CellToAdd));
                    if (getCellSet().get(CellToAdd).getOccupiedBy() != null) break;
                } else break;
            }
        }

        return AllowedMoves;
    }


}
package Figures;

import ChessBoard.Cell;
import Starting.Config;

import java.util.ArrayList;

import static Auxiliary.IconChanger.iconChange;
import static ChessBoard.ChessBoard.getCellSet;
import static ChessBoard.ChessBoard.getLetters;


public class Queen extends AbstractFigure {
    Boolean Color;

    public Queen(Cell SomeCell, int Size, Boolean Color) {
        super(SomeCell, Color);
        ActualCell = SomeCell;
        ActualCell.setOccupation(this);
        if (Config.COLOR.equals("WHITE")) {
            this.Color = Color;
        } else this.Color = !Color;

        if (Color) {
            if (Config.COLOR.equals("WHITE")) {
                ActualCell.setOccupiedColor(true);
                setIcon(iconChange(Size).get("WQueen.png"));
            } else {
                ActualCell.setOccupiedColor(false);
                setIcon(iconChange(Size).get("BQueen.png"));
            }

        } else {
            if (Config.COLOR.equals("BLACK")) {
                ActualCell.setOccupiedColor(true);
                setIcon(iconChange(Size).get("WQueen.png"));
            } else {
                ActualCell.setOccupiedColor(false);
                setIcon(iconChange(Size).get("BQueen.png"));
            }
        }
    }


    @Override
    public ArrayList<Cell> AllowedMoves() {
        ArrayList<Cell> AllowedMoves = new ArrayList<>();
        String[] key = ActualCell.getBoardLoc().split("");

        //            Up Move
        for (int i = Integer.parseInt(key[1])+1; i <= 8; i++) {
            String CellToAdd = key[0] + String.valueOf(i);
            if (getCellSet().get(CellToAdd).getOccupiedColor() != (Color)) {
                AllowedMoves.add(getCellSet().get(CellToAdd));
                if (getCellSet().get(CellToAdd).getOccupation() != null) break;
            } else break;
        }
//            Down Move
        for (int i = Integer.parseInt(key[1])-1; i >= 1; i--) {
            String CellToAdd = key[0] + String.valueOf(i);
            if (getCellSet().get(CellToAdd).getOccupiedColor() != (Color)) {
                AllowedMoves.add(getCellSet().get(CellToAdd));
                if (getCellSet().get(CellToAdd).getOccupation() != null) break;
            } else break;
        }
//        Move Left
        for (int i = (key[0]).charAt(0)-97-1; i >= 0; i--) {
            String CellToAdd = getLetters()[i] + key[1];
            if (getCellSet().get(CellToAdd).getOccupiedColor() != (Color)) {
                AllowedMoves.add(getCellSet().get(CellToAdd));
                if (getCellSet().get(CellToAdd).getOccupation() != null) break;
            } else break;
        }
//        Move Right
        for (int i = (key[0]).charAt(0)-97+1; i < 8; i++) {
            String CellToAdd = getLetters()[i] + key[1];
            if (getCellSet().get(CellToAdd).getOccupiedColor() != (Color)) {
                AllowedMoves.add(getCellSet().get(CellToAdd));
                if (getCellSet().get(CellToAdd).getOccupation() != null) break;
            } else break;
        }

//           Up-Right Move
        int sign = (key[0]).charAt(0)-97+1;
        for (int num = Integer.parseInt(key[1])+1; num <= 8; num++) {
            if (sign < 8) {
                String CellToAdd = getLetters()[sign] + String.valueOf(num);
                sign++;
                if (getCellSet().get(CellToAdd).getOccupiedColor() != (Color)) {
                    AllowedMoves.add(getCellSet().get(CellToAdd));
                    if (getCellSet().get(CellToAdd).getOccupation() != null) {
                        break;
                    }
                } else break;
            }
        }
//          Down-Right Move
        sign = (key[0]).charAt(0)-97+1;
        for (int num = Integer.parseInt(key[1])-1; num >= 1; num--) {
            if (sign < 8) {
                String CellToAdd = getLetters()[sign] + String.valueOf(num);
                sign++;
                if (getCellSet().get(CellToAdd).getOccupiedColor() != (Color)) {
                    AllowedMoves.add(getCellSet().get(CellToAdd));
                    if (getCellSet().get(CellToAdd).getOccupation() != null) break;
                } else break;
            }
        }
//          Up-Left Move
        sign = (key[0]).charAt(0)-97-1;
        for (int num = Integer.parseInt(key[1])+1; num <= 8; num++) {
            if (sign >= 0) {
                String CellToAdd = getLetters()[sign] + String.valueOf(num);
                sign--;
                if (getCellSet().get(CellToAdd).getOccupiedColor() != (Color)) {
                    AllowedMoves.add(getCellSet().get(CellToAdd));
                    if (getCellSet().get(CellToAdd).getOccupation() != null) break;
                } else break;
            }
        }
//          Down-Left Move
        sign = (key[0]).charAt(0)-97-1;
        for (int num = Integer.parseInt(key[1])-1; num >= 1; num--) {
            if (sign >= 0) {
                String CellToAdd = getLetters()[sign] + String.valueOf(num);
                sign--;
                if (getCellSet().get(CellToAdd).getOccupiedColor() != (Color)) {
                    AllowedMoves.add(getCellSet().get(CellToAdd));
                    if (getCellSet().get(CellToAdd).getOccupation() != null) break;
                } else break;
            }
        }

        return AllowedMoves;
    }


}


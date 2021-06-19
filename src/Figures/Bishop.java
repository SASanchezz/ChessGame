package Figures;

import ChessBoard.Cell;
import Starting.Config;

import java.util.ArrayList;

import static Auxiliary.IconChanger.iconChange;
import static ChessBoard.ChessBoard.getCellSet;
import static ChessBoard.ChessBoard.getLetters;


public class Bishop extends AbstractFigure {
    Boolean Color;

    public Bishop(Cell SomeCell, int Size, Boolean Color) {
        super(SomeCell, Color);
        ActualCell = SomeCell;
        ActualCell.setOccupation(this);
        if (Config.COLOR.equals("WHITE")) {
            this.Color = Color;
        } else this.Color = !Color;

        if (Color) {
            if (Config.COLOR.equals("WHITE")) {
                ActualCell.setOccupiedColor(true);
                setIcon(iconChange(Size).get("WBishop.png"));
            } else {
                ActualCell.setOccupiedColor(false);
                setIcon(iconChange(Size).get("BBishop.png"));
            }

        } else {
            if (Config.COLOR.equals("BLACK")) {
                ActualCell.setOccupiedColor(true);
                setIcon(iconChange(Size).get("WBishop.png"));
            } else {
                ActualCell.setOccupiedColor(false);
                setIcon(iconChange(Size).get("BBishop.png"));
            }
        }
    }


    @Override
    public ArrayList<Cell> AllowedMoves() {
        ArrayList<Cell> AllowedMoves = new ArrayList<>();
        String[] key = ActualCell.getBoardLoc().split("");

//           Up-Right Move
        int sign = (key[0]).charAt(0)-97+1;
        for (int num = Integer.parseInt(key[1])+1; num <= 8; num++) {
            if (sign < 8) {
                String CellToAdd = getLetters()[sign] + String.valueOf(num);
                sign++;
                if (getCellSet().get(CellToAdd).getOccupiedColor() != (Color)) {
                    AllowedMoves.add(getCellSet().get(CellToAdd));
                    if (getCellSet().get(CellToAdd).getOccupation() != null) break;
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


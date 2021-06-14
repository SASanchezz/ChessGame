package Figures;

import ChessBoard.Cell;

import java.util.ArrayList;

import static Auxiliary.IconChanger.iconChange;
import static ChessBoard.ChessBoard.getCellSet;
import static ChessBoard.ChessBoard.getLetters;


public class Bishop extends AbstractFigure {
    String Color;

    public Bishop(Cell SomeCell, int Size, String Color) {
        super(SomeCell, Color);
        ActualCell = SomeCell;
        ActualCell.setOccupation(this);
        this.Color = Color;
        if (Color.equals("White")) {
            ActualCell.setOccupiedColor("White");
            setIcon(iconChange(Size).get("WBishop.png"));
        } else {
            ActualCell.setOccupiedColor("Black");
            setIcon(iconChange(Size).get("BBishop.png"));
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
                    if (getCellSet().get(CellToAdd).getOccupiedColor() != null) break;
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
                    if (getCellSet().get(CellToAdd).getOccupiedColor() != null) break;
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
                    if (getCellSet().get(CellToAdd).getOccupiedColor() != null) break;
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
                    if (getCellSet().get(CellToAdd).getOccupiedColor() != null) break;
                } else break;
            }
        }

        return AllowedMoves;
    }


}


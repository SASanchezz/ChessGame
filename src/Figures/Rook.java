package Figures;

import static Auxiliary.GetKey.getKey;
import static ChessBoard.ChessBoard.CellSet;
import static ChessBoard.ChessBoard.getCellSet;
import static Auxiliary.IconChanger.iconChange;

import ChessBoard.Cell;

import java.util.ArrayList;


public class Rook extends AbstractFigure {
    String Color;

    public Rook(Cell SomeCell, int Size, String Color) {
        super(SomeCell, Color);
        ActualCell = SomeCell;
        ActualCell.setOccupation(this);


















































































































        if (Color.equals("White")) {
            ActualCell.setOccupiedBy("White");
            setIcon(iconChange(Size).get("WRook.png"));
        } else {
            ActualCell.setOccupiedBy("Black");
            setIcon(iconChange(Size).get("BRook.png"));
        }
    }


    @Override
    public ArrayList<Cell> AllowedMoves() {
        ArrayList<Cell> AllowedMoves = new ArrayList<>();
        String[] key = ActualCell.getBoardLoc().split("");
        if (!key[1].equals("8")) {
//            Up Move
            for (int i = Integer.parseInt(key[1])+1; i <= 8; i++) {
                String CellToAdd = key[0] + String.valueOf(i);

                if (CellSet.get(CellToAdd).getOccupation() == null || CellSet.get(CellToAdd).getOccupiedBy() != (Color)) {
                    AllowedMoves.add(getCellSet().get(CellToAdd));
                }
            }

        }
        return AllowedMoves;
    }


}
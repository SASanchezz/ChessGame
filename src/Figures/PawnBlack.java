package Figures;

import ChessBoard.Cell;
import static ChessBoard.ChessBoard.getCellSet;
import java.util.ArrayList;
import static Auxiliary.GetKey.getKey;


public class PawnBlack extends AbstractFigure {
    ArrayList<Cell> AllowedHits = new ArrayList<>();


    PawnBlack(Cell StartingCell) {
        super(StartingCell);
    }

    @Override
    public void move(Cell toCell) {
        if (AllowedMoves.contains(toCell)) {
            this.ActualCell = toCell;
            String key = getKey(toCell);
            AllowedMoves.clear();
            AllowedHits.clear();
            if (key != null) {
                String CellAhead = key.charAt(0) + String.valueOf(((int) key.charAt(1) - 1));
                System.out.println("Cell ahead: " + CellAhead);
                AllowedMoves.add(getCellSet().get(CellAhead));
                if (key.charAt(0) != 'h') {
                    String CellRight = key.charAt(0) + 1 + String.valueOf(((int) key.charAt(1) - 1));
                    System.out.println("Cell Right: " + CellRight);
                    AllowedHits.add(getCellSet().get(CellRight));
                }
                if (key.charAt(0) != 'a') {
                    String CellLeft = key.charAt(0) - 1 + String.valueOf(((int) key.charAt(1) - 1));
                    System.out.println("Cell Left: " + CellLeft);
                    AllowedHits.add(getCellSet().get(CellLeft));
                }
            }
        }
    }
}
package Figures;

import ChessBoard.Cell;

import static Auxiliary.IconChanger.iconChange;
import static ChessBoard.ChessBoard.getCellSet;
import java.util.ArrayList;
import static Auxiliary.GetKey.getKey;

public class PawnWhite extends AbstractFigure{


    public PawnWhite(Cell SomeCell, int Size) {
        super(SomeCell);
        setIcon(iconChange(Size).get("WPawn.png"));
    }


    @Override
    public void move (Cell toCell) {
        if (AllowedMoves(toCell).contains(toCell)) {
            this.ActualCell = toCell;
        }

    }


    @Override
    public ArrayList AllowedMoves(Cell cell) {
        ArrayList<Cell> AllowedMoves = new ArrayList<>();
        String key = getKey(cell);
        if (key != null) {

            String CellAhead = key.charAt(0) + String.valueOf(((int) key.charAt(1) + 1));
            //System.out.println("Cell ahead: " + CellAhead);
            AllowedMoves.add(getCellSet().get(CellAhead));
        }

        return AllowedMoves;
    }

    public ArrayList AllowedHits(Cell cell) {
        ArrayList<Cell> AllowedHits = new ArrayList<>();
        String key = getKey(cell);

        if (key != null) {

            if (key.charAt(0) != 'h') {
                String CellRight = key.charAt(0)+1 + String.valueOf(((int) key.charAt(1) + 1));
                //System.out.println("Cell Right: "+ CellRight);
                AllowedHits.add(getCellSet().get(CellRight));
            }
            if (key.charAt(0) != 'a') {
                String CellLeft = key.charAt(0)-1 + String.valueOf(((int) key.charAt(1) + 1));
                //System.out.println("Cell Left: "+ CellLeft);
                AllowedHits.add(getCellSet().get(CellLeft));
            }
        }

        return AllowedHits;
    }
}

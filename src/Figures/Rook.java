package Figures;

import static Auxiliary.GetKey.getKey;
import static ChessBoard.ChessBoard.getCellSet;
import static Auxiliary.IconChanger.iconChange;

import ChessBoard.Cell;



public class Rook extends AbstractFigure {

    public Rook(Cell StartingCell, int Size, boolean Color) {
        super(StartingCell);
        if (Color) {
            setIcon(iconChange(Size).get("WRook.png"));
        } else setIcon(iconChange(Size).get("BRook.png"));

    }

    @Override
    public void move(Cell toCell) {
        if (AllowedMoves.contains(toCell)) {
            this.ActualCell = toCell;
            String key = getKey(toCell);
            AllowedMoves.clear();
            if (key != null) {

                String CellAhead = key.charAt(0) + String.valueOf(((int) key.charAt(1) + 1));
                System.out.println("Cell ahead: " + CellAhead);
                AllowedMoves.add(getCellSet().get(CellAhead));
            }
        }
    }
}
package Figures;

import static Auxiliary.GetKey.getKey;
import static ChessBoard.ChessBoard.getCellSet;
import static Auxiliary.IconChanger.iconChange;

import ChessBoard.Cell;

import java.util.ArrayList;


public class Rook extends AbstractFigure {

    public Rook(Cell SomeCell, int Size, boolean Color) {
        super(SomeCell);
        if (Color) {
            setIcon(iconChange(Size).get("WRook.png"));
        } else setIcon(iconChange(Size).get("BRook.png"));

    }

    @Override
    public void move(Cell toCell) {
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
}
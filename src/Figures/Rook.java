package Figures;

import static Auxiliary.GetKey.getKey;
import static ChessBoard.ChessBoard.getCellSet;
import static Auxiliary.IconChanger.iconChange;

import ChessBoard.Cell;

import java.util.ArrayList;


public class Rook extends AbstractFigure {

    public Rook(Cell SomeCell, int Size, String Color) {
        super(SomeCell, Color);
        if (Color == "White") {
            setIcon(iconChange(Size).get("WRook.png"));
        } else setIcon(iconChange(Size).get("BRook.png"));

    }

    @Override
    public void move(Cell toCell) {
        if (AllowedMoves().contains(toCell)) {
            this.ActualCell = toCell;

        }
    }

    @Override
    public ArrayList<Cell> AllowedMoves() {
        ArrayList<Cell> AllowedMoves = new ArrayList<>();
        String key = ActualCell.getBoardLoc();
        if (key != null) {


            //AllowedMoves.add(getCellSet().get(CellAhead));
        }

        return AllowedMoves;
    }
}
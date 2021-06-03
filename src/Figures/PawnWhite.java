package Figures;

import ChessBoard.Cell;

import static Auxiliary.IconChanger.iconChange;
import static ChessBoard.ChessBoard.getCellSet;
import java.util.ArrayList;

public class PawnWhite extends AbstractFigure{


    public PawnWhite(Cell SomeCell, int Size) {
        super(SomeCell);
        ActualCell = SomeCell;
        SomeCell.setOccupation(this);
        setIcon(iconChange(Size).get("WPawn.png"));
    }




    @Override
    public ArrayList<Cell> AllowedMoves() {
        ArrayList<Cell> AllowedMoves = new ArrayList<>();
        String key = ActualCell.getBoardLoc();
        if (!ActualCell.getBoardLoc().split("")[1].equals("8")) {

            String CellAhead = key.charAt(0) + Character.toString(key.charAt(1) + 1);

            System.out.println("HERE " + getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) + 1)));
            for (String child : getCellSet().keySet()) {
                System.out.println(child + " - " + getCellSet().get(child));
            }
            System.out.println();
            if (!getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) + 1)).getOccupied()) {
                AllowedMoves.add(getCellSet().get(CellAhead));
            }
            if (key.split("")[1].equals("2") && !getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) + 1)).getOccupied()) {
                CellAhead = key.charAt(0) + Character.toString(key.charAt(1) + 2);
                AllowedMoves.add(getCellSet().get(CellAhead));

            }

        }
            return AllowedMoves;
    }

    public ArrayList AllowedHits(Cell cell) {
        ArrayList<Cell> AllowedHits = new ArrayList<>();
        String key = cell.getBoardLoc();

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

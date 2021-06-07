package Figures;

import ChessBoard.Cell;

import static Auxiliary.IconChanger.iconChange;
import static ChessBoard.ChessBoard.getCellSet;
import java.util.ArrayList;

public class PawnWhite extends AbstractFigure{
    String Color = "White";

    public PawnWhite(Cell SomeCell, int Size, String Color) {
        super(SomeCell, Color);
        ActualCell = SomeCell;
        ActualCell.setOccupation(this);
        ActualCell.setOccupiedBy("White");
        setIcon(iconChange(Size).get("WPawn.png"));
    }


    @Override
    public void move (Cell toCell) {

        if (AllowedMoves().contains(toCell)) {
            ActualCell.setOccupation(null);
            ActualCell = toCell;
            ActualCell.setOccupiedBy(Color);
            ActualCell.setOccupation(this);
            setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1], ActualCell.getCellSize(), ActualCell.getCellSize());

        } else if (AllowedHits().contains(toCell)) {
            if (toCell.getOccupation() != null && Color != toCell.getOccupiedBy()) {
                toCell.getOccupation().setBounds(0,0,0,0);
                ActualCell.setOccupation(null);
                ActualCell = toCell;
                ActualCell.setOccupiedBy(Color);
                ActualCell.setOccupation(this);
                setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1],
                        ActualCell.getCellSize(), ActualCell.getCellSize());
            }
        }
    }


    @Override
    public ArrayList<Cell> AllowedMoves() {
        ArrayList<Cell> AllowedMoves = new ArrayList<>();
        String key = ActualCell.getBoardLoc();
        if (!ActualCell.getBoardLoc().split("")[1].equals("8")) {

            String CellAhead = key.charAt(0) + Character.toString(key.charAt(1) + 1);

            if (getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) + 1)).getOccupiedBy() == null ) {
                AllowedMoves.add(getCellSet().get(CellAhead));
            }
            if (key.split("")[1].equals("2") &&
                    getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) + 1)).getOccupation() == null &&
                    getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) + 2)).getOccupation() == null) {
                CellAhead = key.charAt(0) + Character.toString(key.charAt(1) + 2);
                AllowedMoves.add(getCellSet().get(CellAhead));

            }

        }
            return AllowedMoves;
    }

    public ArrayList AllowedHits() {
        ArrayList<Cell> AllowedHits = new ArrayList<>();
        String key = ActualCell.getBoardLoc();

        if (key != null) {

            if (key.charAt(0) != 'h') {
                String CellRight = Character.toString(key.charAt(0)+1) + Character.toString(key.charAt(1) + 1);
                AllowedHits.add(getCellSet().get(CellRight));
            }
            if (key.charAt(0) != 'a') {
                String CellLeft = Character.toString(key.charAt(0)-1) + Character.toString(key.charAt(1) + 1);
                AllowedHits.add(getCellSet().get(CellLeft));
            }
        }
        return AllowedHits;
    }
}

package Figures;

import ChessBoard.Cell;
import static Auxiliary.IconChanger.iconChange;
import static ChessBoard.ChessBoard.getCellSet;
import java.util.ArrayList;


public class PawnBlack extends AbstractFigure {
    String Color = "Black";


    public PawnBlack(Cell SomeCell, int Size, String Color) {
        super(SomeCell, Color);
        ActualCell = SomeCell;
        ActualCell.setOccupation(this);
        ActualCell.setOccupiedBy("Black");

        setIcon(iconChange(Size).get("BPawn.png"));

    }

    @Override
    public void move (Cell toCell) {
        System.out.println("Moving...");


        if (AllowedMoves().contains(toCell)) {
            System.out.println("Real moving..."+ Color);
            if (toCell.getOccupation() != null && Color != toCell.getOccupiedBy()) {
                toCell.getOccupation().setVisible(false);
            }
            ActualCell.setOccupation(null);
            ActualCell = toCell;
            ActualCell.setOccupiedBy(Color);
            ActualCell.setOccupation(this);
            setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1], ActualCell.getCellSize(), ActualCell.getCellSize());

        } else if (AllowedHits().contains(toCell)) {
            if (toCell != null && Color != toCell.getOccupiedBy()) {
                System.out.println(Color+" " + toCell.getOccupiedBy());

                toCell.getOccupation().setBounds(0,0,0,0);
                ActualCell.setOccupation(null);
                ActualCell = toCell;
                ActualCell.setOccupiedBy(Color);
                ActualCell.setOccupation(this);
                setBounds(ActualCell.getREAL_COORDINATES()[0], ActualCell.getREAL_COORDINATES()[1], ActualCell.getCellSize(), ActualCell.getCellSize());
            }
        }
    }


    @Override
    public ArrayList<Cell> AllowedMoves() {
        ArrayList<Cell> AllowedMoves = new ArrayList<>();
        String key = ActualCell.getBoardLoc();
        if (!ActualCell.getBoardLoc().split("")[1].equals("1")) {

            String CellAhead = key.charAt(0) + Character.toString(key.charAt(1) - 1);

            if (getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) - 1)).getOccupiedBy() == null ) {
                AllowedMoves.add(getCellSet().get(CellAhead));
            }
            if (key.split("")[1].equals("7") && getCellSet().get(key.charAt(0) + Character.toString(key.charAt(1) + 1)).getOccupiedBy() == null) {
                CellAhead = key.charAt(0) + Character.toString(key.charAt(1) - 2);
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
                String CellRight = Character.toString(key.charAt(0)+1) + Character.toString(key.charAt(1) - 1);
                AllowedHits.add(getCellSet().get(CellRight));
            }
            if (key.charAt(0) != 'a') {
                String CellLeft = Character.toString(key.charAt(0)-1) + Character.toString(key.charAt(1) - 1);
                AllowedHits.add(getCellSet().get(CellLeft));
            }
        }
        return AllowedHits;
    }
}
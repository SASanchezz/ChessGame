package Figures;

import ChessBoard.Cell;

import java.util.ArrayList;

import static Auxiliary.IconChanger.iconChange;
import static ChessBoard.ChessBoard.getCellSet;
import static ChessBoard.ChessBoard.getLetters;


public class Knight extends AbstractFigure {
    String Color;

    public Knight(Cell SomeCell, int Size, String Color) {
        super(SomeCell, Color);
        ActualCell = SomeCell;
        ActualCell.setOccupation(this);
        this.Color = Color;
        if (Color.equals("White")) {
            ActualCell.setOccupiedColor("White");
            setIcon(iconChange(Size).get("WKnight.png"));
        } else {
            ActualCell.setOccupiedColor("Black");
            setIcon(iconChange(Size).get("BKnight.png"));
        }
    }


    @Override
    public ArrayList<Cell> AllowedMoves() {
        ArrayList<Cell> AllowedMoves = new ArrayList<>();
        String[] key = ActualCell.getBoardLoc().split("");
        int sign = (key[0]).charAt(0)-97;
        int num = Integer.parseInt(key[1]);


        if (num <= 6 && sign != 7) {
            String G1 = getLetters()[sign+1] + (num + 2);
            if (getCellSet().get(G1).getOccupiedColor() == Color );
            else AllowedMoves.add(getCellSet().get(G1));

        }

        if (num != 8 && sign <= 5) {
            String G2 = getLetters()[sign+2] + (num + 1);
            if (getCellSet().get(G2).getOccupiedColor() == Color );
            else AllowedMoves.add(getCellSet().get(G2));

        }

        if (num != 1 && sign <= 5) {
            String G3 = getLetters()[sign+2] + (num - 1);
            if (getCellSet().get(G3).getOccupiedColor() == Color );
            else AllowedMoves.add(getCellSet().get(G3));

        }

        if (num >= 3 && sign != 7) {
            String G4 = getLetters()[sign+1] + (num - 2);
            if (getCellSet().get(G4).getOccupiedColor() == Color );
            else AllowedMoves.add(getCellSet().get(G4));

        }

        if (num >= 3 && sign != 0) {
            String G5 = getLetters()[sign-1] + (num - 2);
            if (getCellSet().get(G5).getOccupiedColor() == Color );
            else AllowedMoves.add(getCellSet().get(G5));

        }

        if (num != 1 && sign >= 2) {
            String G6 = getLetters()[sign-2] + (num - 1);
            if (getCellSet().get(G6).getOccupiedColor() == Color );
            else AllowedMoves.add(getCellSet().get(G6));

        }

        if (num != 8 && sign >= 2) {
            String G7 = getLetters()[sign-2] + (num + 1);
            if (getCellSet().get(G7).getOccupiedColor() == Color );
            else AllowedMoves.add(getCellSet().get(G7));

        }

        if (num <= 6 && sign != 0) {
            String G8 = getLetters()[sign-1] + (num + 2);
            if (getCellSet().get(G8).getOccupiedColor() == Color );
            else AllowedMoves.add(getCellSet().get(G8));

        }


        return AllowedMoves;
    }


}


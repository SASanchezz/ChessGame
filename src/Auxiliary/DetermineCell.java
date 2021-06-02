package Auxiliary;

import ChessBoard.Cell;
import static ChessBoard.ChessBoard.getCellSet;



public class DetermineCell {
    public static Cell determineCell(int X, int Y) {
        for (String cell: getCellSet().keySet()) {
            int Size = getCellSet().get(cell).getCellSize();
            int[] Coordinates = getCellSet().get(cell).getREAL_COORDINATES();
            if (X > Coordinates[0] && Y > Coordinates[1] && X < Coordinates[0] + Size && Y < Coordinates[1] + Size) {
                return getCellSet().get(cell);
            }
        }


    }
}

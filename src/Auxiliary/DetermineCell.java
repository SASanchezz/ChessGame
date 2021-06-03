package Auxiliary;

import ChessBoard.Cell;
import static ChessBoard.ChessBoard.getCellSet;



public class DetermineCell {
    public static Cell determineCell(int X, int Y) {
        for (String cell: getCellSet().keySet()) {
            int Size = getCellSet().get(cell).getCellSize();
            int[] Coordinates = getCellSet().get(cell).getREAL_COORDINATES();
            //System.out.println("X:  "+X+"  Y: "+Y+"  Size: "+Size );
            //System.out.println("coord 0: "+Coordinates[0]+"  coord 1: "+Coordinates[1] );
            if (X > Coordinates[0] && Y > Coordinates[1] && X < Coordinates[0] + Size && Y < Coordinates[1] + Size) {
                System.out.println(cell);
                return getCellSet().get(cell);
            }
        }
        return null;
    }
}

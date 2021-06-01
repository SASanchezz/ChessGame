package ChessBoard;

import java.awt.*;

public class Cell {
    Color COLOR;
    int[] REAL_COORDINATES = new int[2];

    Cell(int x, int y, int Size) {
        REAL_COORDINATES[0] = x;
        REAL_COORDINATES[1] = y;
    }
}

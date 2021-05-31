package ChessBoard;

import java.awt.*;

public class Cell {
    Color COLOR;
    String BOARD_COORDINATES;
    int[] REAL_COORDINATES = new int[2];

    Cell(int x, int y) {
        REAL_COORDINATES[0] = x;
        REAL_COORDINATES[1] = y;
    }
}

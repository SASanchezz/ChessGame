package Auxiliary;

import ChessBoard.Cell;

import static ChessBoard.ChessBoard.getCellSet;



public class GetKey {
    public static String getKey(Cell desiredCell) {
        for (String key : getCellSet().keySet()) {
            Object obj = getCellSet().get(key);
            if (key != null) {
                if (desiredCell.equals(obj)) {
                    return key;
                }
            }
        }
        return null;
    }
}

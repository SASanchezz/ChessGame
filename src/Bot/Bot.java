package Bot;

import ChessBoard.Cell;
import Figures.AbstractFigure;
import Figures.King;
import Starting.Config;

import java.util.ArrayList;
import java.util.Random;

import static ChessBoard.ChessBoard.*;

public class Bot {


    public static void RandomMover() {
        Random random_method = new Random();
        boolean MyColor = !Config.COLOR.equals("WHITE"); // true - white; false - black
        King MainWKing = WKing;
        King MainBKing = BKing;
        if (! Config.COLOR.equals("WHITE")) {
            MainWKing = BKing;
            MainBKing = WKing;
        }
        ArrayList<AbstractFigure> MyFigures;
        if (MyColor) {
            MyFigures = new ArrayList<>(getWhiteFigures());
            MyFigures.add(MainWKing);
        } else {
            MyFigures = new ArrayList<>(getBlackFigures());
            MyFigures.add((MainBKing));
        }

        for (AbstractFigure MyFigure: MyFigures ) {



            for (Cell AttackCell: MyFigure.AllowedMoves()) {

                if (AttackCell.getOccupation() != null && AttackCell.getOccupiedColor() != MyColor) {
                    System.out.println("Attack figure: "+ MyFigure.getActualCell() + " to cell: "+ AttackCell );
                    MyFigure.move(AttackCell);
                    return;
                }
            }
        }
        AbstractFigure RandomFigure;
        ArrayList<Cell> MainList;

//        in case of check
        while (true){

            while (true) {

                int RandomInt = random_method.nextInt(MyFigures.size());
                RandomFigure = MyFigures.get(RandomInt);

                if (RandomFigure.getClass().getName().equals("Figures.PawnWhite") || RandomFigure.getClass().getName().equals("Figures.PawnBlack")) {

                    MainList = RandomFigure.StraightAllowedMoves();
                } else MainList = RandomFigure.AllowedMoves();

                if (MainList.size() != 0) {
                    break;
                }
            }

            Cell PreviousCell = RandomFigure.getActualCell();

            int RandomInt = random_method.nextInt(MainList.size());
            Cell RandomCell = MainList.get(RandomInt);
            System.out.println(RandomFigure.getActualCell().getBoardLoc() + " --> " + RandomCell.getBoardLoc());
            RandomFigure.move(RandomCell);

            if (! PreviousCell.equals(RandomFigure.getActualCell())) break;
        }

    }
}

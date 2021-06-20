package Bot;

import ChessBoard.Cell;
import Figures.AbstractFigure;
import Starting.Config;

import java.util.ArrayList;
import java.util.Random;

import static ChessBoard.ChessBoard.*;

public class Bot {


    public static void RandomMover() {
        Random random_method = new Random();
        boolean MyColor = !Config.COLOR.equals("WHITE"); // true - white; false - black
        ArrayList<AbstractFigure> MyFigures;
        MyFigures = (MyColor) ? getWhiteFigures() : getBlackFigures();
        for (AbstractFigure MyFigure: MyFigures ) {
            for (Cell AttackCell: MyFigure.AllowedMoves()) {
                if (AttackCell.getOccupation() != null) {
                    MyFigure.move(AttackCell);
                    return;
                }
            }
        }
        System.out.println("NO RETURN OF BOT");
        AbstractFigure RandomFigure;

        while (true) {
            int RandomInt = random_method.nextInt(MyFigures.size());
            RandomFigure = MyFigures.get(RandomInt);

            if (RandomFigure.AllowedMoves().size() != 0) {
                break;
            }
        }



        int RandomInt = random_method.nextInt(RandomFigure.AllowedMoves().size());
        Cell RandomCell = RandomFigure.AllowedMoves().get(RandomInt);
        System.out.println(RandomFigure.getActualCell().getBoardLoc() + " --> " + RandomCell.getBoardLoc());
        RandomFigure.move(RandomCell);


    }
}

package ChessBoard;

import Figures.PawnBlack;
import Figures.PawnWhite;
import Figures.Rook;
import Starting.StartingMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ChessBoard extends JFrame{
    private static HashMap<String, Cell> CellSet = new HashMap<>();
    public static HashMap<String, Cell> getCellSet() {
        return CellSet;
    }

    String[] letters = {
            "a", "b", "c", "d", "e", "f", "g", "h"
    };

    public ChessBoard() {

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setContentPane(new DrawBoard());
        setVisible(true);
        repaint();
    }

    public void DrawFigures() {
        int Size = ChessBoard.this.getHeight()/8;
//      White Pawns
        for (int i=0; i<8; i++) {
            Cell ItsCell = CellSet.get(letters[i]+2);
            System.out.println(ItsCell.getREAL_COORDINATES()[0]);
            PawnWhite Pawn = new PawnWhite(ItsCell, Size);
            Pawn.setBounds(ItsCell.getREAL_COORDINATES()[0], ItsCell.getREAL_COORDINATES()[1], ItsCell.getCellSize(), ItsCell.getCellSize());
            getContentPane().add(Pawn);
        }
//      Black Pawns
        for (int i=0; i<8; i++) {
            Cell ItsCell = CellSet.get(letters[i]+7);
            System.out.println(ItsCell.getREAL_COORDINATES()[0]);
            PawnBlack Pawn = new PawnBlack(ItsCell, Size);
            Pawn.setBounds(ItsCell.getREAL_COORDINATES()[0], ItsCell.getREAL_COORDINATES()[1], ItsCell.getCellSize(), ItsCell.getCellSize());
            getContentPane().add(Pawn);
        }
//      White Rooks
        Rook WLeftRook = new Rook(CellSet.get("a1"), Size, true);
        WLeftRook.setBounds(CellSet.get("a1").getREAL_COORDINATES()[0], CellSet.get("a1").getREAL_COORDINATES()[1], CellSet.get("a1").getCellSize(), CellSet.get("a1").getCellSize());
        Rook WRightRook = new Rook(CellSet.get("h1"), Size, true);
        WRightRook.setBounds(CellSet.get("h1").getREAL_COORDINATES()[0], CellSet.get("h1").getREAL_COORDINATES()[1], CellSet.get("h1").getCellSize(), CellSet.get("h1").getCellSize());
//      Black Rooks
        Rook BLeftRook = new Rook(CellSet.get("a8"), Size, false);
        BLeftRook.setBounds(CellSet.get("a8").getREAL_COORDINATES()[0], CellSet.get("a8").getREAL_COORDINATES()[1], CellSet.get("a8").getCellSize(), CellSet.get("a8").getCellSize());
        Rook BRightRook = new Rook(CellSet.get("h8"), Size, false);
        BRightRook.setBounds(CellSet.get("h8").getREAL_COORDINATES()[0], CellSet.get("h8").getREAL_COORDINATES()[1], CellSet.get("h8").getCellSize(), CellSet.get("h8").getCellSize());
        getContentPane().add(WLeftRook);getContentPane().add(WRightRook);getContentPane().add(BLeftRook);getContentPane().add(BRightRook);

    }




    class DrawBoard extends JPanel {

        DrawBoard() {
            setLayout(null);

        }

        public void paintComponent (Graphics g) {
            setBackground(Color.ORANGE);

            add(ExitButton());
            add(ReturnButton());

            Graphics2D g2 = (Graphics2D) g;
            super.paintComponent(g);

            int WindowHeight = ChessBoard.this.getHeight();
            int WindowWidth = ChessBoard.this.getWidth();
            int CellSize = WindowHeight / 8;

            System.out.println("gen width: " + WindowWidth + " Cell width: " + CellSize);


            setLayout(new GridLayout(8, 8));

            for (int i = 1; i <= 8; i++) {
                for (int j = 0; j <8; j++) {
                    if ((j + i) % 2 == 0) {
                        g2.setColor(Color.getHSBColor(1.40F, 0.98F, 0.4F));
                    } else {
                        g2.setColor(Color.getHSBColor(1.40F, 0, 0.85F));

                    }
                    CellSet.put(letters[j] + (9 - i), new Cell(((WindowWidth / 4) - (CellSize / 2)) + (j * CellSize), (i - 1) * CellSize, CellSize));
                    //System.out.print(letters[j] + (9 - i)+";  ");

                    g2.fillRect(((WindowWidth / 4) - (CellSize / 2)) + (j * CellSize), (i - 1) * CellSize, CellSize, CellSize);
                    //System.out.println((WindowWidth / 4) +"+"+ j * CellSize + " " + (i - 1) * CellSize + " " + CellSize + " " + CellSize);
                }

            }

            //repaint();
            DrawFigures();



        }
        private JButton ExitButton() {
            JButton button = new JButton("Exit");
            button.setBounds(ChessBoard.this.getWidth()-80, 0, 80, 30);
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(1);
                }
            } );
            return button;
        }

        private JButton ReturnButton() {
            JButton button = new JButton("To menu");
            button.setBounds(ChessBoard.this.getWidth()-100, ChessBoard.this.getHeight()-30, 100, 30);
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    dispose();
                    new StartingMenu();
                }
            } );
            return button;
        }
    }

}

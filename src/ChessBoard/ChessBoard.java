package ChessBoard;

import Starting.StartingMenu;

import Figures.PawnBlack;
import Figures.PawnWhite;
import Figures.Rook;
import Figures.Bishop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class ChessBoard extends JFrame{
    public static HashMap<String, Cell> CellSet = new HashMap<>();
    public static HashMap<String, Cell> getCellSet() {
        return CellSet;
    }

    private static String[] letters = {
            "a", "b", "c", "d", "e", "f", "g", "h"
    };
    public static String[] getLetters() {
        return letters;
    }


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
            PawnWhite Pawn = new PawnWhite(ItsCell, Size, "White");
            getContentPane().add(Pawn);
        }
//      Black Pawns
        for (int i=0; i<8; i++) {
            Cell ItsCell = CellSet.get(letters[i]+7);
            PawnBlack Pawn = new PawnBlack(ItsCell, Size, "Black");
            getContentPane().add(Pawn);
        }
//      White Rooks
        Rook WLeftRook = new Rook(CellSet.get("a1"), Size, "White");
        Rook WRightRook = new Rook(CellSet.get("h1"), Size, "White");
//      Black Rooks
        Rook BLeftRook = new Rook(CellSet.get("a8"), Size, "Black");
        Rook BRightRook = new Rook(CellSet.get("h8"), Size, "Black");
        getContentPane().add(WLeftRook);getContentPane().add(WRightRook);getContentPane().add(BLeftRook);getContentPane().add(BRightRook);

//      White Bishop
        Bishop WLeftBishop = new Bishop(CellSet.get("c1"), Size, "White");
        Bishop WRightBishop = new Bishop(CellSet.get("f1"), Size, "White");
//      Black Bishop
        Bishop BLeftBishop = new Bishop(CellSet.get("c8"), Size, "Black");
        Bishop BRightBishop = new Bishop(CellSet.get("f8"), Size, "Black");
        getContentPane().add(WLeftBishop);getContentPane().add(WRightBishop);getContentPane().add(BLeftBishop);getContentPane().add(BRightBishop);

    }




    class DrawBoard extends JPanel {

        DrawBoard() {

            setLayout(null);



        }
        boolean DrawFiguresOnce = true;
        public void paintComponent (Graphics g) {

            setBackground(Color.ORANGE);

            add(ExitButton());
            add(ReturnButton());

            Graphics2D g2 = (Graphics2D) g;
            super.paintComponent(g);

            int WindowHeight = ChessBoard.this.getHeight();
            int WindowWidth = ChessBoard.this.getWidth();
            int CellSize = WindowHeight / 8;


            setLayout(new GridLayout(8, 8));

            for (int i = 1; i <= 8; i++) {
                for (int j = 0; j <8; j++) {
                    if ((j + i) % 2 == 0) {
                        g2.setColor(Color.getHSBColor(1.40F, 0.98F, 0.4F));
                    } else {
                        g2.setColor(Color.getHSBColor(1.40F, 0, 0.85F));

                    }
                    if (DrawFiguresOnce) {
                        CellSet.put(letters[j] + (9 - i), new Cell(((WindowWidth / 4) - (CellSize / 2)) + (j * CellSize), (i - 1) * CellSize, CellSize, letters[j] + (9 - i)));

                    }

                    g2.fillRect(((WindowWidth / 4) - (CellSize / 2)) + (j * CellSize), (i - 1) * CellSize, CellSize, CellSize);
                }

            }

            if (DrawFiguresOnce) {
                DrawFiguresOnce = false;
                DrawFigures();
            }
            repaint();



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

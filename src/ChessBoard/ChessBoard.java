package ChessBoard;

import Starting.StartingMenu;

import Figures.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class ChessBoard extends JFrame{
    private static HashMap<String, Cell> CellSet = new HashMap<>();
    public static HashMap<String, Cell> getCellSet() {
        return CellSet;
    }

    private static ArrayList<AbstractFigure> BlackFigures = new ArrayList<>();
    private static ArrayList<AbstractFigure> WhiteFigures = new ArrayList<>();
    public static ArrayList<AbstractFigure> getBlackFigures() {
        return BlackFigures;
    }
    public static ArrayList<AbstractFigure> getWhiteFigures() {
        return WhiteFigures;
    }

    public static void setBlackFigures(ArrayList<AbstractFigure> blackFigures) {
        BlackFigures = blackFigures;
    }
    public static void setWhiteFigures(ArrayList<AbstractFigure> whiteFigures) {
        WhiteFigures = whiteFigures;
    }

    public static King WKing;
    public static King BKing;

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
            WhiteFigures.add(Pawn);
        }
//      Black Pawns
        for (int i=0; i<8; i++) {
            Cell ItsCell = CellSet.get(letters[i]+7);
            PawnBlack Pawn = new PawnBlack(ItsCell, Size, "Black");
            getContentPane().add(Pawn);
            BlackFigures.add(Pawn);
        }
//      White Rooks
        Rook WLeftRook = new Rook(CellSet.get("a1"), Size, "White");
        Rook WRightRook = new Rook(CellSet.get("h1"), Size, "White");
//      Black Rooks
        Rook BLeftRook = new Rook(CellSet.get("a8"), Size, "Black");
        Rook BRightRook = new Rook(CellSet.get("h8"), Size, "Black");
        getContentPane().add(WLeftRook);getContentPane().add(WRightRook);getContentPane().add(BLeftRook);getContentPane().add(BRightRook);
        WhiteFigures.add(WLeftRook); WhiteFigures.add(WRightRook);
        BlackFigures.add(BLeftRook); BlackFigures.add(BRightRook);


//      White Bishop
        Bishop WLeftBishop = new Bishop(CellSet.get("c1"), Size, "White");
        Bishop WRightBishop = new Bishop(CellSet.get("f1"), Size, "White");
//      Black Bishop
        Bishop BLeftBishop = new Bishop(CellSet.get("c8"), Size, "Black");
        Bishop BRightBishop = new Bishop(CellSet.get("f8"), Size, "Black");
        getContentPane().add(WLeftBishop);getContentPane().add(WRightBishop);getContentPane().add(BLeftBishop);getContentPane().add(BRightBishop);
        WhiteFigures.add(WLeftBishop); WhiteFigures.add(WRightBishop);
        BlackFigures.add(BLeftBishop); BlackFigures.add(BRightBishop);

//      White Knight
        Knight WLeftKnight = new Knight(CellSet.get("b1"), Size, "White");
        Knight WRightKnight = new Knight(CellSet.get("g1"), Size, "White");
//      Black Knight
        Knight BLeftKnight = new Knight(CellSet.get("b8"), Size, "Black");
        Knight BRightKnight = new Knight(CellSet.get("g8"), Size, "Black");
        getContentPane().add(WLeftKnight);getContentPane().add(WRightKnight);getContentPane().add(BLeftKnight);getContentPane().add(BRightKnight);
        WhiteFigures.add(WLeftKnight); WhiteFigures.add(WRightKnight);
        BlackFigures.add(BLeftKnight); BlackFigures.add(BRightKnight);


//      White Queen
        Queen WQueen = new Queen(CellSet.get("d1"), Size, "White");
//      Black Queen
        Queen BQueen = new Queen(CellSet.get("d8"), Size, "Black");
        getContentPane().add(WQueen);getContentPane().add(BQueen);
        WhiteFigures.add(WQueen); BlackFigures.add(BQueen);

//      White King
        WKing = new King(CellSet.get("e1"), Size, "White");
//      Black King
        BKing = new King(CellSet.get("e8"), Size, "Black");
        getContentPane().add(WKing);getContentPane().add(BKing);
    }


    class DrawBoard extends JPanel {
        DrawBoard() {


            setLayout(null);

        }
        boolean DrawFiguresOnce = true;
        public void paintComponent (Graphics g) {
//            setBackground(Color.ORANGE);



            add(ExitButton());
            add(ReturnButton());

            Graphics2D g2 = (Graphics2D) g;
            super.paintComponent(g);

            ImageIcon background = new ImageIcon("images/background.jpg");
            g2.drawImage(background.getImage(), 0, 0, null);

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

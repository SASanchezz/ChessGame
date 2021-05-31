package ChessBoard;

import Starting.StartingMenu;

import javax.swing.*;
import javax.swing.text.EditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ChessBoard extends JFrame{
    protected static HashMap<String, Cell> CellSet = new HashMap<>();

    public ChessBoard() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setContentPane(new DrawBoard());

        setVisible(true);
    }


    class MyPanel extends JPanel {
        public MyPanel() {
            setBackground(Color.ORANGE);
            setLayout(new GridBagLayout());

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.CENTER;


            add(new DrawBoard(), c);
        }

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

            String[] letters = {
                    "a", "b", "c", "d", "e", "f", "g", "h"
            };
            setLayout(new GridLayout(8, 8));

            for (int i = 1; i <= 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((j + i) % 2 == 0) {
                        g2.setColor(Color.getHSBColor(1.40F, 0.98F, 0.3F));
                    } else {
                        g2.setColor(Color.WHITE);

                    }
                    CellSet.put(letters[j] + (9-i), new Cell(((WindowWidth / 4) - (CellSize / 2)) + (j * CellSize), (i - 1) * CellSize));
                    //System.out.print(letters[j] + (9-i) +";  ");

                    g2.fillRect(((WindowWidth / 4) - (CellSize / 2)) + (j * CellSize), (i - 1) * CellSize, CellSize, CellSize);
                    //System.out.println((WindowWidth / 4) +"+"+ j * CellSize + " " + (i - 1) * CellSize + " " + CellSize + " " + CellSize);
                }

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

package ChessBoard;

import javax.swing.*;
import javax.swing.text.EditorKit;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ChessBoard extends JFrame{
    static HashMap<String, Cell> CellSet = new HashMap<>();

    public ChessBoard() {
        setSize(1600, 800);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
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
            JButton ExitButton = new JButton("Exit");
            ExitButton.setBounds(ChessBoard.this.getWidth()/8, ChessBoard.this.getHeight()/8, 50, 20);
            ExitButton.addActionListener(ActionListener );
        }

        public void paintComponent (Graphics g) {
            setBackground(Color.ORANGE);

            setLayout(null);
            Graphics2D g2 = (Graphics2D) g;
            super.paintComponent(g);

            int WindowHeight = ChessBoard.this.getHeight();
            int CellSize = WindowHeight / 8;

            System.out.println("gen width: " + WindowHeight + " Cell width: " + CellSize);

            String[] letters = {
                    "a", "b", "c", "d", "e", "f", "g", "h"
            };
            setLayout(new GridLayout(8, 8));

            for (int i = 1; i <= 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((j + i) % 2 == 0) {
                        g2.setColor(Color.WHITE);
                    } else {
                        g2.setColor(Color.getHSBColor(1.40F, 0.98F, 0.3F));

                    }
                    CellSet.put(letters[j] + i, new Cell());
                    g2.fillRect((WindowHeight - WindowHeight / 2) + j * CellSize, (i - 1) * CellSize, CellSize, CellSize);
                    g2.drawRect((WindowHeight - WindowHeight / 2) + j * CellSize, (i - 1) * CellSize, CellSize, CellSize);
                    //System.out.println((WindowHeight - WindowHeight / 2) +"+"+ j * CellSize + " " + (i - 1) * CellSize + " " + CellSize + " " + CellSize);
                }

            }

            repaint();



        }
    }

}

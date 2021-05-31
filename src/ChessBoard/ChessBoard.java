package ChessBoard;

import javax.swing.*;
import javax.swing.text.EditorKit;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ChessBoard extends JFrame{
    static HashMap<String, Cell> CellSet = new HashMap<>();

    public ChessBoard() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1920, 1080);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setContentPane(new MyPanel());

        setVisible(true);
    }


    class MyPanel extends JPanel {
        public MyPanel() {
            setBackground(Color.ORANGE);
            setLayout(new GridBagLayout());

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 1;

            add(new DrawBoard(), c);
        }

    }

    class DrawBoard extends JPanel {
        public DrawBoard() {
            int WindowWidth = ChessBoard.this.getWidth();
            int CellSize = WindowWidth/8;

            System.out.println("gen width: "+WindowWidth+" Cell width: "+CellSize);

            String[] letters = {
                    "a", "b", "c", "d", "e", "f", "g", "h"
            };
            setLayout(new GridLayout(8, 8));

            Graphics g = imag.getGraphics();
            Graphics2D g2 = (Graphics2D)g;
            for (int i=1; i<=8; i++) {
                for (int j=0; j<8; j++) {
                    if ((j + i) % 2 == 0) {
                        g2.setColor(Color.WHITE);
                    } else {
                        g2.setColor(Color.getHSBColor(0, 86, 28));

                    }
                    CellSet.put(letters[j]+ i, new Cell());
                    g2.fillRect((WindowWidth - WindowWidth / 2) + j * CellSize, (i-1) * CellSize, CellSize, (CellSize));
                    g.drawRect((WindowWidth - WindowWidth / 2) + j * CellSize, (i-1) * CellSize, CellSize, (CellSize));
                }

            }

        }
        BufferedImage imag;
        public void paintComponent (Graphics g)
        {
            if (imag == null) {
                imag = new  BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D d2 = (Graphics2D) imag.createGraphics();
                d2.setColor(Color.white);
                d2.fillRect(0, 0, this.getWidth(), this.getHeight());
            }


            super.paintComponent(g);
            g.drawImage(imag, 0, 0,this);
            setPreferredSize(new Dimension(imag.getWidth(), imag.getHeight()));
            repaint();

        }
    }

}

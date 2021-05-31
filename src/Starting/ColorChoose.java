package Starting;

import ChessBoard.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChoose extends JFrame {
    JPanel MainPanel = new MyPanel();

    public ColorChoose() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Chess menu");
        setContentPane(MainPanel);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        setSize(300, 250);

        setVisible(true);
    }

    private static class MyPanel extends JPanel {
        public MyPanel() {
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(Label1());
            add(Box.createRigidArea(new Dimension(0, 15)));
            add(White());
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(Black());
            add(Box.createRigidArea(new Dimension(0, 40)));
            add(Exit());


        }

        private JLabel Label1() {
            JLabel label = new JLabel("First player is..");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            return label;
        }

        private JButton White() {
            JButton button = new JButton("White");
            button.setAlignmentX(Component.CENTER_ALIGNMENT);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Config.COLOR = "WHITE";

                }
            });
            return button;
        }
        //
        private JButton Black() {
            JButton button = new JButton("African American");
            button.setAlignmentX(Component.CENTER_ALIGNMENT);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Config.COLOR = "BLACK";
                    new ChessBoard();
                }
            });
            return button;
        }

        private JButton Exit() {
            JButton button = new JButton("Exit");
            button.setAlignmentX(Component.CENTER_ALIGNMENT);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            return button;
        }
    }
}
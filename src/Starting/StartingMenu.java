package Starting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class StartingMenu extends JFrame {
    JPanel MainPanel = new MyPanel();

    public StartingMenu() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Chess menu");
        setContentPane(MainPanel);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        setSize(800, 400);

        setVisible(true);
    }



    private class MyPanel extends JPanel {
        public MyPanel() {

            add(Box.createRigidArea(new Dimension(0, 50)));
            add(OneAgainstOne());
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(OneAgainstComputer());
            add(Box.createRigidArea(new Dimension(0, 20)));
            add(Exit());



        }


        private JButton OneAgainstOne() {
            JButton button = new JButton("1 vs 1");
            button.setAlignmentX(Component.CENTER_ALIGNMENT);

            button.setMaximumSize(new Dimension(600, 100));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config.BOT = false;
                setVisible(false);
                dispose();
                new ColorChoose();
            }
        });
            return button;
        }
//
        private JButton OneAgainstComputer() {
            JButton button = new JButton("1 vs PC");
            setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);

            button.setMaximumSize(new Dimension(600, 100));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Config.BOT = true;
                    setVisible(false);
                    dispose();
                    new ColorChoose();
                }
            });
            return button;
        }

        private JButton Exit() {
            JButton button = new JButton("Exit");
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(200, 50));

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

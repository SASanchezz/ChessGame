package Auxiliary;

import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class IconChanger {


    public static HashMap<String, ImageIcon> iconChange(int Size){
        HashMap<String, ImageIcon> Icons = new HashMap<>();
        File dir = new File("images");
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {
            for (File child : directoryListing) {

                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File(String.valueOf(child)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Image dimg = img.getScaledInstance(Size, Size,
                        Image.SCALE_SMOOTH);

                ImageIcon imageIcon = new ImageIcon(dimg);

                Icons.put(child.getName(), imageIcon);
            }
        } else {
            System.out.println("Path doesn't exist");
        }
        return Icons;
    }

}

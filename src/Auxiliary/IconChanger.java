package Auxiliary;

import java.io.File;


public class IconChanger {

    public static void iconChange(int Size){
        File dir = new File("images");
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {
            for (File child : directoryListing) {
                System.out.println(child.getName());
            }
        } else {
            System.out.println("Path doesn't exist");
        }
    }

}

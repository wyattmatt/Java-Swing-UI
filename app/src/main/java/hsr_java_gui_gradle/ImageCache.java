package hsr_java_gui_gradle;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;

public class ImageCache {
    private static final Map<String, ImageIcon> cache = new HashMap<>();

    public static void preload(String[] names, String basePath) {
        for (String name : names) {
            String fullPath = basePath + name.toLowerCase().replace(" ", "") + ".png";
            File file = new File(fullPath);
            if (file.exists()) {
                ImageIcon raw = new ImageIcon(fullPath);
                Image scaled = raw.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                cache.put(name, new ImageIcon(scaled));
            } else {
                System.err.println("Image not found: " + fullPath);
            }
        }
    }

    public static ImageIcon get(String name) {
        return cache.get(name);
    }
}

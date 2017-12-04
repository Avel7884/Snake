package GUI;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Craft {

    private int dx;
    private int dy;
    private int x;
    private int y;
    private Image image;

    public Craft(String path) {
        
        initCraft(path);
    }
    
    private void initCraft(String path) {
        
        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage();
        x = 40;
        y = 60;        
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
}

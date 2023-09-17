package org.shadowmaster435.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageDisplay extends JLabel {

    private BufferedImage image;

    public ImageDisplay(int x, int y, int w, int h, String path) {
        setBounds(x, y, w, h);
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException ex) {
            // handle exception...
        }
    }
    public ImageDisplay(int x, int y, int w, int h, BufferedImage image) {
        setBounds(x, y, w, h);
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, getX(), getY(), this); // see javadoc for more info on the parameters
    }
}

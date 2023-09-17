package org.shadowmaster435.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Instance {

    public static JPanel frame(int x, int y, int w, int h) {
        JPanel panel=new JPanel();
        panel.setBounds(x,y,w,h);
        panel.setBackground(Color.black);
        return panel;
    }

    public static JButton button(int x, int y, int w, int h, String text) {
        JButton result = new JButton(text);
        result.setBounds(x,y,w,h);
        result.setMinimumSize(new Dimension(w, h));
        return result;
    }

    public static BufferedImage load_image(String path) {
        BufferedImage result;
        try {
            result = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

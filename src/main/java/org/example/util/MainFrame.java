package org.example.util;

import org.example.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame implements ActionListener {


    public MainFrame(int x, int y, int w, int h) {
        setBounds(x,y,w,h);
        setBackground(Color.black);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String com = e.getActionCommand();
        if (com.equals("save")) {
            AtlasGenerator.generate(Instance.load_image(Main.curr_file_path));

            JFileChooser jf = new JFileChooser(FileSystemView.getFileSystemView());

            int r = jf.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                save(jf.getSelectedFile().getAbsolutePath());
            }
            else {
                System.out.println("A");
            }

        }

        // if the user presses the open dialog, show the open dialog
        else {
            // create an object of JFileChooser class
            JFileChooser jf = new JFileChooser(FileSystemView.getFileSystemView());

            // calling the showOpenDialog method to display the save dialog on the frame
            int r = jf.showOpenDialog(null);

            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                Main.curr_file_path = jf.getSelectedFile().getAbsolutePath();
            }
            else {

            }
        }
    }

    public void save(String fp) {
        try {
            // retrieve image
            BufferedImage bi = Main.image;
            File outputfile = new File(fp + ".png");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {

        }
    }
}

package org.shadowmaster435;

import org.shadowmaster435.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    public static MainFrame main_window;
    public static JButton file_button;
    public static JButton save_button;
    public static JButton generate_button;

    public static String curr_file_path = "";

    public static BufferedImage image;

    public static JTextField x;
    public static JTextField y;

    public static final Dimension window_size = new Dimension(512, 100);

    public static void main(String[] args) {
        main_window = new MainFrame(0,0,window_size.width,window_size.height);

        init();
    }

    public static void init() {
        JPanel root_frame = Instance.frame(0,0, window_size.width,window_size.height);
        JPanel button_frame = Instance.frame(0,0,window_size.width,window_size.height / 2);
        JPanel padding_frame = Instance.frame(0,0,0,0);
        JTextField tile_x = new JTextField();
        JTextField tile_y = new JTextField();
        JTextArea x_text = new JTextArea("x");
        JTextArea y_text = new JTextArea("y");
        x_text.setEditable(false);
        y_text.setEditable(false);
        x_text.setFocusable(false);
        y_text.setFocusable(false);
        root_frame.setLayout(new GridLayout(1,1,0,0));
        JButton fb = Instance.button(50, 50, 200, 40, "Open");
        fb.addActionListener(main_window);
        JButton gb = Instance.button(50, 50, 200, 40, "Generate");
        gb.addActionListener(e -> open_preview_window(AtlasGenerator.generate(Instance.load_image(curr_file_path))));
        JButton sb = Instance.button(50, 50, 200, 40, "Save");
        sb.addActionListener(main_window);
        fb.setActionCommand("open");
        sb.setActionCommand("save");
        file_button = fb;
        save_button = sb;
        generate_button = gb;
        button_frame.setLayout(new GridLayout(2,4, 0,0));
        button_frame.add(file_button);
        button_frame.add(save_button);
        button_frame.add(generate_button);
        button_frame.add(padding_frame);
        button_frame.add(x_text);
        button_frame.add(tile_x);
        button_frame.add(y_text);
        button_frame.add(tile_y);
        root_frame.add(button_frame);
        x = tile_x;
        y = tile_y;
        main_window.add(root_frame);
        main_window.setResizable(false);
        main_window.revalidate();
    }
    public static void open_preview_window(BufferedImage image) {
        JFrame popup = new JFrame();
        var display = new ImageDisplay(0,0,0,0,image);
        popup.add(display);
        popup.setSize(AtlasGenerator.tile_resolution);
        popup.setVisible(true);
    }
}
package org.example.util;

import org.example.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class AtlasGenerator {
    public static BufferedImage input;
    public static Dimension tile_resolution = new Dimension(64,64);
    public static BufferedImage output = new BufferedImage(tile_resolution.width * 12,tile_resolution.height * 4,2);
    public static final String[] corners = {"tl", "tr", "bl", "br"};
    public static BufferedImage generate(BufferedImage in) {
        tile_resolution = new Dimension(Integer.parseInt(Main.x.getText()),Integer.parseInt(Main.y.getText()));
        input = in;
        set_uvs();
        Main.image = output;
        return output;
    }
    public static void modify(Dimension uv, Dimension out_uv) {
        var in_buffer = input.getSubimage(uv.width, uv.height,  tile_resolution.width / 2, tile_resolution.height / 2).getRaster();
        output.getRaster().setRect(out_uv.width, out_uv.height, in_buffer);
    }
    public static Map<String, Rectangle> none = Map.ofEntries(
            Map.entry("tl", new Rectangle(0, 2 * tile_resolution.height, 32, 32)),
            Map.entry("tr", new Rectangle(tile_resolution.width / 2, 2 * tile_resolution.height, 32, 32)),
            Map.entry("bl", new Rectangle(0, (2 * tile_resolution.height)+ (tile_resolution.height / 2), 32, 32)),
            Map.entry("br", new Rectangle(tile_resolution.width / 2, (2 * tile_resolution.height) + (tile_resolution.height / 2), 32, 32))
    );
    public static Map<String, Rectangle> horizontal = Map.ofEntries(
            Map.entry("tl", new Rectangle(0, tile_resolution.height, 32, 32)),
            Map.entry("tr", new Rectangle(tile_resolution.width / 2, tile_resolution.height, 32, 32)),
            Map.entry("bl", new Rectangle(0, tile_resolution.height + (tile_resolution.height / 2), 32, 32)),
            Map.entry("br", new Rectangle(tile_resolution.width / 2, tile_resolution.height + (tile_resolution.height / 2), 32, 32))
    );
    public static Map<String, Rectangle> vertical = Map.ofEntries(
            Map.entry("tl", new Rectangle(tile_resolution.width, 0, 32, 32)),
            Map.entry("tr", new Rectangle(tile_resolution.width + (tile_resolution.width / 2), 0, 32, 32)),
            Map.entry("bl", new Rectangle(tile_resolution.width, tile_resolution.height / 2, 32, 32)),
            Map.entry("br", new Rectangle(tile_resolution.width + (tile_resolution.width / 2), tile_resolution.height / 2, 32, 32))
    );
    public static Map<String, Rectangle> cross = Map.ofEntries(
            Map.entry("tl", new Rectangle(tile_resolution.width, tile_resolution.height, 32, 32)),
            Map.entry("tr", new Rectangle(tile_resolution.width + (tile_resolution.width / 2), tile_resolution.height, 32, 32)),
            Map.entry("bl", new Rectangle(tile_resolution.width, tile_resolution.height + (tile_resolution.height / 2), 32, 32)),
            Map.entry("br", new Rectangle(tile_resolution.width + (tile_resolution.width / 2), tile_resolution.height + (tile_resolution.height / 2), 32, 32))
    );
    public static Map<String, Rectangle> all = Map.ofEntries(
            Map.entry("tl", new Rectangle(0, 0, 32, 32)),
            Map.entry("tr", new Rectangle(tile_resolution.width / 2, 0, 32, 32)),
            Map.entry("bl", new Rectangle(0, tile_resolution.height / 2, 32, 32)),
            Map.entry("br", new Rectangle(tile_resolution.width / 2, tile_resolution.height / 2, 32, 32))
    );


    public static Rectangle get_sub_img_uv(String type, String corner) {
        Rectangle result_rect;

        switch (type) {
            case "all" -> result_rect = all.get(corner);
            case "cross" -> result_rect = cross.get(corner);
            case "horizontal" -> result_rect = horizontal.get(corner);
            case "vertical" -> result_rect = vertical.get(corner);
            default -> result_rect = none.get(corner);
        }
	return result_rect;
}
    public static Dimension corner_uv(String corner) {
        var result = new Dimension(0,0);
        switch(corner) {
            case "tl" -> result = new Dimension(0, 0);
            case "tr" -> result = new Dimension(32, 0);
            case "bl" -> result = new Dimension(0, 32);
            case "br"-> result = new Dimension(32, 32);
        }
        return result;
    }
    public static void set_uvs() {
        for (String corner : corners) {
            for (int x = 0; x < 12; ++x) {
                for (int y = 0; y < 4; ++y) {
                    Map<Dimension, String[]> uvs = Map.ofEntries(
                            Map.entry(new Dimension(11, 3), new String[]{"t", "l", "tl"}),
                            Map.entry(new Dimension(10, 3), new String[]{"t", "l", "r", "tl", "tr", "d"}),
                            Map.entry(new Dimension(9, 3), new String[]{"t", "l", "r", "tl", "tr"}),
                            Map.entry(new Dimension(8, 3), new String[]{"t", "r", "tr"}),
                            Map.entry(new Dimension(7, 3), new String[]{"t", "l", "br", "d", "r"}),
                            Map.entry(new Dimension(6, 3), new String[]{"t", "l", "r", "tl"}),
                            Map.entry(new Dimension(5, 3), new String[]{"t", "l", "r", "tr"}),
                            Map.entry(new Dimension(4, 3), new String[]{"t", "l", "r", "bl", "d"}),
                            Map.entry(new Dimension(3, 3), new String[]{"l"}),
                            Map.entry(new Dimension(2, 3), new String[]{"l", "r"}),
                            Map.entry(new Dimension(1, 3), new String[]{"r"}),
                            Map.entry(new Dimension(0, 3), new String[]{}),
                            Map.entry(new Dimension(11, 2), new String[]{"d", "t", "l", "tl", "bl"}),
                            Map.entry(new Dimension(10, 2), new String[]{"d", "t", "l", "r", "tl", "br"}),
                            Map.entry(new Dimension(9, 2), new String[]{"d", "t", "l", "r", "bl", "br", "tl", "tr"}),
                            Map.entry(new Dimension(8, 2), new String[]{"d", "t", "l", "r", "tr", "br"}),
                            Map.entry(new Dimension(7, 2), new String[]{"d", "t", "l", "tl"}),
                            Map.entry(new Dimension(6, 2), new String[]{"d", "t", "l", "r", "tl", "tr", "bl"}),
                            Map.entry(new Dimension(5, 2), new String[]{"d", "t", "l", "r", "tl", "tr", "br"}),
                            Map.entry(new Dimension(4, 2), new String[]{"d", "t", "r", "tr"}),
                            Map.entry(new Dimension(3, 2), new String[]{"t", "l"}),
                            Map.entry(new Dimension(2, 2), new String[]{"t", "l", "r"}),
                            Map.entry(new Dimension(1, 2), new String[]{"t", "r"}),
                            Map.entry(new Dimension(0, 2), new String[]{"t"}),
                            Map.entry(new Dimension(11, 1), new String[]{"d", "t", "l", "r", "bl", "tl"}),
                            Map.entry(new Dimension(10, 1), new String[]{"empty"}),
                            Map.entry(new Dimension(9, 1), new String[]{"d", "t", "l", "r", "bl", "tr"}),
                            Map.entry(new Dimension(8, 1), new String[]{"d", "t", "r", "tr", "br"}),
                            Map.entry(new Dimension(7, 1), new String[]{"d", "l", "t", "bl"}),
                            Map.entry(new Dimension(6, 1), new String[]{"d", "t", "l", "r", "bl", "br", "tl"}),
                            Map.entry(new Dimension(5, 1), new String[]{"d", "t", "l", "r", "tr", "bl", "br"}),
                            Map.entry(new Dimension(4, 1), new String[]{"d", "t", "r", "br"}),
                            Map.entry(new Dimension(3, 1), new String[]{"d", "t", "l"}),
                            Map.entry(new Dimension(2, 1), new String[]{"d", "t", "r", "l"}),
                            Map.entry(new Dimension(1, 1), new String[]{"d", "r", "t"}),
                            Map.entry(new Dimension(0, 1), new String[]{"d", "t"}),
                            Map.entry(new Dimension(11, 0), new String[]{"d", "l", "bl"}),
                            Map.entry(new Dimension(10, 0), new String[]{"d", "l", "r", "bl", "br"}),
                            Map.entry(new Dimension(9, 0), new String[]{"d", "l", "r", "t", "bl", "br"}),
                            Map.entry(new Dimension(8, 0), new String[]{"d", "r", "br"}),
                            Map.entry(new Dimension(7, 0), new String[]{"d", "l", "r", "tr", "t"}),
                            Map.entry(new Dimension(6, 0), new String[]{"d", "l", "r", "bl"}),
                            Map.entry(new Dimension(5, 0), new String[]{"d", "l", "r", "br"}),
                            Map.entry(new Dimension(4, 0), new String[]{"d", "l", "r", "tl", "t"}),
                            Map.entry(new Dimension(3, 0), new String[]{"d", "l"}),
                            Map.entry(new Dimension(2, 0), new String[]{"d", "l", "r"}),
                            Map.entry(new Dimension(1, 0), new String[]{"d", "r"}),
                            Map.entry(new Dimension(0, 0), new String[]{"d"})
                    );
                    var type = get_type(corner, Arrays.stream(uvs.get(new Dimension(x, y))).toList());
                    if (Objects.equals(type, "empty")) {
                        continue;
                    }
                    var uv_offset = new Dimension((x * tile_resolution.width) + corner_uv(corner).width, (y * tile_resolution.height) + corner_uv(corner).height);
                    var input_uv_rect = get_sub_img_uv(type, corner);
                    var input_uv_offset = new Dimension(input_uv_rect.x, input_uv_rect.y);
                    modify(input_uv_offset, uv_offset);
                }
            }
        }
    }
    public static String get_type(String corner, List<String> directions) {
        boolean upleft_corner = Objects.equals(corner, "tl");
        boolean upright_corner = Objects.equals(corner, "tr");
        boolean downleft_corner = Objects.equals(corner, "bl");
        boolean downright_corner = Objects.equals(corner, "br");

        boolean up = directions.contains("t");
        boolean down = directions.contains("d");
        boolean left = directions.contains("l");
        boolean right = directions.contains("r");
        boolean upleft = directions.contains("tl");
        boolean upright = directions.contains("tr");
        boolean downleft = directions.contains("bl");
        boolean downright = directions.contains("br");

        boolean upleft_x = !upleft && !up && left && upleft_corner;
        boolean upright_x = !upright && !up && right && upright_corner;
        boolean downleft_x = !downleft && !down && left && downleft_corner;
        boolean downright_x = !downright && !down && right && downright_corner;

        boolean upleft_y = !upleft && up && !left && upleft_corner;
        boolean upright_y = !upright && up && !right && upright_corner;
        boolean downleft_y = !downleft && down && !left && downleft_corner;
        boolean downright_y = !downright && down && !right && downright_corner;

        boolean upleft_xy = !upleft && up && left && upleft_corner;
        boolean upright_xy = !upright && up && right && upright_corner;
        boolean downleft_xy = !downleft && down && left && downleft_corner;
        boolean downright_xy = !downright && down && right && downright_corner;
        boolean upleft_all = upleft && up && left && upleft_corner;
        boolean upright_all = upright && up && right && upright_corner;
        boolean downleft_all = downleft && down && left && downleft_corner;
        boolean downright_all = downright && down && right && downright_corner;

        boolean v = downleft_y || downright_y || upleft_y || upright_y;
        boolean h = downleft_x || downright_x || upleft_x || upright_x;
        boolean c = downleft_xy || downright_xy || upleft_xy || upright_xy;
        boolean a = downleft_all || downright_all || upleft_all || upright_all;
        boolean n = directions.size() == 0;

        var result = "none";

        if (v) {
            result = "vertical";
        }
        if (h) {
            result = "horizontal";
        }
        if (c) {
            result = "cross";
        }
        if (a) {
            result = "all";
        }
        if (n) {
            result = "none";
        }
        if (!directions.contains("empty")) {
            return result;
        } else {
            return "empty";
        }
    }
}

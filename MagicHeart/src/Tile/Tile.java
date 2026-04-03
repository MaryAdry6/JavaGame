package Tile;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Tile
{
    public static final int BASE = 0;
    public static final int OVERLAY = 1;


    public BufferedImage image;
    public boolean collision = false;
    public int renderLayer = BASE;

}

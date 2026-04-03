package Tile;

import java.awt.*;

public interface AbstractTileManager {
    void draw(Graphics2D g2, boolean isOverlay);
    void draw(Graphics2D g2);
}

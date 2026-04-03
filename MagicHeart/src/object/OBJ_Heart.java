package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_Heart extends Entity{

    public BufferedImage[] healthbar = new BufferedImage[21];
    BufferedImage tileSheet;


    public OBJ_Heart(GamePanel gp)
    {
        super(gp);

        name = "Heart";

        getTileSheet();
        getTileImage();
    }

    public BufferedImage scaleImage(BufferedImage original, int width, int height)
    {

        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }

    public void getTileSheet()
    {
        try
        {
            tileSheet = ImageIO.read(getClass().getResourceAsStream("/player/HEALTHBAR_v3.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Eroare la TILESHEET PROSTULE");
        }

    }

    public void getTileImage() {
        int tileWidth = 64;
        int tileHeight = 64;
        int barWidth = tileWidth * 3; // 192 px wide

        for (int i = 0; i < 21; i++) {
            int y = i * tileHeight;
            if (y + tileHeight > tileSheet.getHeight()) break; // safety check
            healthbar[i] = tileSheet.getSubimage(0, y, barWidth, tileHeight);
            healthbar[i] = scaleImage(healthbar[i], gp.tileSize*2*3+60*3, gp.tileSize*2+60);
        }
    }





}

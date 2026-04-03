package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_Gems extends Entity{

    public BufferedImage[] pietre = new BufferedImage[21];
    BufferedImage tileSheet;


    public OBJ_Gems(GamePanel gp)
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
            tileSheet = ImageIO.read(getClass().getResourceAsStream("/gems/pietre.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Eroare la TILESHEET PROSTULE");
        }

    }

    public void getTileImage() {
        pietre[0] = tileSheet.getSubimage(0, 0, 64, 64);
        pietre[1] = tileSheet.getSubimage(0, 64, 64, 64);
        pietre[2] = tileSheet.getSubimage(64, 64, 64, 64);
        pietre[3] = tileSheet.getSubimage(0, 128, 64, 64);
        pietre[4] = tileSheet.getSubimage(64, 128, 64, 64);
        pietre[5] = tileSheet.getSubimage(128, 128, 64, 64);
        pietre[6] = tileSheet.getSubimage(0, 192, 64, 64);

//        int tileWidth = 64;
        int tileHeight = 64;

        for (int i = 0; i < 7; i++) {
            pietre[i] = scaleImage(pietre[i], gp.tileSize*2+60, gp.tileSize*2+60);
        }
    }





}

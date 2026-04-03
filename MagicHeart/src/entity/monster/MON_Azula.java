package entity.monster;

import Tile.Tile;
import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class MON_Azula extends Entity
{
    BufferedImage azula_back, azula_front, azula_left, azula_right;


    public MON_Azula(GamePanel gp)
    {
        super(gp);

        type = 40;
        direction = "down";
        name = "Azula";
        speed = 4;
        maxLife = 50;
        life = maxLife;

        solidArea = new Rectangle();
        solidArea.x = gp.tileSize * 2 - 4;
        solidArea.y = gp.tileSize * 2 + 9;
        solidArea.width = 9;
        solidArea.height = 9;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        spriteWidth = gp.tileSize * 4;
        spriteHeight = gp.tileSize * 4;

        getImage();

    }

    public void getImage()
    {

        try
        {
            azula_back = ImageIO.read(getClass().getResourceAsStream("/monster/Azula/BACK.png"));

            int x = 0, y = 3 * 64;
            for(int i = 0; i < 6; i++)
            {
                up[i] = new Tile();
                up[i].image = azula_back.getSubimage(x, y, 64, 64);
                up[i].image = scaleImage(up[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 64;
            }


            azula_front = ImageIO.read(getClass().getResourceAsStream("/monster/Azula/FRONT.png"));

            x = 0; y = 3 * 64;
            for(int i = 0; i < 6; i++)
            {
                down[i] = new Tile();
                down[i].image = azula_front.getSubimage(x, y, 64, 64);
                down[i].image = scaleImage(down[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 64;
            }


            azula_right = ImageIO.read(getClass().getResourceAsStream("/monster/Azula/SIDEright.png"));

            x = 0; y = 3 * 64;
            for(int i = 0; i < 6; i++)
            {
                right[i] = new Tile();
                right[i].image = azula_right.getSubimage(x, y, 64, 64);
                right[i].image = scaleImage(right[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 64;
            }


            azula_left = ImageIO.read(getClass().getResourceAsStream("/monster/Azula/SIDEleft.png"));

            x = 0; y = 3 * 64;
            for(int i = 0; i < 6; i++)
            {
                left[i] = new Tile();
                left[i].image = azula_left.getSubimage(x, y, 64, 64);
                left[i].image = scaleImage(left[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 64;
            }
        }
        catch(IOException e)
        {
            System.out.println("Eroare la getPlayerImage");
            e.printStackTrace();
        }
    }

    public BufferedImage scaleImage(BufferedImage original, int width, int height)
    {

        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }

    //monster aggro

    public void update()
    {
        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;

        if(onPath == false && tileDistance<5)
        {
            int i = new Random().nextInt(100) + 1;
            if(i > 50)
            {
                onPath = true;
            }
        }
    }

    public void setAction()
    {
        spriteCounter++;
        if(spriteCounter > 9)
        {
            if(spriteNum == 3)
                spriteNum = 0;
            else spriteNum++;
            spriteCounter = 0;
        }

        if(onPath == true)
        {
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.x) / gp.tileSize;



            searchPath(goalCol,goalRow);
        }
        else {
            actionLockCounter++;

            if (actionLockCounter == 120) {

                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75 && i <= 100) {
                    direction = "right";
                }

                actionLockCounter = 0;
            }

        }
    }
}
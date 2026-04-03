package entity.monster;

import Tile.Tile;
import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class MON_WolfForest extends Entity
{
    BufferedImage wolf_back, wolf_front, wolf_left, wolf_right;


    public MON_WolfForest(GamePanel gp)
    {
        super(gp);

        type = 21;
        direction = "down";
        name = "Forest Wolf";
        speed = 2;

         solidArea = new Rectangle();
         solidArea.x = 5;
         solidArea.y = 13;
         solidArea.width = gp.tileSize + 15;
         solidArea.height = gp.tileSize + 5;
         solidAreaDefaultX = solidArea.x;
         solidAreaDefaultY = solidArea.y;
         spriteWidth = gp.tileSize * 2;
         spriteHeight = gp.tileSize * 2;

         getImage();

    }

    public void getImage()
    {

        try
        {
            wolf_back = ImageIO.read(getClass().getResourceAsStream("/monster/wolf/wolf_official/WOLF_BACK.png"));

            int x = 0, y = 0;
            for(int i = 0; i < 6; i++)
            {
                up[i] = new Tile();
                up[i].image = wolf_back.getSubimage(x, y, 32, 32);
                up[i].image = scaleImage(up[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 32;
            }


            wolf_front = ImageIO.read(getClass().getResourceAsStream("/monster/wolf/wolf_official/WOLF_FRONT.png"));

            x = 0; y = 0;
            for(int i = 0; i < 6; i++)
            {
                down[i] = new Tile();
                down[i].image = wolf_front.getSubimage(x, y, 32, 32);
                down[i].image = scaleImage(down[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 32;
            }


            wolf_right = ImageIO.read(getClass().getResourceAsStream("/monster/wolf/wolf_official/WOLF_RIGHT.png"));

            x = 0; y = 0;
            for(int i = 0; i < 6; i++)
            {
                right[i] = new Tile();
                right[i].image = wolf_right.getSubimage(x, y, 32, 32);
                right[i].image = scaleImage(right[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 32;
            }


            wolf_left = ImageIO.read(getClass().getResourceAsStream("/monster/wolf/wolf_official/WOLF_LEFT.png"));

            x = 0; y = 0;
            for(int i = 0; i < 6; i++)
            {
                left[i] = new Tile();
                left[i].image = wolf_left.getSubimage(x, y, 32, 32);
                left[i].image = scaleImage(left[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 32;
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
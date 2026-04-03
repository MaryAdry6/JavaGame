package entity.monster;

import Tile.Tile;
import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class MON_Golem extends Entity
{
    BufferedImage golem_back, golem_front, golem_left, golem_right;


    public MON_Golem(GamePanel gp)
    {
        super(gp);

        type = 30;
        direction = "down";
        name = "Golem";
        speed = 3;
        maxLife = 40;
        life = maxLife;

        solidArea = new Rectangle();
        solidArea.x = gp.tileSize + 25;
        solidArea.y = gp.tileSize + 27;
        solidArea.width = gp.tileSize * 4 - 15;
        solidArea.height = gp.tileSize * 4 - 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        spriteWidth = gp.tileSize * 7;
        spriteHeight = gp.tileSize * 7;

        getImage();

    }

    public void getImage()
    {

        try
        {
            golem_back = ImageIO.read(getClass().getResourceAsStream("/monster/golem/GOLEM_RIGHT.png"));

            int x = 0, y = 0;
            for(int i = 0; i < 8; i++)
            {
                up[i] = new Tile();
                up[i].image = golem_back.getSubimage(x, y, 96, 96);
                up[i].image = scaleImage(up[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 96;
            }


            golem_front = ImageIO.read(getClass().getResourceAsStream("/monster/golem/GOLEM_LEFT.png"));

            x = 0; y = 0;
            for(int i = 0; i < 8; i++)
            {
                down[i] = new Tile();
                down[i].image = golem_front.getSubimage(x, y, 96, 96);
                down[i].image = scaleImage(down[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 96;
            }


            golem_right = ImageIO.read(getClass().getResourceAsStream("/monster/golem/GOLEM_RIGHT.png"));

            x = 0; y = 0;
            for(int i = 0; i < 8; i++)
            {
                right[i] = new Tile();
                right[i].image = golem_right.getSubimage(x, y, 96, 96);
                right[i].image = scaleImage(right[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 96;
            }


            golem_left = ImageIO.read(getClass().getResourceAsStream("/monster/golem/GOLEM_LEFT.png"));

            x = 0; y = 0;
            for(int i = 0; i < 8; i++)
            {
                left[i] = new Tile();
                left[i].image = golem_left.getSubimage(x, y, 96, 96);
                left[i].image = scaleImage(left[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 96;
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
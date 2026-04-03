package entity;

import Tile.Tile;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC_Fairy extends Entity {

    BufferedImage idle;

    public NPC_Fairy(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 0;

        solidArea = new Rectangle();
        solidArea.x = gp.tileSize;
        solidArea.y = gp.tileSize * 2;
        solidArea.width = 60;
        solidArea.height = 60;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        spriteWidth = gp.tileSize * 4;
        spriteHeight = gp.tileSize * 4;
        maxLife = 1;
        life = maxLife;

        getPlayerImage();
        setDialogue();
    }

    public BufferedImage scaleImage(BufferedImage original, int width, int height)
    {

        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }

    public void getPlayerImage() {

        try
        {
            idle = ImageIO.read(getClass().getResourceAsStream("/npc/fairySprite.png"));

            int x = 0, y = 0;
            for(int i = 0; i < 3; i++)
            {
                up[i] = new Tile();
                up[i].image = idle.getSubimage(x, y, 128, 128);
                up[i].image = scaleImage(up[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 128;
            }


            x = 0; y = 0;
            for(int i = 0; i < 3; i++)
            {
                down[i] = new Tile();
                down[i].image = idle.getSubimage(x, y, 128, 128);
                down[i].image = scaleImage(down[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 128;
            }

            x = 0; y = 0;
            for(int i = 0; i < 3; i++)
            {
                right[i] = new Tile();
                right[i].image = idle.getSubimage(x, y, 128, 128);
                right[i].image = scaleImage(right[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 128;
            }

            x = 0; y = 0;
            for(int i = 0; i < 3; i++)
            {
                left[i] = new Tile();
                left[i].image = idle.getSubimage(x, y, 128, 128);
                left[i].image = scaleImage(left[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 128;
            }
        }
        catch(IOException e)
        {
            System.out.println("Eroare la getPlayerImage");
            e.printStackTrace();
        }
    }

    @Override
    public void setAction()
    {
        spriteCounter++;
        if(spriteCounter > 8)
        {
            if(spriteNum == 2)
                spriteNum = 0;
            else spriteNum++;
            spriteCounter = 0;
        }
    }

    public void setDialogue()
    {
        dialogues[0] = "Ma bucur ca m-ai gasit!\nLasa-ma sa te ajut!";
        dialogues[1] = "Ia niste cozonac de la nasa...\nE de casa, sa stii.\n Te pup. Ne vedem!";
        dialogues[2] = "(+20 viata)";

    }

    public void speak()
    {
        super.speak();

    }




}

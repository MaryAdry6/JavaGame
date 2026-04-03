package entity;

import Tile.Tile;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity
{

    public GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public int dashSpeed = 10, defaultSpeed = 3;
    public int spriteWidth = 32;
    public int spriteHeight = 32;

    public Tile []up = new Tile[10];
    public Tile []down = new Tile[10];
    public Tile []left = new Tile[10];
    public Tile []right = new Tile[10];

    public Tile []dash_up = new Tile[10];
    public Tile []dash_down = new Tile[10];
    public Tile []dash_left = new Tile[10];
    public Tile []dash_right = new Tile[10];

    public Tile []attack_up = new Tile[15];
    public Tile []attack_down = new Tile[15];
    public Tile []attack_left = new Tile[15];
    public Tile []attack_right = new Tile[15];

    /*public Tile []down2 = new Tile[10]; // npc

    public Tile []wolf_up = new Tile[10];
    public Tile []wolf_down = new Tile[10];
    public Tile []wolf_leftside = new Tile[10];
    public Tile []wolf_rightside = new Tile[10]; */

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 0;

    public Rectangle solidArea;
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;

    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    String currentDialogue = dialogues[dialogueIndex];
    public boolean active = true;

    public boolean onPath = false;

    public int dash_spriteCounter = 0;
    public int dash_spriteNum = 0;

    public int dashCooldown = 110;
    public int dashStart = 0;

    public int attack_spriteCounter = 0;
    public int attack_spriteNum = 0;

    public String name;

    public int type; // 0 = player, 1 = npc, 21 = lup_padure, 22 = paiangan_padure, 31 = lup_munte, 32 = paiangan_munte, 41 = lup_lava, 42 = paiangan_lava

    //CHARACTER STATUS
    public int maxLife = 10;
    public int life = maxLife;


    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setAction() {}

    public void speak() {
        // If no more dialogues, reset


        currentDialogue = dialogues[dialogueIndex];

        // Heal the player if the current dialogue matches
        if ("(+20 viata)".equals(currentDialogue)) {
            gp.player.life += 20;
            if (gp.player.life > gp.player.maxLife) {
                gp.player.life = gp.player.maxLife;
            }
        }

        // Set the dialogue on UI
        gp.ui.currentDialogue = currentDialogue;

        // Move to next dialogue for next interaction
        dialogueIndex++;

        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;

            solidArea.x = 0;
            solidArea.y = 0;
            solidArea.width = 0;
            solidArea.height = 0;
        }
    }


    public void checkCollision()
    {
        collisionOn = false;
        gp.cChecker.checkTile(this, gp.mapManager.getCurrentBaseMaps());
        gp.cChecker.checkTile(this, gp.mapManager.getCurrentOverlayMaps());
        gp.cChecker.checkEntity(this, gp.mapManager.getCurrentNPC());
        gp.cChecker.checkEntity(this, gp.mapManager.getCurrentMobs());
        boolean contactPlayer =  gp.cChecker.checkPlayer(this);

        if((this.type == 21 || this.type == 31 || this.type == 41 || this.type == 22 || this.type == 32 || this.type == 42) && contactPlayer == true)
        {
            if(gp.player.invincible == false)
            {
                if(this.type == 21)
                    gp.player.life -= 6;
                else if(this.type == 22)
                    gp.player.life -= 4;
                else if(this.type == 31)
                    gp.player.life -= 8;
                else if(this.type == 32)
                    gp.player.life -= 6;
                else if(this.type == 41)
                    gp.player.life -= 10;
                else
                    gp.player.life -= 8;
                gp.player.invincible = true;
            }
        }
    }
    public void update()

    {
        setAction();

        checkCollision();
        if(collisionOn == false)
        {
            switch(direction)
            {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left":  worldX -= speed; break;
                case "right": worldX += speed; break;
            }

        }
        if(invincible == true)
        {
            invincibleCounter++;
            if(invincibleCounter > 30)
            {
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }
    public void draw(Graphics2D g2)
    {
         BufferedImage image = null;
         int screenX = worldX - gp.player.worldX + gp.player.screenX;
         int screenY = worldY - gp.player.worldY + gp.player.screenY;


         if(screenX > worldX)
         {
             screenX = worldX;
         }
         if(screenY > worldY)
         {
             screenY = worldY;
         }

         int rightOffset = gp.screenWidth - screenX;
         if(rightOffset > gp.worldWidth - worldX)
         {
             screenX = gp.screenWidth - (gp.worldWidth - worldX);
         }

         int bottomOffset = gp.screenHeight - screenY;
         if(bottomOffset > gp.worldHeight - worldY)
         {
             screenY = gp.screenHeight - (gp.worldHeight - worldY);
         }

         if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
         {
             switch (direction)
             {
                 case "up":
                     image = up[spriteNum].image;
                     break;
                 case "down":
                     image = down[spriteNum].image;
                     break;
                 case "left":
                     image = left[spriteNum].image;
                     break;
                 case "right":
                     image = right[spriteNum].image;
                     break;
             }
             if (invincible == true)
             {
                 g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
             }
             g2.drawImage(image, screenX, screenY, spriteWidth, spriteHeight, null);
             g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
             g2.setColor(Color.RED);
             g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);


         }



    }
    public void searchPath(int goalCol, int goalRow)
    {
         int startCol = (worldX + solidArea.x)/gp.tileSize;
         int startRow = (worldY + solidArea.y)/gp.tileSize;

         gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

         if(gp.pFinder.search() == true)
         {
             int nextX = gp.pFinder.pathList.get(0).col*gp.tileSize;
             int nextY = gp.pFinder.pathList.get(0).row*gp.tileSize;

             int enLeftX = worldX + solidArea.x;
             int enRightX = worldX + solidArea.x + solidArea.width;
             int enTopY = worldY + solidArea.y;
             int enBottomY = solidArea.y + worldY + solidArea.height;

             if(enTopY > nextY && enLeftX>=nextX && enRightX<nextX+gp.tileSize)
             {
                 direction = "up";
             }
             else if(enTopY < nextY && enLeftX>=nextX && enRightX<nextX+gp.tileSize)
             {
                 direction = "down";
             }
             else if(enTopY >= nextY && enBottomY < nextY+gp.tileSize)
             {
                 if(enLeftX > nextX)
                 {
                     direction = "left";
                 }
                 else if(enLeftX < nextX)
                 {
                     direction = "right";
                 }
             }
             else if(enTopY > nextY && enLeftX > nextX)
             {
                 direction = "up";
                 checkCollision();
                 if(collisionOn == true)
                 {
                     direction = "left";
                 }
             }
             else if(enTopY > nextY && enLeftX < nextX)
             {
                 direction = "up";
                 checkCollision();
                 if(collisionOn == true)
                 {
                     direction = "right";
                 }
             }
             else if(enTopY < nextY && enLeftX > nextX)
             {
                 direction = "down";
                 checkCollision();
                 if(collisionOn == true)
                 {
                     direction = "left";
                 }
             }
             else if(enTopY < nextY && enLeftX < nextX)
             {
                 direction = "down";
                 checkCollision();
                 if(collisionOn == true)
                 {
                     direction = "right";
                 }
             }
             //if it reaches the goal, stop the search
             int nextCol = gp.pFinder.pathList.get(0).col;
             int nextRow = gp.pFinder.pathList.get(0).row;
             if(nextCol == goalCol && nextRow == goalRow)
             {
                 onPath = false;
             }
         }
    }
}

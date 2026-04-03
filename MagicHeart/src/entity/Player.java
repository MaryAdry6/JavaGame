package entity;

import main.*;
import Tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class Player extends Entity
{

    KeyHandler keyH;
    BufferedImage front, back, side_left, side_right;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        super(gp);


        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize) - 32;
        screenY = gp.screenHeight/2 - (gp.tileSize) - 32;

        solidArea = new Rectangle();
        solidArea.x = gp.tileSize * 2 - 4;
        solidArea.y = gp.tileSize * 2 + 9;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 9;
        solidArea.height = 9;

        attackArea.width = 65;
        attackArea.height = 65;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues()
    {
        worldX = gp.tileSize * 36;
        worldY = gp.tileSize * 46;
        speed = 3;
        direction = "down";
        // PLAYER STATUS
        maxLife = 100;
        life = maxLife;
    }
    public BufferedImage scaleImage(BufferedImage original, int width, int height)
    {

        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }

    public void getPlayerImage()
    {
        try
        {
            back = ImageIO.read(getClass().getResourceAsStream("/player/Aileen/BACK.png"));

            int x = 0, y = 3*64;
            for(int i = 0; i < 6; i++)
            {
                up[i] = new Tile();
                up[i].image = back.getSubimage(x, y, 64, 64);
                up[i].image = scaleImage(up[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 64;
            }


            front = ImageIO.read(getClass().getResourceAsStream("/player/Aileen/FRONT.png"));

            x = 0; y = 3*64;
            for(int i = 0; i < 6; i++)
            {
                down[i] = new Tile();
                down[i].image = front.getSubimage(x, y, 64, 64);
                down[i].image = scaleImage(down[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 64;
            }


            side_right = ImageIO.read(getClass().getResourceAsStream("/player/Aileen/SIDEright.png"));

            x = 0; y = 3*64;
            for(int i = 0; i < 6; i++)
            {
                right[i] = new Tile();
                right[i].image = side_right.getSubimage(x, y, 64, 64);
                right[i].image = scaleImage(right[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 64;
            }


            side_left = ImageIO.read(getClass().getResourceAsStream("/player/Aileen/SIDEleft.png"));

            x = 0; y = 3*64;
            for(int i = 0; i < 6; i++)
            {
                left[i] = new Tile();
                left[i].image = side_left.getSubimage(x, y, 64, 64);
                left[i].image = scaleImage(left[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 64;
            }


            x = 0; y = 2*64;
            for(int i = 0; i < 4; i++)
            {
                dash_up[i] = new Tile();
                dash_up[i].image = back.getSubimage(x, y, 64, 64);
                dash_up[i].image = scaleImage(dash_up[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 64;
            }



            x = 0; y = 2*64;
            for(int i = 0; i < 4; i++)
            {
                dash_down[i] = new Tile();
                dash_down[i].image = front.getSubimage(x, y, 64, 64);
                dash_down[i].image = scaleImage(dash_down[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 64;
            }



            x = 0; y = 2*64;
            for(int i = 0; i < 4; i++)
            {
                dash_right[i] = new Tile();
                dash_right[i].image = side_right.getSubimage(x, y, 64, 64);
                dash_right[i].image = scaleImage(dash_right[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 64;
            }


            x = 0; y = 2 * 64;
            for(int i = 0; i < 4; i++)
            {
                dash_left[i] = new Tile();
                dash_left[i].image = side_left.getSubimage(x, y, 64, 64);
                dash_left[i].image = scaleImage(dash_left[i].image, gp.tileSize * 4, gp.tileSize * 4);
                x += 64;
            }

            x = 0 ; y = 0;
            for(int i = 0; i<12; i++)
            {
                attack_down[i] = new Tile();
                attack_down[i].image = front.getSubimage(x,y,64,64);
                attack_down[i].image = scaleImage(attack_down[i].image, gp.tileSize*4,gp.tileSize*4);
                x+=64;
                if (i == 5)
                {
                    x = 0;
                    y+=64;
                }
            }

            x = 0 ; y = 0;
            for(int i = 0; i<12; i++)
            {
                attack_up[i] = new Tile();
                attack_up[i].image = back.getSubimage(x,y,64,64);
                attack_up[i].image = scaleImage(attack_up[i].image, gp.tileSize*4,gp.tileSize*4);
                x+=64;
                if (i == 5)
                {
                    x = 0;
                    y+=64;
                }
            }
            x = 0 ; y = 0;
            for(int i = 0; i<12; i++)
            {
                attack_left[i] = new Tile();
                attack_left[i].image = side_left.getSubimage(x,y,64,64);
                attack_left[i].image = scaleImage(attack_left[i].image, gp.tileSize*4,gp.tileSize*4);
                x+=64;
                if (i == 5)
                {
                    x = 0;
                    y+=64;
                }
            }
            x = 0 ; y = 0;
            for(int i = 0; i<12; i++)
            {
                attack_right[i] = new Tile();
                attack_right[i].image = side_right.getSubimage(x,y,64,64);
                attack_right[i].image = scaleImage(attack_right[i].image, gp.tileSize*4,gp.tileSize*4);
                x+=64;
                if (i == 5)
                {
                    x = 0;
                    y+=64;
                }
            }


        }
        catch(IOException e)
        {
            System.out.println("Eroare la getPlayerImage");
            e.printStackTrace();
        }
    }

    public void update()
    {
        boolean moving = false;

        // Vertical movement
        if (keyH.upPressed) {
            direction = "up";
            collisionOn = false;
            gp.cChecker.checkTile(this, gp.mapManager.getCurrentBaseMaps());
            gp.cChecker.checkTile(this, gp.mapManager.getCurrentOverlayMaps());
            int npcIndex = gp.cChecker.checkEntity(this, gp.mapManager.getCurrentNPC());
            interactNPC(npcIndex);
            int monsterIndex = gp.cChecker.checkEntity(this, gp.mapManager.getCurrentMobs());
            contactMonster(monsterIndex);
            if (!collisionOn) {
                worldY -= speed;
            }
            moving = true;
        } else if (keyH.downPressed) {
            direction = "down";
            collisionOn = false;
            gp.cChecker.checkTile(this, gp.mapManager.getCurrentBaseMaps());
            gp.cChecker.checkTile(this, gp.mapManager.getCurrentOverlayMaps());
            int npcIndex = gp.cChecker.checkEntity(this, gp.mapManager.getCurrentNPC());
            interactNPC(npcIndex);
            int monsterIndex = gp.cChecker.checkEntity(this, gp.mapManager.getCurrentMobs());
            contactMonster(monsterIndex);
            if (!collisionOn) {
                worldY += speed;
            }
            moving = true;
        }

        // Horizontal movement
        if (keyH.leftPressed) {
            direction = "left";
            collisionOn = false;
            gp.cChecker.checkTile(this, gp.mapManager.getCurrentBaseMaps());
            gp.cChecker.checkTile(this, gp.mapManager.getCurrentOverlayMaps());
            int npcIndex = gp.cChecker.checkEntity(this, gp.mapManager.getCurrentNPC());
            interactNPC(npcIndex);
            int monsterIndex = gp.cChecker.checkEntity(this, gp.mapManager.getCurrentMobs());
            contactMonster(monsterIndex);
            if (!collisionOn) {
                worldX -= speed;
            }
            moving = true;
        } else if (keyH.rightPressed) {
            direction = "right";
            collisionOn = false;
            gp.cChecker.checkTile(this, gp.mapManager.getCurrentBaseMaps());
            gp.cChecker.checkTile(this, gp.mapManager.getCurrentOverlayMaps());
            int npcIndex = gp.cChecker.checkEntity(this, gp.mapManager.getCurrentNPC());
            interactNPC(npcIndex);
            int monsterIndex = gp.cChecker.checkEntity(this, gp.mapManager.getCurrentMobs());
            contactMonster(monsterIndex);
            if (!collisionOn) {
                worldX += speed;
            }
            moving = true;
        }

        // Handle animation
        if (moving) {
            spriteCounter++;
            if(spriteCounter > 8)
            {
                if(spriteNum == 5)
                    spriteNum = 0;
                else spriteNum++;
                spriteCounter = 0;
            }
        }
        dashCooldown++;

        if(keyH.spacePressed == true)
        {
            if(dashCooldown > 120 && dashStart <= 16)
            {
                dashStart++;
                speed = dashSpeed;

                invincible = true;

                dash_spriteCounter++;
                if (dash_spriteCounter > 5)
                {
                    if (dash_spriteNum == 3)
                        dash_spriteNum = 0;
                    else dash_spriteNum++;
                    dash_spriteCounter = 0;
                }
            }
            else if(dashCooldown > 120 && dashStart > 16)
            {
                dashStart = 0;
                dashCooldown = 0;
                keyH.spacePressed = false;
                speed = defaultSpeed;
                invincible = false;
            }
            else
                keyH.spacePressed = false;
        }
            if (keyH.pPressed == true) {
                attack_spriteCounter++;
                if (attack_spriteCounter > 3) {

                    //save the current world coords and solidArea
                    int currentWorldX = worldX;
                    int currentWorldY = worldY;
                    int solidAreaWidth = solidArea.width;
                    int solidAreaHeight = solidArea.height;

                    //adjust player worldx/y for attackArea

                    switch(direction)
                    {
                        case "up" : worldY -= attackArea.height; break;
                        case "down" : worldY += attackArea.height; break;
                        case "left" : worldX -= attackArea.width; break;
                        case "right" : worldX += attackArea.width; break;
                    }

                    //attack area devine solid area
                    solidArea.width = attackArea.width;
                    solidArea.height = attackArea.height;
                    //verificam coliziunea cu inamicii cu valorile updatate ale lui worldx/y si solidArea
                    int monsterIndex = gp.cChecker.checkEntity(this, gp.mapManager.getCurrentMobs());
                    damageMonster(monsterIndex, gp.mapManager.getCurrentMobs());

                    //restore la datele originale

                    worldX = currentWorldX;
                    worldY = currentWorldY;
                    solidArea.width = solidAreaWidth;
                    solidArea.height = solidAreaHeight;

                    if (attack_spriteNum == 11)
                    {
                        attack_spriteNum = 0;
                        keyH.pPressed = false;
                    }
                    else attack_spriteNum++;
                    attack_spriteCounter = 0;
                }

        }

        //invincible frames
        if(invincible == true)
        {
            invincibleCounter++;
            if(invincibleCounter > 60)
            {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void contactMonster(int i)
    {
        if(i!=999)
        {
            if(invincible == false)
            {
                life -= 5;
                invincible = true;
            }
        }
    }


    public void interactNPC(int i)
    {
        if(i!=999)
        {
            if(gp.keyH.enterPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.enterPressed = false;
    }

    private int getMonsterIndex(Entity monster) {
        for (int j = 0; j < gp.monster.length; j++) {
            if (gp.monster[j] == monster) {
                return j;
            }
        }
        return -1;
    }

    public void damageMonster(int i, List<Entity> targetList) {
        if (i != 999 && i < targetList.size()) {
            Entity monster = targetList.get(i);

            if (monster != null && monster.invincible == false) {

                int realIndex = getMonsterIndex(monster);

                System.out.println("HP înainte: " + monster.life);


                int damage = 1;

                if (gp.statePiatra == 0 || gp.statePiatra == 1 || gp.statePiatra == 3) {
                    if (monster.type == 20 || monster.type == 21 || monster.type == 22)
                        damage = 5;
                } else if (gp.statePiatra == 2 || gp.statePiatra == 4) {
                    if (monster.type == 30 || monster.type == 31 || monster.type == 32)
                        damage = 5;
                } else if (gp.statePiatra == 5) {
                    if (monster.type == 40 || monster.type == 41 || monster.type == 42)
                        damage = 5;
                }

                monster.life -= damage;
                monster.invincible = true;

                System.out.println("Damage: " + damage + ", HP dupa: " + monster.life);

                if (monster.life <= 0) {
                    targetList.set(i, null);
                    if (realIndex != -1) {
                        gp.monster[realIndex] = null;

                    }
                }
            }
        } 
    }



    public void draw(Graphics2D g2)
    {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch (direction)
        {
            case "up":
                if(keyH.spacePressed == true)
                    image = dash_up[dash_spriteNum].image;
                else if (keyH.pPressed == true)
                    image = attack_up[attack_spriteNum].image;
                else image = up[spriteNum].image;
                break;
            case "down":
                if(keyH.spacePressed == true)
                    image = dash_down[dash_spriteNum].image;
                else if (keyH.pPressed == true)
                    image = attack_down[attack_spriteNum].image;
                else image = down[spriteNum].image;
                break;
            case "left":
                if(keyH.spacePressed == true)
                    image = dash_left[dash_spriteNum].image;
                else if (keyH.pPressed == true)
                    image = attack_left[attack_spriteNum].image;
                else image = left[spriteNum].image;
                break;
            case "right":
                if(keyH.spacePressed == true)
                    image = dash_right[dash_spriteNum].image;
                else if (keyH.pPressed == true)
                    image = attack_right[attack_spriteNum].image;
                else image = right[spriteNum].image;
                break;
        }

        if (invincible == true && !keyH.spacePressed)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        int x = screenX;
        int y = screenY;

        if(screenX > worldX)
        {
            x = worldX;
        }
        if(screenY > worldY)
        {
            y = worldY;
        }

        int rightOffset = gp.screenWidth - screenX;
        if(rightOffset > gp.worldWidth - worldX)
        {
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }

        int bottomOffset = gp.screenHeight - screenY;
        if(bottomOffset > gp.worldHeight - worldY)
        {
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }

        g2.drawImage(image, x, y, null);

        //reset opacity
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        g2.setColor(Color.RED);
        g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);

    }
}

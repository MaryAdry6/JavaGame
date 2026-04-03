package main;

import Tile.Tile;
import object.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class UI
{
    GamePanel gp;
    Graphics2D g2;

    public BufferedImage[] healthbarUI = new BufferedImage[21];
    public BufferedImage [] pietreUI = new BufferedImage[8];

    public BufferedImage img1 = ImageIO.read(getClass().getResourceAsStream("/gems/PiatraDePadure.png"));
    public BufferedImage img2 = ImageIO.read(getClass().getResourceAsStream("/gems/PiatraDeMunte.png"));
    public BufferedImage img3 = ImageIO.read(getClass().getResourceAsStream("/gems/PiatraDeVulcan.png"));
    public BufferedImage img4 = ImageIO.read(getClass().getResourceAsStream("/gems/PiatraFinala.png"));
    public BufferedImage logo = ImageIO.read(getClass().getResourceAsStream("/gems/LOGO.png"));

    //animatie logo
    int spriteCounter = 0, spriteNum = 0;
    boolean cresc = true;
    Tile [] l = new Tile[6];

    ArrayList<String> highscoreList = new ArrayList<>();

    public int commandNum = 0;

    public String currentDialogue = "";

    int subState = 0;

    public UI(GamePanel gp) throws IOException {
        this.gp = gp;

        //logo
        int x = 0;
        for (int i = 0; i < 4; i++) {
            if (l[i] == null) {
                l[i] = new Tile();
                l[i].image = logo.getSubimage(x, 0, 64, 64);

                x += 64;
            }
        }

        //bara de viata
        OBJ_Heart heart = new OBJ_Heart(gp);
        for (int i = 0; i < 20; i++) {
            healthbarUI[i] = heart.healthbar[i];
        }

        //puteri
        OBJ_Gems gems = new OBJ_Gems(gp);
        for (int i = 0; i < 7; i++) {
            pietreUI[i] = gems.pietre[i];
        }
    }


    public void draw(Graphics2D g2) throws IOException {
        this.g2 = g2;

        g2.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 40));
        g2.setColor(Color.white);

        //MENU STATE
        if(gp.gameState == gp.meniuState)
        {
            drawMeniuScreen();
        }

        //PLAY STATE
        if(gp.gameState == gp.playState)
        {
            drawPlayerLife();
            drawCurrentScore();
            drawPietre();
        }

        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState)
        {
            drawDialogueScreen();
        }
        //OPTIONS STATE
        if(gp.gameState == gp.optionsState)
        {
            drawOptionsScreen();
        }

        //GAME OVER
        if (gp.gameState == gp.gameOverState)
        {
            drawAMIMIR();
        }

        //GAME FINISHED
        if(gp.gameState == gp.gameFinishedState)
        {
            drawGameFinished();
        }

    }

    public void drawPlayerLife()
    {
        int life = gp.player.life;

        if (life < 0)
            life = 0;
        else if (life > 100)
            life = 100;
        
        int index = 20 - (life / 5);
        if(life < 5 && life > 0)
            index = 20;

        g2.drawImage(healthbarUI[index], 10, 10, null);
    }

    public void drawCurrentScore()
    {
        g2.setFont(new Font("Papyrus", Font.BOLD, 20));
        g2.setColor(new Color(74, 74, 74));
        String t = String.format("%06d", gp.scorFinal/60);
        g2.drawString(t , gp.tileSize*4-5, gp.tileSize*3-10);
//        g2.drawString("//", gp.tileSize*7, gp.tileSize*3-10);
        g2.drawString(gp.player.life + "/100", gp.tileSize*9-10, gp.tileSize*3-10);
    }
    public void drawPietre()
    {
        g2.drawImage(pietreUI[gp.statePiatra], 10, 10, null);
    }

    public void drawDialogueScreen()
    {
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 7;

        drawSubWindow(x,y,width,height);

        g2.setFont(new Font("Papyrus", Font.BOLD, 30));
        x += gp.tileSize;
        y += gp.tileSize+10;

        for(String line : currentDialogue.split("\n"))
        {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height)
    {
        Color c = new Color(192, 192, 192, 230);
        g2.setColor(c);
        g2.fillRect(x,y,width,height);

        c = new Color(89, 40, 111);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(x+5, y+5, width-10, height-10);
    }

    public void drawMeniuScreen()
    {
        //Background
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //Game NAME
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 120F));
        String text = "Magic Heart";
        int x = getX_centru(text), y = gp.tileSize*5;


        //Shadow
        g2.setColor(new Color(89, 40, 111));
        g2.drawString(text, x+5, y+5);

        //Main Text
        g2.setColor(new Color(216,162,255));
        g2.drawString(text, x, y);

        //LOGO
        x = gp.screenWidth/2 - (gp.tileSize*6)/2;
        y += gp.tileSize;



        g2.drawImage(l[spriteNum].image, x, y, gp.tileSize*6, gp.tileSize*6, null);


        //Butoane
        g2.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 66));

        text = "- New Game -";
        x = getX_centru(text);
        y += gp.tileSize*11;

        g2.setColor(new Color(216,162,255));
        g2.drawString(text, x, y+4);
        g2.setColor(new Color(145,38,221));
        g2.drawString(text, x, y);

        if(commandNum == 0)
        {
            g2.drawString(">", x-gp.tileSize-5, y);
        }


        text = "- Load Game -";
        x = getX_centru(text);
        y += gp.tileSize*2;

        g2.setColor(new Color(216,162,255));
        g2.drawString(text, x, y+4);
        g2.setColor(new Color(145,38,221));
        g2.drawString(text, x, y);

        if(commandNum == 1)
        {
            g2.drawString(">", x-gp.tileSize-5, y);
        }


        text = "- Quit -";
        x = getX_centru(text);
        y += gp.tileSize*2;

        g2.setColor(new Color(216,162,255));
        g2.drawString(text, x, y+4);
        g2.setColor(new Color(145,38,221));
        g2.drawString(text, x, y);

        if(commandNum == 2)
        {
            g2.drawString(">", x-gp.tileSize-5, y);
        }

    }

    public int getX_centru(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public void LogoUpdate()
    {
        spriteCounter++;
        if(spriteCounter > 11)
        {
            if(cresc == true)
            {
                if(spriteNum == 3)
                {
                    cresc = false;
                    spriteNum--;
                }
                else spriteNum++;
                spriteCounter = 0;
            }
            else
            {
                if(spriteNum == 0)
                {
                    cresc = true;
                    spriteNum++;
                }
                else spriteNum--;
                spriteCounter = 0;
            }
        }
    }

    public void GameOver(Leaderboard l) throws IOException {
        highscoreList = l.getBest(6);
        l.close();
    }

    public void drawGameFinished(){
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //aesthetic
        int x1 = 25;
        int x2 = x1 + gp.tileSize*2 + 10;
        int x3 = x2 + gp.tileSize*2;
        int x4 = x3 + gp.tileSize*2 + 10;

        int y1 = 6;

        for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                g2.drawImage(img1, x1, y1, gp.tileSize * 3, gp.tileSize * 3, null);
                g2.drawImage(img2, x2, y1, gp.tileSize * 3, gp.tileSize * 3, null);
                g2.drawImage(img3, x3, y1, gp.tileSize * 3, gp.tileSize * 3, null);
                g2.drawImage(img4, x4, y1, gp.tileSize * 3, gp.tileSize * 3, null);

                x1 += gp.tileSize * 10;
                x2 = x1 + gp.tileSize * 2 + 10;
                x3 = x2 + gp.tileSize * 2;
                x4 = x3 + gp.tileSize * 2 + 10;

            }
            x1 = 25;
            x2 = x1 + gp.tileSize * 2 + 10;
            x3 = x2 + gp.tileSize * 2;
            x4 = x3 + gp.tileSize * 2 + 10;

            y1 += gp.tileSize*3;
        }

        g2.setColor(Color.black);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));
        g2.fillRect(gp.tileSize*5+11, gp.tileSize*2, gp.tileSize*22-2, gp.tileSize*20);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


        //titlu:
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 90F));
        String text = "Highscores:";
        int x = getX_centru(text), y = gp.tileSize*5;
        g2.setColor(new Color(89, 40, 111));
        g2.drawString(text, x+5, y+5);
        g2.setColor(new Color(216,162,255));
        g2.drawString(text, x, y);

        //top players
        g2.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 35));
        y += gp.tileSize;

        for(int i = 0; i < highscoreList.size(); i++)
        {
            String player = highscoreList.get(i);
            x = getX_centru(player); y += gp.tileSize*2;
            g2.setColor(new Color(216,162,255));
            g2.drawString(player, x, y+3);
            g2.setColor(new Color(145,38,221));
            g2.drawString(player, x, y);
        }

        g2.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 32));
        text = "Press ESC to return to Main Menu";
        x = getX_centru(text); y += gp.tileSize*3-10;
        g2.setColor(new Color(89, 40, 111));
        g2.drawString(text, x, y+3);
        g2.setColor(new Color(216,162,255));
        g2.drawString(text, x, y);


    }

    public void drawOptionsScreen()
    {
//        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //sub window
        int frameX = gp.tileSize * 11;
        int frameY = gp.tileSize * 7;
        int frameWidth = gp.tileSize * 10;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        switch(subState)
        {
            case 0: options_top(frameX,frameY); break;
            case 1: options_save_game(frameX,frameY); break;
            case 2: options_endGameConfirmation(frameX,frameY); break;
        }

    }
    public void options_top(int frameX, int frameY)
    {
        int textX;
        int textY;

        //title
        String text = "Options";
        textX = getX_centru(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text,textX,textY);

        //SAVE
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*3;
        g2.drawString("Save Game",textX,textY);
        if(commandNum == 0)
        {
            g2.drawString(">", textX-22, textY);
            if(gp.keyH.enterPressed == true)
            {
                subState = 1;
                commandNum = 0;
                gp.keyH.enterPressed = false;
            }
        }

        //QUIT
        textX = frameX + +gp.tileSize;
        textY = frameY + gp.tileSize*5;
        g2.drawString("Quit Game",textX,textY);
        if(commandNum == 1)
        {
            g2.drawString(">", textX-22, textY);
            if(gp.keyH.enterPressed == true)
            {
                subState = 2;
                commandNum = 0;
                gp.keyH.enterPressed = false;
            }
        }

    }
    public void options_endGameConfirmation(int frameX, int frameY)
    {
        int textX = frameX + 10;
        int textY = frameY + gp.tileSize*2;
        currentDialogue = "Quit the game\nand return to\nthe menu?";
        for(String line: currentDialogue.split("\n"))
        {
            g2.drawString(line,textX,textY);
            textY += 40;
        }

        //YES
        String text = "Yes!";
        textX = getX_centru(text);
        textY += gp.tileSize*2;
        g2.drawString(text,textX,textY);
        if(commandNum == 0)
        {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true)
            {
                subState = 0;
//                gp.gameState = gp.meniuState;
                gp.setupGame();
                gp.keyH.enterPressed = false;
            }
        }

        //NO
        String text2 = "No!";
        textX = getX_centru(text);
        textY += gp.tileSize;
        g2.drawString(text2,textX+9,textY);
        if(commandNum == 1)
        {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true)
            {
                subState = 0;
                commandNum = 0;
                gp.keyH.enterPressed = false;
            }
        }

    }
    public void options_save_game(int frameX, int frameY)
    {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;
        String text = "Game saved \nsuccessfully!";
        for(String line: text.split("\n"))
        {
            g2.drawString(line,textX,textY);
            textY += 40;
        }
        if(commandNum == 0)
        {
            if(gp.keyH.enterPressed == true)
            {
                subState = 0;
                gp.keyH.enterPressed = false;
            }
        }
        gp.saveLoad.connect();
        gp.saveLoad.save();

    }

    public void drawAMIMIR()
    {
        g2.setFont(g2.getFont().deriveFont(32F));

        //sub window
        int frameX = gp.tileSize * 11;
        int frameY = gp.tileSize * 7;
        int frameWidth = gp.tileSize * 10;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        int textX = frameX + gp.tileSize-5;
        int textY = frameY + gp.tileSize*4;
        String text = "Ai murit!\nMAI INCEARCA!";
        for(String line: text.split("\n"))
        {
            g2.drawString(line,textX,textY);
            textY += 40;
        }
        if(gp.keyH.enterPressed == true)
        {
            gp.gameState = gp.playState;
            gp.saveLoad.connect();
            gp.saveLoad.load();
//            subState = 0;
            gp.keyH.enterPressed = false;
        }

    }


}

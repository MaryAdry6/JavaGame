package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, spacePressed, pPressed;

    public KeyHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();

        //Meniu State
        if(gp.gameState == gp.meniuState)
        {
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0)
                    gp.ui.commandNum = 2;
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2)
                    gp.ui.commandNum = 0;
            }

            if(code == KeyEvent.VK_ENTER)
            {
                if(gp.ui.commandNum == 0)
                {
                    gp.startNewGame();
                }
                if(gp.ui.commandNum == 1)
                {
                    gp.startLoadGame();
                }
                if(gp.ui.commandNum == 2)
                    System.exit(0);
            }
            enterPressed = false;
        }


        //Play State
        if (gp.gameState == gp.playState)
        {
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            {
                upPressed = true;
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            {
                downPressed = true;
            }
            if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
            {
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            {
                rightPressed = true;
            }

            if(code == KeyEvent.VK_ENTER)
            {
                enterPressed = true;
            }

            if(code == KeyEvent.VK_ESCAPE)
            {
                gp.gameState = gp.optionsState;
                gp.ui.subState = 0;
                spacePressed = false;
            }

            if(code == KeyEvent.VK_SPACE)
            {
                spacePressed = true;
            }
            if (code == KeyEvent.VK_P)
            {
                pPressed = true;
            }
            if (code == KeyEvent.VK_Q)
            {
                if (gp.mapManager.getCurrentIndex() == 0)
                {
//                    gp.statePiatra = 0;
                    //nimic, e doar o piatra deblocata
                }
                else if (gp.mapManager.getCurrentIndex() == 1)
                {
                    if(gp.statePiatra == 1)
                        gp.statePiatra = 2;
                    else if(gp.statePiatra == 2)
                        gp.statePiatra = 1;
                }
                else if(gp.mapManager.getCurrentIndex() == 2)
                {
                    if(gp.statePiatra == 3)
                        gp.statePiatra = 5;
                    else if(gp.statePiatra == 4)
                        gp.statePiatra = 3;
                    else if(gp.statePiatra == 5)
                        gp.statePiatra = 4;
                }
            }
            if (code == KeyEvent.VK_E)
            {
                if (gp.mapManager.getCurrentIndex() == 0)
                {
//                    gp.statePiatra = 0;
                    //nimic, e doar o piatra deblocata
                }
                else if (gp.mapManager.getCurrentIndex() == 1)
                {
                    if(gp.statePiatra == 1)
                        gp.statePiatra = 2;
                    else if(gp.statePiatra == 2)
                        gp.statePiatra = 1;
                }
                else if(gp.mapManager.getCurrentIndex() == 2)
                {
                    if(gp.statePiatra == 3)
                        gp.statePiatra = 4;
                    else if(gp.statePiatra == 4)
                        gp.statePiatra = 5;
                    else if(gp.statePiatra == 5)
                        gp.statePiatra = 3;
                }
            }
        }

        //Dialogue State
        else if(gp.gameState == gp.dialogueState)
        {
            if(code == KeyEvent.VK_ENTER)
            {
                gp.gameState = gp.playState;
            }
        }

        //Options state
        else if(gp.gameState == gp.optionsState)
        {
            if(code == KeyEvent.VK_ESCAPE)
            {
                gp.gameState = gp.playState;
                gp.ui.subState = 0;
            }
            if(code == KeyEvent.VK_ENTER)
            {
                enterPressed = true;
            }
            else
            {
                enterPressed = false;
            }
            int maxCommandNum = 0;
            switch(gp.ui.subState)
            {
                case 0: maxCommandNum = 1; break;
                case 1: maxCommandNum = 0; break;
                case 2: maxCommandNum = 1; break;
            }
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0)
                    gp.ui.commandNum = maxCommandNum;
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > maxCommandNum)
                    gp.ui.commandNum = 0;
            }
        }

        //Game Over
        else if(gp.gameState == gp.gameOverState)
        {
            if(code == KeyEvent.VK_ENTER)
            {
                enterPressed = true;
            }
        }

        //Game Finished
        else if(gp.gameState == gp.gameFinishedState)
        {
            if(code == KeyEvent.VK_ESCAPE)
            {
                gp.setupGame();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
        {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
        {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
        {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
        {
            rightPressed = false;
        }
    }
}

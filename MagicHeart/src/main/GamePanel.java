package main;


import Tile.*;
import ai.PathFinder;
import entity.Entity;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    private final JFrame window;

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 32x32 tile-uri pt caractere si tileuri n shit
    final int scale = 2; // 16*2 = 32x32 o sa arate caracteru pe ecranu

    public final int tileSize = originalTileSize * scale; // 32x32 tile
    public final int maxScreenCol = 32; //  768(height)/64px(tile)
    public final int maxScreenRow = 24; //  1024(width)/64px(tile)
    public final int screenWidth = tileSize * maxScreenCol; // 768
    public final int screenHeight = tileSize * maxScreenRow; // 1024

    //WORLD SETTINGS
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;

    //System
    public TileManager tileM = new TileManager(this, "/maps/world01.txt");
//    public TileManager tileMOverlay = new TileManager(this, "/maps/map01Overlay.txt");
    public MapManager mapManager = new MapManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound sound = new Sound();
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    SaveLoad saveLoad = SaveLoad.getInstance(this);
    public PathFinder pFinder = new PathFinder(this);

    //UI
    public UI ui = new UI(this);

    //event
    public EventHandler eHandler = new EventHandler(this);

    //Player
    public Player player = new Player(this, keyH);
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[40];
    private ArrayList<Entity> entityList = new ArrayList<>();

    //Game State
    public int gameState;
    public final int meniuState = 0;
    public final int playState = 1;
    public final int optionsState = 2;
    public final int dialogueState = 3;
    public final int gameOverState = 4;
    public final int gameFinishedState = 5;

    public int statePiatra = 0;

    //pt scor
    public static boolean scorSalvat = false;
    public static int scorFinal = 0;

    public static int musicScroller = 1;

    public GamePanel(JFrame window) throws IOException
    {
        this.window = window;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //daca-i true face rendering performance u mai bun
        this.addKeyListener(keyH);  // tastatura
        this.setFocusable(true); // cu asta gamepanel poate fi "focusat" sa primeasca key input
    }

    public void setupGame() {
          aSetter.setObject();
          aSetter.setNPC();
          aSetter.setMonster();
          mapManager.loadEntities();
          playMusic(0, 0);
          gameState = meniuState;
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;   // 0.0166666 seconds , 1bil nanosecunde folosit
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null)
        {
            try {
                update(); // 1 update : update information such as character positions
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            repaint(); // 2 draw : draw the screen with the updated information

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0)
                {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() throws IOException {
        if(!window.isActive())//sa nu continue jocul singur daca schimbam focusul de pe window-ul curent
        {
            keyH.upPressed = false;
            keyH.downPressed = false;
            keyH.leftPressed = false;
            keyH.rightPressed = false;
            keyH.enterPressed = false;
            keyH.spacePressed = false;
            keyH.pPressed = false;
            return;
        }

        //System.out.println("Este goală MobsMap3(): " + mapManager.gol(mapManager.MobsMap2()));
        if(gameState == playState)
        {
            scorFinal++;

            //PLAYER
            player.update();

            //NPC & MOBS
            for(Entity mob : mapManager.getCurrentMobs())
            {
                if (mob != null)
                {
                    mob.update();
                }
            }

            for(Entity npc : mapManager.getCurrentNPC())
            {

                npc.update();
            }

            //Schimbare Harta
            if(mapManager.getCurrentIndex() == 2 && mapManager.isBossDefeated(2))
            {
                if(player.worldX < 0)
                {
                    stopMusic();
                    playMusic(musicScroller++, 0);

                    mapManager.nextMap();
                    mapManager.loadEntities();

                }

            }
            else if(mapManager.getCurrentIndex() == 1)
            {
                if(player.worldY < 0 && mapManager.isBossDefeated(1))
                {
                    stopMusic();
                    playMusic(musicScroller++, 0);

                    mapManager.nextMap();
                    mapManager.loadEntities();

                    statePiatra = 3;
                    player.worldX = tileSize * 7;
                    player.worldY = tileSize * 19;
                }

            }
            else if(mapManager.getCurrentIndex() == 0)
            {
                if(player.worldY < 0 && mapManager.isBossDefeated(0))
                {
                    stopMusic();
                    playMusic(musicScroller++, 0);

                    mapManager.nextMap();
                    mapManager.loadEntities();

                    statePiatra = 1;
                    player.worldX = tileSize * 50;
                    player.worldY = tileSize * 46;
                }

            }

            //MOARE JUCATORUL
            if(player.life <= 0)
                gameState = gameOverState;


        }
        if(gameState == meniuState)
        {
            ui.LogoUpdate();
        }
        if (gameState == gameFinishedState && !scorSalvat)
        {
            scorSalvat = true;//ca sa nu apara fereastra aia la infinit doamne cat am putut sa imi bat capul aici

            String numeJucator = null;
            while (numeJucator == null)
            {
                numeJucator = JOptionPane.showInputDialog(null, "Enter your name:", "Game Finished!", JOptionPane.PLAIN_MESSAGE);
            }

            if (numeJucator != null && !numeJucator.isEmpty()) {
                Leaderboard l = Leaderboard.getInstance();
                l.connect();
                l.createTable();
                l.newEntry(numeJucator, scorFinal/60);

                playMusic(0, 78);
                ui.GameOver(l);
            }
        }
    }

    public void startNewGame()
    {
        player.setDefaultValues();
        mapManager.currentIndex = 0;

        statePiatra = 0;
        player.worldX = tileSize * 36;
        player.worldY = tileSize * 46;

        aSetter.setMonster();
        aSetter.setNPC();

        mapManager.loadEntities();
        gameState = playState;

        scorFinal = 0;
        scorSalvat = false;

        musicScroller = 1;
        stopMusic();
        playMusic(musicScroller++);
        ui.subState = 0;

        SaveLoad saveLoad = SaveLoad.getInstance(this);
        saveLoad.connect();
        saveLoad.deleteAllSaves();
        saveLoad.save();

    }

    public void startLoadGame()
    {
        saveLoad.connect();
        saveLoad.load();
        gameState = playState;
        stopMusic();
        ui.subState = 0;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //Meniu Screen
        if(gameState == meniuState)
        {
            try {
                ui.draw(g2);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                System.out.println("Eroare la DRAW_UI");
            }
        }
        else //Altele |--------------> daca modificam ceva, AICI scriem
        {
            //HARTA
           // tileM.draw(g2, false); // mai intai desenam tile-urile si dupa playeru. daca facem invers playerul se duce sub tiles si nu-l mai vedem
            //tileMOverlay.draw(g2, true);
            //tileMOverlay.draw(g2, false);
            //mapManager.getCurrentMap().draw(g2);

            for(AbstractTileManager tm : mapManager.getCurrentBaseMaps())
            {
                tm.draw(g2,false);
            }

            for (AbstractTileManager tm : mapManager.getCurrentOverlayBaseMaps()) {
                tm.draw(g2, false);
            }



            entityList.add(player);


            for (Entity e : mapManager.getCurrentNPC()) {
                if (e != null && e.active) {
                    entityList.add(e);
                }
            }


            for (Entity e : mapManager.getCurrentMobs()) {
                if (e != null) {
                    entityList.add(e);
                }
            }



            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY,e2.worldY);
                    return result;
                }
            });

            //DRAW ENTITIES
            for (Entity entity : entityList) {
                entity.draw(g2);
            }
            entityList.clear();

            //mapManager.getCurrentMap().draw(g2, true);
        //tileMOverlay.draw(g2, true);

            for(AbstractTileManager tm : mapManager.getCurrentOverlayMaps())
            {
                tm.draw(g2,true);
            }


            //UI
            try {
                ui.draw(g2);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                System.out.println("Eroare la DRAW_UI");
            }
        }

        g2.dispose();
    }

    public void playMusic(int i) {playMusic(i, 0);}//simulam parametru predefinit
    public void playMusic(int i, int startSecond)
    {
        sound.setFile(i);
        sound.setPosition(startSecond); //da play de la o anumita secunda
        sound.play();
    }

    public void stopMusic()
    {
        sound.stop();
    }

//    public void playSE(int i)
//    {
//        sound.setFile(i);
//        sound.play();
//    }
}

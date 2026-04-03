package Tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static Tile.Tile.BASE;
import static Tile.Tile.OVERLAY;

public class TileManager3 extends TileManager implements AbstractTileManager
{

    public TileManager3(GamePanel gp, String mapPath)
    {
        super(gp, mapPath);

        getTileSheet();
        getTileImage();
        loadMap(mapPath, mapTileNum);
    }

    @Override
    public void getTileSheet()
    {
        try
        {
            tileSheet = ImageIO.read(getClass().getResourceAsStream("/tiles/freelavatileset-Sheet.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Eroare la TILESHEET PROSTULE");
        }

    }

    @Override
    public void getTileImage()
    {

        setupTile16(131,  48, 32);
        setupTile16(132,  64, 32);
        setupTile16(133,  80, 32);

        setupTile16(0,  32, 48);        //floor
        setupTile16(1,  48, 48);
        setupTile16(2,  64, 48);
        setupTile16(3,  80, 48);

        setupTile16(4,  96, 48);
        setupTile16(5,  16, 64);
        setupTile16(101,  16, 64);      //collision
        setupTile16(6,  32, 64);
        setupTile16(7,  48, 64);
        setupTile16(8,  64, 64);
        setupTile16(9,  80, 64);
        setupTile16(10, 96, 64);
        setupTile16(11, 112, 64);
        setupTile16(12,  16, 80);
        setupTile16(13,  32, 80);
        setupTile16(14,  48, 80);
        setupTile16(15,  64, 80);
        setupTile16(16,  80, 80);
        setupTile16(17,  96, 80);
        setupTile16(18,  112, 80);
        setupTile16(19,  16, 96);
        setupTile16(20,  32, 96);
        setupTile16(21,  48, 96);
        setupTile16(22,  64, 96);
        setupTile16(23,  80, 96);
        setupTile16(24,  96, 96);
        setupTile16(25,  112, 96);

        setupTile16(26,  16, 112);      //wall
        setupTile16(27,  32, 112);

        setupTile16(28,  48, 112);      //floor
        setupTile16(29,  64, 112);
        setupTile16(30,  80, 112);

        setupTile16(31,  96, 112);      //wall
        setupTile16(32,  112, 112);
        setupTile16(33,  16, 128);
        setupTile16(34,  32, 128);
        setupTile16(35,  48, 128);
        setupTile16(36,  64, 128);
        setupTile16(99,  64, 128);      //colission
        setupTile16(37,  80, 128);
        setupTile16(38,  96, 128);
        setupTile16(39,  112, 128);

        setupTile16(40,  16, 144);      //lava cu rock

        setupTile16(41,  32, 144);      //wall
        setupTile16(42,  48, 144);
        setupTile16(43,  64, 144);
        setupTile16(44,  80, 144);
        setupTile16(45,  96, 144);

        setupTile16(46,  112, 144);     //lava cu rock
        setupTile16(47,  16, 160);
        setupTile16(48,  32, 160);

        setupTile16(49,  48, 160);      //wall
        setupTile16(50,  64, 160);
        setupTile16(51,  80, 160);

        setupTile16(52,  96, 160);      //lava cu rock
        setupTile16(53,  112, 160);
        setupTile16(54,  16, 176);
        setupTile16(55,  32, 176);
        setupTile16(56,  48, 176);
        setupTile16(57,  64, 176);
        setupTile16(58,  80, 176);
        setupTile16(59,  96, 176);
        setupTile16(60,  112, 176);

        setupTile16(91, 0, 16);         //rock
        setupTile16(92, 0, 32);         //spike rock
        setupTile16(93, 0, 48);         //static lava
        setupTile16(94, 16, 48);        //floor unreachable
        setupTile16(95, 16, 32);        //chietre fara lava
        setupTile16(96, 16, 16);
        setupTile16(97, 32, 16);        //lampa
        setupTile16(98, 32, 0);

        setupTile16(100, 16, 0);        //bg free

        setupTile16(61, 16, 192);      //lava animated
        setupTile16(62, 32, 192);
        setupTile16(63, 48, 192);
        setupTile16(64, 64, 192);
        setupTile16(65, 80, 192);
        setupTile16(66, 96, 192);
        setupTile16(67, 112, 192);
        setupTile16(68, 16, 208);
        setupTile16(69, 32, 208);
        setupTile16(70, 48, 208);
        setupTile16(71, 64, 208);
        setupTile16(72, 80, 208);
        setupTile16(73, 96, 208);
        setupTile16(74, 112, 208);
        setupTile16(75, 16, 224);
        setupTile16(76, 32, 224);
        setupTile16(77, 48, 224);
        setupTile16(78, 64, 224);
        setupTile16(79, 80, 224);
        setupTile16(80, 96, 224);
        setupTile16(81, 112, 224);


        tile[0].collision = true;
        tile[4].collision = true;
        tile[5].collision = true;
        tile[12].collision = true;
        tile[19].collision = true;
        tile[11].collision = true;
        tile[18].collision = true;
        tile[25].collision = true;
        tile[27].collision = true;
        tile[35].collision = true;
        tile[36].collision = true;
        tile[37].collision = true;
        tile[31].collision = true;
        tile[131].collision = true;
        tile[132].collision = true;
        tile[133].collision = true;
        tile[97].collision = true;
        tile[93].collision = true;
        tile[56].collision = true;
        tile[58].collision = true;

        tile[40].collision = true;
        tile[46].collision = true;
        tile[47].collision = true;
        tile[48].collision = true;
        tile[52].collision = true;
        tile[53].collision = true;
        tile[55].collision = true;
        tile[59].collision = true;
        tile[54].collision = true;
        tile[60].collision = true;
    }

    @Override
    public void loadMap(String filePath, int mapTileNum[][])
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow)
            {
                String line = br.readLine();

                while(col < gp.maxWorldCol)
                {
                    String numbers[] = line.split(" "); //ia fiecare cifra din matricea aia din map01.txt

                    int num = Integer.parseInt(numbers[col]); // string -> int
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Eroare la LOADMAP PROSTULE");
        }


    }

    @Override
    public void draw(Graphics2D g2, boolean isOverlay)
    {
        int worldCol = 0;
        int worldRow = 0;


        if(isOverlay)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) // sa nu iasa din fereastra
        {
            int tileNum = mapTileNum[worldCol][worldRow];
            Tile currentTile = tile[tileNum];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //oprim camera sa nu se vada negru

            if(gp.player.screenX > gp.player.worldX)
            {
                screenX = worldX;
            }

            if (gp.player.screenY > gp.player.worldY)
            {
                screenY = worldY;
            }

            int rightOffset = gp.screenWidth - gp.player.screenX;
            if(rightOffset > gp.worldWidth - gp.player.worldX)
            {
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }

            int bottomOffset = gp.screenHeight - gp.player.screenY;
            if(bottomOffset > gp.worldHeight - gp.player.worldY)
            {
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }

            // Fog of war check
            boolean isInFogOfWar = worldX + gp.tileSize*20 > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize*20 < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize*20 > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize*20 < gp.player.worldY + gp.player.screenY;

            // Draw tile if it's visible and matches the current render layer
            if (tileNum != 100 && isInFogOfWar) {
                if (isOverlay && currentTile.renderLayer == OVERLAY) {
                    g2.drawImage(currentTile.image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                } else if (!isOverlay && currentTile.renderLayer == BASE) {
                    g2.drawImage(currentTile.image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }

            worldCol++;

            if(worldCol==gp.maxWorldCol)
            {
                worldCol = 0;
                worldRow++;
            }
        }

        if(isOverlay)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }



    }

    @Override
    public void draw(Graphics2D g2) {
        draw(g2, false);
    }


}

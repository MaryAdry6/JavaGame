package Tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager2 extends TileManager implements AbstractTileManager
{

    public TileManager2(GamePanel gp, String mapPath)
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
            tileSheet = ImageIO.read(getClass().getResourceAsStream("/tiles/tileset.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Eroare la TILESHEET PROSTULE");
        }

    }

    @Override
    public void getTileImage() {
        int tileWidth = 16;
        int tileHeight = 16;

        int tilesetCols = tileSheet.getWidth() / tileWidth;
        int tilesetRows = tileSheet.getHeight() / tileHeight;

        int index = 0;

        for (int row = 0; row < tilesetRows; row++) {
            for (int col = 0; col < tilesetCols; col++) {
                if (index >= tile.length) {
                    break;
                }
                int x = col * tileWidth;
                int y = row * tileHeight;

                setupTile16(index, x, y);
                index++;
            }
        }
        tile[140].collision = true;
        tile[148].collision = true;
        tile[149].collision = true;
        tile[24].collision = true;
        tile[25].collision = true;
        tile[47].collision = true;
        tile[48].collision = true;
        tile[30].collision = true;
        tile[79].collision = true;
        tile[80].collision = true;
        tile[37].collision = true;
        tile[146].collision = true;
        tile[151].collision = true;
        tile[76].collision = true;
        tile[77].collision = true;
        tile[53].collision = true;
        tile[54].collision = true;
        tile[99].collision = true;
        tile[100].collision = true;
        tile[123].collision = true;
        tile[125].collision = true;
        tile[126].collision = true;
        tile[102].collision = true;
        tile[103].collision = true;
        tile[128].collision = true;
        tile[105].collision = true;
        tile[106].collision = true;
        tile[82].collision = true;
        tile[83].collision = true;
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

            if(tileNum != 160)
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null); // pune tile la (0,0)

            //fog of war
            if(worldX > gp.player.worldX - gp.player.screenX &&
                    worldX < gp.player.worldX +gp.player.screenX &&
                    worldY > gp.player.worldY - gp.player.screenY &&
                    worldY < gp.player.worldY + gp.player.screenY )
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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

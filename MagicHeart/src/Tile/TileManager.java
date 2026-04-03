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

public class TileManager implements AbstractTileManager
{
    GamePanel gp;
    public Tile[] tile;
    BufferedImage tileSheet;
    public int mapTileNum[][];


    public TileManager(GamePanel gp, String mapPath)
    {
        this.gp = gp;
        tile = new Tile[200]; //cate tile uri
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileSheet();
        getTileImage();
        loadMap(mapPath, mapTileNum);
    }

    public BufferedImage scaleImage(BufferedImage original, int width, int height)
    {

        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }

    public void setupTile16(int index, int x, int y)
    {
        tile[index] = new Tile();
        tile[index].image = tileSheet.getSubimage(x, y, 16, 16);
        tile[index].image = scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
    }

    public void getTileSheet()
    {
        try
        {
            tileSheet = ImageIO.read(getClass().getResourceAsStream("/tiles/spr_grass_tileset3.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Eroare la TILESHEET PROSTULE");
        }

    }

    public void getTileImage()
    {

        setupTile16(0, 16, 32); // 0 - 16 (fancy grass)
        setupTile16(1, 32, 32);
        setupTile16(2, 48, 32);
        setupTile16(3, 64, 32);
        setupTile16(4, 16, 48);
        setupTile16(5, 32, 48);
        setupTile16(6, 48, 48);
        setupTile16(7, 64, 48);
        setupTile16(8, 16,64);
        setupTile16(9, 32, 64);
        setupTile16(10, 48, 64);
        setupTile16(11, 64, 64);
        setupTile16(12, 16, 80);
        setupTile16(13, 0, 32); // tile mov
        setupTile16(14, 32, 80);
        setupTile16(15, 48,80);
        setupTile16(16, 64,80);

        setupTile16(17, 96,128); // 17 - 20 (path)
        setupTile16(18, 112,128);
        setupTile16(19, 96,144);
        setupTile16(20, 112,144);

        setupTile16(21, 96, 112 );  // 21 - 22 (grass care l folosim mai des)
        setupTile16(22, 112, 112);

        setupTile16(23, 256, 96); // 23 - 38 (transition dintre grass si path (all angles)
        setupTile16(24, 272, 96);
        setupTile16(25, 288, 96);
        setupTile16(26, 304, 96);
        setupTile16(27, 256, 112);
        setupTile16(28, 272, 112);
        setupTile16(29, 288, 112);
        setupTile16(30, 304, 112);
        setupTile16(31, 256, 128);
        setupTile16(32, 272, 128);
        setupTile16(33, 288, 128);
        setupTile16(34, 304, 128);
        setupTile16(35, 256, 144);
        setupTile16(36, 272, 144);
        setupTile16(37, 288, 144);
        setupTile16(38, 304, 144);

        setupTile16(39, 176, 32); // pentru hill-uri
        setupTile16(40, 192, 32);
        setupTile16(41, 208, 32);
        setupTile16(42, 224, 32);
        setupTile16(43, 176, 48);
        setupTile16(44, 192, 48);
        setupTile16(45, 208, 48);
        setupTile16(46, 224, 48);
        setupTile16(47, 176, 64);
        setupTile16(48, 192, 64);
        setupTile16(49, 208, 64);
        setupTile16(50, 224, 64);
        setupTile16(51, 176, 80);
        setupTile16(52, 192, 80);
        setupTile16(53, 208, 80);
        setupTile16(54, 224, 80);

        setupTile16(55, 176, 208); // pt apa
        setupTile16(56, 176, 240);

        setupTile16(57, 0, 0); // transparent tile

        // setupTile16(58, 16, 192);

        setupTile16(58, 176, 96); // grass pt apa
        setupTile16(59, 192, 96);
        setupTile16(60, 208, 96);
        setupTile16(61, 224, 96);
        setupTile16(62, 240, 96);
        setupTile16(63, 176, 112);
        setupTile16(64, 240, 112);
        setupTile16(65, 176, 128);
        setupTile16(66, 240, 128);
        setupTile16(67, 176, 144);
        setupTile16(68, 192, 144);
        setupTile16(69, 208, 144);
        setupTile16(70, 224, 144);
        setupTile16(71, 240, 144);

        setupTile16(72, 192 , 192 ); //grass pt apa better
        setupTile16(73, 240 , 192 );
        setupTile16(74, 192 , 240 );
        setupTile16(75, 240 , 240 );
        setupTile16(76, 256 , 192 );
        setupTile16(77, 320 , 192 );
        setupTile16(78, 272 , 192 );
        setupTile16(79, 288 , 192 );
        setupTile16(80, 288 , 208 );

        setupTile16(81, 128,  96); // grasspath din afara
        setupTile16(82, 128 , 128 );
        setupTile16(83, 160 , 96 );
        setupTile16(84, 160 , 128 );

        setupTile16(85, 96,   80); //actual grass tile we use


        setupTile16(86, 320 , 208 ); //grass pt apa better 2
        setupTile16(87, 256 , 208 );
        setupTile16(88, 272 , 240 );
        setupTile16(89, 256 , 240 );
        setupTile16(90, 320 , 240 );


        setupTile16(91, 272 , 272 ); //bridge
        setupTile16(92, 288 , 272 );
        setupTile16(93, 304 , 272 );
        setupTile16(94, 272 , 288 );
        setupTile16(95, 288 , 288 );
        setupTile16(96, 304 , 288 );
        setupTile16(97, 272 , 304 );
        setupTile16(98, 288 , 304 );
        setupTile16(99, 304 , 304 );
        setupTile16(100, 272 , 352 );
        setupTile16(101, 288 , 352 );
        setupTile16(102, 304 , 352 );

        setupTile16(103, 176 , 352 ); //fence
        setupTile16(104, 192 , 352 );
        setupTile16(105, 192 , 368 );
        setupTile16(106, 160 , 384 );

        setupTile16(107, 16, 304); //copac 1
        setupTile16(108, 32, 304);
        setupTile16(109, 48, 304);
        setupTile16(110, 16, 320);
        setupTile16(111, 32, 320);
        setupTile16(112, 48, 320);
        setupTile16(113, 16, 336);
        setupTile16(114, 32, 336);
        setupTile16(115, 48, 336);
        setupTile16(116, 16, 352);
        setupTile16(117, 32, 352);
        setupTile16(118, 48, 352);

        setupTile16(119, 64, 304); // copac 2
        setupTile16(120, 80, 304);
        setupTile16(121, 96, 304);
        setupTile16(122, 64, 320);
        setupTile16(123, 80, 320);
        setupTile16(124, 96, 320);
        setupTile16(125, 64, 336);
        setupTile16(126, 80, 336);
        setupTile16(127, 96, 336);
        setupTile16(128, 64, 352);
        setupTile16(129, 80, 352);
        setupTile16(130, 96, 352);

        setupTile16(131, 112, 320); // copac 3
        setupTile16(132, 128, 320);
        setupTile16(133, 144, 320);
        setupTile16(134, 112, 336);
        setupTile16(135, 128, 336);
        setupTile16(136, 144, 336);
        setupTile16(137, 112, 352);
        setupTile16(138, 128, 352);
        setupTile16(139, 144, 352);
        setupTile16(140, 112, 368);
        setupTile16(141, 128, 368);
        setupTile16(142, 144, 368);

        setupTile16(143, 16, 384); // rock 1

        setupTile16(144, 32, 384); // rock 2
        setupTile16(145, 48, 384);

        setupTile16(146, 64, 384); // rock 3
        setupTile16(147, 80, 384);

        setupTile16(148, 96, 384); // rock 4
        setupTile16(149, 112, 384);
        setupTile16(150, 96, 400);
        setupTile16(151, 112, 400);

        setupTile16(152, 16, 400); // rock 5
        setupTile16(153, 32, 400);
        setupTile16(154, 16, 416);
        setupTile16(155, 32, 416);

        tile[74].collision = true;
        //tile[77].collision = true;
        tile[78].collision = true;
        tile[79].collision = true;
        tile[80].collision = true;

        tile[103].collision = true;
        tile[104].collision = true;
        tile[105].collision = true;
        tile[106].collision = true;

        tile[143].collision = true;

        tile[144].collision = true;
        tile[145].collision = true;

        tile[146].collision = true;
        tile[147].collision = true;

        tile[148].collision = true;
        tile[149].collision = true;
        tile[150].collision = true;
        tile[151].collision = true;

        tile[152].collision = true;
        tile[153].collision = true;
        tile[154].collision = true;
        tile[155].collision = true;

        tile[117].collision = true;
        tile[129].collision = true;
        tile[141].collision = true;


        tile[107].renderLayer = OVERLAY;
        tile[108].renderLayer = OVERLAY;
        tile[109].renderLayer = OVERLAY;
        tile[110].renderLayer = OVERLAY;
        tile[111].renderLayer = OVERLAY;
        tile[112].renderLayer = OVERLAY;
        tile[113].renderLayer = OVERLAY;
        tile[114].renderLayer = OVERLAY;
        tile[115].renderLayer = OVERLAY;

        tile[119].renderLayer = OVERLAY;
        tile[120].renderLayer = OVERLAY;
        tile[121].renderLayer = OVERLAY;
        tile[122].renderLayer = OVERLAY;
        tile[123].renderLayer = OVERLAY;
        tile[124].renderLayer = OVERLAY;
        tile[125].renderLayer = OVERLAY;
        tile[126].renderLayer = OVERLAY;
        tile[127].renderLayer = OVERLAY;

        tile[131].renderLayer = OVERLAY;
        tile[132].renderLayer = OVERLAY;
        tile[133].renderLayer = OVERLAY;
        tile[134].renderLayer = OVERLAY;
        tile[135].renderLayer = OVERLAY;
        tile[136].renderLayer = OVERLAY;
        tile[137].renderLayer = OVERLAY;
        tile[138].renderLayer = OVERLAY;
        tile[139].renderLayer = OVERLAY;

    }

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
            if (tileNum != 57 && isInFogOfWar) {
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

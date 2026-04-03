package Tile;

import entity.Entity;
import main.GamePanel;
//import monster.MON_WolfForest;

import java.util.*;

public class MapManager {

    public GamePanel gp;
    private final List<AbstractTileManager> maps = new ArrayList<>();
    public List<List<Entity>> mapMobs = new ArrayList<>();
    public List<List<Entity>> mapNPC = new ArrayList<>();

    public int currentIndex = 0;

    public MapManager(GamePanel gp)
    {

        this.gp = gp;

        maps.add(new TileManager(gp, "/maps/world01.txt"));
        maps.add(new TileManager(gp, "/maps/map01Overlay.txt"));
        maps.add(new TileManager2(gp, "/maps/world02.txt"));
        maps.add(new TileManager3(gp,"/maps/world03.txt" ));
        maps.add(new TileManager3(gp, "/maps/map03Overlay.txt"));

    }

    public void loadEntities() {

        mapMobs.clear();
        mapNPC.clear();

        mapMobs.add(MobsMap1());
        mapMobs.add(MobsMap2());
        mapMobs.add(MobsMap3());
        mapMobs.add(List.of());
        mapMobs.add(List.of());

        mapNPC.add(List.of(gp.npc[0]));
        mapNPC.add(List.of(gp.npc[1]));
        mapNPC.add(List.of(gp.npc[2]));
        mapNPC.add(List.of());
        mapNPC.add(List.of());
    }

    public int getCurrentIndex()
    {
        return currentIndex;
    }

    public void nextMap()
    {
        if(currentIndex < maps.size() - 1)
        {
            currentIndex++;
        }

        if(currentIndex == 3)
        {
            gp.gameState = gp.gameFinishedState;
        }
    }

    public List<AbstractTileManager> getCurrentBaseMaps() {
        if (currentIndex == 0) {
            return List.of(maps.get(0)); // world01 base
        } else if (currentIndex == 1) {
            return List.of(maps.get(2)); // world02 base
        } else if (currentIndex == 2) {
            return List.of(maps.get(3)); // world03 base
        } else {
            return List.of(); // optional fallback
        }
    }


    public List<AbstractTileManager> getCurrentOverlayBaseMaps()
    {
        if (currentIndex == 0)
            return List.of(maps.get(1)); // overlay01
        else if (currentIndex == 2)
            return List.of(maps.get(4));
        return List.of();
    }

    public List<AbstractTileManager> getCurrentOverlayMaps()
    {
        if (currentIndex == 0)
            return List.of(maps.get(1)); // world01 overlay
        else return List.of(); // no overlay on others
    }

    /*public boolean gol(List<Entity> m)
    {

        if(m.isEmpty())
            return true;
        else
        {
            for(Entity e : m)
            {
                if(e != null)
                    return false;
            }
            return true;
        }
    }*/

    public List<Entity> MobsMap1()
    {
        List<Entity> list = new ArrayList<>();

        list.add(gp.monster[0]);
        list.add(gp.monster[1]);
        list.add(gp.monster[2]);
        list.add(gp.monster[7]); //boss
        list.add(gp.monster[10]);
        list.add(gp.monster[11]);
        list.add(gp.monster[23]);

        return list;
    }

    public List<Entity> MobsMap2()
    {
        List<Entity> list = new ArrayList<>();

        list.add(gp.monster[3]);
        list.add(gp.monster[5]);
        list.add(gp.monster[8]); //boss
        list.add(gp.monster[14]);
        list.add(gp.monster[15]);
        list.add(gp.monster[16]);
        list.add(gp.monster[17]);
        list.add(gp.monster[18]);


        return list;
    }

    public List<Entity> MobsMap3()
    {
        List<Entity> list = new ArrayList<>();

        list.add(gp.monster[4]);
        list.add(gp.monster[6]);
        list.add(gp.monster[9]); // boss
        list.add(gp.monster[12]);
        list.add(gp.monster[13]);
        list.add(gp.monster[19]);
        list.add(gp.monster[20]);
        list.add(gp.monster[21]);
        list.add(gp.monster[22]);
        list.add(gp.monster[24]);
        list.add(gp.monster[25]);

        return list;
    }

    private List<Entity> NPCAllMaps(int mapIndex)
    {
        List<Entity> list = new ArrayList<>();

        Entity npc = gp.npc[0];

        switch (mapIndex) {
            case 0 -> {
                npc.worldX = gp.tileSize * 50;
                npc.worldY = gp.tileSize * 27;
            }
            case 1 -> {
                npc.worldX = gp.tileSize * 30;
                npc.worldY = gp.tileSize * 22;
            }
            case 2 -> {
                npc.worldX = gp.tileSize * 10;
                npc.worldY = gp.tileSize * 17;
            }
        }

        list.add(npc);
        return list;

    }

    public List<Entity> getCurrentMobs()
    {
        return mapMobs.get(currentIndex);
    }

    public List<Entity> getCurrentNPC()
    {
        return mapNPC.get(currentIndex);
    }

    public boolean isBossDefeated(int mapIndex) {
        Entity boss = switch (mapIndex) {
            case 0 -> gp.monster[7];   // boss map 1
            case 1 -> gp.monster[8];   // boss map 2
            case 2 -> gp.monster[9];   // boss map 3
            default -> null;
        };

        return boss == null;
    }


}

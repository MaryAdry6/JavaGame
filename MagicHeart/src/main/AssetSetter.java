package main;
import entity.*;
import entity.monster.*;

public class AssetSetter
{
    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setObject()
    {
        // pt iteme (mai tarziu)
    }

    public void setNPC()
    {
        gp.npc[0] = new NPC_Fairy(gp);
        gp.npc[0].worldX = gp.tileSize * 50;
        gp.npc[0].worldY = gp.tileSize * 27;

        gp.npc[1] = new NPC_Fairy(gp);
        gp.npc[1].worldX = gp.tileSize * 58;
        gp.npc[1].worldY = gp.tileSize * 17;

        gp.npc[2] = new NPC_Fairy(gp);
        gp.npc[2].worldX = gp.tileSize * 59;
        gp.npc[2].worldY = gp.tileSize * 44;

    }

    public void setMonster()
    {

        gp.monster[0] = MonsterFactory.createMonster("WolfForest", gp);
        gp.monster[0].worldX = gp.tileSize * 44;
        gp.monster[0].worldY = gp.tileSize * 33;

        gp.monster[1] = MonsterFactory.createMonster("WolfForest", gp);
        gp.monster[1].worldX = gp.tileSize * 49;
        gp.monster[1].worldY = gp.tileSize * 37;

        gp.monster[2] = MonsterFactory.createMonster("SpiderForest", gp);
        gp.monster[2].worldX = gp.tileSize * 21;
        gp.monster[2].worldY = gp.tileSize * 32;

        gp.monster[3] = MonsterFactory.createMonster("SpiderMountain", gp);
        gp.monster[3].worldX = gp.tileSize * 26;
        gp.monster[3].worldY = gp.tileSize * 38;

        gp.monster[4] = MonsterFactory.createMonster("SpiderLava", gp);
        gp.monster[4].worldX = gp.tileSize * 30;
        gp.monster[4].worldY = gp.tileSize * 15;

        gp.monster[5] = MonsterFactory.createMonster("WolfMountain", gp);
        gp.monster[5].worldX = gp.tileSize * 39;
        gp.monster[5].worldY = gp.tileSize * 33;

        gp.monster[6] = MonsterFactory.createMonster("WolfLava", gp);
        gp.monster[6].worldX = gp.tileSize * 38;
        gp.monster[6].worldY = gp.tileSize * 12;

        gp.monster[7] = MonsterFactory.createMonster("Gorilla", gp);
        gp.monster[7].worldX = gp.tileSize * 28;
        gp.monster[7].worldY = gp.tileSize * 3;

        gp.monster[8] = MonsterFactory.createMonster("Golem", gp);
        gp.monster[8].worldX = gp.tileSize * 28;
        gp.monster[8].worldY = gp.tileSize * 8;

        gp.monster[9] = MonsterFactory.createMonster("Azula", gp);
        gp.monster[9].worldX = gp.tileSize * 14;
        gp.monster[9].worldY = gp.tileSize * 45;

        gp.monster[10] = MonsterFactory.createMonster("SpiderForest", gp);
        gp.monster[10].worldX = gp.tileSize * 15;
        gp.monster[10].worldY = gp.tileSize * 20;

        gp.monster[11] = MonsterFactory.createMonster("SpiderForest", gp);
        gp.monster[11].worldX = gp.tileSize * 10;
        gp.monster[11].worldY = gp.tileSize * 28;

        gp.monster[12] = MonsterFactory.createMonster("SpiderMountain", gp);
        gp.monster[12].worldX = gp.tileSize * 45;
        gp.monster[12].worldY = gp.tileSize * 7;

        gp.monster[13] = MonsterFactory.createMonster("SpiderMountain", gp);
        gp.monster[13].worldX = gp.tileSize * 37;
        gp.monster[13].worldY = gp.tileSize * 19;

        gp.monster[14] = MonsterFactory.createMonster("SpiderMountain", gp);
        gp.monster[14].worldX = gp.tileSize * 24;
        gp.monster[14].worldY = gp.tileSize * 18;

        gp.monster[15] = MonsterFactory.createMonster("WolfMountain", gp);
        gp.monster[15].worldX = gp.tileSize * 23;
        gp.monster[15].worldY = gp.tileSize * 40;

        gp.monster[16] = MonsterFactory.createMonster("WolfMountain", gp);
        gp.monster[16].worldX = gp.tileSize * 41;
        gp.monster[16].worldY = gp.tileSize * 20;

        gp.monster[17] = MonsterFactory.createMonster("WolfForest", gp);
        gp.monster[17].worldX = gp.tileSize * 30;
        gp.monster[17].worldY = gp.tileSize * 23;

        gp.monster[18] = MonsterFactory.createMonster("SpiderForest", gp);
        gp.monster[18].worldX = gp.tileSize * 49;
        gp.monster[18].worldY = gp.tileSize * 37;

        gp.monster[19] = MonsterFactory.createMonster("WolfLava", gp);
        gp.monster[19].worldX = gp.tileSize * 49;
        gp.monster[19].worldY = gp.tileSize * 26;

        gp.monster[20] = MonsterFactory.createMonster("SpiderLava", gp);
        gp.monster[20].worldX = gp.tileSize * 47;
        gp.monster[20].worldY = gp.tileSize * 21;

        gp.monster[21] = MonsterFactory.createMonster("SpiderForest", gp);
        gp.monster[21].worldX = gp.tileSize * 49;
        gp.monster[21].worldY = gp.tileSize * 45;

        gp.monster[22] = MonsterFactory.createMonster("WolfForest", gp);
        gp.monster[22].worldX = gp.tileSize * 41;
        gp.monster[22].worldY = gp.tileSize * 46;

        gp.monster[23] = MonsterFactory.createMonster("WolfForest", gp);
        gp.monster[23].worldX = gp.tileSize * 30;
        gp.monster[23].worldY = gp.tileSize * 22;

        gp.monster[24] = MonsterFactory.createMonster("SpiderLava", gp);
        gp.monster[24].worldX = gp.tileSize * 20;
        gp.monster[24].worldY = gp.tileSize * 15;

        gp.monster[25] = MonsterFactory.createMonster("WolfMountain", gp);
        gp.monster[25].worldX = gp.tileSize * 38;
        gp.monster[25].worldY = gp.tileSize * 43;



    }

}

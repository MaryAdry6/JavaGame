package main;

import Tile.*;
import entity.Entity;
import entity.Player;

import java.util.List;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public void checkTileAjutator(Entity entity, TileManager tileManager)
    {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction)
        {
            case "up":

                entityTopRow = (entityTopWorldY - entity.speed)/ gp.tileSize;

                if(entityTopRow > 0 && entityTopRow < tileManager.mapTileNum[0].length)
                {
                    if (entityLeftCol > 0 && entityLeftCol < tileManager.mapTileNum.length)
                    {
                        tileNum1 = tileManager.mapTileNum[entityLeftCol][entityTopRow];
                    }
                    else tileNum1 = 0;

                    if (entityRightCol >= 0 && entityRightCol < tileManager.mapTileNum.length)
                    {
                        tileNum2 = tileManager.mapTileNum[entityRightCol][entityTopRow];
                    }
                    else tileNum2 = 0;


                    if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true)
                    {
                        entity.collisionOn = true;
                    }

                }
                else
                {
                    entity.collisionOn = true;
                }
                break;

            case "down":

                entityBottomRow = (entityBottomWorldY + entity.speed)/ gp.tileSize;

                if(entityBottomRow >= 0 && entityBottomRow < tileManager.mapTileNum[0].length)
                {
                    if (entityLeftCol > 0 && entityLeftCol < tileManager.mapTileNum.length)
                    {
                        tileNum1 = tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                    }
                    else tileNum1 = 0;

                    if (entityRightCol >= 0 && entityRightCol < tileManager.mapTileNum.length)
                    {
                        tileNum2 = tileManager.mapTileNum[entityRightCol][entityBottomRow];
                    }
                    else tileNum2 = 0;


                    if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true)
                    {
                        entity.collisionOn = true;
                    }

                }
                else
                {
                    entity.collisionOn = true;
                }
                break;


            case "left":

                entityLeftCol = (entityLeftWorldX - entity.speed)/ gp.tileSize;

                if(entityLeftCol > 0 && entityLeftCol < tileManager.mapTileNum.length)
                {
                    if (entityTopRow >= 0 && entityTopRow < tileManager.mapTileNum[0].length)
                    {
                        tileNum1 = tileManager.mapTileNum[entityLeftCol][entityTopRow];
                    }
                    else tileNum1 = 0;

                    if (entityBottomRow >= 0 && entityBottomRow < tileManager.mapTileNum[0].length)
                    {
                        tileNum2 = tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                    }
                    else tileNum2 = 0;


                    if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true)
                    {
                        entity.collisionOn = true;
                    }

                }
                else
                {
                    entity.collisionOn = true;
                }
                break;


            case "right":

                entityRightCol = (entityRightWorldX + entity.speed)/ gp.tileSize;

                if(entityRightCol >= 0 && entityRightCol < tileManager.mapTileNum.length)
                {
                    if (entityTopRow >= 0 && entityTopRow < tileManager.mapTileNum[0].length)
                    {
                        tileNum1 = tileManager.mapTileNum[entityRightCol][entityTopRow];
                    }
                    else tileNum1 = 0;

                    if (entityBottomRow >= 0 && entityBottomRow < tileManager.mapTileNum[0].length)
                    {
                        tileNum2 = tileManager.mapTileNum[entityRightCol][entityBottomRow];
                    }
                    else tileNum2 = 0;


                    if (tileManager.tile[tileNum1].collision == true || tileManager.tile[tileNum2].collision == true)
                    {
                        entity.collisionOn = true;
                    }

                }
                else
                {
                    entity.collisionOn = true;
                }
                break;

        }
    }

    public void checkTile(Entity entity, List<AbstractTileManager> tileManagers) {
        for (AbstractTileManager manager : tileManagers) {
            if (manager instanceof TileManager) {
                checkTileAjutator(entity, (TileManager) manager);
            }
        }
    }





    //NPC OR MONSTER COLLISION
    public int checkEntity(Entity entity, List<Entity> targetList)
    {
        int index = 999;

        for(int i = 0; i < targetList.size(); i++) {
            Entity target = targetList.get(i);

            if (target != null) {
                //get entity solid area
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get the npc solid area
                target.solidArea.x = target.worldX + target.solidArea.x;
                target.solidArea.y = target.worldY + target.solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;

                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;

                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;

                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;

                }

                if (entity.solidArea.intersects(target.solidArea)) {

                    if(target != entity)
                    {
                        entity.collisionOn = true;
                        index = i;
                    }

                }


                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target.solidArea.x = target.solidAreaDefaultX;
                target.solidArea.y = target.solidAreaDefaultY;
            }


        }
        return index;
    }

    public boolean checkPlayer(Entity entity)
    {
        boolean contactPlayer = false;

        //entity solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        //object solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch(entity.direction)
        {
            case"up":
                entity.solidArea.y -= entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    entity.collisionOn = true;
                    contactPlayer = true;
                }
                break;
            case"down":
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    entity.collisionOn = true;
                    contactPlayer = true;
                }
                break;
            case"left":
                entity.solidArea.y -= entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    entity.collisionOn = true;
                    contactPlayer = true;
                }
                break;
            case"right":
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects(gp.player.solidArea))
                {
                    entity.collisionOn = true;
                    contactPlayer = true;
                }
                break;

        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        return contactPlayer;
    }


}

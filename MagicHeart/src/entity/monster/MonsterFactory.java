package entity.monster;

import entity.Entity;
import main.GamePanel;

public class MonsterFactory {
    public static Entity createMonster(String type, GamePanel gp) {
        return switch (type) {
            case "WolfForest" -> new MON_WolfForest(gp);
            case "WolfMountain" -> new MON_WolfMountain(gp);
            case "WolfLava" -> new MON_WolfLava(gp);

            case "SpiderForest" -> new MON_SpiderForest(gp);
            case "SpiderMountain" -> new MON_SpiderMountain(gp);
            case "SpiderLava" -> new MON_SpiderLava(gp);

            case "Gorilla" -> new MON_Gorilla(gp);
            case "Golem" -> new MON_Golem(gp);
            case "Azula" -> new MON_Azula(gp);

            default -> null;
        };
    }
}

package main;

import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 20 * gp.tileSize;
        gp.obj[0].worldY = 3 * gp.tileSize;
    }
}

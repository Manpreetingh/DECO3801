package main;

import entity.NPC_OldMan;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[39] = new OBJ_Sign(gp);
        gp.obj[39].worldX = 20 * gp.tileSize;
        gp.obj[39].worldY = 3 * gp.tileSize;

        gp.obj[40] = new OBJ_Computer(gp);
        gp.obj[40].worldX = 25 * gp.tileSize;
        gp.obj[40].worldY = 2 * gp.tileSize;

        gp.obj[41] = new OBJ_Computer(gp);
        gp.obj[41].worldX = 31 * gp.tileSize;
        gp.obj[41].worldY = 2 * gp.tileSize;

        gp.obj[42] = new OBJ_Computer(gp);
        gp.obj[42].worldX = 37 * gp.tileSize;
        gp.obj[42].worldY = 2 * gp.tileSize;

        gp.obj[43] = new OBJ_Computer(gp);
        gp.obj[43].worldX = 42 * gp.tileSize;
        gp.obj[43].worldY = 2 * gp.tileSize;

        gp.obj[44] = new OBJ_Computer(gp);
        gp.obj[44].worldX = 47 * gp.tileSize;
        gp.obj[44].worldY = 2 * gp.tileSize;

        gp.obj[45] = new OBJ_Kitchen(gp);
        gp.obj[45].worldX = 2 * gp.tileSize;
        gp.obj[45].worldY = 2 * gp.tileSize;

        gp.obj[46] = new OBJ_Sofa(gp);
        gp.obj[46].worldX = 13 * gp.tileSize;
        gp.obj[46].worldY = 3 * gp.tileSize;

        gp.obj[47] = new OBJ_Pool(gp);
        gp.obj[47].worldX = 3 * gp.tileSize;
        gp.obj[47].worldY = 5 * gp.tileSize;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 12 * gp.tileSize;
        gp.obj[1].worldY = 7 * gp.tileSize;

        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 13 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 23 * gp.tileSize;
        gp.obj[3].worldY = 23 * gp.tileSize;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 24 * gp.tileSize;
        gp.obj[4].worldY = 23 * gp.tileSize;

        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldX = 13 * gp.tileSize;
        gp.obj[5].worldY = 18 * gp.tileSize;

        gp.obj[6] = new OBJ_Door();
        gp.obj[6].worldX = 13 * gp.tileSize;
        gp.obj[6].worldY = 17 * gp.tileSize;

        gp.obj[7] = new OBJ_Door();
        gp.obj[7].worldX = 33 * gp.tileSize;
        gp.obj[7].worldY = 18 * gp.tileSize;

        gp.obj[8] = new OBJ_Door();
        gp.obj[8].worldX = 33 * gp.tileSize;
        gp.obj[8].worldY = 17 * gp.tileSize;

        gp.obj[9] = new OBJ_Door();
        gp.obj[9].worldX = 22 * gp.tileSize;
        gp.obj[9].worldY = 12 * gp.tileSize;

        gp.obj[10] = new OBJ_Door();
        gp.obj[10].worldX = 23 * gp.tileSize;
        gp.obj[10].worldY = 12 * gp.tileSize;

        gp.obj[11] = new OBJ_Door();
        gp.obj[11].worldX = 25 * gp.tileSize;
        gp.obj[11].worldY = 5 * gp.tileSize;

        gp.obj[12] = new OBJ_Door();
        gp.obj[12].worldX = 26 * gp.tileSize;
        gp.obj[12].worldY = 5 * gp.tileSize;


        gp.obj[13] = new OBJ_Door();
        gp.obj[13].worldX = 38 * gp.tileSize;
        gp.obj[13].worldY = 5 * gp.tileSize;


        gp.obj[14] = new OBJ_Door();
        gp.obj[14].worldX = 37 * gp.tileSize;
        gp.obj[14].worldY = 5 * gp.tileSize;


        gp.obj[15] = new OBJ_Door();
        gp.obj[15].worldX = 47 * gp.tileSize;
        gp.obj[15].worldY = 5 * gp.tileSize;

        gp.obj[16] = new OBJ_Door();
        gp.obj[16].worldX = 48 * gp.tileSize;
        gp.obj[16].worldY = 5 * gp.tileSize;

        gp.obj[17] = new OBJ_Door();
        gp.obj[17].worldX = 42 * gp.tileSize;
        gp.obj[17].worldY = 5 * gp.tileSize;

        gp.obj[18] = new OBJ_Door();
        gp.obj[18].worldX = 43 * gp.tileSize;
        gp.obj[18].worldY = 5 * gp.tileSize;

        gp.obj[19] = new OBJ_Door();
        gp.obj[19].worldX = 39 * gp.tileSize;
        gp.obj[19].worldY = 11 * gp.tileSize;

        gp.obj[20] = new OBJ_Door();
        gp.obj[20].worldX = 39 * gp.tileSize;
        gp.obj[20].worldY = 12 * gp.tileSize;

        gp.obj[21] = new OBJ_Door();
        gp.obj[21].worldX = 39 * gp.tileSize;
        gp.obj[21].worldY = 19 * gp.tileSize;

        gp.obj[22] = new OBJ_Door();
        gp.obj[22].worldX = 39 * gp.tileSize;
        gp.obj[22].worldY = 20 * gp.tileSize;

        gp.obj[23] = new OBJ_Door();
        gp.obj[23].worldX = 10 * gp.tileSize;
        gp.obj[23].worldY = 34 * gp.tileSize;

        gp.obj[24] = new OBJ_Door();
        gp.obj[24].worldX = 10 * gp.tileSize;
        gp.obj[24].worldY = 35 * gp.tileSize;

        gp.obj[25] = new OBJ_Door();
        gp.obj[25].worldX = 45 * gp.tileSize;
        gp.obj[25].worldY = 33 * gp.tileSize;

        gp.obj[26] = new OBJ_Door();
        gp.obj[26].worldX = 45 * gp.tileSize;
        gp.obj[26].worldY = 34 * gp.tileSize;

        gp.obj[27] = new OBJ_Door();
        gp.obj[27].worldX = 9 * gp.tileSize;
        gp.obj[27].worldY = 44 * gp.tileSize;

        gp.obj[28] = new OBJ_Door();
        gp.obj[28].worldX = 8 * gp.tileSize;
        gp.obj[28].worldY = 44 * gp.tileSize;

        gp.obj[29] = new OBJ_Door();
        gp.obj[29].worldX = 11 * gp.tileSize;
        gp.obj[29].worldY = 44 * gp.tileSize;

        gp.obj[30] = new OBJ_Door();
        gp.obj[30].worldX = 12 * gp.tileSize;
        gp.obj[30].worldY = 44 * gp.tileSize;

        gp.obj[31] = new OBJ_Door();
        gp.obj[31].worldX = 25 * gp.tileSize;
        gp.obj[31].worldY = 44 * gp.tileSize;

        gp.obj[32] = new OBJ_Door();
        gp.obj[32].worldX = 24 * gp.tileSize;
        gp.obj[32].worldY = 44 * gp.tileSize;

        gp.obj[33] = new OBJ_Door();
        gp.obj[33].worldX = 38 * gp.tileSize;
        gp.obj[33].worldY = 44 * gp.tileSize;

        gp.obj[34] = new OBJ_Door();
        gp.obj[34].worldX = 39 * gp.tileSize;
        gp.obj[34].worldY = 44 * gp.tileSize;

        gp.obj[35] = new OBJ_Door();
        gp.obj[35].worldX = 41 * gp.tileSize;
        gp.obj[35].worldY = 44 * gp.tileSize;

        gp.obj[36] = new OBJ_Door();
        gp.obj[36].worldX = 42 * gp.tileSize;
        gp.obj[36].worldY = 44 * gp.tileSize;

        gp.obj[37] = new OBJ_Door();
        gp.obj[37].worldX = 31 * gp.tileSize;
        gp.obj[37].worldY = 5 * gp.tileSize;

        gp.obj[38] = new OBJ_Door();
        gp.obj[38].worldX = 32 * gp.tileSize;
        gp.obj[38].worldY = 5 * gp.tileSize;
    }

    public void setNPC() {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldx = 19 * gp.tileSize;
        gp.npc[0].worldy= 3 * gp.tileSize;
    }
}

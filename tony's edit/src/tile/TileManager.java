package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[30];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/map.txt");

    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/floor01.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
            tile[1].collision = true;
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/earth.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/pool_topleft.png"));
            tile[3].collision = true;
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/pool_botleft.png"));
            tile[4].collision = true;
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/pool_topmid.png"));
            tile[5].collision = true;
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/pool_botmid.png"));
            tile[6].collision = true;
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/pool_topright.png"));
            tile[7].collision = true;
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/pool_botright.png"));
            tile[8].collision = true;
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/rec_topleft.png"));
            tile[9].collision = true;
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/rec_topmid.png"));
            tile[10].collision = true;
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/rec_topright.png"));
            tile[11].collision = true;
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/rec_midleft.png"));
            tile[12].collision = true;
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/rec_midmid.png"));
            tile[13].collision = true;
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/rec_midright.png"));
            tile[14].collision = true;
            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/rec_botleft.png"));
            tile[15].collision = true;
            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/rec_botmid.png"));
            tile[16].collision = true;
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/rec_botright.png"));
            tile[17].collision = true;
            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/flower1.png"));
            tile[18].collision = true;
            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/flower2.png"));
            tile[19].collision = true;
            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/floor01.png"));
            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/light1.png"));
            tile[21].collision = true;
            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/light2.png"));
            tile[22].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filepath) {
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int worldcol = 0;
            int worldrow = 0;
            while (worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow) {
                String line = br.readLine();
                while (worldcol < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[worldcol]);
                    mapTileNum[worldcol][worldrow] = num;
                    worldcol++;
                }
                if (worldcol == gp.maxWorldCol) {
                    worldcol = 0;
                    worldrow++;

                }
            }
            br.close();


        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void draw(Graphics2D g2) {
        int worldcol = 0;
        int worldrow = 0;


        while (worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldcol][worldrow];
            int worldX = worldcol * gp.tileSize;
            int worldY = worldrow * gp.tileSize;
            int screenX = worldX - gp.player.worldx + gp.player.screenX;
            int screenY = worldY - gp.player.worldy + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldx - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldx + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldy - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldy + gp.player.screenY ) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }

            worldcol++;

            if (worldcol == gp.maxWorldCol) {
                worldcol = 0;

                worldrow++;

            }

        }


    }
}


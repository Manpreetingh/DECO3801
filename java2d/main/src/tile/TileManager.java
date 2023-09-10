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
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
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
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/earth.png"));


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


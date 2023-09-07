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

    public TileManager(GamePanel gp)
    {
        this.gp =gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();

    }
    public void getTileImage(){
        try {
            tile[0] = new Tile();
            Tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/wall.png")));
            tile[1] = new Tile();
            Tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/floor01.png")));
            tile[2] = new Tile();
            Tile.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/earth.png")));


        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/Map.txt.");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while(col  < gp.maxScreenCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;

                }
            }
            br.close();


        }catch(Exception e){

        }

    }
    public void draw (Graphics2D g2){
      int col = 0;
      int row = 0;
      int x = 0;
      int y = 0;

      while(col < gp.maxScreenCol && row< gp.maxScreenRow){
          int tileNum = mapTileNum[col][row];
          gp.drawImage(tile[tileNum].image, x, gp.tileSize, null);
          col++;
          x += gp.tileSize;

          if(col == gp.maxScreenCol) {
              col = 0;
              x = 0;
              row++;
              y  += gp.tileSize;
          }

      }













    }
}

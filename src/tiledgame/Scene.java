package tiledgame;

import com.hadesvine.tiledgame.examples.MapHelper;
import org.newdawn.slick.SpriteSheet;

/**
 * @author HadesVine
 */
public class Scene {

    private SpriteSheet tileSet;
    private int[][] map;
    private boolean generating;

    public boolean isGenerating() {
        return generating;
    }

    public void setGenerating(boolean generating) {
        this.generating = generating;
    }
    
    

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }
    
    
    public Scene(SpriteSheet tileSet) {
        this.tileSet = tileSet;
        map = MapHelper.getClarified2DIslandMap(32,24);
    }
    
    public void draw() {
        for (int i = 0; i < map.length; i++) {
            int row[] = map[i];
            for (int j = 0; j < row.length; j++) {
                if(row[j]==1){
                    tileSet.getSprite(7, 0).draw(i * tileSet.getWidth()/tileSet.getHorizontalCount(),j*tileSet.getHeight()/tileSet.getVerticalCount());
                }else{
                    tileSet.getSprite(8, 0).draw(i * tileSet.getWidth()/tileSet.getHorizontalCount(),j*tileSet.getHeight()/tileSet.getVerticalCount());
                }
            }
        }
    }
}

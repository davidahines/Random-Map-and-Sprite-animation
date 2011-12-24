package tiledgame;

import com.hadesvine.tiledgame.examples.MapHelper;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * @author HadesVine
 */
public class TiledGame extends BasicGame {

    private GameContainer gc;
    private float moveSpeed = 1;
    private CharacterEntity testEntity;
    private boolean playerIsMoving = false;
    private Scene scene;
    private int WIDTH = 1024;
    private int HEIGHT = 768;
    

    public TiledGame() {
        super("Tiled game");
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new TiledGame());
        app.setDisplayMode(1024, 768, false);
        app.setTargetFrameRate(60);
        app.setFullscreen(true);
        app.start();
    }

    @Override 
    public void init(GameContainer gc) throws SlickException {
        testEntity = new CharacterEntity(new SpriteSheet("com/hadesvine/tilegame/tiles/crono.png", 24, 32,new Color(41,156,0) ), 400, 300);
        testEntity.setWalkColumn(4);
        testEntity.setWalkDelay(200);
        scene = new Scene(new SpriteSheet("com/hadesvine/tilegame/tiles/masterEnviromentSheet.png", 32, 32,new Color(255,0,176)));
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        playerIsMoving = false;
        Input input = gc.getInput();
        float rate = delta / 10f;
        if (input.isKeyDown(Input.KEY_W)) {
            testEntity.setFacing(CharacterEntity.UP);
            testEntity.setLocationY(testEntity.getLocationY() - moveSpeed * rate);
            playerIsMoving = true;
        }
        if (input.isKeyDown(Input.KEY_D)) {
            testEntity.setFacing(CharacterEntity.RIGHT);
            testEntity.setLocationX(testEntity.getLocationX() + moveSpeed * rate);
            playerIsMoving = true;
        }
        if (input.isKeyDown(Input.KEY_S)) {
            testEntity.setFacing(CharacterEntity.DOWN);
            testEntity.setLocationY(testEntity.getLocationY() + moveSpeed * rate);
            playerIsMoving = true;
        }
        if (input.isKeyDown(Input.KEY_A)) {
            testEntity.setFacing(CharacterEntity.LEFT);
            testEntity.setLocationX(testEntity.getLocationX() - moveSpeed * rate);
            playerIsMoving = true;
        }
        if (input.isKeyDown(Input.KEY_2)) {
            if (moveSpeed != 500) {
                moveSpeed += 1;
            }
        }
        if (input.isKeyDown(Input.KEY_1)) {
            if (moveSpeed > 1) {
                moveSpeed -= 1;
            }
        }
        if (input.isKeyDown(Input.KEY_3)) {
            if (moveSpeed > 1) {
                moveSpeed = 1;
            }
        }
        if (input.isKeyDown(Input.KEY_BACK)) {
            if(!scene.isGenerating()){
                this.scene.setGenerating(true);
                this.scene.setMap(MapHelper.getClarified2DIslandMap(WIDTH, HEIGHT));
                this.scene.setGenerating(false);
            }
        }
        if (playerIsMoving) {
            testEntity.setState(CharacterEntity.WALK);
        } else {
            testEntity.setState(CharacterEntity.IDLE);
        }
        testEntity.tick(delta);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        scene.draw();
        Image sprite = testEntity.getSprite();
        sprite.draw(testEntity.getLocationX(), testEntity.getLocationY());
    }
}

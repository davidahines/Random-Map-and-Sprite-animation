package tiledgame;

import java.awt.Point;
import java.util.Map;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 * @author HadesVine
 */
public class CharacterEntity {
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int IDLE = 0;
    public static final int WALK = 1;
    
    private SpriteSheet spriteSheet;
    private int facing = 0;//The direction a sprite is facing;
    private int state = 0;
    private float locationX;
    private float locationY;
    private int walkDelay = 300;
    private int currentFrame = 0;
    private int timePassed = 0;
    private int walkColumn = 0;

    public CharacterEntity(SpriteSheet spriteSheet, float locationX, float locationY) {
        this.spriteSheet = spriteSheet;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public int getFacing() {
        return facing;
    }

    public void setFacing(int facing) {
        this.facing = facing;
    }

    public float getLocationX() {
        return locationX;
    }

    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    public float getLocationY() {
        return locationY;
    }

    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public int getWalkDelay() {
        return walkDelay;
    }

    public void setWalkDelay(int walkDelay) {
        this.walkDelay = walkDelay;
    }
    

    public int getWalkColumn() {
        return walkColumn;
    }

    public void setWalkColumn(int walkColumn) {
        this.walkColumn = walkColumn;
    }

    public void tick(int delta) {
        if (this.state == WALK) {
            if (timePassed >= walkDelay) {
                timePassed = 0;
                if (this.currentFrame == 1) {
                    this.currentFrame = -1;
                } else {
                    this.currentFrame = 1;
                }
            } else {
                timePassed += delta;
            }
        }else{
            currentFrame = 0;
        }
    }
    public Image getSprite() {
        return this.spriteSheet.getSprite(this.currentFrame + this.walkColumn, this.facing);
    }
}

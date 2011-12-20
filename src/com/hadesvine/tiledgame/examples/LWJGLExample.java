package com.hadesvine.tiledgame.examples;

import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class LWJGLExample {

    public static final int SCREENWIDTH = 1024;
    public static final int SCREENHEIGHT = 768;
    public static int numberOfTimesRun = 0;
    /** The texture that will hold the image details */
    private Texture grassTexture;
    private Texture treeTexture;
    private SpriteSheet testSheet;
    /**
     * Start the example
     */
    public void start() {

        initGL(SCREENWIDTH, SCREENHEIGHT);
        init();

        while (true) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            drawTiled(SCREENWIDTH, SCREENHEIGHT);
            drawTree();
            drawSprite();
            //render();



            Display.update();
            Display.sync(100);

            if (Display.isCloseRequested()) {
                Display.destroy();
                System.exit(0);
            }
        }
    }

    /**
     * Initialise the GL display
     * 
     * @param width The width of the display
     * @param height The height of the display
     */
    private void initGL(int width, int height) {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            
            Display.create();
            Display.setVSyncEnabled(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        // enable alpha blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glViewport(0, 0, width, height);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, height, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    /**
     * Initialise resources
     */
    public void init() {

        try {
            // load texture from PNG file
            grassTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("com/hadesvine/tilegame/tiles/seamlessgrass.png"));
            treeTexture =  TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("com/hadesvine/tilegame/tiles/tree64x96.png"));
            try {
                this.testSheet = new SpriteSheet("com/hadesvine/tilegame/tiles/crono.png",24,32);
            } catch (Exception e) {
                System.out.println("init(): "+e);
            }
            
                    
            
            System.out.println("Texture loaded: " + grassTexture);
            System.out.println(">> Image width: " + grassTexture.getImageWidth());
            System.out.println(">> Image height: " + grassTexture.getImageHeight());
            System.out.println(">> Texture width: " + grassTexture.getTextureWidth());
            System.out.println(">> Texture height: " + grassTexture.getTextureHeight());
            System.out.println(">> Texture ID: " + grassTexture.getTextureID());
            
            System.out.println("Texture loaded: " + treeTexture);
            System.out.println(">> Image width: " + treeTexture.getImageWidth());
            System.out.println(">> Image height: " + treeTexture.getImageHeight());
            System.out.println(">> Texture width: " + treeTexture.getTextureWidth());
            System.out.println(">> Texture height: " + treeTexture.getTextureHeight());
            System.out.println(">> Texture ID: " + treeTexture.getTextureID());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * draw a quad with the image on it
     */
    public void render() {
        Color.white.bind();
        grassTexture.bind(); // or GL11.glBind(texture.getTextureID());

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(100, 100);
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(100 + grassTexture.getTextureWidth(), 100);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(100 + grassTexture.getTextureWidth(), 100 + grassTexture.getTextureHeight());
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(100, 100 + grassTexture.getTextureHeight());
        GL11.glEnd();
    }

    /**
     * Main Class
     */
    public static void main(String[] argv) {
        LWJGLExample textureExample = new LWJGLExample();
        textureExample.start();
    }

    public void drawTiled(int screenWidth, int screenHeight) {
        Color.white.bind();
        grassTexture.bind(); // or GL11.glBind(texture.getTextureID());
        int numberPerRow = (int) screenWidth / (int) grassTexture.getTextureWidth();
        double numberOfRows = screenHeight / grassTexture.getTextureHeight();
        GL11.glBegin(GL11.GL_QUADS);
        for (int j = 0; j < numberOfRows; j++) {
            //System.out.print("{");
            for (int i = 0; i < numberPerRow; i++) {

                //top left
                GL11.glTexCoord2f(0, 0);
                GL11.glVertex2f(grassTexture.getTextureWidth() * i, grassTexture.getTextureHeight() * j);

                //top right
                GL11.glTexCoord2f(1, 0);
                GL11.glVertex2f(grassTexture.getTextureWidth() * (i + 1), grassTexture.getTextureHeight() * j);

                //bottom right
                GL11.glTexCoord2f(1, 1);
                GL11.glVertex2f(grassTexture.getTextureWidth() * (i + 1), grassTexture.getTextureHeight() * (j + 1));

                //bottom left
                GL11.glTexCoord2f(0, 1);
                GL11.glVertex2f(grassTexture.getTextureWidth() * i, grassTexture.getTextureHeight() * (j + 1));
                //System.out.print("[]");
            }
            //System.out.println("}");
        }
        //close gl


        GL11.glEnd();
        System.out.println("numberOfTimesRun" + ++numberOfTimesRun);
    }

    public void drawTree() {
        Color.white.bind();
        treeTexture.bind();
        GL11.glBegin(GL11.GL_QUADS);
        

        
        
        int i = 0;
        int j = 0;
        
        
        
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(treeTexture.getTextureWidth() * i, treeTexture.getTextureHeight() * j);

        //top right
        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(treeTexture.getTextureWidth() * (i + 1), treeTexture.getTextureHeight() * j);

        //bottom right
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(treeTexture.getTextureWidth() * (i + 1), treeTexture.getTextureHeight() * (j + 1));

        //bottom left
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(treeTexture.getTextureWidth() * i, treeTexture.getTextureHeight() * (j + 1));
        GL11.glEnd();
    }
    public void drawSprite() {
        Color.white.bind();
        Image image = testSheet.getSprite(0, 0);
        
    }
}
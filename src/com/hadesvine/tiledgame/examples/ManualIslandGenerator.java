package com.hadesvine.tiledgame.examples;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.SlickException;
import java.util.Random;

public class ManualIslandGenerator extends BasicGame {

    public static final int WIDTH = 32;
    public static final int HEIGHT = 25;
    public static final int CLARIFICATION = 4;
    private Polygon[][] rand = new Polygon[HEIGHT][WIDTH];
    private int[][][] grid;
    private int tempax = 0, tempay = 0;
    private Random r = new Random();
    private int x = 20;
    private int y = 20;
    private int tempx;
    private int tempy;
    private int timer;
    private int checksum;
    private int chance;
    private int countd = 0;

    public ManualIslandGenerator() {
        super("polygontest");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        map();
    }

    private void map() {
        grid = new int[5][HEIGHT][WIDTH];
        for (int z = 0; z < HEIGHT; z++) {
            for (int p = 0; p < WIDTH; p++) {
                rand[z][p] = new Polygon(new float[]{
                            0, 0,
                            0, y,
                            x, y,
                            x, 0
                        });

                chance = r.nextInt(2);
                grid[0][z][p] = chance;
                if (chance == 1) {
                    rand[z][p].setLocation(z * x, p * y);
                } else {
                    rand[z][p].setLocation(900, 900);
                }
                tempx++;
            }
            tempy++;
            tempx = 0;
        }

        for (int i = 0; i < CLARIFICATION; i++) {
            for (int mattb = 0; mattb < HEIGHT; mattb++) {
                for (int mattx = 0; mattx < WIDTH; mattx++) {
                    if (mattb - 1 >= 0 && mattb + 1 < HEIGHT && mattx - 1 >= 0 && mattx + 1 < WIDTH) {
                        checksum = grid[i][mattb - 1][mattx - 1] + grid[i][mattb - 1][mattx] + grid[i][mattb - 1][mattx + 1]
                                + grid[i][mattb][mattx - 1] + grid[i][mattb][mattx + 1] + grid[i][mattb + 1][mattx - 1]
                                + grid[i][mattb + 1][mattx] + grid[i][mattb + 1][mattx + 1];

                        if (checksum >= CLARIFICATION && grid[i][mattb][mattx] == 1) {
                            grid[i + 1][mattb][mattx] = 1;
                        }

                        if (checksum >= 5) {
                            grid[i + 1][mattb][mattx] = 1;
                        }
                    }
                }
            }
        }
        for (int ax = 0; ax < HEIGHT; ax++) {
            for (int ay = 0; ay < WIDTH; ay++) {
                if (grid[CLARIFICATION][ax][ay] == 1) {
                    rand[ax][ay].setLocation(ax * x, ay * y);
                    tempax++;
                }
            }
            tempay++;
            tempax = 0;
        }
    }

    public void update(GameContainer container, int delta) {
        timer--;
        if (container.getInput().isKeyDown(Input.KEY_SPACE) && timer < 0) {
            timer = 30;
            countd++;
            if (countd > CLARIFICATION) {
                countd = 0;
            }
            for (int ax = 0; ax < HEIGHT; ax++) {
                for (int ay = 0; ay < WIDTH; ay++) {
                    if (grid[countd][ax][ay] == 1) {
                        rand[ax][ay].setLocation(ax * x, ay * y);
                        tempax++;
                    }
                }
                tempay++;
                tempax = 0;
            }

        }
        if (container.getInput().isKeyPressed(Input.KEY_N)) {
            map();
        }
    }

    public void render(GameContainer container, Graphics g) {
        draw(g);
        System.out.println("============================");
    }

    private void draw(Graphics g) {
        for (int d = 0; d < HEIGHT; d++) {
            for (int o = 0; o < WIDTH; o++) {
                if (grid[countd][d][o] == 1) {
                    g.drawString("#", d * x, o * y);
                    System.out.print("#");
                } else {
                    g.drawString(" ", d * x, o * y);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] argv) throws SlickException {
        AppGameContainer container = new AppGameContainer(new ManualIslandGenerator(), 500, 500, false);
        container.setShowFPS(true);
        container.setTargetFrameRate(60);
        container.start();
        container.setVSync(true);
    }
}
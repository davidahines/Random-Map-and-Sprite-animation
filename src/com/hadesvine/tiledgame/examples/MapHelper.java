package com.hadesvine.tiledgame.examples;

import org.newdawn.slick.geom.Polygon;
import java.util.Random;

public class MapHelper {

    public static final int DEFAULTWIDTH = 32;
    public static final int DEFAULTHEIGHT = 25;
    public static final int CLARIFICATION = 4;

    public MapHelper() {
    }

    public static int[][][] getNewMap(int width, int height, int clarification) {
        Polygon[][] rand = new Polygon[width][height];
        int[][][] grid;
        int tempax = 0, tempay = 0;
        Random r = new Random();
        int x = 20;
        int y = 20;
        int tempx = 0;
        int tempy = 0;
        int checksum = 0;
        int chance = 0;
        grid = new int[5][width][height];
        for (int z = 0; z < width; z++) {
            for (int p = 0; p < height; p++) {
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
        for (int i = 0; i < clarification; i++) {
            for (int mattb = 0; mattb < width; mattb++) {
                for (int mattx = 0; mattx < height; mattx++) {
                    if (mattb - 1 >= 0 && mattb + 1 < width && mattx - 1 >= 0 && mattx + 1 < height) {
                        checksum = grid[i][mattb - 1][mattx - 1] + grid[i][mattb - 1][mattx] + grid[i][mattb - 1][mattx + 1]
                                + grid[i][mattb][mattx - 1] + grid[i][mattb][mattx + 1] + grid[i][mattb + 1][mattx - 1]
                                + grid[i][mattb + 1][mattx] + grid[i][mattb + 1][mattx + 1];

                        if (checksum >= clarification && grid[i][mattb][mattx] == 1) {
                            grid[i + 1][mattb][mattx] = 1;
                        }

                        if (checksum >= 5) {
                            grid[i + 1][mattb][mattx] = 1;
                        }
                    }
                }
            }
        }
        for (int ax = 0; ax < width; ax++) {
            for (int ay = 0; ay < height; ay++) {
                if (grid[CLARIFICATION][ax][ay] == 1) {
                    rand[ax][ay].setLocation(ax * x, ay * y);
                    tempax++;
                }
            }
            tempay++;
            tempax = 0;
        }
        return grid ;
    }
    
    public static void main(String[] args) {
        //int [][][]testMap = getNewMap(32,24);
        printRandomMapToConsole(800,600,4);
    }
    
    public static void printRandomMapToConsole(int width, int height, int clarification){
        int [][][]grid = getNewMap(width, height,clarification);
                for (int d = 0; d < height; d++) {
            for (int o = 0; o < width; o++) {
                if (grid[clarification][d][o] == 1) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    public static void printRandomMapToConsole(int width, int height){
        int [][][]grid = getNewMap(width, height,CLARIFICATION);
                for (int d = 0; d < height; d++) {
            for (int o = 0; o < width; o++) {
                if (grid[CLARIFICATION][d][o] == 1) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    
    public static int[][] getClarified2DIslandMap(int width, int height){
        int [][][] gridWithClarification = getNewMap(width,height,4);
        int [][] clarifiedGrid = gridWithClarification[4]; 
        return clarifiedGrid;
    }
}
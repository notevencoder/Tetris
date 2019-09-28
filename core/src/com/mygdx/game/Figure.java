package com.mygdx.game;

public abstract class Figure {

    int rot =0;

    boolean isLanded = false;
    int color;
    int x , y;
    int[][] mas;
    Figure(int color, int SpawnX, int SpawnY, int[][] mas) {
        this.color = color;
        this.mas = mas;
        x = SpawnX;
        y = SpawnY;
    }



    abstract void spawn(int x, int y);
    abstract void move();
    abstract void fall();
    abstract void draw();
    abstract void clear();



}

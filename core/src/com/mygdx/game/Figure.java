package com.mygdx.game;


public abstract class Figure {
    boolean isNegative = false;
    int color;
    int x , y;
    int[][] mas;
    Figure(int color, int SpawnX, int SpawnY, int[][] mas) {
        this.color = color;
        this.mas = mas;
        x = SpawnX;
        y = SpawnY;
    }

    void update(){

        fall();
    }


    abstract void spawn(int x, int y);
    abstract void move();
    abstract void fall();
    abstract void setNegative();
    abstract void draw();



}

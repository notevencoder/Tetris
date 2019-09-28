package com.mygdx.game;

public abstract class Figure {
    boolean isLanded = false;
    int color;          //Цвет фигуры
    int x , y;          //Координаты центральной точки фигуры
    int rot =0;            //Фаза вращения фигуры
    int[][] mas;        //Массив поля
    //Конструктор
    Figure(int color, int SpawnX, int SpawnY, int[][] mas) {
        this.color = color;
        this.mas = mas;
        x = SpawnX;
        y = SpawnY;
    }
    abstract void spawn(int x, int y);
    abstract void move();
    abstract void fall();
    abstract void rotate();
    abstract void draw();
    abstract void clear();



}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public abstract class Figure {
    boolean isLanded = false;
    int state = 0;
    int maxstate;
    int color;
    int x , y;
    int[][] mas;
    //Следующие массиввы должны состоять из четного числа элементов, четные координаты - X, нечетные - Y
    //Это вспомогательные массивы, необходимые для проверки условий поворота, движения
    int[] deltaCoords;
    int[] checkBottom;
    int[] checkRight;
    int[] checkLeft;

    Figure(int color, int SpawnX, int SpawnY, int[][] mas) {
        this.color = color;
        this.mas = mas;
        x = SpawnX;
        y = SpawnY;

    }
    //вспомогательная функция, получающая на вход массив координат переменной длины, возвращающая true, если пересечений не найдено
    boolean checkCollisions(int... coordsXY){
        int checkX, checkY;

        for(int i = 0; i < coordsXY.length - 1; i+=2){
            checkX = x+coordsXY[i];
            checkY = y + coordsXY[i+1];
            if ( checkX < 0 || checkX >= GameLogic.width || checkY < 0 || checkY >= GameLogic.height
                    || mas[y + coordsXY[i+1]][x+coordsXY[i]] != 0){
                return false;
            }
        }
        return true;
    }
    void draw() {
        mas[y][x]                               =   color;
        mas[y+deltaCoords[1]][x+deltaCoords[0]] =   color;
        mas[y+deltaCoords[3]][x+deltaCoords[2]] =   color;
        mas[y+deltaCoords[5]][x+deltaCoords[4]] =   color;
    }
    void clear(){
        mas[y][x]                               =   0;
        mas[y+deltaCoords[1]][x+deltaCoords[0]] =   0;
        mas[y+deltaCoords[3]][x+deltaCoords[2]] =   0;
        mas[y+deltaCoords[5]][x+deltaCoords[4]] =   0;
    }
    void fall(){
        if (checkCollisions(checkBottom)) {
            clear();
            y++;
            draw();
        }
        else {
            isLanded = true;
            if (y == 0) GameLogic.GameOver();
        }
    }
    void move(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            if(checkCollisions(checkLeft)){
                clear();
                x--;
                draw();
                return;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if(checkCollisions(checkRight)) {
                clear();
                x++;
                draw();
                return;
            }
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            clear();
            rotate();
            draw();
            return;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            clear();
            rotateBack();
            draw();
        }
    }
    void rotate(){
                setState(state == maxstate ? 0 : state+1);
    }
    void rotateBack(){
        setState(state == 0 ? maxstate : state-1);
    }

    abstract void setState(int state);





}

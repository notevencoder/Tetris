package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.logging.ConsoleHandler;

public class Stick extends Figure {

    Stick(int color, int x, int y, int[][] mas){
        super(color, x, y, mas);
        spawn(x, y+2);
        this.y=y+2;

    }
    int height = GameLogic.height;
    int width = GameLogic.width;
    @Override
    void spawn(int x, int y) {
        if (mas[y + 1][x] ==0 && mas[y][x] ==0 && mas[y - 1][x] ==0 && mas[y - 2][x] ==0) {
            mas[y - 2][x] = color;
            mas[y - 1][x] = color;
            mas[y][x] = color;
            mas[y + 1][x] = color;
        }
    }
    @Override
    void fall() {
        switch (rot){
            case 0:
                if(y+2 < GameLogic.height && mas[y+2][x]==0){
                    clear();
                    y++;
                    draw();
                }
                else isLanded = true;
                break;
            case 1:
                if(y+1 < GameLogic.height && mas[y+1][x-2]==0 && mas[y+1][x-1]==0&& mas[y+1][x]==0&& mas[y+1][x+1]==0){
                    clear();
                    y++;
                    draw();
                }
                else  isLanded = true;
                break;
        }
    }

    @Override
    void move() {
        switch (rot){
            case 0:
                if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && x > 0){
                    if(mas[y-2][x-1]==0 && mas[y-1][x-1]==0 && mas[y][x-1]==0 && mas[y+1][x-1]==0) {
                        clear();
                        x--;
                        draw();
                    }
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && x+1 < GameLogic.width) {
                    if(mas[y-2][x+1]==0 && mas[y-1][x+1]==0 && mas[y][x+1]==0 && mas[y+1][x+1]==0) {
                        clear();
                        x++;
                        draw();
                    }
                }
                break;
            case 1:
                if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && x-2 > 0) {
                    if(mas[y][x-3]==0) {
                        clear();
                        x--;
                        draw();
                    }
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && x+2 < GameLogic.width) {
                    if(mas[y][x+2]==0) {
                        clear();
                        x++;
                        draw();
                    }
                }
                break;
        }
        rotate();
    }
    //У палки есть 2 состоян
    // ия вращения : 0- вертикальное, 1 - горизонтальное
    void rotate() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (Check(rot + 1 >1 ? 0:1)) {
                clear();
                rot= rot + 1 >1 ? 0:1;
                draw();
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (Check(rot - 1 <0 ? 1:0)) {
                clear();
                rot= rot - 1 <0 ? 1:0;
                draw();
            }
        }
    }

    @Override
    void draw() {
        switch (rot){
            case 0:
                mas[y-2][x] = color;
                mas[y-1][x] = color;
                mas[y][x]   = color;
                mas[y+1][x] = color;
                break;
            case 1:
                mas[y][x-2] = color;
                mas[y][x-1] = color;
                mas[y][x]   = color;
                mas[y][x+1] = color;
                break;
        }
    }
    @Override
    void clear(){
        switch (rot){
            case 0:
                mas[y-2][x] = 0;
                mas[y-1][x] = 0;
                mas[y][x]   = 0;
                mas[y+1][x] = 0;
                break;
            case 1:
                mas[y][x-2] = 0;
                mas[y][x-1] = 0;
                mas[y][x]   = 0;
                mas[y][x+1] = 0;
                break;
        }
    }
    boolean Check(int rot){
        switch (rot){
            case 0:
                if(y+1< height && mas[y-2][x] == 0 &&mas[y-1][x] == 0 && mas[y+1][x] == 0) return true;
                else return false;
            case 1:
                if(x+1<width&& x>1 && mas[y][x-2] == 0 &&mas[y][x-1] == 0 && mas[y][x+1] == 0) return true;
                else return false;
        }
        return false;
    }

}

package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Square extends Figure {

    Square(int color, int x, int y, int[][] mas){
        super(color, x, y, mas);
        spawn(x, y);


    }
    int height = GameLogic.height;
    int width = GameLogic.width;
    @Override
    void setNegative() {
        mas[y][x]     *= -1;
        mas[y+1][x]   *= -1;
        mas[y][x+1]   *= -1;
        mas[y+1][x+1] *= -1;
        isNegative = true;
    }

    @Override
    void spawn(int x, int y) {
        if (mas[y + 2][x] == 0 && mas[y + 2][x + 1] == 0){
            mas[y][x] =     color;
            mas[y][x+1] =   color;
            mas[y+1][x] =   color;
            mas[y+1][x+1] = color;
        }
    }

    @Override
    void fall() {
        if(y+2 < GameLogic.height && mas[y+2][x]==0 && mas[y+2][x+1]==0){
            y++;
        }
        else isNegative = true;
    }

    @Override
    boolean move() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && x > 0){
            if(mas[y][x-1]==0&&mas[y+1][x-1]==0)
            x--;
            return true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && x+2 < GameLogic.width) {
            if(mas[y][x+2]==0&&mas[y+1][x+2]==0)
            x++;
            return true;
        }
        return false;
    }

    @Override
    void draw() {
        mas[y][x] =     isNegative ? -color : color;
        mas[y][x+1] =   isNegative ? -color : color;
        mas[y+1][x] =   isNegative ? -color : color;
        mas[y+1][x+1] = isNegative ? -color : color;
}


}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Square extends Figure {

    Square(int color, int x, int y, int[][] mas){
        super(color, x, y, mas);
        spawn(x, y);


    }

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
        }else {

            for (int i = 0; i < Field.height; i++){
                for (int j = 0; j < Field.width; j++){
                    mas[i][j] = 0;
                }
            }
        }
    }

    @Override
    void fall() {
        if(y+2 < Field.height){
            y++;
            mas[y-1][x] = 0;
            mas[y-1][x+1] = 0;
        }

        if(y+2 == Field.height || mas[y+2][x] < 0 || mas[y+2][x+1] < 0) isNegative = true;

    }

    @Override
    void move() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && x > 0){
            x--;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && x+2 < Field.width){
            x++;
        }


    }

    @Override
    void draw() {
        mas[y][x] =     isNegative ? -color : color;
        mas[y][x+1] =   isNegative ? -color : color;
        mas[y+1][x] =   isNegative ? -color : color;
        mas[y+1][x+1] = isNegative ? -color : color;
}


}

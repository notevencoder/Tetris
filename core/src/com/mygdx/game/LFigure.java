package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class LFigure extends Figure {
    //LFigure


    int state = 0; //Состояние обЪеккта

    //Следующие массиввы должны состоять из четного числа элементов, четные координаты - X, нечетные - Y
    //Это вспомогательные массивы, необходимые для проверки условий поворота, движения
    //------------------------------------//
    int[] checkLeft;
    int[] checkRight;
    int[] checkBottom;

    int[] deltaCoords = new int[6]; //относительные координаты фигуры
    //-------------------------------------//


    LFigure(int color, int SpawnX, int SpawnY, int[][] mas) {
        super(color, SpawnX, SpawnY, mas);
        spawn(SpawnX, SpawnY);
    }

    @Override
    void spawn(int x, int y){

        deltaCoords = new int[] {-1, 0, 1, 0, -1, 1};
        checkBottom = new int[] {0, 1, 1, 1, -1, 2};
        checkLeft   = new int[] {-2, 0, -2, 1};
        checkRight  = new int[] {2, 0, 0, 1};
        draw();

    }

    @Override
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

    @Override
    void fall(){
        if (checkCollisions(checkBottom)) {
            clear();
            y++;
            draw();
        }
        else isLanded = true;

    }

    @Override
    void draw() {
        mas[y][x]                               =   color;
        mas[y+deltaCoords[1]][x+deltaCoords[0]] =   color;
        mas[y+deltaCoords[3]][x+deltaCoords[2]] =   color;
        mas[y+deltaCoords[5]][x+deltaCoords[4]] =   color;
    }
    @Override
    void clear(){
        mas[y][x]                               =   0;
        mas[y+deltaCoords[1]][x+deltaCoords[0]] =   0;
        mas[y+deltaCoords[3]][x+deltaCoords[2]] =   0;
        mas[y+deltaCoords[5]][x+deltaCoords[4]] =   0;
    }


    void rotate(){
        setState(state == 3? 0 : state+1);
    }
    void rotateBack(){
        setState(state == 0? 3 : state-1);
    }


    //вспомогательная функция, получающая на вход массив координат переменной длины, возвращающая true, если пересечений не найдено
    boolean checkCollisions(int... coordsXY){
        boolean noCollisions = true;
        int checkX, checkY;

        for(int i = 0; (i < coordsXY.length - 1) && noCollisions; i+=2){
            checkX = x+coordsXY[i];
            checkY = y + coordsXY[i+1];
            if ( checkX < 0 || checkX >= GameLogic.width || checkY < 0 || checkY >= GameLogic.height
                    || mas[y + coordsXY[i+1]][x+coordsXY[i]] != 0){
                noCollisions = false;
            }
        }
        return noCollisions;
    }


    // изменить состояние
    void setState(int state){
        switch (state){
            case 0:{
                if(checkCollisions(-1, 0, 1, 0, -1, 1)){
                    deltaCoords = new int[] {-1, 0, 1, 0, -1, 1};
                    checkBottom = new int[] {0, 1, 1, 1, -1, 2};
                    checkLeft   = new int[] {-2, 0, -2, 1};
                    checkRight  = new int[] {2, 0, 0, 1};
                    this.state = state;
                }
                return;
            }
            case 1:{
                if(checkCollisions(0, 1, 0, -1, 1, 1)){
                    deltaCoords = new int[] {0, 1, 0, -1, 1, 1};
                    checkBottom = new int[] {0, 2, 1, 2};
                    checkLeft   = new int[] {-1, 1, -1, 0, -1, -1};
                    checkRight  = new int[] {1, 1, 1, 0, 2, -1};
                    this.state = state;
                }
                return;
            }
            case 2:{
                if(checkCollisions(-1, 0, 1, 0, 1, -1)){
                    deltaCoords = new int[] {-1,  0 , 1 , 0, 1, -1};
                    checkBottom = new int[] { 0 , 1 ,-1 , 1, 1,  1};
                    checkLeft   = new int[] {-2,  0 , 0 ,-1};
                    checkRight  = new int[] { 2 ,-1 , 2 , 0};
                    this.state = state;
                }
                return;
            }
            case 3:{
                if(checkCollisions(0, 1, 0, -1, -1, -1)){
                    deltaCoords = new int[]{0, 1, 0, -1, -1, -1};
                    checkBottom = new int[]{-1, 0, 0, 2};
                    checkLeft = new int[]{-1, 0, -1, 1, -2, -1};
                    checkRight = new int[]{1, 1, 1, 0, 1, -1};
                    this.state = state;
                }
                return;
            }

        }

    }

}

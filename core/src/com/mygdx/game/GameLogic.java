package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class GameLogic {

    static private int gameCondition = 0;                       //Состояние игры
    private Texture[] textures;                         //Массив текстур блоков
    private final int LB = 287, RB =587, bSize = 30;
    private final float delay = 0.2f;                   //Константа, определающая скорость игры
    private int[][] Field;                              //Массив поля
    Random rand = new Random();
    Figure curFig;                                      //Текцщая активная фигура на поле

    public static final int width = 10, height = 20;    //Константы размера поля

    final int SpawnX = 4, SpawnY = 0;                   //Позиция появаления новой фигуры

    private float time = 0;                             //Переменная для расчёта скорости игры

    public GameLogic() {
        Field = new int[height][width];
        textures = new Texture[6];
        textures[0] = new Texture ("0.png");
        textures[1] = new Texture ("1.png");
        textures[2] = new Texture ("2.png");
        textures[3] = new Texture ("3.png");
        textures[4] = new Texture ("4.png");
        textures[5] = new Texture ("5.png");
        curFig = new Stick(rand.nextInt(5)+1, SpawnX, SpawnY, Field);
    }


    //Функция отрисовки игрового поля по блокам
    public void render(SpriteBatch batch){
            for  (int i = 0; i < height; i++){
                for (int j = 0; j < width; j++){
                    batch.draw(textures[Math.abs(Field[i][j])], LB + j * bSize, Math.abs(i - height + 1) * bSize);
                }
            }
    }

    //Основная функция, содержащая логику игры
    public void update() {
        if (gameCondition == 0){
            curFig.move();
            curFig.rotate();
            time+=Gdx.graphics.getRawDeltaTime();
            if (time >= delay){
                curFig.fall();
                if (curFig.isLanded) {
                    LineDestroy();
                    curFig = new Stick(rand.nextInt(5) + 1, SpawnX, SpawnY, Field);
                }
                time = 0;
            }
        }
        else System.out.println("Game Over");
    }

    static public void ChangeGameCond(int input){
        switch (input){
            case 0:
                gameCondition=0;
                break;
            case 1:
                gameCondition=1;
                break;
        }

    }
    //Функция проверки поля на наличие полных линий и их удаления
    private void LineDestroy(){
        int i = height - 1;
        while(i >= 0){
            int j = 0;
            while(j < width && Field[i][j] != 0) j++;

            if(j == width)
            {
                for (int ii = i - 1; ii >= 0; ii--){
                    Field[ii + 1] = Field[ii].clone();
                }
                for (int n = 0; n < width; n++) Field[0][n] = 0;
                i++;
            }
            i--;
        }
    }

}

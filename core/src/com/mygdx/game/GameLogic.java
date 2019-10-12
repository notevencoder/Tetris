package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class GameLogic {

    static int gameCondition = 0;
    private Texture[] textures;
    private final int LB = 287, RB =587, bSize = 30;
    private float delay = 0.4f;
    private float curDelay = 0.4f;
    private int[][] mas;
    Random rand = new Random();
    Figure curFig;
    public static final int width = 10, height = 20;
    final int SpawnX = 4, SpawnY = 0;
    private float time = 0;
    public GameLogic() {
        mas = new int[height][width];
        textures = new Texture[6];
        textures[0] = new Texture ("0.png");
        textures[1] = new Texture ("1.png");
        textures[2] = new Texture ("2.png");
        textures[3] = new Texture ("3.png");
        textures[4] = new Texture ("4.png");
        textures[5] = new Texture ("5.png");
        curFig = nextFig();
    }
    public void render(SpriteBatch batch){
        for  (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                batch.draw(textures[Math.abs(mas[i][j])], LB + j * bSize, Math.abs(i - height + 1) * bSize);
            }
        }
    }
    public void update() {
        if (gameCondition == 0){
            curFig.move();
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) delay = curDelay/8;
            else delay = curDelay;
            time+=Gdx.graphics.getRawDeltaTime();
            if (time >= delay){
                curFig.fall();
                if (curFig.isLanded) {
                    lineDestroy();
                    curFig = nextFig();
                }
                time = 0;
            }
        }
        /*else{
            for(int i = height - 1; i >= 0; i--){
                for(int j = width- 1; j >= 0; j--){
                    mas[i][j] = 0;
                }
            }
            gameCondition = 0;
        }*/
    }
    public static void GameOver(){ gameCondition = 1; }
    private void lineDestroy(){
        int i = height - 1;
        while(i >= 0){
            int j = 0;
            while(j < width && mas[i][j] != 0){
                j++;
            }
            if(j == width){
                for (int ii = i - 1; ii >= 0; ii--){
                    mas[ii + 1] = mas[ii].clone();
                }
                for (int n = 0; n < width; n++) mas[0][n] = 0;
                i++;
            }
            i--;
        }
    }
    // Функция, возвращающая объект Figure со случайными типом и цветом
    private Figure nextFig() {
        return new Figure(rand.nextInt(7),rand.nextInt(5) + 1,SpawnX,SpawnY,mas);
        }
}

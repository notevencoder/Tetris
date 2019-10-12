package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Currency;
import java.util.Random;

public class GameLogic {

    public static int gameCondition = 0;
    private float delay = 1f;
    private int score = 0;
    private BitmapFont scoreText = new BitmapFont();
    private int linesDestroyed = 0;
    private float curDelay = 5f/((float)(linesDestroyed/10)+5f);
    public int[][] mas;
    public static final int width = 10, height = 20;
    Figure curFig;
    final int SpawnX = width/2, SpawnY = 0;
    Random rand = new Random();
    private float time = 0;
    public GameLogic() {
        mas = new int[height][width];
        curFig = nextFig();
        scoreText.setColor(Color.FIREBRICK);
    }
    public void update() {
            curFig.move();
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) delay = curDelay/20;
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
    public static void GameOver(){ gameCondition = 1; }
    private void lineDestroy(){
        int i = height - 1;
        int amount = 0;

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
                amount++;
                linesDestroyed++;
                curDelay = 5f/((float)(linesDestroyed/10)+5f);
            }
            i--;
        }
        score += amount*amount*100;

    }
    // Функция, возвращающая объект Figure со случайными типом и цветом
    private Figure nextFig() {
        return new Figure(rand.nextInt(7),rand.nextInt(5) + 1,SpawnX,SpawnY,mas);
        }
}

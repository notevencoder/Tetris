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


    public static final int width = 10, height = 20;
    final int SpawnX = width/2, SpawnY = 0;

    public int[][]      mas;
    Figure              curFig;
    private BitmapFont  scoreText           = new BitmapFont();
    Random              rand                = new Random();
    public static int   gameCondition       = 0;
    private float       delay               = 1f;
    private float       time                = 0;
    private int         nextFig             = rand.nextInt(7);
    private int         nextFigColor        = rand.nextInt(5) + 1;
    private int         linesDestroyed      = 0;
    private int         score               = 0;
    private float       curDelay            = 5f/((float)(linesDestroyed/10)+5f);

    public GameLogic() {
        mas = new int[height][width];
        curFig = newFig();
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
                    curFig = newFig();
                }
                time = 0;
            }
        }

        public static void GameOver(){ gameCondition = 1; }

        public static boolean isGameOver(){
            return gameCondition == 1? true: false;
        }

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
    private Figure newFig() {
        Figure f = new Figure(nextFig,nextFigColor,SpawnX,SpawnY,mas);
        nextFig = rand.nextInt(7);
        nextFigColor = rand.nextInt(5) + 1;
        return f;
        }
}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Field {

    public int gameCondition = 0;
    private Texture[] textures;

    private final int LB = 287, RB =737, bSize = 30;
    private final int delay = 15;

    private int[][] mas;
    Random rand = new Random();
    Figure curFig;

    public static final int width = 10, height = 20;

    final int SpawnX = 7, SpawnY = 0;


    private int time = 0;


    public Field() {
        mas = new int[height][width];
        textures = new Texture[6];
        textures[0] = new Texture ("0.png");
        textures[1] = new Texture ("1.png");
        textures[2] = new Texture ("2.png");
        textures[3] = new Texture ("3.png");
        textures[4] = new Texture ("4.png");
        textures[5] = new Texture ("5.png");
        fill0();
        curFig = new Square(2, 7, 0, mas);

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
            fill0();
            curFig.move();
            curFig.draw();
            time++;
            if (time == delay){


            curFig.update();

            curFig.draw();
                lineDestroy();

                if (curFig.isNegative) curFig = new Square(rand.nextInt(5) + 1, SpawnX, SpawnY, mas);
            fill0();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    System.out.print(mas[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            time = 0;
            }
        }
    }

    public void GameOcer(){
        gameCondition = 1;

    }

    private void lineDestroy(){
        int i = height - 1;
        while(i >= 0){
            int j = 0;
            while(j < width && mas[i][j] < 0){
                j++;

            }
            if(j == width){

                for (int k = 0; k < width; k++){
                   mas[i][k] = 0;
                }

                for (int ii = i - 1; ii >= 0; ii--){
                    mas[ii + 1] = mas[ii].clone();
                }
                for (int n = 0; n < width; n++) mas[0][n] = 0;
                i++;
            }
            i--;
        }
    }
    private void fill0(){
        for (int i = height - 1; i >= 0; i -= 1){
            for (int j = width - 1; j >= 0; j--){
                if (mas[i][j] > 0) mas[i][j] = 0;
            }
        }
    }

}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Field {

    public int gameCondition = 0;
    private Texture c0, c1, c2, c3, c4, c5;

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
        {c0 = new Texture ("0.png");
        c1 = new Texture ("1.png");
        c2 = new Texture ("2.png");
        c3 = new Texture ("3.png");
        c4 = new Texture ("4.png");
        c5 = new Texture ("5.png");}
        fill0();
        curFig = new Square(2, 7, 0, mas);

    }



    public void render(SpriteBatch batch){

            //colors
            for  (int i = 0; i < height; i++){
                for (int j = 0; j < width; j++){
                    if (mas[i][j] == 0)
                        batch.draw(c0, LB + j * bSize, Math.abs(i - height + 1) * bSize);
                    else
                    if (Math.abs(mas[i][j]) == 1)
                        batch.draw(c1, LB + j * bSize, Math.abs(i - height + 1) * bSize);
                    else
                    if (Math.abs(mas[i][j]) == 2)
                        batch.draw(c2, LB + j * bSize, Math.abs(i - height + 1) * bSize);
                    else
                    if (Math.abs(mas[i][j]) == 3)
                        batch.draw(c3, LB + j * bSize, Math.abs(i - height + 1) * bSize);
                    else
                    if (Math.abs(mas[i][j]) == 4)
                        batch.draw(c4, LB + j * bSize, Math.abs(i - height + 1) * bSize);
                    else
                    if (Math.abs(mas[i][j]) == 5)
                        batch.draw(c5, LB + j * bSize, Math.abs(i - height + 1) * bSize);


                }

            }
            //colors


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

            //вывод массива
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    System.out.print(mas[i][j]);
                }
                System.out.println();
            }
            //вывод массива конец

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
        //Кто прочитал, тот сдохнет
    }

}

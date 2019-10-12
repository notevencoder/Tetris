package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;
import java.util.Random;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MyGdxGame extends ApplicationAdapter {
    private Stage GameStage;
    private Stage MenuStage;
    static public Stage curStage;
    int height =20, width =10;
    private Texture[] textures;
    Image[][] image;
    int bSize =30, LB = 200;
    SpriteBatch batch;
    Texture bg;
    GameLogic gl;
    public void create () {
        bg = new Texture("bg.jpg");
        batch = new SpriteBatch();
        gl = new GameLogic();
        textures = new Texture[6];
        textures[0] = new Texture ("0.png");
        textures[1] = new Texture ("1.png");
        textures[2] = new Texture ("2.png");
        textures[3] = new Texture ("3.png");
        textures[4] = new Texture ("4.png");
        textures[5] = new Texture ("5.png");
        GameStage = new Stage(new ScreenViewport());
        image = new Image[height][width];
        for (int i = 0; i < height; i++){
            for(int j=0;j<width;j++){
                image[i][j] = new Image(textures[gl.mas[i][j]]);
                image[i][j].setPosition(LB + j * bSize, Math.abs(i - height + 1) * bSize);
                GameStage.addActor(image[i][j]);
            }
        }
        //CreateGameStage();
        curStage = GameStage;
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(curStage == GameStage){
            gl.update();
            for (int i = 0; i < height; i++)
                for(int j=0;j<width;j++)
                    image[i][j].setDrawable(new TextureRegionDrawable(new TextureRegion(textures[gl.mas[i][j]])));
        }
        else {

        }
        curStage.act();
        curStage.draw();
    }
    @Override
    public void dispose () {
    }
    public static void ChangeStage(Stage stage){
        curStage = stage;
    }
    private void CreateGameStage(){
        GameStage = new Stage(new ScreenViewport());
        for (int i = 0; i < height; i++){
            for(int j=0;j<width;j++){
                image[i][j] = new Image(textures[gl.mas[i][j]]);
                image[i][j].setPosition(LB + j * bSize, Math.abs(i - height + 1) * bSize);
                GameStage.addActor(image[i][j]);
            }
        }
    }
}

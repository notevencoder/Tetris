package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameLogic;

public class GameScreen implements Screen{

    Game game;
    Stage stage;

    final int height = 20, width = 10;
    private Texture[] textures;
    Image[][] image;
    int bSize = Gdx.graphics.getHeight() / 20, center =Gdx.graphics.getWidth() / 2 , LB =center -  width * bSize / 2;

    Texture bg;
    GameLogic gl;

    public GameScreen(Game aGame){
        game = aGame;
        stage = new Stage (new ScreenViewport());

        bg = new Texture("bg.jpg");


        gl = new GameLogic();

        textures = new Texture[6];
        textures[0] = new Texture ("0.png");
        textures[1] = new Texture ("1.png");
        textures[2] = new Texture ("2.png");
        textures[3] = new Texture ("3.png");
        textures[4] = new Texture ("4.png");
        textures[5] = new Texture ("5.png");



        image = new Image[height][width];

        for (int i = 0; i < height; i++){
            for(int j=0;j<width;j++){
                image[i][j] = new Image(textures[gl.mas[i][j]]);
                image[i][j].setPosition(LB + j * bSize, Math.abs(i - height + 1) * bSize);
                //image[i][j].scaleBy(1);
                stage.addActor(image[i][j]);

            }
        }


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);// Сообщаем игре, что мы работаем с этой сценой(все клики мышей будут относиться к этой сцене)
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gl.update();

        for (int i = 0; i < height; i++)
            for(int j=0;j<width;j++)
                image[i][j].setDrawable(new TextureRegionDrawable(new TextureRegion(textures[gl.mas[i][j]])));
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Buttons.MenuButton;
import com.mygdx.game.MyGdxGame;

import java.time.format.TextStyle;

public class TitleScreen implements Screen{

    private Game game;
    private Stage stage;
    private Label title;
    private MenuButton start, options, exit;
    private int curWidth = Gdx.graphics.getWidth();
    private int curHeight = Gdx.graphics.getHeight();

    public TitleScreen(Game aGame){
            game = aGame;
            stage = new Stage(new ScreenViewport());




            ////Надпись Тетрис/////////////////////////////////



            title = new Label("TetriS", MyGdxGame.gameSkin,"big");    //Инициализация (текст надписи, скин(см main), стиль текста(есть не во всех скинах))
            title.setAlignment(Align.center);                                       //Центровка текста
            title.setY(Gdx.graphics.getHeight() / 10 * 8);                          //Позиционирование надписи по У
            title.setWidth(Gdx.graphics.getWidth());                                //Установка ширины надписи
            stage.addActor(title);                                                  //Добавдение актера на сцены
            ///////////////////////////////////////////////////

            ////Кнопка старта игры/////////////////////////////
            start = new MenuButton("Start Game"){
                @Override
                public void onClick(){
                    game.setScreen(new GameScreen(game));
                }
            };
            start.setPosition(curWidth / 4, curHeight /4 + 3 * curHeight / 10);
            stage.addActor(start);
            ///////////////////////////////////////////////////

            //  Далее все кнопки делаются аналогично

            ////Кнопка настроек игры///////////////////////////
            options = new MenuButton("Options"){
                @Override
                public void onClick(){
                    game.setScreen(new OptionScreen(game));
                }
            };
            options.setPosition(curWidth / 4, curHeight / 4 + 1.5f * curHeight / 10);
            stage.addActor(options);
            ///////////////////////////////////////////////////

            ////Кнопка выхода из игры//////////////////////////
            exit = new MenuButton("Exit game"){
                @Override
                public void onClick(){
                    Gdx.app.exit();
                }
            };
            stage.addActor(exit);
            ///////////////////////////////////////////////////


    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);// Сообщаем игре, что мы работаем с этой сценой(все клики мышей будут относиться к этой сцене)
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if (curHeight != height || curWidth != width)
        game.setScreen(new TitleScreen(game));
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

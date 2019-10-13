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
    private TextButton start, option, exit;

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
            start = new MenuButton("Start Game", MyGdxGame.gameSkin);                                                           //Инициализация (текст надписи, скин(см main), стиль текста(есть не во всех скинах))
            start.setWidth(Gdx.graphics.getWidth() / 16 * 8);                                                    //Задаем ширину кнопки
            start.setPosition(Gdx.graphics.getWidth() / 16 * 4,Gdx.graphics.getHeight() / 10 * 6);        //Задаем координаты кнопки
            start.setTransform(true);
            //start.scaleBy(1);
            start.addListener(new InputListener(){                                                                   //  InputListener - штука, которая обрабатывает нажатия
                @Override                                                                                            //
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {                  //  обработка момента "отжатия" кнопки
                    game.setScreen(new GameScreen(game));                                                            //  переключение на GameScreen
                }                                                                                                    //
                @Override                                                                                            //
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {             //  Обработка нажатия
                    return true;                                                                                     //
                }
            });
            stage.addActor(start);
            ///////////////////////////////////////////////////

            //  Далее все кнопки делаются аналогично

            ////Кнопка настроек игры///////////////////////////
            option = new TextButton("Options", MyGdxGame.gameSkin);
            option.setWidth(Gdx.graphics.getWidth() / 16 * 8);
            option.scaleBy(0.05f);
            option.setPosition(Gdx.graphics.getWidth() / 16 * 4,Gdx.graphics.getHeight() / 10 * 4);

            option.addListener(new InputListener(){
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    game.setScreen(new OptionScreen(game));
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            stage.addActor(option);
            ///////////////////////////////////////////////////

            ////Кнопка выхода из игры//////////////////////////
            exit = new TextButton("Exit game", MyGdxGame.gameSkin);
            exit.setWidth(Gdx.graphics.getWidth() / 16 * 8);
            //start.scaleBy(0.5f);
            exit.setPosition(Gdx.graphics.getWidth() / 16 * 4,Gdx.graphics.getHeight() / 10 * 2);

            exit.addListener(new InputListener(){
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.exit();
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
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
        start.setWidth(Gdx.graphics.getWidth() / 16 * 8);
        start.setPosition(Gdx.graphics.getWidth() / 16 * 4,Gdx.graphics.getHeight() / 10 * 6);

        option.setPosition(Gdx.graphics.getWidth() / 16 * 4,Gdx.graphics.getHeight() / 10 * 4);
        exit.setPosition(Gdx.graphics.getWidth() / 16 * 4,Gdx.graphics.getHeight() / 10 * 2);

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

package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;
import java.util.Random;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Screens.TitleScreen;

public class MyGdxGame extends Game {




    int height =20, width =10;
    private Texture[] textures;
    Image[][] image;
    int bSize =30, LB = 200;
    SpriteBatch batch;
    Texture bg;
    GameLogic gl;
    public static Skin gameSkin;


    public void create () {

        gameSkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));// Текстуры для кнопокб надписей и тд

        this.setScreen(new TitleScreen(this));


        /*

        */

    }

    @Override
    public void render () {
        super.render();
    }
    @Override
    public void dispose () {
    }


}

package com.mygdx.game.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screens.GameScreen;

import java.lang.reflect.Method;

public class MenuButton extends ImageTextButton {

    private BitmapFont menuFont;
    private TextureAtlas atlas;
    private Skin skin;
    public MenuButton(String text) {

        super(text, MyGdxGame.gameSkin);


        atlas = new TextureAtlas("ui/menu_button.pack");
        skin = new Skin(atlas);



        menuFont = new BitmapFont(Gdx.files.internal("fonts/menu.fnt"), false);

        final ImageTextButtonStyle imageTextButtonStyle = new ImageTextButtonStyle();
        imageTextButtonStyle.up = skin.getDrawable("button.up");
        imageTextButtonStyle.down = skin.getDrawable("button.down");
        imageTextButtonStyle.pressedOffsetX =  1;
        imageTextButtonStyle.pressedOffsetY = -1;
        imageTextButtonStyle.font = menuFont;


        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                onClick();
            }
        });

        this.setSize(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/10);
        this.setStyle(imageTextButtonStyle);
        this.setTransform(true);


    }
    public Skin getSkin(){
        return skin;
    }

    public void onClick(){}

}

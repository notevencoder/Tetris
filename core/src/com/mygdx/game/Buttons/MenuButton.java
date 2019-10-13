package com.mygdx.game.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.MyGdxGame;

public class MenuButton extends TextButton {

    private BitmapFont menuFont;
    private TextureAtlas atlas;
    private Skin skin;

    public MenuButton(String text, Skin skin1) {

        super(text, skin1);


        atlas = new TextureAtlas("ui/menu_button.pack");
        skin = new Skin(atlas);




        menuFont = new BitmapFont(Gdx.files.internal("fonts/menu.fnt"), false);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.pressedOffsetX =  1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = menuFont;




        this.setStyle(textButtonStyle);
        //this.setTransform(true);


    }



}

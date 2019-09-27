package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {

    private Texture tx;


    public Background(){
        tx = new Texture("bg.jpg");
    }
    void render(SpriteBatch batch){
        batch.draw(tx,0,0);
    }

}

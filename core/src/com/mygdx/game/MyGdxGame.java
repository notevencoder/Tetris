package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background bg;
	Field fl;

	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new Background();
		fl = new Field();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		bg.render(batch);
		fl.update();
		fl.render(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}

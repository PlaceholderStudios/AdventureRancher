package com.Adventure.Rancher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AdventureRancher extends ApplicationAdapter {
//	SpriteBatch batch;
//	Texture img;

	GraphicsProcessor testMap;
	
	@Override
	public void create () {
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        testMap = new GraphicsProcessor("testmap");
        testMap.setToRender(true);
	}

	@Override
	public void render () {
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();

        testMap.render();
	}
}

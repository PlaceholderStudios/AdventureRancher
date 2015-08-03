package com.Adventure.Rancher;

import com.Adventure.Rancher.Util.Assets.Text;
import com.Adventure.Rancher.Util.Assets.TextAsset;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Atlas on 8/2/2015.
 */
public class GraphicsProcessor
{
    private AssetManager assetManager;

    private ArrayList<Texture> textureList;
    private ArrayList<Byte> mapLayout;

    private SpriteBatch spriteBatch;

    private final int tileSize = 32;

    private boolean hasRendered = false;

    public GraphicsProcessor(String mapName)
    {
        Gdx.graphics.setContinuousRendering(false);

        assetManager = new AssetManager();
        textureList = new ArrayList<Texture>();

        assetManager.setLoader(Text.class, new TextAsset(new InternalFileHandleResolver()));

        assetManager.load("grass.png", Texture.class);
        assetManager.load("water.png", Texture.class);
        assetManager.load(new AssetDescriptor<Text>(mapName + ".txt", Text.class, new TextAsset.TextParameter()));

        boolean loadingDone = assetManager.update();
        while(!loadingDone)
            loadingDone = assetManager.update();

        textureList.add(assetManager.get("grass.png", Texture.class));
        textureList.add(assetManager.get("water.png", Texture.class));

        mapLayout = new ArrayList<Byte>();

        String returnedFile = assetManager.get(mapName + ".txt", Text.class).getString();
        returnedFile = returnedFile.replaceAll("[\r\n+]", "");
        String[] tokens = returnedFile.split(",+");

        for (int i = 0; i < tokens.length; i++)
            mapLayout.add(Byte.valueOf(tokens[i]));

        spriteBatch = new SpriteBatch();
    }

    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int mapX = 0;
        int mapY = 0;

        spriteBatch.begin();

        for (int i = 0; i < mapLayout.size(); i++) {
            if (i % 20 == 0 && i > 0) {
                mapX = 0;
                mapY = mapY + tileSize;
            }

            byte tileVal = mapLayout.get(i);
            spriteBatch.draw(textureList.get(tileVal), mapX, mapY);

            mapX = mapX + tileSize;
        }
        spriteBatch.end();
        Gdx.graphics.requestRendering();

        hasRendered = true;
    }

    public boolean isDoneRendering() {
        return hasRendered;
    }

    public void setToRender(boolean shouldRender){
        this.hasRendered = !shouldRender;
    }

}

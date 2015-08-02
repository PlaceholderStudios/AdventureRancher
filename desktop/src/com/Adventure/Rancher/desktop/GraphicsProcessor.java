package com.Adventure.Rancher.desktop;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by Atlas on 8/2/2015.
 */
public class GraphicsProcessor
{
    AssetManager assetManager;

    ArrayList<Texture> textureList;

    public GraphicsProcessor(String map)
    {
        assetManager = new AssetManager();
        textureList = new ArrayList<Texture>();

        assetManager.load("grass.png", Texture.class);
        assetManager.load("water.png", Texture.class);

        boolean loadingDone = assetManager.update();
        while(!loadingDone)
            loadingDone = assetManager.update();

        textureList.add(assetManager.get("grass.png", Texture.class));
        textureList.add(assetManager.get("water.png", Texture.class));

        Path path = Paths.get("testmap.txt");
    }
}

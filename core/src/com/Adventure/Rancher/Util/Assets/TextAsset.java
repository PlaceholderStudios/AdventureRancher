package com.Adventure.Rancher.Util.Assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Atlas on 8/2/2015.
 */
public class TextAsset extends AsynchronousAssetLoader<Text, TextAsset.TextParameter> {

    public TextAsset(FileHandleResolver resolver){
        super(resolver);
    }

    Text text;

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, TextAsset.TextParameter parameter) {
        return null;
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, TextAsset.TextParameter parameter) {
        this.text = null;
        this.text = new Text(file);
    }

    @Override
    public Text loadSync(AssetManager manager, String fileName, FileHandle file, TextAsset.TextParameter parameter) {
        Text text = this.text;
        this.text = null;
        return text;
    }

    public static class TextParameter extends AssetLoaderParameters<Text>{

    }
}

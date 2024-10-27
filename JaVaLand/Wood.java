package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Wood extends Block{
    public Wood(Core game, String s){
        super(game, 30, new Texture(Gdx.files.internal(s)));
    }
}

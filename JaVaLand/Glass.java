package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Glass extends Block{
    public Glass(Core game, String s){
        super(game, 40, new Texture(Gdx.files.internal(s)));
    }
}

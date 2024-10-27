package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Metal extends Block{
    public Metal(Core game, String s){
        super(game, 50, new Texture(Gdx.files.internal(s)));
    }
}

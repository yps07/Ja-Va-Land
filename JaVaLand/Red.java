package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Red extends Bird{
    public Red(Core game){
        super(game, 5, 50, new Texture(Gdx.files.internal("red.png")));
        super.setSize(45, 45);
    }
}

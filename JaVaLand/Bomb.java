package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Bomb extends Bird{
    public Bomb(Core game){
        super(game, 3, 70, new Texture(Gdx.files.internal("bomb.png")));
        super.setSize(60, 60);
    }
}

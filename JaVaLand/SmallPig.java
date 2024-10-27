package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SmallPig extends Pig {
    public SmallPig(Core game){
        super(game, 40, 40, 50, new Texture(Gdx.files.internal("s_pig.png")));
    }
}

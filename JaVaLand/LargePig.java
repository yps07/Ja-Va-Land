package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class LargePig extends Pig {
    public LargePig(Core game){
        super(game, 65, 65, 100, new Texture(Gdx.files.internal("l_pig.png")));
    }
}

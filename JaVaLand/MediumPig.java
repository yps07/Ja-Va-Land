package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MediumPig extends Pig {
    public MediumPig(Core game){
        super(game, 50, 50, 75, new Texture(Gdx.files.internal("m_pig.png")));
    }
}

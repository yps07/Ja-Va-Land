package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Chuck extends Bird{
    public Chuck(Core game){
        super(game, 9, 30, new Texture(Gdx.files.internal("chuck.png")));
        super.setSize(40, 40);
    }
}

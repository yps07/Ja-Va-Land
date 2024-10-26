package io.github.JaVaLand;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Core extends Game{
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MainGameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
}

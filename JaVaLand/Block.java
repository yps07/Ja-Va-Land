package io.github.JaVaLand;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Block {
    private float durability;
    private Texture img;
    private Sprite sprite;
    private Core game;

    public Block(Core game, float durability, Texture img) {
        this.game = game;
        this.durability = durability;
        this.img = img;
        sprite = new Sprite(img);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2); // Set origin for rotation
    }

    public void durabilityReduction(float amount) {
        durability -= amount;
        if (durability < 0) {
            durability = 0;
        }
    }

    public float getDurability() {
        return durability;
    }

    public void setSize(float x, float y) {
        sprite.setSize(x, y);
    }

    public boolean isSmashed() {
        return (durability == 0);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public void draw() {
        sprite.draw(game.batch);
    }

    public void dispose() {
        img.dispose();
    }
}

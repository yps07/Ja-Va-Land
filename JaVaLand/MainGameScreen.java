package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainGameScreen implements Screen {

    private Texture img, img2, img3, img4, img5;
    private Sprite sprite, text, b1, b2, b3;
    private final Core game;
    private BitmapFont font;
    private Skin skin;
    private Stage stage;

    public MainGameScreen(Core game) {
        this.game = game;
    }

    @Override
    public void show() {
        img = new Texture("bgi.jpg");
        img2 = new Texture("abtext.png");
        img3 = new Texture("red.png");
        img4 = new Texture("bombnew.png");
        img5 = new Texture("chuckrev.png");

        sprite = new Sprite(img);
        text = new Sprite(img2);
        b1 = new Sprite(img3);
        b2 = new Sprite(img4);
        b3 = new Sprite(img5);

        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite.setPosition((Gdx.graphics.getWidth() - sprite.getWidth()) / 2,
            ((Gdx.graphics.getHeight() - sprite.getHeight()) / 2)-50);

        text.setSize(800, 320);
        text.setPosition(100, 375);

        b1.setSize(250, 250);
        b1.setPosition(350, 145);

        b2.setSize(225, 225);
        b2.setPosition(550, 165);

        b3.setSize(200, 200);
        b3.setPosition(230,160);

        stage =  new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont(Gdx.files.internal("abfont.fnt"));

        skin = new Skin();
        skin.add("default", font);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;

        textButtonStyle.fontColor = new Color(1, 1, 1, 1);
        textButtonStyle.downFontColor = new Color(0.5f, 0, 0, 1);

        TextButton button = new TextButton(">>", textButtonStyle);
        button.setPosition(935, 25);
        stage.addActor(button);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Menu(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Set alpha to 1
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        sprite.draw(game.batch);
        text.draw(game.batch);
        b2.draw(game.batch);
        b3.draw(game.batch);
        b1.draw(game.batch);

        game.batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        img.dispose();
        img2.dispose();
        stage.dispose();
        font.dispose();
        skin.dispose();
    }
}

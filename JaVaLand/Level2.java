package io.github.JaVaLand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Level2 implements Screen {

    Texture img, img2, img3;
    Sprite sprite, sprite2, sprite3;
    Core game;
    private BitmapFont font;
    private Stage stage;
    private Texture buttonTexture;
    private TextButton saveButton;
    private TextButton resumeButton;
    private TextButton next;
    private TextButton forfeitButton;
    private Group menuGroup;
    private boolean isMenuVisible = false;
    private Red red;
    private Chuck chuck;
    private Bomb bomb;
    private SmallPig s_pig, s_pig2;
    private MediumPig m_pig, m_pig2;
    private LargePig l_pig;
    private Wood w1, w2, w3, w4, w5;
    private Glass g1, g2, g3;
    private Metal m1, m2, m3, m4, m5;

    public Level2(Core game) {
        this.game = game;
    }

    @Override
    public void show() {
        img = new Texture("bg.jpg");
        img2 = new Texture("planet.png");
        img3 = new Texture("slingshot.png");

        sprite = new Sprite(img);
        sprite2 = new Sprite(img2);
        sprite3 = new Sprite(img3);

        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite.setPosition(0, 0);

        sprite2.setSize(2500, 1200);
        sprite2.setPosition(-740, -980);

        sprite3.setSize(100, 100);
        sprite3.setPosition(170, 50);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("abfont1.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        font = generator.generateFont(parameter);
        generator.dispose();

        stage = new Stage(new ScreenViewport());

        buttonTexture = new Texture(Gdx.files.internal("menu.png"));
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(buttonTexture);
        ImageButton button = new ImageButton(buttonDrawable);

        button.setPosition(30, 530);
        button.setSize(45, 45);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toggleMenu();
            }
        });

        stage.addActor(button);

        menuGroup = new Group();
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;
        buttonStyle.downFontColor = new Color(0.5f, 0, 0, 1);

        saveButton = new TextButton("Save & Exit", buttonStyle);
        resumeButton = new TextButton("Resume", buttonStyle);
        forfeitButton = new TextButton("Forfeit", buttonStyle);

        next = new TextButton(">>", buttonStyle);

        saveButton.setPosition(30, 440);
        resumeButton.setPosition(30, 480);
        forfeitButton.setPosition(30, 400);

        next.setPosition(920, 530);

        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveGame();
            }
        });

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resumeGame();
            }
        });

        next.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new WinScreen(game, 3));
            }
        });

        forfeitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoseScreen(game, 2));
            }
        });

        menuGroup.addActor(saveButton);
        menuGroup.addActor(resumeButton);
        menuGroup.addActor(forfeitButton);

        stage.addActor(menuGroup);
        stage.addActor(next);

        menuGroup.setVisible(false);

        Gdx.input.setInputProcessor(stage);

        red = new Red(game);
        chuck = new Chuck(game);
        bomb = new Bomb(game);

        bomb.setPosition(30, 50);
        chuck.setPosition(85, 50);
        red.setPosition(130, 50);

        s_pig = new SmallPig(game);
        m_pig = new MediumPig(game);
        l_pig = new LargePig(game);

        s_pig2 = new SmallPig(game);
        m_pig2 = new MediumPig(game);

        s_pig.setPosition(855, 50);
        m_pig.setPosition(853, 131);
        l_pig.setPosition(846, 217);

        s_pig2.setPosition(755, 50);
        m_pig2.setPosition(753, 131);

        w1 = new Wood(game, "wood1.png");
        w1.setSize(20, 70);
        w2 = new Wood(game, "wood1.png");
        w2.setSize(20, 70);
        w3 = new Wood(game, "wood.png");
        w3.setSize(120, 20);

        w4 = new Wood(game, "wood1.png");
        w4.setSize(20, 70);
        w5 = new Wood(game, "wood.png");
        w5.setSize(120, 20);

        g1 = new Glass(game, "glass1.png");
        g1.setSize(20, 70);
        g2 = new Glass(game, "glass1.png");
        g2.setSize(20, 70);
        g3 = new Glass(game, "glass.png");
        g3.setSize(110, 20);

        m1 = new Metal(game, "metal1.png");
        m1.setSize(20, 70);
        m2 = new Metal(game, "metal1.png");
        m2.setSize(20, 70);
        m3 = new Metal(game, "metal.png");
        m3.setSize(109, 20);

        m4 = new Metal(game, "metal1.png");
        m4.setSize(20, 70);
        m5 = new Metal(game, "metal.png");
        m5.setSize(106, 20);

        w1.setPosition(910,50);
        w2.setPosition(820, 50);
        w3.setPosition(815, 115);

        w4.setPosition(715, 50);
        w5.setPosition(710, 115);

        g1.setPosition(910,133);
        g2.setPosition(820, 133);
        g3.setPosition(820, 202);

        m1.setPosition(910,221);
        m2.setPosition(820, 221);
        m3.setPosition(820, 288);

        m4.setPosition(715, 133);
        m5.setPosition(715, 202);
    }

    private void toggleMenu() {
        isMenuVisible = !isMenuVisible;
        menuGroup.setVisible(isMenuVisible);
    }

    private void saveGame() {
        game.setScreen(new Menu(game));
    }

    private void resumeGame() {
        isMenuVisible = false;
        menuGroup.setVisible(false);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        sprite.draw(game.batch);
        sprite2.draw(game.batch);

        font.draw(game.batch, "Level 2", 480, 557);

        red.draw();
        chuck.draw();
        bomb.draw();

        s_pig.draw();
        m_pig.draw();
        l_pig.draw();
        s_pig2.draw();
        m_pig2.draw();

        w1.draw();
        w2.draw();
        w3.draw();
        w4.draw();
        w5.draw();

        g1.draw();
        g2.draw();
        g3.draw();

        m1.draw();
        m2.draw();
        m3.draw();
        m4.draw();
        m5.draw();

        sprite3.draw(game.batch);

        game.batch.end();

        stage.act();
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
    }

    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
        img.dispose();
        img2.dispose();
        buttonTexture.dispose();
    }
}

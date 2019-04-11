package es.codemonsters.ranero.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import es.codemonsters.ranero.Ranero;
import es.codemonsters.ranero.gameobjects.Player;

public class GameScreen implements Screen {

    public static final int ANCHO_DEL_MUNDO = 400;
    public static final int ALTO_DEL_MUNDO = 300;

    private final Ranero game;

    private OrthographicCamera camera;

    private SpriteBatch batch;
    private Texture raneroSpriteSheet;
    private TextureRegion ranaTextureJ1;
    private TextureRegion ranaTextureJ2;
    private Player jugador1, jugador2;
    private Stage stage;

    public GameScreen(final Ranero game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800, 480);
        Gdx.gl.glClearColor(0,0,0, 1);

        batch = new SpriteBatch();
        raneroSpriteSheet = new Texture("raneroSpriteSheet.png");

        ranaTextureJ1 = new TextureRegion(raneroSpriteSheet, 15, 18, 12, 13);
        ranaTextureJ2 = new TextureRegion(raneroSpriteSheet, 15, 50, 12, 13);

        jugador1 = new Player(ranaTextureJ1);
        jugador1.setPosition(100, 250);
        jugador2 = new Player(ranaTextureJ2);
        jugador2.setPosition(500, 250);
        resetLevel();

    }

    public void resetLevel() {
        if (stage != null) {
            stage.dispose();
        }
        stage = new Stage(new FitViewport(ANCHO_DEL_MUNDO, ALTO_DEL_MUNDO));
        stage.addActor(jugador1);
        stage.addActor(jugador1);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(dt);
        stage.draw();
        /*
        jugador1.setRotation(270);
        jugador2.setRotation(90);
        game.batch.begin();
        jugador1.draw(game.batch);
        jugador2.draw(game.batch);
        game.batch.end();
        */
    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
        raneroSpriteSheet.dispose();

    }
}

package es.codemonsters.ranero.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dongbat.jbump.World;

import es.codemonsters.ranero.Ranero;
import es.codemonsters.ranero.gameobjects.Bloque;
import es.codemonsters.ranero.gameobjects.GameObject;
import es.codemonsters.ranero.gameobjects.Player;

public class GameScreen implements Screen, InputProcessor {

    public static final int ANCHO_DEL_MUNDO = 400;
    public static final int ALTO_DEL_MUNDO = 300;

    private final Ranero game;

    private OrthographicCamera camera;

    private SpriteBatch batch;
    private Texture raneroSpriteSheet;
    private TextureRegion ranaTextureJ1, ranaTextureJ2, bloqueTexture;
    private Player jugador1, jugador2;
    private Bloque bloque;
    private Stage stage;
    private World<GameObject> world;

    public GameScreen(final Ranero game){
        stage = new Stage(new FitViewport(100, 100));

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800, 480);
        Gdx.gl.glClearColor(0,0,0, 1);

        batch = new SpriteBatch();
        raneroSpriteSheet = new Texture("raneroSpriteSheet.png");

        ranaTextureJ1 = new TextureRegion(raneroSpriteSheet, 15, 18, 12, 13);
        ranaTextureJ2 = new TextureRegion(raneroSpriteSheet, 15, 50, 12, 13);
        bloqueTexture = new TextureRegion(raneroSpriteSheet, 637, 16, 1, 1);

        world = new World<GameObject>();

        jugador1 = new Player(ranaTextureJ1, 100, 250, world);
        jugador2 = new Player(ranaTextureJ2, 300, 250, world);
        bloque = new Bloque(bloqueTexture, 0, 0, 10, 10, world);

        Gdx.input.setInputProcessor(this);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        resetLevel();

    }

    public void resetLevel() {
        if (stage != null) {
            stage.dispose();
        }
        stage = new Stage(new FitViewport(ANCHO_DEL_MUNDO, ALTO_DEL_MUNDO));
        stage.addActor(jugador1);
        stage.addActor(jugador2);
        stage.addActor(bloque);
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
        batch.dispose();
        raneroSpriteSheet.dispose();
        stage.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.RIGHT) { // flechas de dirección
            Gdx.app.debug("GameScreen", "keyDown: FLECHA DERECHA");
            jugador1.right(true);
        } else if (keycode == Input.Keys.UP) {
            Gdx.app.debug("GameScreen", "keyDown: FLECHA ARRIBA");
            jugador1.up(true);
        } else if (keycode == Input.Keys.LEFT) {
            Gdx.app.debug("GameScreen", "keyDown: FLECHA IZQUIERDA");
            jugador1.left(true);
        } else if (keycode == Input.Keys.DOWN) {
            Gdx.app.debug("GameScreen", "keyDown: FLECHA ABAJO");
            jugador1.down(true);
        } else if (keycode == Input.Keys.W) { // ahora las letras
            Gdx.app.debug("GameScreen", "keyDown: W");
            jugador2.up(true);
        } else if (keycode == Input.Keys.A) {
            Gdx.app.debug("GameScreen", "keyDown: A");
            jugador2.left(true);
        } else if (keycode == Input.Keys.S) {
            Gdx.app.debug("GameScreen", "keyDown: S");
            jugador2.down(true);
        } else if (keycode == Input.Keys.D) {
            Gdx.app.debug("GameScreen", "keyDown: D");
            jugador2.right(true);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.RIGHT) { // flechas de dirección
            jugador1.right(false);
        } else if (keycode == Input.Keys.UP) {
            jugador1.up(false);
        } else if (keycode == Input.Keys.LEFT) {
            jugador1.left(false);
        } else if (keycode == Input.Keys.DOWN) {
            jugador1.down(false);
        } else if (keycode == Input.Keys.W) { // ahora las letras
            jugador2.up(false);
        } else if (keycode == Input.Keys.A) {
            jugador2.left(false);
        } else if (keycode == Input.Keys.S) {
            jugador2.down(false);
        } else if (keycode == Input.Keys.D) {
            jugador2.right(false);
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

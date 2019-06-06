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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dongbat.jbump.World;

import es.codemonsters.ranero.Ranero;
import es.codemonsters.ranero.gameobjects.Bloque;
import es.codemonsters.ranero.gameobjects.Coche;
import es.codemonsters.ranero.gameobjects.GameObject;
import es.codemonsters.ranero.gameobjects.Player;

public class GameScreen implements Screen, InputProcessor {

    private static final int ANCHO_DEL_MUNDO = 400;
    private static final int ALTO_DEL_MUNDO = 300;

    private OrthographicCamera camera;

    private SpriteBatch batch;
    private Texture raneroSpriteSheet;
    private TextureRegion bloqueTexture, cocheTexture;
    private Player jugador1, jugador2;
    private Bloque muroIzquierdo, muroSuperior, muroDerecho, muroInferior;
    private Coche coche;
    private Stage stage;
    private World<GameObject> world;

    public GameScreen(final Ranero game){
        stage = new Stage(new FitViewport(100, 100));

        camera = new OrthographicCamera();
        camera.setToOrtho(false,800, 480);
        Gdx.gl.glClearColor(0,0,0, 1);

        batch = new SpriteBatch();
        raneroSpriteSheet = new Texture("raneroSpriteSheet.png");

        bloqueTexture = new TextureRegion(raneroSpriteSheet, 253, 16, 1, 1);
        cocheTexture = new TextureRegion(raneroSpriteSheet, 125, 337, 16, 14);

        world = new World<GameObject>();

        Array<TextureRegion> regionesTexturaJugador1 = new Array<TextureRegion>();
        regionesTexturaJugador1.add(new TextureRegion(raneroSpriteSheet, 63, 19, 12, 9));
        regionesTexturaJugador1.add(new TextureRegion(raneroSpriteSheet, 40, 18, 10, 14));
        regionesTexturaJugador1.add(new TextureRegion(raneroSpriteSheet, 15, 18, 12, 13));
        regionesTexturaJugador1.add(new TextureRegion(raneroSpriteSheet, 40, 18, 10, 14));
        jugador1 = new Player(100, 250, Player.Direcciones.DERECHA, regionesTexturaJugador1, world);

        Array<TextureRegion> regionesTexturaJugador2 = new Array<TextureRegion>();
        regionesTexturaJugador2.add(new TextureRegion(raneroSpriteSheet, 63, 50, 12, 9));
        regionesTexturaJugador2.add(new TextureRegion(raneroSpriteSheet, 40, 50, 10, 14));
        regionesTexturaJugador2.add(new TextureRegion(raneroSpriteSheet, 15, 51, 12, 13));
        regionesTexturaJugador2.add(new TextureRegion(raneroSpriteSheet, 40, 50, 10, 14));
        jugador2 = new Player(300, 250, Player.Direcciones.IZQUIERDA, regionesTexturaJugador2, world);

        coche = new Coche(cocheTexture, 200, 200, 16, 14, world);

        muroIzquierdo = new Bloque(bloqueTexture, -9, 0, ALTO_DEL_MUNDO, 10, world);  // muro izquierdo
        muroSuperior = new Bloque(bloqueTexture, 0, ALTO_DEL_MUNDO - 1, 10, ANCHO_DEL_MUNDO, world);
        muroDerecho = new Bloque(bloqueTexture, ANCHO_DEL_MUNDO - 1, 0, ALTO_DEL_MUNDO, 10, world);
        muroInferior = new Bloque(bloqueTexture, 0, -9, 10, ANCHO_DEL_MUNDO, world);

        Gdx.input.setInputProcessor(this);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        resetLevel();

    }

    private void resetLevel() {
        if (stage != null) {
            stage.dispose();
        }
        stage = new Stage(new FitViewport(ANCHO_DEL_MUNDO, ALTO_DEL_MUNDO));
        stage.addActor(jugador1);
        stage.addActor(jugador2);
        stage.addActor(coche);

        stage.addActor(muroIzquierdo);
        stage.addActor(muroSuperior);
        stage.addActor(muroDerecho);
        stage.addActor(muroInferior);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(dt);
        stage.draw();

        jugador1.setRotation(270);
        jugador2.setRotation(90);

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

        muroIzquierdo.dispose();
        muroSuperior.dispose();
        muroDerecho.dispose();
        muroInferior.dispose();


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

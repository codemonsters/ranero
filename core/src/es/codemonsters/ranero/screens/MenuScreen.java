package es.codemonsters.ranero.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.Random;

import es.codemonsters.ranero.Ranero;

public class MenuScreen implements Screen {
    final Ranero game;

    OrthographicCamera camera;


    public MenuScreen(final Ranero game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Fuente obtenida de https://www.fontsquirrel.com/fonts/list/classification/comic, Comic Relief
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            System.out.println("SPACIO");
        }
        game.fontBig.draw(game.batch, "RANERO", 430f, 600f);

        game.fontMedium.draw(game.batch, "Pulsa disparo para empezar", 330f, 400f);
        game.batch.end();
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

    }
}

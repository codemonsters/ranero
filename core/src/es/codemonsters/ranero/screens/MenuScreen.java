package es.codemonsters.ranero.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import es.codemonsters.ranero.Ranero;

public class MenuScreen implements Screen {
    final Ranero game;
    private Stage stage;
    //private Table table;

    public MenuScreen(final Ranero game) {
        this.game = game;
        stage = new Stage(new FitViewport(100, 100));
        Gdx.gl.glClearColor(0f, 1f, 1f, 1);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        //stage.draw();

        // Fuente obtenida de https://www.fontsquirrel.com/fonts/list/classification/comic, Comic Relief
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game));
        }
        game.fontBig.draw(game.batch, "RANERO", 430f, 600f);

        game.fontMedium.draw(game.batch, "Pulsa espacio para empezar", 330f, 400f);
        game.batch.end();
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
    }
}

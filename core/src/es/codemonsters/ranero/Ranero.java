package es.codemonsters.ranero;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import es.codemonsters.ranero.gameobjects.RanaJugador;
import es.codemonsters.ranero.screens.MenuScreen;

public class Ranero extends Game {
	public SpriteBatch batch;
	public BitmapFont fontBig;
	public BitmapFont fontMedium;
	FreeTypeFontGenerator generator;
	FreeTypeFontGenerator.FreeTypeFontParameter parameter;


	public RanaJugador ranaJugador1 = new RanaJugador();
	public RanaJugador ranaJugador2 = new RanaJugador();

	@Override
	public void create() {
		batch = new SpriteBatch();

		generator = new FreeTypeFontGenerator(Gdx.files.internal("ComicRelief.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameter.size = 100;

		fontBig = generator.generateFont(parameter);
		fontBig.setColor(Color.BLACK);

		parameter.size = 40;
		fontMedium = generator.generateFont(parameter);
		fontMedium.setColor(Color.BLACK);
		// Ahora utiliza freetype font = new BitmapFont();	//Use LibGDX's default Arial font.
		this.setScreen(new MenuScreen(this)); // fuente en el constructor para mayor facilidad en caso de cambiar la fuente.
		MovimientoJugador inputProcessor = new MovimientoJugador();
		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		fontBig.dispose();
		fontMedium.dispose();
		generator.dispose();
	}
	/*


	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	*/
}

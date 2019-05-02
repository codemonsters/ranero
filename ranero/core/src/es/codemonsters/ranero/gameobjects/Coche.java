package es.codemonsters.ranero.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import es.codemonsters.ranero.gameobjects.GameObject;

public class Coche extends GameObject {

    private TextureRegion regionTextura;

    public Coche(TextureRegion regionTextura) {
        this.regionTextura = regionTextura;
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(regionTextura, getX(), getY());
        Gdx.app.debug("Coche.draw", "Dibujando coche!");
    }

    @Override
    public void dispose() {
    }
}

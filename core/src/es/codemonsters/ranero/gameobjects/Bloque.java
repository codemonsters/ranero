package es.codemonsters.ranero.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

public class Bloque extends GameObject {
    private Item item;
    private TextureRegion regionTextura;
    private World world;
    float h, w;


    public Bloque(TextureRegion regionTextura, float x, float y, float h, float w, World world) {
        this.regionTextura = regionTextura;
        this.world = world;
        this.h = h;
        this.w = w;
        setPosition(x, y);
        setSize(regionTextura.getRegionWidth(), regionTextura.getRegionHeight());
        item = world.add(new Item<GameObject>(this), getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(regionTextura, getX(), getY(),w, h);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void dispose() {

    }
}

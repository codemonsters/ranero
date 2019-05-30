package es.codemonsters.ranero.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

import es.codemonsters.ranero.gameobjects.GameObject;

public class Coche extends GameObject {

    private Item item;
    private TextureRegion regionTextura;
    private World world;


    public Coche (TextureRegion regionTextura, float x, float y, float h, float w, World world) {
        this.regionTextura = regionTextura;
        this.world = world;
        setPosition(x,y);
        setSize(regionTextura.getRegionWidth(), regionTextura.getRegionHeight());
        item = world.add(new Item<GameObject>(this), getX(), getY(), getWidth(), getHeight());


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(regionTextura, getX(), getY());
    }


    //No se usa
    /*
    //@Override
    public void update() {


    }*/

    @Override
    public void dispose() {
    }
}

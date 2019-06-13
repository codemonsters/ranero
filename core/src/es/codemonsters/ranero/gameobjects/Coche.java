package es.codemonsters.ranero.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dongbat.jbump.Collision;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.World;

import es.codemonsters.ranero.gameobjects.GameObject;
import es.codemonsters.ranero.screens.GameScreen;

public class Coche extends GameObject {

    private Item item;
    private TextureRegion regionTextura;
    private World world;

    float h, w;
    public Coche (TextureRegion regionTextura, float x, float y, float h, float w, World world) {
        this.regionTextura = regionTextura;
        this.world = world;
        this.h = h;
        this.w = w;
        setPosition(x,y);
        setSize(regionTextura.getRegionWidth(), regionTextura.getRegionHeight());
        item = world.add(new Item<GameObject>(this), getX(), getY(), w, h);


    }

    @Override
    public void act(float dt) {
        this.setPosition(this.getX(), this.getY() + 100 * dt);
        if (this.getY() >= GameScreen.ALTO_DEL_MUNDO + 16) this.setPosition(this.getX(), -16);
        Response.Result result = world.move(item, this.getX(), this.getY(), CollisionFilter.defaultFilter);
        Collisions col = result.projectedCollisions;
        // FIXME No funcionan las colisiones (tontito)
        for(int i = 0; i > col.size(); i++)
        {
            Collision collision = col.get(i);
            Gdx.app.debug("Colisiones", "Hola " + String.valueOf(collision.other.userData));
            if(collision.other.userData instanceof Player)
            {
                Gdx.app.debug("Colisiones", "Accidente de coche");
            }
        }

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

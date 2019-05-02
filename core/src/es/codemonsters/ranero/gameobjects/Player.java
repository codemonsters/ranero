package es.codemonsters.ranero.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

public class Player extends GameObject {

    private static final float VELX = 200;
    private static final float VELY = VELX; //constantes de velocidad

    private TextureRegion regionTextura;
    private boolean quiereMoverseIzquierda, quiereMoverseDerecha, quiereMoverseArriba, quiereMoverseAbajo;

    private World world;
    private Item<GameObject> item;


    public Player(TextureRegion regionTextura, float x, float y, World world) {
        this.regionTextura = regionTextura;
        this.world = world;
        setPosition(x, y);
        setSize(regionTextura.getRegionWidth(), regionTextura.getRegionHeight());
        item = world.add(new Item<GameObject>(this), getX(), getY(), getWidth(), getHeight());
    }

    public void left(boolean value) {
        this.quiereMoverseIzquierda = value;
    }

    public void right(boolean value) {
        this.quiereMoverseDerecha = value;
    }

    public void up(boolean value) {
        this.quiereMoverseArriba = value;
    }

    public void down(boolean value) {
        this.quiereMoverseAbajo = value;
    }

    @Override
    public void act(float dt) {
        float targetX = this.getX();
        float targetY = this.getY();
        if (quiereMoverseIzquierda && !quiereMoverseDerecha) {

            targetX -= dt * VELX;
            Gdx.app.debug("AQUI", this.getX() + "--> " + targetX);
            //this.setX(this.getX() - dt * VELX);
        } else if (quiereMoverseDerecha && !quiereMoverseIzquierda) {
            this.setX(this.getX() + dt * VELX);
        } else if (quiereMoverseArriba && !quiereMoverseAbajo) {
            this.setY(this.getY() + dt * VELY);
        } else if (quiereMoverseAbajo && !quiereMoverseArriba) {
            this.setY(this.getY() - dt * VELY);
        }
        world.move(item, targetX, targetY, CollisionFilter.defaultFilter);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(regionTextura, getX(), getY());
     }

    @Override
    public void dispose() {
        /*
        for(Texture textura : texturas) {
            textura.dispose();
        }
        */
    }

}

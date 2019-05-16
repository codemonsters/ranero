package es.codemonsters.ranero.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
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
        float targetX = 0;
        float targetY = 0;
        if (quiereMoverseIzquierda && !quiereMoverseDerecha) {
            targetX += -VELX;
        } else if (quiereMoverseDerecha && !quiereMoverseIzquierda) {
            targetX += VELX;
        } else if (quiereMoverseArriba && !quiereMoverseAbajo) {
            targetY += VELY;
        } else if (quiereMoverseAbajo && !quiereMoverseArriba) {
            targetY += -VELY;

        }
        Response.Result result = world.move(item, targetX * dt + this.getX(), targetY * dt + this.getY(), CollisionFilter.defaultFilter);
        this.setPosition(result.goalX, result.goalY);
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



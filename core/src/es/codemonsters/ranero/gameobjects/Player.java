package es.codemonsters.ranero.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends GameObject {

    private static final float VELX = 200;
    private static final float VELY = VELX; //constantes de velocidad

    private TextureRegion regionTextura;
    private boolean quiereMoverseIzquierda, quiereMoverseDerecha, quiereMoverseArriba, quiereMoverseAbajo;


    public Player(TextureRegion regionTextura) {
        this.regionTextura = regionTextura;
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
        if (quiereMoverseIzquierda && !quiereMoverseDerecha) {
            this.setX(this.getX() - dt * VELX);
        } else if (quiereMoverseDerecha && !quiereMoverseIzquierda) {
            this.setX(this.getX() + dt * VELX);
        } else if (quiereMoverseArriba && !quiereMoverseAbajo) {
            this.setY(this.getY() + dt * VELY);
        } else if (quiereMoverseAbajo && !quiereMoverseArriba) {
            this.setY(this.getY() - dt * VELY);
        }
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

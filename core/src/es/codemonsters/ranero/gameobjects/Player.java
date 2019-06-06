package es.codemonsters.ranero.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.World;

public class Player extends GameObject {
    public static final int ancho = 12;
    public static final int alto = 12;
    public static enum Direcciones {
        ARRIBA,
        DERECHA,
        ABAJO,
        IZQUIERDA
    }
    private static final float VELX = 100;
    private static final float VELY = VELX; //constantes de velocidad
    private Array<TextureRegion> regionesTextura;
    private boolean quiereMoverseIzquierda, quiereMoverseDerecha, quiereMoverseArriba, quiereMoverseAbajo;
    private Direcciones direccion;
    private World world;
    private Item<GameObject> item;
    private int numRegionTexturaActual;
    private float tiempoMostrandoLaMismaTextura;

    public Player(float x, float y, Direcciones direccion, Array<TextureRegion> regionesTextura, World world) {
        this.regionesTextura = regionesTextura;
        this.world = world;
        this.direccion = direccion;
        numRegionTexturaActual = 0;
        setPosition(x, y);
        setSize(ancho, alto);
        item = world.add(new Item<GameObject>(this), getX(), getY(), ancho, alto);
        tiempoMostrandoLaMismaTextura = 0;
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
        tiempoMostrandoLaMismaTextura += dt;
        float targetX = 0;
        float targetY = 0;
        boolean quiereMoverse = false;
        if (quiereMoverseIzquierda && !quiereMoverseDerecha) {
            targetX -= VELX;
            quiereMoverse = true;
            direccion = Direcciones.IZQUIERDA;
        } else if (quiereMoverseDerecha && !quiereMoverseIzquierda) {
            targetX += VELX;
            quiereMoverse = true;
            direccion = Direcciones.DERECHA;
        } else if (quiereMoverseArriba && !quiereMoverseAbajo) {
            targetY += VELY;
            quiereMoverse = true;
            direccion = Direcciones.ARRIBA;
        } else if (quiereMoverseAbajo && !quiereMoverseArriba) {
            targetY -= VELY;
            quiereMoverse = true;
            direccion = Direcciones.ABAJO;
        }
        Response.Result result = world.move(item, this.getX() + targetX * dt, this.getY() + targetY * dt, CollisionFilter.defaultFilter);
        this.setPosition(result.goalX, result.goalY);

        // Actualizamos la región que utilizamos para dibujar la rana
        if (quiereMoverse) {
            if (tiempoMostrandoLaMismaTextura > 0.1) {
                tiempoMostrandoLaMismaTextura = 0;
                numRegionTexturaActual++;
                if (numRegionTexturaActual >= regionesTextura.size) {
                    numRegionTexturaActual = 0;
                }
            }
        } else {
            numRegionTexturaActual = 0;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // TODO: rotarla si procede
        // TODO: dibujarla desplazándola correctamente respecto al cuerpo
        float anguloRotacion = 0;
        if (direccion == Direcciones.DERECHA) {
            anguloRotacion = -90;
        } else if (direccion == Direcciones.ARRIBA){
            anguloRotacion = 0;
        }
        else if (direccion == Direcciones.IZQUIERDA){
        anguloRotacion = 90;
        }
        else if (direccion == Direcciones.ABAJO){
        anguloRotacion = 180;
        }
        batch.draw(regionesTextura.get(numRegionTexturaActual),getX(), getY(), regionesTextura.get(numRegionTexturaActual).getRegionWidth()/2,regionesTextura.get(numRegionTexturaActual).getRegionHeight()/2, regionesTextura.get(numRegionTexturaActual).getRegionWidth(), regionesTextura.get(numRegionTexturaActual).getRegionHeight(), 1f,1f, anguloRotacion);
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



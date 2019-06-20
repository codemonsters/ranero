package es.codemonsters.ranero.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.dongbat.jbump.Collision;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.World;

public class Player extends GameObject {
    private static final int ancho = 12;
    private static final int alto = 12;

    public enum Direcciones {
        ARRIBA(0),
        DERECHA(-90),
        ABAJO(180),
        IZQUIERDA(90);
        public int angulo;
        Direcciones(int angulo)
        {
            this.angulo = angulo;
        }
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

        // Actualizamos la región que utilizamos para dibujar la rana
        if (quiereMoverse) {
            Response.Result result = world.move(item, this.getX() + targetX * dt, this.getY() + targetY * dt, CollisionFilter.defaultFilter);
            Collisions col = result.projectedCollisions;
            for(int i = 0; i < col.size(); i++)
            {
                Collision collision = col.get(i);
                if(collision.other.userData instanceof Coche)
                {
                    Gdx.app.debug("Colisiones", "Accidente de coche");
                }
            }
            this.setPosition(result.goalX, result.goalY);
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
        batch.draw(regionesTextura.get(numRegionTexturaActual),getX(), getY(), regionesTextura.get(numRegionTexturaActual).getRegionWidth()/2,regionesTextura.get(numRegionTexturaActual).getRegionHeight()/2, regionesTextura.get(numRegionTexturaActual).getRegionWidth(), regionesTextura.get(numRegionTexturaActual).getRegionHeight(), 1f,1f, direccion.angulo);
        // TODO: Dibujar un rectángulo para tener una referencia visual de los bordes del cuerpo durante las colisiones
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



package yoshi;

public abstract class Enemigo extends ElementoBasico{

	public Enemigo(int posicionX, int posicionY, int velocidadX, int velocidadY, int ancho, int largo) {
        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo);
    }
	
	public void rebotarEnEjeX() {
        velocidadX = -velocidadX;
    }

    public void rebotarEnEjeY() {
        velocidadY = -velocidadY;
    } 
}

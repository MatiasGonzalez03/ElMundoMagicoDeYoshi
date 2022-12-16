package yoshi;

import java.awt.Rectangle;

public abstract class ElementoBasico implements Elemento {
	private int ancho;
	private int largo;
	protected int posicionX;
	protected int posicionY;
	protected int velocidadX;
	protected int velocidadY;
	public Rectangle solidArea;

	
	public ElementoBasico(int posicionX, int posicionY, int velocidadX, int velocidadY, int ancho, int largo) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.ancho = ancho;
        this.largo = largo;
    }
	
	public int getAncho() {
		return ancho;
	}

	public int getLargo() {
		return largo;
	}

	@Override
	public int getPosicionX() {
		return posicionX;
	}

	@Override
	public int getPosicionY() {
		return posicionY;
	}

	@Override
	public int getVelocidadX() {
		return velocidadX;
	}

	@Override
	public int getVelocidadY() {
		return velocidadY;
	}

	@Override
	public void moverse() {
		posicionX = posicionX + velocidadX;
		posicionY = posicionY + velocidadY;
	}

	@Override
	public boolean hayColision(Elemento elemento) {
		if (Utilidades.hayColision(
				this.getPosicionX(),
				this.getPosicionY(),
				this.getAncho(),
				this.getLargo(),
				elemento.getPosicionX(),
				elemento.getPosicionY(),
				elemento.getAncho(),
				elemento.getLargo())) {
				return true;
	        } else {
	        	return false;
	        }
    }
	
	public boolean hayColisionEnY(Elemento elemento) {
        if (Utilidades.verificarColisionEnEjeY(
            this.getPosicionX(),
            this.getPosicionY(),
            this.getAncho(),
            this.getLargo(),
            elemento.getPosicionX(),
            elemento.getPosicionY(),
            elemento.getAncho(),
            elemento.getLargo())) {
                return true;
        } else {
            return false;
        }
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

}

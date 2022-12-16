package yoshi;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Yoshi extends ElementoBasico{
	private BufferedImage img;
	private BufferedImage imgWalk1;
	private BufferedImage imgWalk2;
	private BufferedImage imgWalk3;
	private BufferedImage imgWalk1izq;
	private BufferedImage imgWalk2izq;
	private BufferedImage imgWalk3izq;
	private int contador;



	public Yoshi(int posicionX, int posicionY, int velocidadX, int velocidadY, int ancho, int largo) {
		super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo);
		
		String path = Utilidades.obtenerRuta("imagenes/yoshi.png");
        String pathWalk1 = Utilidades.obtenerRuta("imagenes/walk1.png");
        String pathWalk2 = Utilidades.obtenerRuta("imagenes/walk2.png");
        String pathWalk3 = Utilidades.obtenerRuta("imagenes/walk3.png");
        String pathWalk1izq = Utilidades.obtenerRuta("imagenes/walk1izq.png");
        String pathWalk2izq = Utilidades.obtenerRuta("imagenes/walk2izq.png");
        String pathWalk3izq = Utilidades.obtenerRuta("imagenes/walk3izq.png");
        try {
        	img = ImageIO.read(new File(path));
        	imgWalk1 = ImageIO.read(new File(pathWalk1));
        	imgWalk2 = ImageIO.read(new File(pathWalk2));
        	imgWalk3 = ImageIO.read(new File(pathWalk3));
        	imgWalk1izq = ImageIO.read(new File(pathWalk1izq));
        	imgWalk2izq = ImageIO.read(new File(pathWalk2izq));
        	imgWalk3izq = ImageIO.read(new File(pathWalk3izq));
        } catch (IOException e) {
        	System.out.println ("No se encontro la imagen");
            throw new RuntimeException(e);
        }
	}
	@Override
	public void dibujarse(Graphics graphics) {
		try {
            if (getVelocidadX() == 0 && getVelocidadY() == 0 ) {
            	graphics.drawImage(img, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
            } else {
            	contador++;
                if (contador == 30) {
               	 contador = 0;
                }
                
                if (0 <= contador && contador < 10) {
                    if (getVelocidadX() == -1) {
                    	graphics.drawImage(imgWalk1izq, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                    }else if (getVelocidadX() == -2) {
                    	graphics.drawImage(imgWalk1izq, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                    } else {
                    	graphics.drawImage(imgWalk1, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                    }	
                }
                if (10 <= contador  && contador < 20) {
                	if (getVelocidadX() == -1) {
                		graphics.drawImage(imgWalk2izq, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                	}else if (getVelocidadX() == -2) {
                    	graphics.drawImage(imgWalk2izq, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                	} else {
                		graphics.drawImage(imgWalk2, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                	}
                }
               
                if (20 <= contador  && contador < 30) {
                	if (getVelocidadX() == -1) {
                		graphics.drawImage(imgWalk3izq, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                	}else if (getVelocidadX() == -2) {
                    	graphics.drawImage(imgWalk3izq, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                	} else {
                		graphics.drawImage(imgWalk3, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
                	}
                }
            }
        	
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
	}
	
	
	public void frenarEnEjeXPorDerecha(ElementoBasico elemento) {
    	setPosicionX(elemento.getPosicionX()- getAncho()-1);
    }
	
	 public void frenarEnEjeYPorAbajo(ElementoBasico elemento) {
	    	setPosicionY(elemento.getPosicionY()- getLargo()-1);
	 }
	    
	    public void frenarEnEjeXPorIzquierda(ElementoBasico elemento) {
	    	setPosicionX(elemento.getPosicionX()+elemento.getAncho()+1);
	 }

	 public void frenarEnEjeYPorArriba(ElementoBasico elemento) {
	    	setPosicionY(elemento.getPosicionY()+elemento.getLargo()+1);
	 }
	
	
	public void moverse() {
		posicionX = posicionX + velocidadX;
		posicionY = posicionY + velocidadY;
	}
	
	public void morir() {
			setPosicionY(600);
			setPosicionX(50);
		

	}
	
}
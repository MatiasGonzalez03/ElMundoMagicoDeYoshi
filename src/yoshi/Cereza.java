package yoshi;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Cereza extends ElementoBasico {

	private BufferedImage img;

	public Cereza(int posicionX, int posicionY, int velocidadX, int velocidadY, int ancho, int largo) {
		super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo);
		try {
			String path = Paths.get(Cereza.class.getClassLoader().getResource("imagenes/cereza.png").toURI()).toString();
			this.img = ImageIO.read(new File(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dibujarse(Graphics g) {
		try {
			g.drawImage(img, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
		} catch (Exception e1) {
	            throw new RuntimeException(e1);
	    }
		
	}

}

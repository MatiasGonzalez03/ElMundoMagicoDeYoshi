package yoshi;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;


import javax.imageio.ImageIO;


	public class Mapa {

		static int[][] pantalla = new int [50][50];
		public static int fila = 0; 
		public static int columna = 0;
		private static int numeroFilas = 14; 
		private static int numeroColumnas = 23;
		private static int anchoBloque = 50;
		private static int largoBloque = 50;
		private BufferedImage bloque;
		private BufferedImage pasto;
		
		
		public Mapa() {
			cargarImagenes();
		}
		

		public void dibujarse (Graphics g) {
			for (fila = 0; fila<numeroFilas; fila++) {
				for (columna = 0; columna<numeroColumnas; columna++) {
					// FONDO
					if (obtenerNivel()[fila][columna]== 0) {
						g.drawImage(pasto, columna*50, fila*50, anchoBloque, largoBloque, null);
					}
					// MADERA
					if (obtenerNivel()[fila][columna]== 1) {
						// x y widht height null
						g.drawImage(bloque, columna*50, fila*50, anchoBloque, largoBloque, null);
					}
				}
			}
		}
		
	public void cargarImagenes() { 
		try {
			String path = Paths.get(Mapa.class.getClassLoader().getResource("imagenes/block.png").toURI()).toString();
			this.bloque = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println ("No se encontro la imagen");
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		try {
			String path1 = Paths.get(Mapa.class.getClassLoader().getResource("imagenes/pasto.png").toURI()).toString();
			this.pasto = ImageIO.read(new File(path1));
		} catch (IOException e) {
			System.out.println ("No se encontro la imagen");
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public static int[][] obtenerNivel () {
		if (Juego.getPantallaActual() == 1) {
			pantalla = pantallaActual;
		}
		return pantalla;
	}
	
	public static int pantallaActual [][] = 
		{	{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
				{ 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
				{ 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
				{ 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1},
				{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
				{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
				{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1},
				{ 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
				{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
				{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				   
		};

}
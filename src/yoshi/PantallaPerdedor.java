package yoshi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PantallaPerdedor extends Pantalla {
	private int llaves;
	
	public PantallaPerdedor(int ancho, int largo, String resource, int llaves) {
		super(ancho, largo, resource);
		this.llaves = llaves;
	}

	public void dibujarse(Graphics g) {
		super.dibujarse(g);
		mostrarMensaje(g);
		esperar(10);
	}
	
	private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

	public void mostrarMensaje(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Arial black", 30, 50));
        if (llaves > 0) {
        	g.drawString("Recolectaste: " + llaves + " llave", 80, 500); 
        }else {
            g.drawString("No recolectaste llaves :(", 80, 500);
        }
    }
}

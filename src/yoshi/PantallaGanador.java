package yoshi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PantallaGanador extends Pantalla {
	
	private int monedas;

	public PantallaGanador(int ancho, int largo, String resource, int monedas) {
		super(ancho, largo, resource);
		this.monedas = monedas;
		
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
        g.drawString("Recolectaste " + monedas + " monedas", 80, 500); 
    }
	
}
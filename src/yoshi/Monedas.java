package yoshi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Monedas implements Dibujable{
	
	private int posicionX;
    private int posicionY;
    private Font font;
    private Color color;
    private int Monedas;
    

    public Monedas(int posicionX, int posicionY, Font font, Color color, int monedas) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.font = font;
        this.color = color;
        this.Monedas = monedas;
    }

    public void dibujarse(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString("Monedas: " + String.valueOf(Monedas), posicionX, posicionY);
    }
	

    public void ganarMonedas() {
    	Monedas++;
    }
    
    public void reiniciarContadorDeMonedas() {
    	Monedas = 0;
    }

    public int getMonedas() {
    	return Monedas;
    }

}

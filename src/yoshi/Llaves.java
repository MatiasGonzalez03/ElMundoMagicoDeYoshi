package yoshi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Llaves implements Dibujable{
	
	private int posicionX;
    private int posicionY;
    private Font font;
    private Color color;
    private int Llaves;
    

    public Llaves(int posicionX, int posicionY, Font font, Color color, int llaves) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.font = font;
        this.color = color;
        this.Llaves = llaves;
    }

    public void dibujarse(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString("Llaves: " + String.valueOf(Llaves), posicionX, posicionY);
    }
	

    public void ganarLlaves() {
    	Llaves++;
    }
    
    public void reiniciarContadorDeLlaves() {
    	Llaves = 0;
    }

    public int getLlaves() {
    	return Llaves;
    }

}
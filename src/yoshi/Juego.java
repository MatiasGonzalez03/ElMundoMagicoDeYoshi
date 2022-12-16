package yoshi;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import javax.swing.JPanel;

public class Juego extends JPanel implements KeyListener, Runnable {
	private final static int PANTALLA_INICIO = 1;
	private final static int PANTALLA_JUEGO = 2;
	private final static int PANTALLA_PERDEDOR = 3;
	private final static int PANTALLA_GANADOR = 4;
	private static final long serialVersionUID = 1L;
	private static int pantallaActual;
	private int anchoJuego;
	private int largoJuego;
	private Puerta puerta;
	private Manzana manzana;
	private Cereza cereza;
	public Yoshi yoshi;
	private Pantalla pantallaInicio;
	private Pantalla pantallaGanaste;
	private Pantalla pantallaPerdiste;
    private Vidas vidas;
    private Sonidos sonidos;
    private Font fuente;
    private Llaves llavesTexto;
    private Llave llaves;
    private Llave llaves2;
    private Llave llaves3;
    private Monedas monedasTexto;
    private Moneda monedas;
    private Moneda monedas2;
    private Moneda monedas3;
    private List<Enemigo> enemigos;
    private boolean colision = false;
    Mapa pantallas = new Mapa();
	private List<Roca> rocas;




    public Juego(int anchoJuego, int largoJuego, int vidas, int cantidadLlaves, int cantidadMonedas) {
		Juego.pantallaActual = PANTALLA_INICIO;
		this.anchoJuego = anchoJuego;
		this.largoJuego = largoJuego;	
        this.rocas = new ArrayList<Roca>();
		this.enemigos = new ArrayList<Enemigo>();
		this.pantallaInicio = new PantallaInicio(anchoJuego, largoJuego, "imagenes/pantallaInicio.png");
        this.llavesTexto = new Llaves(50, 680, fuente, Color.WHITE, cantidadLlaves);
        this.monedasTexto = new Monedas(50, 300, fuente, Color.WHITE, cantidadMonedas);
		cargarSonidos();
		sonidos.repetirSonido("background");
		inicializarJuego();
	}
	
	public void run() {
		while (true) {
			
			if (pantallaActual == PANTALLA_JUEGO) {
				actualizarJuego();
			}
			
			dibujarJuego();
			esperar(10);
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension (anchoJuego, largoJuego);
	}
	
	private void inicializarJuego(){
		llavesTexto.reiniciarContadorDeLlaves();
		monedasTexto.reiniciarContadorDeMonedas();
		this.enemigos.clear();
		this.rocas.clear();
		this.pantallaPerdiste = null;
		this.pantallaGanaste = null;
		this.yoshi = new Yoshi(50, 600, 0, 0, 50, 50);
		this.puerta = new Puerta(1095, 60, 0, 0, 60, 60);
		this.monedas = new Moneda(615,475, 0, 0, 30, 20);
		this.monedas2 = new Moneda(1030,205, 0, 0, 30, 20);
		this.monedas3 = new Moneda(300,80, 0, 0, 30, 20);
		this.llaves = new Llave(75,80, 0, 0, 30, 20);
		this.llaves2 = new Llave(860,395, 0, 0, 30, 20);
		this.llaves3 = new Llave(710,120, 0, 0, 30, 20);
		this.manzana = new Manzana(new Random().nextInt(750),new Random().nextInt(550), 0, 0, 30, 20);
		this.cereza = new Cereza(new Random().nextInt(750),new Random().nextInt(550), 0, 0, 30, 20);
        this.vidas = new Vidas(1000, 680, new Font("Arial Black", 10, 20), Color.cyan, 3);
        agregarRocas();
        agregarEnemigos();
        colision = false;
	}
	
	public void agregarEnemigos() {
   				agregarEnemigo(new Goomba(600, 460, 2, 0, 30, 20));
            	agregarEnemigo(new Koopa(100, 320, 1, 0, 30, 20));
            	agregarEnemigo(new Goomba(200, 90, 2, 0, 30, 20));
            	agregarEnemigo(new Goomba(480, 60, 2, 0, 30, 20));
            	agregarEnemigo(new Goomba(465, 60, 0, 2, 30, 20));
            	agregarEnemigo(new Koopa(350, 520, 1, 0, 30, 20));
            	agregarEnemigo(new Koopa(600, 320, 1, 0, 30, 20));
            	agregarEnemigo(new Koopa(1030, 290, 1, 0, 30, 20));
            	agregarEnemigo(new Goomba(900, 630, 2, 0, 30, 20));
            	agregarEnemigo(new Koopa(620, 590, 0, 1, 30, 20));
            	agregarEnemigo(new Koopa(1030, 460, 1, 0, 30, 20));
            	agregarEnemigo(new Goomba(1030, 320, 0, 2, 30, 20));
            	agregarEnemigo(new Koopa(915, 590, 0, 1, 30, 20));
            	agregarEnemigo(new Koopa(915, 400, 0, 1, 30, 20));
            	agregarEnemigo(new Koopa(1020, 90, 0, 1, 30, 20));
            	agregarEnemigo(new Koopa(770, 390, 1, 0, 30, 20));
            	agregarEnemigo(new Goomba(770, 250, 0, 2, 30, 20));
            	agregarEnemigo(new Goomba(820, 90, 0, 2, 30, 20));
            	agregarEnemigo(new Goomba(870, 90, 0, 2, 30, 20));
            	agregarEnemigo(new Goomba(920, 90, 0, 2, 30, 20));

            	
	}


	public void agregarEnemigo(Enemigo enemigo) {
		this.enemigos.add(enemigo);
	}

	private void cargarSonidos() {
        try {
            sonidos = new Sonidos();
            sonidos.agregarSonido("background", "sonidos/background.wav");
            sonidos.agregarSonido("comer", "sonidos/comer.wav");
            sonidos.agregarSonido("llave", "sonidos/llave.wav");
            sonidos.agregarSonido("moneda", "sonidos/moneda.wav");
            sonidos.agregarSonido("muerte", "sonidos/muerte.wav");
            sonidos.agregarSonido("ganaste", "sonidos/ganaste.wav");
            sonidos.agregarSonido("perdiste", "sonidos/perdiste.wav");
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }
	
	protected void paintComponent (Graphics g) {
		super.paintComponent(g);
		if (pantallaActual == PANTALLA_INICIO) {
			pantallaInicio.dibujarse(g);
        }if (pantallaActual == PANTALLA_JUEGO) {
        	dibujar(g);
        }if(pantallaActual == PANTALLA_PERDEDOR) {
        	if (this.pantallaPerdiste == null) {
        		this.pantallaPerdiste = new PantallaPerdedor(anchoJuego, largoJuego, "imagenes/pantallaPerdiste.png", this.llavesTexto.getLlaves());
        		
        	}
        	pantallaPerdiste.dibujarse(g);
        }if(pantallaActual == PANTALLA_GANADOR) {
        	if(this.pantallaGanaste == null) {
        		this.pantallaGanaste = new PantallaGanador(anchoJuego, largoJuego, "imagenes/pantallaGanaste.png", this.monedasTexto.getMonedas());
        	}
        	pantallaGanaste.dibujarse(g);
        }
        
	}
	
	public void dibujar(Graphics g) {
		pantallas.dibujarse(g);
		yoshi.dibujarse(g);
		manzana.dibujarse(g);
		cereza.dibujarse(g);
		puerta.dibujarse(g);
		vidas.dibujarse(g);
		llavesTexto.dibujarse(g);
		llaves.dibujarse(g);
		llaves2.dibujarse(g);
		llaves3.dibujarse(g);
		monedas.dibujarse(g);
		monedas2.dibujarse(g);
		monedas3.dibujarse(g);
        dibujarEnemigos(g);

	}
	
	
	public void agregarRocas() {
		agregaRoca(new Roca(0,0,0,0,40,700));
		agregaRoca(new Roca(0,0,0,0,1150,45));
		agregaRoca(new Roca(1115,160,0,0,40,700));
		agregaRoca(new Roca(0,660,0,0,1100,1));
		agregaRoca(new Roca(263,350,0,0,25,600));
		agregaRoca(new Roca(360,0,0,0,30,200));
		agregaRoca(new Roca(160,160,0,0,230,40));
		agregaRoca(new Roca(160,310,0,0,378,40));
		agregaRoca(new Roca(660,110,0,0,30,440));
		agregaRoca(new Roca(510,160,0,0,30,190));
		agregaRoca(new Roca(760,0,0,0,30,200));
		agregaRoca(new Roca(410,510,0,0,280,40));
		agregaRoca(new Roca(960,160,0,0,280,40));
		agregaRoca(new Roca(960,160,0,0,30,190));
		agregaRoca(new Roca(810,310,0,0,180,40));
		agregaRoca(new Roca(810,460,0,0,180,40));
		agregaRoca(new Roca(810,380,0,0,30,110));
		agregaRoca(new Roca(960,510,0,0,30,40));


	}
	
	public void agregaRoca(Roca rocas) {
		this.rocas.add(rocas);
	}

	private void dibujarJuego() {
		this.repaint();
	}

	public void agregarLlaves() {
		this.llaves = new Llave(1500,1500, 0, 0, 20, 10);
    }
	public void agregarLlaves2() {
		this.llaves2 = new Llave(1500,1500, 0, 0, 20, 10);
    }
	public void agregarLlaves3() {
		this.llaves3 = new Llave(1500,1500, 0, 0, 20, 10);
    }
	
	public void agregarMonedas() {
		this.monedas = new Moneda(1500,1500, 0, 0, 20, 10);
    }
	public void agregarMonedas2() {
		this.monedas2 = new Moneda(1500,1500, 0, 0, 20, 10);
    }
	public void agregarMonedas3() {
		this.monedas3 = new Moneda(1500,1500, 0, 0, 20, 10);
    }
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}	
	
	public void keyPressed(KeyEvent arg0) {
    	
    	if (pantallaActual == PANTALLA_INICIO && arg0.getKeyCode() == KeyEvent.VK_ENTER) {
            inicializarJuego();
            pantallaActual = PANTALLA_JUEGO;
        }

        if ((pantallaActual == PANTALLA_PERDEDOR && arg0.getKeyCode() == KeyEvent.VK_ENTER) || (pantallaActual == PANTALLA_GANADOR && arg0.getKeyCode() == KeyEvent.VK_ENTER)) {
        	pantallaActual = PANTALLA_INICIO;
        }
    	
        if (pantallaActual == PANTALLA_JUEGO) {
        	if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
        			yoshi.setVelocidadX(1);
        			if (colision == true) {
    		    	yoshi.setVelocidadX(2);
    		    	}
  

        	}

        	if (arg0.getKeyCode() == KeyEvent.VK_UP) {
        			yoshi.setVelocidadY(-1);
        			if (colision == true) {
        				yoshi.setVelocidadY(-2);
        			}
        	}

        	if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
        		yoshi.setVelocidadY(1);
        		if (colision == true) {
        			yoshi.setVelocidadY(2);
        		}
        		
        	}

        	if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
        		yoshi.setVelocidadX(-1);
        		if (colision == true) {
        			yoshi.setVelocidadX(-2);
        		}
        	}
       }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    	if (pantallaActual == PANTALLA_INICIO) {
            inicializarJuego();
            pantallaActual = PANTALLA_JUEGO;
        }
        if (pantallaActual == PANTALLA_PERDEDOR || pantallaActual == PANTALLA_GANADOR) {
            pantallaActual = PANTALLA_INICIO;
        }
        
        if (arg0.getKeyCode() == KeyEvent.VK_RIGHT || arg0.getKeyCode() == KeyEvent.VK_LEFT) {
            yoshi.setVelocidadX(0);
        }
        
        if (arg0.getKeyCode() == KeyEvent.VK_DOWN || arg0.getKeyCode() == KeyEvent.VK_UP) {
            yoshi.setVelocidadY(0);
        }
    }
	
	private void actualizarJuego() {
		verificarEstadoAmbiente();
		yoshi.moverse();
		moverEnemigos();
	}
	
	
	private void moverEnemigos() {
        for (Enemigo enemigo : enemigos) {
            enemigo.moverse();
        }
    }

    private void dibujarEnemigos(Graphics g) {
        for (Enemigo enemigo : enemigos) {
            enemigo.dibujarse(g);
        }
    }
	
	private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }
	
	private void verificarEstadoAmbiente() {
		   verificarColisionEntreEnemigoYBloque();
		   verificarColisionContraVentana();
		   verificarColisionEnemigosConVentana();
		   verificarColisionEntreYoshiYCereza();
		   verificarColisionEntreYoshiYManzana();
		   verificarColisionEntreLlaveyYoshi();
		   verificarColisionEntreMonedayYoshi();
		   verificarSolapamientoRoca();
		   verificarReboteEntreEnemigos();
		   verificarColisionEntreEnemigoYYoshi();
		   verificarFinDeJuego();
	   }
	
	private void verificarReboteEntreEnemigos() {
        for (Enemigo enemigo1 : enemigos) {
            for (Enemigo enemigo2 : enemigos) {
                if (enemigo1 != enemigo2 && enemigo1.hayColision(enemigo2)) {
                    enemigo1.rebotarEnEjeX();
                }
            }
        }
    }
	
	private void verificarColisionEntreMonedayYoshi() {
		if (monedas.hayColision(yoshi)) {
            monedasTexto.ganarMonedas();
        	agregarMonedas();
            sonidos.tocarSonido("moneda");
        } else if (monedas2.hayColision(yoshi)) {
            monedasTexto.ganarMonedas();
        	agregarMonedas2();
            sonidos.tocarSonido("moneda");
        } else if (monedas3.hayColision(yoshi)) {
            monedasTexto.ganarMonedas();
        	agregarMonedas3();
            sonidos.tocarSonido("moneda");
    }
		
	}
	
	
	private void verificarFinDeJuego() {
		if (vidas.getVidas() == 0) {
			esperar(1000);
            pantallaActual = PANTALLA_PERDEDOR;
            sonidos.tocarSonido("perdiste");
        }if(yoshi.hayColision(puerta)) {
        	if(llavesTexto.getLlaves() == 3) {
        		esperar(1000);
                pantallaActual = PANTALLA_GANADOR;
                sonidos.tocarSonido("ganaste");
        	}
        	else {
        		if (yoshi.hayColision(puerta)) {
            		if (yoshi.hayColisionEnY(puerta)) {
            			if (yoshi.getPosicionY() < puerta.getPosicionY()) {
            				yoshi.frenarEnEjeYPorAbajo(puerta);
                		} else {
                			yoshi.frenarEnEjeYPorArriba(puerta);
                		}
            		} else {
            			if (yoshi.getPosicionX() < puerta.getPosicionX()) {
            				yoshi.frenarEnEjeXPorDerecha(puerta);
                		} else {
                			yoshi.frenarEnEjeXPorIzquierda(puerta);
                		}
            		}
            	}
        	}
        }
            
   }
	
	private void verificarColisionEntreEnemigoYBloque() {
		Iterator<Roca> iterador = rocas.iterator();
    	while (iterador.hasNext()) {
    		Roca roca = iterador.next();
    		for (Enemigo enemigo : enemigos) {
        		if (enemigo.hayColision(roca)) {
        			if (enemigo.hayColisionEnY(roca)) {
        				enemigo.rebotarEnEjeY();
        			} else {
        				enemigo.rebotarEnEjeX();
        			}	
        		}
    		
    		}
    	}
    		
    }
	
	private void verificarSolapamientoRoca() {
    	Iterator<Roca> iterador = rocas.iterator();
    	while (iterador.hasNext()) {
    		Roca roca = iterador.next();
    		if (manzana.hayColision(roca)) {
  			   this.manzana = new Manzana(new Random().nextInt(750),new Random().nextInt(550), 0, 0, 30, 20);
    		}
    		if (cereza.hayColision(roca)) {
 			   this.cereza = new Cereza(new Random().nextInt(750),new Random().nextInt(550), 0, 0, 30, 20);
    		}
    		if (yoshi.hayColision(roca)) {
            	if (yoshi.hayColisionEnY(roca)) {
            		if (yoshi.getPosicionY() < roca.getPosicionY()) {
            			yoshi.frenarEnEjeYPorAbajo(roca);
                	} else {
                		yoshi.frenarEnEjeYPorArriba(roca);
                	}
            	} else {
            		if (yoshi.getPosicionX() < roca.getPosicionX()) {
            			yoshi.frenarEnEjeXPorDerecha(roca);
                	} else {
                		yoshi.frenarEnEjeXPorIzquierda(roca);
                	}
            	}
		}
		}
     }

	public void verificarColisionEntreYoshiYManzana() {
		   if (yoshi.hayColision(manzana)) {
			   this.manzana = new Manzana(1500,1500, 0, 0, 20, 10);
			   sonidos.tocarSonido("comer");
			   colision = true;
		   }
	   }
	
	private void verificarColisionEntreYoshiYCereza() {
		   if (yoshi.hayColision(cereza)) {
			   this.cereza = new Cereza(new Random().nextInt(750),new Random().nextInt(550), 0, 0, 30, 20);
			   sonidos.tocarSonido("comer");
			   if (vidas.getVidas() <5) {
			   vidas.ganarVida();
			   }
		   }
	   }
	
	private void verificarColisionEntreEnemigoYYoshi() {
        Iterator<Enemigo> iterador = enemigos.iterator();
        while (iterador.hasNext()) {
            Enemigo enemigo = iterador.next();
            if (enemigo.hayColision(yoshi)) {
                vidas.perderVida();
				yoshi.morir();
				sonidos.tocarSonido("muerte");
            }
        }
    }

	
	
	private void verificarColisionEnemigosConVentana() {
		for (Enemigo enemigo : enemigos) {
			if (enemigo.getPosicionX() >= anchoJuego) {
				enemigo.setPosicionX(1);
			}if (enemigo.getPosicionX() <= 0 - enemigo.getAncho()) {
				enemigo.setPosicionX(anchoJuego - 1);
			}
		}
	}
	
	private void verificarColisionContraVentana(){
		   if(yoshi.getPosicionX()>=1100) {
			   yoshi.setPosicionX(1100);
			}else if (yoshi.getPosicionX() <= 0) {
				yoshi.setPosicionX(0);
			}
		   if(yoshi.getPosicionY()>=900) {
			   yoshi.setPosicionY(900);
			} else if (yoshi.getPosicionY()<=0) {
				yoshi.setPosicionY(0);
			}
	   }
	
	
	
	private void verificarColisionEntreLlaveyYoshi() {
            if (llaves.hayColision(yoshi)) {
            	agregarLlaves();
                llavesTexto.ganarLlaves();
                sonidos.tocarSonido("llave");
            } else if (llaves2.hayColision(yoshi)) {
            	agregarLlaves2();
                llavesTexto.ganarLlaves();
                sonidos.tocarSonido("llave");
            } else if (llaves3.hayColision(yoshi)) {
            	agregarLlaves3();
                llavesTexto.ganarLlaves();
                sonidos.tocarSonido("llave");
        }
	}
	


	 
	 public static int getPantallaActual() {
		 if (pantallaActual == PANTALLA_JUEGO) {
			 return 1;
		 }
		return 0;
		}


}
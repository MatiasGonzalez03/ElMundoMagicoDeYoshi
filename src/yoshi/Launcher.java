package yoshi;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Launcher {
	

    public static void main(String[] args) {

        // Propiedades del Juego
        int anchoVentana = 1150;
        int largoVentana = 700;
        int vidas = 3;
        int cantidadLlaves = 0;
        int cantidadMonedas = 0;

        // Activar aceleracion de graficos en 2 dimensiones
        System.setProperty("sun.java2d.opengl", "true");

        // Crear un objeto de tipo JFrame que es la ventana donde va estar el juego
        JFrame ventana = new JFrame("El Mundo Magico de Yoshi");

        // Cerrar la aplicacion cuando el usuario hace click en la 'X'
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Mostrard la ventana
        ventana.setVisible(true);

        // Crear un "Jpanel" llamado Juego y agregarlo a la ventana
        Juego juego = new Juego(anchoVentana, largoVentana, vidas, cantidadLlaves, cantidadMonedas);

        // Agregar a la ventana el JComponent (Juego hereda de JComponent)
        ventana.add(juego);

        // Enviar los eventos recibidos de movimientos del teclado al juego
        ventana.addKeyListener(juego);

        // Achicar la ventana lo maximo posible para que entren los componentes
        ventana.pack();

        // Crear un thread y pasarle como parametro al juego que implementa la interfaz
        // "Runnable"
        Thread thread = new Thread(juego);

        // Arrancar el juego
        thread.start();

    }

}
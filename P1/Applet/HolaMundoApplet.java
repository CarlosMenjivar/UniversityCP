import java.applet.*;
import java.awt.*;

/**
 * La clase HolaMundo implementa un applet que
 * muestra en la salida estándar el texto "Hola Mundo".
 */

public class HolaMundoApplet extends Applet {
	public void paint(Graphics g) {
		g.drawString("Hola Mundo", 50, 25);	// Muestra "Hola Mundo"
	}
}

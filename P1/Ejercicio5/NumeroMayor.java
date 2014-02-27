/************************************************
 * Archivo P1/Ejercicio5/NumeroMayor.java       *
 * Código distribuido bajo licencia GPL V2      *
 *                                              *
 * Entorno:    vim                              *
 * Fecha:      26/02/2014                       *
 * Autor:      Benito Palacios Sánchez          *
 * Asignatura: Complementos de Programación     *
 * Práctica:   1                                *
 * Ejercicio:  05                               *
 *                                              *
 * Resumen:                                     *
 * Lee un conjunto de números de la entrada y   *
 * devuelve el mayor de entre ellos.            *
 ***********************************************/
import java.io.*;
import java.util.ArrayList;

// Devuelve el mayor de un conjunto de números dados por la entrada estándar
public class NumeroMayor {
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> datos  = new ArrayList<Integer>(100);	// Capacidad máxima es 100
		int mayor = 0;
		
		boolean fin = false;
		System.out.println("Introduzca hasta 100 números ('q' para salir):");
		while (datos.size() < 100 && !fin) {
			// Lee el número introducido
			System.out.print((datos.size() + 1) + ": ");
			String entrada = null;
			try {
				entrada = reader.readLine();
			} catch (IOException ex) {
				entrada = "q";	// En caso de error sale
			}
			
			if (entrada.equals("q")) {
				fin = true;
			} else {
				try {
					int num = Integer.parseInt(entrada);
					datos.add(num);
					// Inicializa o actualiza la variable para determinar el mayor
	                       		if (datos.size() == 1 || num > mayor)
        	                        	mayor = num;
				} catch (NumberFormatException ex) {
				}
			}
		}

		System.out.println();
		if (datos.size() == 0)
			System.out.println("¡No ha introducido ningún número!");
		else
			System.out.println("El número mayor es: " + mayor);
	}
}

/*
 * Práctica 1 - Ejercicio 5
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
import java.io.*;
import java.util.ArrayList;

/**
 * Clase principal del programa.
 * Lee un conjunto de números de la entrada y devuelve el mayor de ellos.
 *
 * @author  Benito Palacios Sánchez
 * @version 1.1, 04/03/2014
 */
public class NumeroMayor
{
	/**
	 * Método de entrada del programa.
	 * 
	 * @param args Argumentos pasado al programa. No usado.
	 */
	public static void main(String[] args)
	{	
		BufferedReader reader = new BufferedReader(
										new InputStreamReader(System.in));
		
		// Como no se sabe el número de elementos, uso una lista y reservo 100.
		ArrayList<Integer> datos = new ArrayList<Integer>(100);
		int mayor = 0;
		
		/* 
		   Hasta que no se introduzca 'q' o se hayan introducido 100 elementos,
		   continua leyendo y analizando los números introducidos.
		*/
		System.out.println("Introduzca hasta 100 números ('q' para salir):");
		boolean fin = false;
		while (datos.size() < 100 && !fin) {
			// Lee el número introducido
			System.out.print((datos.size() + 1) + ": ");
			String entrada = null;
			try {
				entrada = reader.readLine();
			} catch (IOException ex) {
				entrada = "q";	// En caso de error sale
			}
			
			// Si es 'q' termina el programa.
			if (entrada.equals("q")) {
				fin = true;
			} else {
				// Si no lo añade a la lista y comprueba si es mayor.
				try {
					int num = Integer.parseInt(entrada);
					datos.add(num);
					
					// Inicializa o actualiza valor del mayor número.
					if (datos.size() == 1 || num > mayor)
						mayor = num;
				} catch (NumberFormatException ex) {
				}
			}
		}

		// Muestra el resultado final
		System.out.println();
		if (datos.size() == 0)
			System.out.println("¡No ha introducido ningún número!");
		else
			System.out.println("El número mayor es: " + mayor);
	}
}

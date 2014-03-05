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
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Clase principal del programa.
 * Lee un conjunto de números de la entrada y devuelve el mayor de ellos.
 * Segunda implementación del ejercicio empleando vectores en lugar de listas.
 *
 * @author  Benito Palacios Sánchez
 * @version 1.1, 04/03/2014
 */
public class NumeroMayor2
{
	private static Scanner Coin = new Scanner(System.in);
	
	/**
	 * Método de entrada del programa.
	 * 
	 * @param args Argumentos pasado al programa. No usado.
	 */
	public static void main(String[] args)
	{		
		// Pide el número de elementos.
		int numElementos = leeNumero(
			"Introduzca el número de elementos a procesar (máximo 100): ");
		if (numElementos < 0) {
			System.out.println("¡Ha de ser positivo!");
			return;
		} else if (numElementos > 100) {
			System.out.println("¡Ha de ser menor o igual a 100!");
			return;
		}

		// Vector de elementos para almacenarlos.
		int[] elementos = new int[numElementos];
		int maximo = 0;
		
		// Itera para pedir cada elemento y comprueba si es máximo.
		for (int i = 0; i < numElementos; i++) {
			elementos[i] = leeNumero((i + 1) + ": ");
			if (i == 0 || elementos[i] > maximo)
				maximo = elementos[i];
		}

		// Muestra el resultado
		System.out.println();
		if (numElementos == 0)
			System.out.println("¡No hay elementos a ordenar!");
		else
			System.out.println("El elemento mayor es: " + maximo);
	}

	/*
	 * Lee un número de la entrada estándar.
	 * Si se produce un error continúa intentándolo.
	 */
	private static int leeNumero(String mensaje)
	{
		int resultado = 0;

		// Mientras no se haya introducido una cadena correcta.
		boolean valido = false;
		while (!valido) {
			System.out.print(mensaje);
			
			try {
				resultado = Coin.nextInt();
				valido = true;
			} catch (InputMismatchException ex) {
				Coin.nextLine();	// Elimina esa línea del buffer.
			}	
		}

		return resultado;
	}
}

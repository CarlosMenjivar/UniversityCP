/*
 * Práctica 1 - Ejercicio 6
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
 * Lee un conjunto de números de la entrada y los invierte.
 *
 * @author  Benito Palacios Sánchez
 * @version 1.1, 05/03/2014
 */
public class InvierteVector
{
	private static Scanner Coin = new Scanner(System.in);

	/**
	 * Método de entrada del programa.
	 * 
	 * @param args Argumentos pasado al programa. No usado.
	 */
	public static void main(String[] args)
	{		
		// Pide el número de elementos
		int numElementos = leeNumero(
					"Introduzca el número de elementos a procesar (máximo 100): ");
		if (numElementos < 0) {
			System.out.println("¡Ha de ser positivo!");
			return;
		} else if (numElementos > 100) {
			System.out.println("¡Ha de ser menor o igual a 100!");
			return;
		}
		
		// Itera para pedir cada elemento
		int[] elementos = new int[numElementos];
		for (int i = 0; i < numElementos; i++)
			elementos[numElementos - 1 - i] = leeNumero((i + 1) + ": ");

		// Muestra los elementos invertidos
		System.out.println();
		System.out.println("Los elementos invertidos son:");
		for (int i = 0; i < numElementos; i++)
			System.out.print(elementos[i] + " ");
		System.out.println();
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

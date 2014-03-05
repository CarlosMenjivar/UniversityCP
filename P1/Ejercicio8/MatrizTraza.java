/*
 * Práctica 1 - Ejercicio 8
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
 * Lee una matriz de la entrada estándar y devuelve su traza.
 *
 * @author  Benito Palacios Sánchez
 * @version 1.1, 05/03/2014
 */
public class MatrizTraza
{
	private static Scanner Coin = new Scanner(System.in);

	/**
	 * Método de entrada del programa.
	 * 
	 * @param args Argumentos pasado al programa. No usado.
	 */
	public static void main(String[] args)
	{
		// Leo la matriz y calculo su traza.
		int[][] matriz = leeMatriz();
		int traza = calculaTraza(matriz);

		// Muestro el resultado
		System.out.println();
		System.out.println("La traza de la matriz es: " + traza);
	}

	/*
	 * Calcula la traza (suma de los elementos de la diagonal) de una matriz.
	 */
	private static int calculaTraza(int[][] matriz)
	{
		int traza = 0;
		for (int i = 0; i < matriz.length; i++)
			traza += matriz[i][i];
			
		return traza;
	}

	/*
	 * Lee una matriz desde la entrada estándar.
	 */
	private static int[][] leeMatriz()
	{		
		// Pide el número de filas y columnas (matriz cuadrada).
		int num = 0;
		do {
			num = leeNumero("Introduzca el número de filas y columnas: ");
			if (num <= 0)
				System.out.println("¡Ha de ser mayor que cero!");
		} while (num <= 0);

		int numFil = num;
		int numCol = num;
		
		// Lee la matriz
		System.out.println("Introduzca una fila por línea.");
		System.out.println("Separe los elementos mediante espacios.");
		System.out.println();
		
		int[][] matriz = new int[numFil][numCol];
		for (int f = 0; f < numFil; f++) 
			matriz[f] = leeVector("[" + f + "]: ", numCol);

		return matriz;
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

	/*
	 * Lee un vector de la longitud dada desde la entrada estándar.
	 * Si se produce un error continúa intentándolo.
	 */
	private static int[] leeVector(String mensaje, int longitud)
	{
		int[] vector = new int[longitud];

		// Mientras que no obtengas un vector válido (sin excepciones)
		boolean valido = false;
		while (!valido) {
			System.out.print(mensaje);
			
			try {
				for (int i = 0; i < longitud; i++)				
					vector[i] = Coin.nextInt();

				// Elimina el resto de caracteres basura restantes de la línea
				// de esta forma se evita introducir números de la línea precedente.
				if (Coin.hasNextLine())
					Coin.nextLine();
				
				valido = true;
			} catch (Exception ex) {
				Coin.nextLine();
			}
		}
		
		return vector;
	}
}

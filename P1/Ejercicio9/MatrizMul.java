/*
 * Práctica 1 - Ejercicio 9
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
 * Lee dos matrices de la entrada estándar y devuelve su producto.
 *
 * @author  Benito Palacios Sánchez
 * @version 1.1, 05/03/2014
 */
public class MatrizMul {
	private static Scanner Coin = new Scanner(System.in);

	/**
	 * Método de entrada del programa.
	 * 
	 * @param args Argumentos pasado al programa. No usado.
	 */
	public static void main(String[] args)
	{
		// Lee matrices.
		System.out.println("Primera matriz");
		int[][] mat1 = leeMatriz();
		System.out.println();
		
		System.out.println("Segunda matriz");
		int[][] mat2 = leeMatriz();
		System.out.println();

		// Calcula el producto.
		int[][] producto = null;
		try {
			producto = matrizMul(mat1, mat2);
		} catch (ArithmeticException ae) {
			System.out.println(ae.getMessage());
			return;
		}

		// Muestra el resultado
		muestraMatriz(producto);
	}

	/*
	 * Multiplica dos matrices dadas.
	 */
	private static int[][] matrizMul(int[][] mat1, int[][] mat2)
	{
		// Suponemos que todas las filas tienen igual número de columnas
		// y que el número de filas y de columnas es mayor que cero.
		int m  = mat1.length;
		int n1 = mat1[0].length;
		int n2 = mat2.length;
		int p  = mat2[0].length;

		// Comprueba que el tamaño de las matrices es el correcto
		if (n1 != n2)
			throw new ArithmeticException("Tamaño inválido de matrices");

		// Realiza la multiplicación
		int[][] producto = new int[m][p];
		for (int f = 0; f < m; f++)
			for (int c = 0; c < p; c++)
				producto[f][c] = vectorMul(mat1, f, mat2, c);

		return producto;
	}

	/*
	 * Multiplica dos vectores, el primero será una fila de mat1, el segundo
	 * será una columna de mat2.
	 */
	private static int vectorMul(int[][] mat1, int fil, int[][] mat2, int col)
	{
		if (mat1[fil].length != mat2.length)
			throw new ArithmeticException("Tamaño inválido de matrices");
	
		int valor = 0;
		for (int i = 0; i < mat2.length; i++)
			valor += mat1[fil][i] * mat2[i][col];
		return valor;
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

	/*
	 * Muestra por la salida estándar la matriz dada.
	 */
	private static void muestraMatriz(int[][] matriz)
	{
		// Por cada fila y columna.
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++)
				System.out.print(matriz[i][j] + "\t");
			
			System.out.println();
		}
	}
}

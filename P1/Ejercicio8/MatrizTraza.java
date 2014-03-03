/************************************************
 * Archivo P1/Ejercicio8/MatrizTraza.java       *
 * Código distribuido bajo licencia GPL V2      *
 *                                              *
 * Entorno:    vim                              *
 * Fecha:      03/03/2014                       *
 * Autor:      Benito Palacios Sánchez          *
 * Asignatura: Complementos de Programación     *
 * Práctica:   1                                *
 * Ejercicio:  08                               *
 *                                              *
 * Resumen:                                     *
 * Lee una matriz de la entrada estándar y      *
 * devuelve su traza.                           *
 ***********************************************/
import java.util.Scanner;
import java.util.InputMismatchException;

public class MatrizTraza {
	private static Scanner Coin = new Scanner(System.in);

	public static void main(String[] args) {
		int[][] matriz = leeMatriz();
		int traza = calculaTraza(matriz);

		System.out.println();
		System.out.println("La traza de la matriz es: " + traza);
	}

	public static int[][] leeMatriz() {		
		// Pide el número de filas
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

	public static int leeNumero(String mensaje) {
		int resultado = 0;

		boolean valido = false;
		while (!valido) {
			System.out.print(mensaje);
			try {
				resultado = Coin.nextInt();
				valido = true;
			} catch (InputMismatchException ex) {
				Coin.nextLine();	// Elimina esa línea del buffer
			}	
		}

		return resultado;
	}

	public static int[] leeVector(String mensaje, int longitud) {
		int[] vector = new int[longitud];


		boolean valido = false;
		while (!valido) {
			System.out.print(mensaje);
			try {
				for (int i = 0; i < longitud; i++)				
					vector[i] = Coin.nextInt();
				if (Coin.hasNextLine())
					Coin.nextLine();
				valido = true;
			} catch (Exception ex) {
				Coin.nextLine();
			}
		}
		
		return vector;
	}

	public static int calculaTraza(int[][] matriz) {
		int traza = 0;
		for (int i = 0; i < matriz.length; i++)
			traza += matriz[i][i];
		return traza;
	}
}

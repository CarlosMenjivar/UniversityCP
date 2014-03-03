/************************************************
 * Archivo P1/Ejercicio9/MatrizMul.java         *
 * Código distribuido bajo licencia GPL V2      *
 *                                              *
 * Entorno:    vim                              *
 * Fecha:      03/03/2014                       *
 * Autor:      Benito Palacios Sánchez          *
 * Asignatura: Complementos de Programación     *
 * Práctica:   1                                *
 * Ejercicio:  09                               *
 *                                              *
 * Resumen:                                     *
 * Lee dos matrices de la entrada estándar y    *
 * devuelve su producto.                        *
 ***********************************************/
import java.util.Scanner;
import java.util.InputMismatchException;

public class MatrizMul {
	private static Scanner Coin = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Primera matriz");
		int[][] mat1 = leeMatriz();
		
		System.out.println();
		System.out.println("Segunda matriz");
		int[][] mat2 = leeMatriz();

		
		int[][] producto = null;
		try {
			producto = matrizMul(mat1, mat2);
		} catch (ArithmeticException ae) {
			System.out.println(ae.getMessage());
			return;
		}

		System.out.println();
		muestraMatriz(producto);
	}

	public static int[][] leeMatriz() {		
		// Pide el número de filas
		int numFil = 0;
		do {
			numFil = leeNumero("Introduzca el número de filas: ");
			if (numFil <= 0)
				System.out.println("¡Ha de ser mayor que cero!");
		} while (numFil <= 0);

		int numCol = 0;
		do {
			numCol = leeNumero("Introduzca el número de columnas: ");
			if (numCol <= 0)
				System.out.println("¡Ha de ser mayor que cero!");
		} while (numCol <= 0);
		
		// Lee la matriz
		System.out.println();
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

	public static int[][] matrizMul(int[][] mat1, int[][] mat2) {
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

	public static int vectorMul(int[][] mat1, int fila, int[][] mat2, int columna) {
		if (mat1[fila].length != mat2.length)
			throw new ArithmeticException("Tamaño inválido de matrices");
	
		int valor = 0;
		for (int i = 0; i < mat2.length; i++)
			valor += mat1[fila][i] * mat2[i][columna];
		return valor;
	}

	public static void muestraMatriz(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++)
				System.out.print(matriz[i][j] + " ");
			System.out.println();
		}
	}
}

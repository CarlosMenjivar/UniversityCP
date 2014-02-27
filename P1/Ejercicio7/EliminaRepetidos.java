/************************************************
 * Archivo P1/Ejercicio5/EliminaRepetidos.java  *
 * Código distribuido bajo licencia GPL V2      *
 *                                              *
 * Entorno:    vim                              *
 * Fecha:      26/02/2014                       *
 * Autor:      Benito Palacios Sánchez          *
 * Asignatura: Complementos de Programación     *
 * Práctica:   1                                *
 * Ejercicio:  07                               *
 *                                              *
 * Resumen:                                     *
 * Lee un conjunto de números de la entrada y   *
 * devuelve el mayor de entre ellos.            *
 ***********************************************/
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class EliminaRepetidos {
	private static Scanner Coin = new Scanner(System.in);

	public static void main(String[] args) {		
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
			elementos[i] = leeNumero((i + 1) + ": ");
		System.out.println();		

		// Elimina los repetidos (como máximo contendrá todos)
		ArrayList<Integer> noRepetidos = new ArrayList<Integer>(numElementos);
		for (int i = 0; i < numElementos; i++) {
			// Método 1 (recomendado)
			if (!noRepetidos.contains(elementos[i]))
				noRepetidos.add(elementos[i]);

/*
			// Método 2
			boolean repetido = false;
			for (int k = 0; k < noRepetidos.size() && !repetido; k++) {
				if (noRepetidos.get(k) == elementos[i])
					repetido = true;
			}

			if (!repetido)
				noRepetidos.add(elementos[i]);
*/
		}

		// Muestra el resultado
		if (numElementos == 0) {
			System.out.println("¡No hay elementos a ordenar!");
		} else {
			// Muestra el vector original
			System.out.println("Vector original:");
			for (int a : elementos)
				System.out.print(a + " ");
			System.out.println();

			// Muestra el vector sin los elementos repetidos
			System.out.println("Vector sin repetidos:");
			for (int a : noRepetidos)
				System.out.print(a + " ");
			System.out.println();
		}
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
}

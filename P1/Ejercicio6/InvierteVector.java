/************************************************
 * Archivo P1/Ejercicio6/InvierteVector.java    *
 * Código distribuido bajo licencia GPL V2      *
 *                                              *
 * Entorno:    vim                              *
 * Fecha:      26/02/2014                       *
 * Autor:      Benito Palacios Sánchez          *
 * Asignatura: Complementos de Programación     *
 * Práctica:   1                                *
 * Ejercicio:  06                               *
 *                                              *
 * Resumen:                                     *
 * Lee un conjunto de números de la entrada y   *
 * lo invierte.                                 *
 ***********************************************/
import java.util.Scanner;
import java.util.InputMismatchException;

public class InvierteVector {
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

		int[] elementos = new int[numElementos];
		
		// Itera para pedir cada elemento
		int idx = 0;
		while (idx < numElementos) {
			elementos[numElementos - 1 - idx] = leeNumero((idx + 1) + ": ");
			idx++;
		}

		// Muestra los elementos invertidos
		System.out.println("Los elementos invertidos son:");
		for (int i = 0; i < numElementos; i++)
			System.out.print(elementos[i] + " ");
		System.out.println();
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

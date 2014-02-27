/************************************************
 * Archivo P1/Ejercicio5/NumeroMayor2.java      *
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
import java.util.Scanner;
import java.util.InputMismatchException;

public class NumeroMayor2 {
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
		int maximo = 0;
		int idx = 0;
		while (idx < numElementos) {
			elementos[idx] = leeNumero((idx + 1) + ": ");
			if (idx == 0 || elementos[idx] > maximo)
				maximo = elementos[idx];
			idx++;
		}

		// Muestra el resultado
		if (idx == 0)
			System.out.println("¡No hay elementos a ordenar!");
		else
			System.out.println("El elemento mayor es: " + maximo);
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

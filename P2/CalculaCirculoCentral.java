/*
 * Práctica 2 - Ejercicio 3
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

/**
 * Clase principal del programa.
 * Muestra el círculo central tras leer dos círculos de la entrada estándar.
 *
 * @author  Benito Palacios Sánchez
 * @version 1.0, 05/03/2014
 */
public class CalculaCirculoCentral
{
	/**
	 * Método de entrada del programa.
	 *
	 * @param args Argumentos pasado al programa. No usado.
	 */
	public static void main(String[] args)
	{
		// En primer lugar leemos dos círculos.
		System.out.println("Introduzca los parámetros de dos círculos.");
		System.out.println("Siga el siguiente formato: origenX origenY radio");

		System.out.print("Círculo 1: ");
		Circulo c1 = Circulo.fromStd();
		c1.print();
		System.out.println();

		System.out.print("Círculo 2: ");
		Circulo c2 = Circulo.fromStd();
		c2.print();
		System.out.println();

		// A continuación calculo el centro y el radio del círculo central.
		Punto  origen = c1.getOrigen().calculaPuntoMedio(c2.getOrigen());
		double radio  = c1.getOrigen().calculaDistancia(c2.getOrigen()) / 2;

		// Finalmente crea el círculo y lo muestra
		Circulo central = new Circulo(origen, radio);

		System.out.print("El círculo central es ");
		central.print();
		System.out.println();

		System.out.print("Su área es " + central.calculaArea());
		System.out.println();
	}
}

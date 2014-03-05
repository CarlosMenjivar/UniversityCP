/*
 * Práctica 2 - Ejercicio 2
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

/**
 * Clase que representa un círculo.
 *
 * @author  Benito Palacios Sánchez
 * @version 1.0, 05/03/2014
 */
public class Circulo
{
	private Punto  origen;
	private double radio;

	/**
	 * Crea un círculo a partir de un punto origen y un radio.
	 *
	 * @param origen Punto del centro del círculo.
	 * @param radio  Radio del círculo.
	 */
	public Circulo(Punto origen, double radio)
	{
		this.origen = origen;
		this.radio  = radio;
	}

	/**
	 * Crea un círculo leyendo los datos separados por un espacio desde
	 * desde el escaneador dado.
	 *
	 * @param scanner Escaneador de texto usado para obtener los parámetros.
	 * @return Nuevo círculo leido.
	 */
	public static Circulo fromScanner(Scanner scanner)
	{
		return new Circulo(Punto.fromScanner(scanner), scanner.nextDouble());
	}

	/**
	 * Devuelve el origen del círculo.
	 *
	 * @return Punto origen del círculo.
	 */
	public Punto getOrigen() {
		return this.origen;
	}

	/**
	 * Devuelve el radio del círculo.
	 *
	 * @return Radio del círculo.
	 */
	public double getRadio() {
		return this.radio;
	}

	/**
	 * Muestra el círculo en la salida estándar.
	 */
	public void print()
	{
		this.origen.print();
		System.out.print(" " + this.radio);
	}

	/**
	 * Calcula el área del círculo.
	 *
	 * @return Área del círculo
	 */
	public double calculaArea()
	{
		return Math.PI * this.radio * this.radio;
	}
}

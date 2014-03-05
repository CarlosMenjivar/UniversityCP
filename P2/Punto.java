/*
 * Práctica 2 - Ejercicio 1
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
 * Clase que representa un punto en el espacio 2D euclídeo.
 *
 * @author  Benito Palacios Sánchez
 * @version 1.0, 05/03/2014
 */
public class Punto
{
	private double x;
	private double y;

	/**
	 * Crea un punto a partir de sus coordenadas.
	 *
	 * @param x Coordenada del eje X del punto.
	 * @param y Coordenada del eje Y del punto.
	 */
	public Punto(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Crea un punto leyendo dos datos double separados por un espacio
         * desde la entrada estándar.
	 *
	 * @return Nuevo punto leído.
	 */
	public static Punto fromStd()
	{
		Scanner scanner = new Scanner(System.in);
		return new Punto(scanner.nextDouble(), scanner.nextDouble());
	}

	/**
	 * Devuelve la coordenada del eje X del punto.
	 *
	 * @return Coordenada del eje X.
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Devuelve la coordenada del eje Y del punto.
	 *
	 * @return Coordenada del eje Y.
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Muestra el punto en la salida estándar.
	 */
	public void print()
	{
		System.out.print(this.x + " " + this.y);
	}

	/**
	 * Calcula la distancia euclídea de este punto a otro dado.
	 *
	 * @param p Segundo extremo para calcular distancia.
	 * @return Distancia euclídea.
	 */
	public double calculaDistancia(Punto p)
	{
		return Math.sqrt(
			(this.x - p.getX()) * (this.x - p.getX()) +
			(this.y - p.getY()) * (this.y - p.getY())
		);
	}

	/**
	 * Devuelve el punto medio entre el actual y otro dado.
	 *
	 * @param p Segundo extremo para calcular el punto medio.
	 * @return Punto medio.
	 */
	public Punto calculaPuntoMedio(Punto p)
	{
		return new Punto(
			(this.x + p.getX()) / 2,
			(this.y + p.getY()) / 2
		);
	}
}

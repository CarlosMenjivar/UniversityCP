/*
 * Copyright (C) 2014 Benito Palacios Sánchez
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
package poligono;

import java.util.Scanner;

/**
 * Clase que representa un punto en el espacio 2D euclídeo.
 *
 * @author  Benito Palacios Sánchez
 * @version 1.0, 05/03/2014
 */
public class Punto {
    private final double coordX;    // NOPMD
    private final double coordY;    // NOPMD

    /**
     * Crea un punto a partir de sus coordenadas.
     *
     * @param coordX Coordenada del eje X del punto.
     * @param coordY Coordenada del eje Y del punto.
     */
    public Punto(final double coordX, final double coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    /**
     * Crea un punto leyendo dos datos double separados por un espacio
     * usando el escaneador dado.
     *
     * @param scanner Escaneador de texto usado para obtener los parámetros.
     * @return Nuevo punto leído.
     */
    public static Punto fromScanner(final Scanner scanner) {
        return new Punto(scanner.nextDouble(), scanner.nextDouble());
    }

    /**
     * Devuelve la coordenada del eje X del punto.
     *
     * @return Coordenada del eje X.
     */
    public double getCoordX() {
        return this.coordX;
    }

    /**
     * Devuelve la coordenada del eje Y del punto.
     *
     * @return Coordenada del eje Y.
     */
    public double getCoordY() {
        return this.coordY;
    }
    
    /**
     * Muestra el punto en la salida estándar.
     */
    public void print() {
        System.out.print(this.coordX + " " + this.coordY);
    }

    /**
     * Calcula la distancia euclídea de este punto a otro dado.
     *
     * @param punto Segundo extremo para calcular distancia.
     * @return Distancia euclídea.
     */
    public double calculaDistancia(final Punto punto) {
        return Math.sqrt(
            (this.coordX - punto.getCoordX()) * (this.coordX - punto.getCoordX())
          + (this.coordY - punto.getCoordY()) * (this.coordY - punto.getCoordY())
        );
    }

    /**
     * Devuelve el punto medio entre el actual y otro dado.
     *
     * @param punto Segundo extremo para calcular el punto medio.
     * @return Punto medio.
     */
    public Punto calculaPuntoMedio(final Punto punto) {
        return new Punto(
            (this.coordX + punto.getCoordX()) / 2,
            (this.coordY + punto.getCoordY()) / 2
        );
    }
}

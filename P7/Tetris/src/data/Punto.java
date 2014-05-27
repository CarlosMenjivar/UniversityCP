/*
 * Copyright (C) 2014 Benito Palacios Sánchez
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */

package data;

/**
 * Representa un punton en el espacio de 2 dimensiones.
 * 
 * @author Benito Palacios Sánchez
 */
public class Punto {
    private final int coordX;
    private final int coordY;

    /**
     * Crea una nueva instancia de la clase a partir de las coordenadas.
     * 
     * @param coordX Coordenada del eje X.
     * @param coordY Coordenada del eje Y.
     */
    public Punto(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    /**
     * Obtiene la coordenada del eje X.
     * 
     * @return Coordenada X.
     */
    public int getCoordX() {
        return coordX;
    }

    /**
     * Obtiene la coordenada del eje Y.
     * 
     * @return Coordenada Y.
     */
    public int getCoordY() {
        return coordY;
    }
    
    /**
     * Desplaza un punto la cantidad indicada.
     * 
     * @param dx Número de puntos a desplazar en el eje X.
     * @param dy Número de puntos a desplazar en el eje Y.
     * @return Nuevo punto con las nuevas coordenadas.
     */
    public Punto offset(final int dx, final int dy) {
        return new Punto(this.getCoordX() + dx, this.getCoordY() + dy);
    }
}

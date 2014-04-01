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

package tsp;

/**
 * Clase que representa una ciudad del problema (un vértice del grafo).
 * 
 * @version 1.0, 19/03/2014
 * @author  Benito Palacios Sánchez
 */
public class Ciudad {
    /**
     * Variable que se irá incrementando cada vez que se llama el constructor
     * y asignará un identificador único a cada instancia.
     */
    private static int AssignedId = 0;
    
    /**
     * Coordenada X de la ciudad. Se asigna en el constructor.
     */
    private final double coordX;    // NOPMD
    
    /**
     * Coordenada Y de la ciudad. Se asigna en el constructor.
     */
    private final double coordY;    // NOPMD
    
    /**
     * Idenficador único de la clase ciudad. Se asigna en el constructor.
     */
    private final int id;           // NOPMD
    
    /**
     * Crea una instancia a partir de la posición y su identificador.
     * 
     * @param coordX Coordenada X de la ciudad.
     * @param coordY Coordenada Y de la ciudad.
     */
    public Ciudad(final double coordX, final double coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.id     = AssignedId++;
    }
    
    /**
     * Devuelve la coordenada X de la ciudad.
     * 
     * @return Coordenada X.
     */
    public double getCoordX() {
        return this.coordX;
    }
    
    /**
     * Devuelve la coordenada Y de la ciudad.
     * 
     * @return Coordenada Y.
     */
    public double getCoordY() {
        return this.coordY;
    }
    
    /**
     * Devuelve el identificador de la ciudad.
     * 
     * @return Identificador.
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Calcula la distancia euclídea de esta ciudad a otra.
     * 
     * @param otra La ciudad usada como otro extremo al calcula la distancia.
     * @return La distancia.
     */
    public double getDistancia(final Ciudad otra) {
        return Math.sqrt(
            (this.getCoordX() - otra.getCoordX()) * (this.getCoordX() - otra.getCoordX())
          + (this.getCoordY() - otra.getCoordY()) * (this.getCoordY() - otra.getCoordY())
        );
    }
}

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

import tsp.algoritmos.ITspAlgoritmo;

/**
 * Contiene métodos para solucionar un problema TSP y devolver su solución.
 * 
 * @version 1.0
 * @author  Benito Palacios Sánchez
 */
public class Heuristica {
    /** Algoritmo que se usa para resolver el problema dado. */
    private ITspAlgoritmo algoritmo;
    
    /** Problema a resolver por el algoritmo. */
    private final Problema problema;
    
    /** Última ruta obtenida tras resolver el problema con el algoritmo. */
    private Ruta ruta;
    
    /**
     * Crea una instancia a partir del problema a resolver y del algoritmo que
     * se usará para resolver dicho problema.
     * 
     * @param problema Problema a resolver
     * @param algoritmo Algoritmo para resolver el problema.
     */
    public Heuristica(final Problema problema, final ITspAlgoritmo algoritmo) {
        this.algoritmo = algoritmo;
        this.problema  = problema;
        this.ruta      = null;
    }
    
    /**
     * Devuelve el algoritmo que se usa para resolver el problema.
     * 
     * @return Algoritmo para resolver el problema.
     */
    public ITspAlgoritmo getAlgoritmo() {
        return this.algoritmo;
    }
    
    /**
     * Establece el algoritmo que se usa para resolver el problema.
     * 
     * @param algoritmo Algoritmo para resolver el problema.
     */
    public void setAlgoritmo(ITspAlgoritmo algoritmo) {
        this.algoritmo = algoritmo;
    }
    
    /**
     * Devuelve la última solución del problema.
     * 
     * @return Solución al problema. 
     */
    public Ruta getRuta() {
        return this.ruta;
    }
    
    /**
     * Devuelve el coste de la última solución del problema.
     * 
     * @return Coste de la ruta.
     */
    public double getCoste() {
        if (this.ruta == null)
            throw new NullPointerException("La ruta todavía no ha sido calculada.");
        
        return this.ruta.getCoste();
    }
    
    /**
     * Ejecuta el algoritmo para solución el problema.
     * La salida se puede obtener desde {@link Heuristica#getRuta()}.
     */
    public void run() {
        this.ruta = this.algoritmo.calculaMejorRuta(this.problema);
    }
}

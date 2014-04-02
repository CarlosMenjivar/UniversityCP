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

import java.util.Arrays;

/**
 * Clase que representa una ruta desde una ciudad origen y que pasa
 * por todas las ciudades existentes hasta volver a la de origen.
 * 
 * Representa una solución al problema TSP.
 * 
 * @version 1.0
 * @author  Benito Palacios Sánchez
 */
public class Ruta {
    /** Ciudades en orden ascendente que componen la ruta. */
    private final Ciudad[] ruta;
    
    /**
     * Índice de la primera ciudad que contiene la variable ruta.
     * Una vez finalizado el algoritmo este valor ha de ser 0.
     * Permite dar soluciones parciales y temporales al problema.
     */
    private int primeraCiudad;
    
    /**
     * Índice de la última ciudad que contiene la variable ruta.
     * Una vez finalizado el algoritmo este valor ha de ser ruta.length - 1.
     * Permite dar soluciones parciales y temporales al problema.
     */
    private int ultimaCiudad;
    
    /**
     * Crea una instancia reservando el número de ciudades especificado.
     * 
     * @param numCiudades Número de ciudades que contendrá la ruta.
     */
    public Ruta(int numCiudades) {
        this.ruta = new Ciudad[numCiudades];
        this.primeraCiudad = -1;
        this.ultimaCiudad  = -1;
    }
    
    /**
     * Devuelve el conjunto de ciudades que actualmente definen a la ruta.
     * Se devolverá una subarray con solo las ciudades insertadas.
     * 
     * @return Conjunto de ciudades que definen la ruta.
     */
    public Ciudad[] getRuta() {
        if (this.primeraCiudad == -1 || this.ultimaCiudad == -1)
            return null;
          
        Ciudad[] subruta = Arrays.copyOfRange(
                this.ruta,
                this.primeraCiudad,
                this.ultimaCiudad + 1
        );
        
        return subruta;
    }
    
    /**
     * Devuelve el coste total de la ruta actual.
     * 
     * @return Coste de la ruta actual.
     */
    public double getCoste() {
        if (this.primeraCiudad == -1 || this.ultimaCiudad == -1)
            return -1;
        
        double coste = 0;
        
        for (int i = this.primeraCiudad; i < this.ultimaCiudad; i++)
            coste += this.ruta[i].getDistancia(this.ruta[i + 1]);
        
        return coste;
    }
    
    /**
     * Inserta una ciudad en la posición dada.
     * 
     * @param ciudad Ciudad a insertar.
     * @param pos Posición de la ruta donde se insertará la ciudad.
     */
    public void insertCiudad(Ciudad ciudad, int pos) {
        if (pos < 0 || pos > this.ruta.length)
            throw new ArrayIndexOutOfBoundsException(pos);
        
        if (this.primeraCiudad == -1 || this.primeraCiudad > pos)
            this.primeraCiudad = pos;
        
        if (this.ultimaCiudad == -1 || this.ultimaCiudad < pos)
            this.ultimaCiudad = pos;
        
        // Dado que el objeto ciudad es inmutable (hay definidos setters)
        // no hace falta clonarlo.
        this.ruta[pos] = ciudad;
    }
    
}

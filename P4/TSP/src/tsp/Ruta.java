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
    private Ciudad[] ruta;
    
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
    public Ruta(final int numCiudades) {
        this.ruta = new Ciudad[numCiudades];
        this.primeraCiudad = -1;
        this.ultimaCiudad  = -1;
    }
    
    /**
     * Devuelve una copia de la instancia actual de la ruta.
     * 
     * @return Copia de la ruta actual. 
     */
    public Ruta Clona() {
        Ruta clon = new Ruta(this.ruta.length);
        clon.primeraCiudad = this.primeraCiudad;
        clon.ultimaCiudad  = this.ultimaCiudad;
        clon.ruta          = (Ciudad[])this.ruta.clone();
        return clon;
    }
    
    /**
     * Devuelve el conjunto de ciudades que actualmente definen a la ruta.
     * Se devolverá una subarray con solo las ciudades insertadas.
     * La última ruta será la ciudad inicial de nuevo para cerrar el recorrido.
     * 
     * @return Conjunto de ciudades que definen la ruta.
     */
    public Ciudad[] getRuta() {
        if (this.primeraCiudad == -1 || this.ultimaCiudad == -1)
            return null;
        
        int length = this.getNumeroCiudades();
        Ciudad[] subruta = new Ciudad[length + 1];
        
        // Copio todas las ciudades en el nuevo vector.
        for (int i = 0; i < length; i++)
            subruta[i] = this.ruta[i + this.primeraCiudad];
        
        // Pongo al final de la ruta la ciudad inicial de nuevo
        // para cerrar el recorrido
        subruta[length] = this.ruta[this.primeraCiudad];
        
        return subruta;
    }
    
    /**
     * Devuelve el coste total de la ruta actual.
     * Incluye el coste de la última ciudad con la ciudad inicial.
     * 
     * @return Coste de la ruta actual.
     */
    public double getCoste() {
        if (this.primeraCiudad == -1 || this.ultimaCiudad == -1)
            return -1;
        
        double coste = 0;
        
        // Le sumo el coste de cada ciudad con la siguiente.
        for (int i = this.primeraCiudad; i < this.ultimaCiudad; i++)
            coste += this.ruta[i].getDistancia(this.ruta[i + 1]);
        
        // Le sumo el coste de la última ciudad con la primera.
        coste += this.ruta[this.ultimaCiudad].getDistancia(this.ruta[this.primeraCiudad]);
        
        return coste;
    }
    
    /**
     * Establece una ciudad en la posición dada.
     * 
     * @param ciudad Ciudad a insertar.
     * @param pos Posición de la ruta donde se insertará la ciudad.
     */
    public void setCiudad(final Ciudad ciudad, final int pos) {
        if (pos < 0 || pos + 1 < this.primeraCiudad ||
            pos >= this.ruta.length || pos - 1 > this.ultimaCiudad)
                throw new ArrayIndexOutOfBoundsException(pos);
        
        if (this.primeraCiudad == -1 || this.primeraCiudad > pos)
            this.primeraCiudad = pos;
        
        if (this.ultimaCiudad == -1 || this.ultimaCiudad < pos)
            this.ultimaCiudad = pos;
        
        // Dado que el objeto ciudad es inmutable (no hay definidos setters)
        // no hace falta clonarlo.
        this.ruta[pos] = ciudad;
    }
      
    /**
     * Inserta una ciudad en la posición dada.
     * Para insertar desplaza hacia arriba todos las ciudades de la ruta a
     * partir de la posición destino, con el objetivo de hacer hueco.
     * 
     * @param ciudad Ciudad a insertar.
     * @param pos Posición a insertar.
     */
    public void insertCiudad(final Ciudad ciudad, final int pos) {
        if (pos < this.primeraCiudad || pos > this.ultimaCiudad)
                throw new ArrayIndexOutOfBoundsException(pos);
        
        // Si al desplazar todo me salgo de los límites de la array
        if (this.ultimaCiudad + 1 >= this.ruta.length)
            throw new ArrayIndexOutOfBoundsException(pos);
        
        // Hago hueco en la array
        for (int i = this.ultimaCiudad; i >= pos; i--)
            this.ruta[i + 1] = this.ruta[i];
        
        // Establezco el valor y aumento ultimaCiudad en 1
        this.ruta[pos] = ciudad;
        this.ultimaCiudad++;
    }
  
    /**
     * Devuelve el número de ciudades que actualmente componen la ruta.
     * 
     * @return Número de ciudades de la ruta.
     */
    public int getNumeroCiudades() {
        return this.ultimaCiudad - this.primeraCiudad + 1;
    }
    
    /**
     * Devuelve el número máximo de ciudades que puede componer la ruta.
     * 
     * @return Número máximos de ciudades en la ruta.
     */
    public int getMaximasCiudades() {
        return this.ruta.length;
    }
    
    /**
     * Devuelve el índice donde se encuentra la primera ciudad de la ruta actual.
     * 
     * @return Índice de la primera ciudad. 
     */
    public int getPrimeraCiudad() {
        return this.primeraCiudad;
    }
    
    /**
     * Devuelve el índice donde se encuentra la última ciudad de la ruta actual.
     * 
     * @return Índice de la última ciudad.
     */
    public int getUltimaCiudad() {
        return this.ultimaCiudad;
    }
}

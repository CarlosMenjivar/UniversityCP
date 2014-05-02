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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
     * Matriz opcional con todas las distancias entre ciudades.
     * Los índices corresponden al ID de la ciudad menos 1. 
     */
    private double[][] distancias;
    
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
     * Genera una ruta aleatoria.
     * 
     * @param ciudades Ciudades que conformarán la ruta.
     * @return Ruta aleatoria
     */
    public static Ruta Aleatoria(final List<Ciudad> ciudades) {
        Ruta ruta = new Ruta(ciudades.size());
        Random random = new Random();
        
        // Listas ir sacando ciudades y posiciones sin repetir.
        List<Ciudad>  ciuSinVisitar = new ArrayList(ciudades);
        List<Integer> posSinVisitar = new ArrayList(ciudades.size());

        // Genero la lista de posiciones, gracias a esto aseguro que no
        // establezco una ciudad en una posición ocupada por otra.
        for (int i = 0; i < ciudades.size(); i++)
            posSinVisitar.add(i);        
        
        // Escojo una ciudad al azar y la introduzco en una posición al azar
        while (ciuSinVisitar.size() > 0) {
            // Ciudad al azar
            int idx = random.nextInt(ciuSinVisitar.size());
            Ciudad ciudad = ciuSinVisitar.remove(idx);

            // Posición al azar
            idx = random.nextInt(posSinVisitar.size());
            int pos = posSinVisitar.remove(idx);

            // La inserto
            ruta.ruta[pos] = ciudad;
        }
        
        ruta.primeraCiudad = 0;
        ruta.ultimaCiudad  = ruta.ruta.length - 1;
        return ruta;        
    }
    
    /**
     * Devuelve una copia de la instancia actual de la ruta.
     * 
     * @return Copia de la ruta actual. 
     */
    public Ruta clona() {
        Ruta clon = new Ruta(this.ruta.length);
        clon.primeraCiudad = this.primeraCiudad;
        clon.ultimaCiudad  = this.ultimaCiudad;
        // Lo idea es hacer un clone de distancias y así garantizar
        // que aunque se modifique un valor en la nueva copia, no se modificará
        // la de esta, pero por rendimiento me he asegurado de que eso no pase
        // asi que no hace falta.
        //clon.distancias    = (double[][])this.distancias.clone();
        clon.distancias    = this.distancias;
        for (int i = this.primeraCiudad; i <= this.ultimaCiudad; i++)
            clon.ruta[i] = this.ruta[i];
        return clon;
    }
    
    /**
     * Devuelve la matriz de distancia entre ciudades.
     * El índice de la matriz corresponde con el ID de la ciudad menos 1.
     * 
     * @return Matriz de distancia entre ciudades.
     */
    public double[][] getDistancias() {
        return (double[][])this.distancias.clone();
    }
    
    /**
     * Establece una matriz con las distancias entre todas las ciudades.
     * Esta matriz es opcional y hará los cálculos de coste más rápido
     * pues no hay que calcular las distancias de nuevo.
     * 
     * @param distancias Matriz de distancias entre ciudades.
     */
    public void setDistancias(final double[][] distancias) {
        this.distancias = (double[][])distancias.clone();
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
        for (int i = this.primeraCiudad; i < this.ultimaCiudad; i++) {
            // No llamo a la función getCoste(i, i + 1) como hacía en un
            // principio para mejorar el rendimiento
            // coste += getCoste(i, i + 1);
            if (this.distancias == null) {
                coste += this.ruta[i].getDistancia(this.ruta[i + 1]);
            } else {
                int id1 = this.ruta[i].getId() - 1;
                int id2 = this.ruta[i + 1].getId() - 1;
                coste += this.distancias[id1][id2];
            }
        }
        
        // Le sumo el coste de la última ciudad con la primera.
        //coste += this.getCoste(this.ultimaCiudad, this.primeraCiudad);
        if (this.distancias == null) {
            coste += this.ruta[this.ultimaCiudad].getDistancia(this.ruta[this.primeraCiudad]);
        } else {
            int id1 = this.ruta[this.ultimaCiudad].getId() - 1;
            int id2 = this.ruta[this.primeraCiudad].getId() - 1;
            coste += this.distancias[id1][id2];
        }
        
        return coste;
    }
    
    /**
     * Establece una ciudad en la posición dada.
     * No se permiten rutas inválidas temporales.
     * 
     * @param ciudad Ciudad a insertar.
     * @param pos Posición de la ruta donde se insertará la ciudad.
     */
    public void setCiudad(final Ciudad ciudad, final int pos) {
        // Si el índice está fuera de los límites de la ruta
        if (pos < 0 || pos >= this.ruta.length)
            throw new ArrayIndexOutOfBoundsException(pos);
        
        // Si intentamos añadir una ciudad dejando más de un hueco libre
        // y no se permite rutas temporales inválidas.
        if (pos + 1 < this.primeraCiudad)
            throw new ArrayIndexOutOfBoundsException(pos);
        
        if (this.ultimaCiudad != -1 && pos - 1 > this.ultimaCiudad)
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
     * Mueva una ciudad de un lugar a otro.
     * 
     * @param posAntigua Posición actual de la ciudad
     * @param posNueva Nueva posición de la ciudad.
     */
    public void mueve(final int posAntigua, final int posNueva) {
        Ciudad ciudad = this.ruta[posAntigua];        
        if (posAntigua < posNueva) {
            // Desplazo los elementos hacia abajo hasta ocupar la antigua pos.
            for (int i = posAntigua; i < posNueva; i++)
                this.ruta[i] = this.ruta[i + 1];
        } else if (posAntigua > posNueva) {
            // Desplazo los elementos hacia arriba hasta ocupar la antigua pos.
            for (int i = posAntigua; i > posNueva; i--)
                this.ruta[i] = this.ruta[i - 1];
        }
        
        this.ruta[posNueva] = ciudad;
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
    
    /**
     * Devuelve una ciudad de la ruta.
     * 
     * @param posicion Posición de la ciudad en la ruta.
     * @return Ciudad en dicha posición.
     */
    public Ciudad getCiudad(final int posicion) {
        // Miro que el índice sea válido.
        if (posicion < this.primeraCiudad || posicion > this.ultimaCiudad)
            throw new ArrayIndexOutOfBoundsException(posicion);
        
        return this.ruta[posicion];
    }
    
    /**
     * Devuelve la distancia entre dos ciudades.
     * Si la matriz distancia se ha establecido se tomará de allí.
     * 
     * @param idx1 Índice en la ruta de una de las ciudades.
     * @param idx2 Índice en la ruta de la otra ciudad.
     * @return Distancia entre ciudades.
     */
    private double getCoste(final int idx1, final int idx2) {
        if (this.distancias == null) {
            return this.ruta[idx1].getDistancia(this.ruta[idx2]);
        } else {
            int id1 = this.ruta[idx1].getId() - 1;
            int id2 = this.ruta[idx2].getId() - 1;
            return this.distancias[id1][id2];
        }
    }
}

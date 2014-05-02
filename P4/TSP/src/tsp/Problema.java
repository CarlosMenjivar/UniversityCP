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

import java.util.Scanner;
import java.util.regex.MatchResult;
import org.omg.IOP.CodecPackage.FormatMismatch;

/**
 * Representa un conjunto de ciudades que componen un problema TSP.
 * 
 * @version 1.0
 * @author  Benito Palacios Sánchez
 */
public class Problema {
    /** Contiene el conjunto de ciudades que componen el problema. */
    private final Ciudad[] ciudades;
    
    /**
     * Contiene todas las distancias entre una ciudades y las restantes.
     * El índice de la matriz corresponde con el ID de la ciudad menos 1.
     */
    private final double[][] distancias;
    
    /**
     * Crea una instancia del problema a partir de las ciudades que lo definen.
     * 
     * @param ciudades Ciudades que definen un problema.
     */
    public Problema(final Ciudad[] ciudades) {
        this.ciudades   = ciudades;
        this.distancias = CalculaDistancias(ciudades);
    }
    
    /**
     * Crea una instancia del problema leyendo las ciudades desde la entrada
     * estándar.
     * El formato usado es el siguiente:
     * <pre>{@code
     * DIMENSION: n
     * 1 x1 y1
     * 2 x2 y2
     * .....
     * n xn yn
     * }</pre>
     * Donde 'n' es el número de ciudades que componen el problema.
     * 
     * @return Problema con las ciudades leídas.
     * @throws FormatMismatch Ocurre cuando la primera línea es inválida.
     * La expresión regular a cumple es: DIMENSION\s?:\s*(\d+)
     */
    public static Problema FromStdIn() throws FormatMismatch {
        Scanner reader = new Scanner(System.in);
        
        // Primer obtiene el número de ciudades
        String numCiudadesRegex = "DIMENSION\\s?:\\s*(\\d+)";
        reader.findInLine(numCiudadesRegex);
        
        MatchResult result = reader.match();
        if (result.groupCount() != 1)
            throw new FormatMismatch("Invalid format: " + numCiudadesRegex);
        
        // A continuación se leen las ciudades
        String a = result.group(1);
        int numCiudades   = Integer.parseInt(a);
        Ciudad[] ciudades = new Ciudad[numCiudades];
        for (int i = 0; i < numCiudades; i++)
            ciudades[i] = Ciudad.FromStdIn(reader);
        
        return new Problema(ciudades);
    }
    
    /**
     * Devuelve la ciudad en el índice dado.
     * 
     * @param index Índice de la ciudad a devolver
     * @return Ciudad
     */
    public Ciudad getCiudad(final int index) {
        return this.ciudades[index];
    }
    
    /**
     * Devuelve la ciudad que tiene el mismo Id al dado.
     * 
     * @param id Id de ciudad a buscar.
     * @return Ciudad con igual Id.
     */
    public Ciudad getCiudadById(final int id) {
        for (Ciudad ciudad : this.ciudades)
            if (ciudad.getId() == id)
                return ciudad;
        
        return null;
    }
    
    /**
     * Devuelve el conjunto de ciudades que componen el problema.
     * 
     * @return Ciudades del problema.
     */
    public Ciudad[] getCiudades() {
        return (Ciudad[])this.ciudades.clone();
    }
    
    /**
     * Devuelve el número de ciudades que compone el problema.
     * 
     * @return Número de ciudades del problema.
     */
    public int getNumCiudades() {
        return this.ciudades.length;
    }
    
    /**
     * Devuelve la matriz con la distancia entre ciudades.
     * El índice de la matriz corresponde con el ID de la ciudad menos 1.
     * 
     * @return Matriz de distancia entre ciudades.
     */
    public double[][] getDistancias() {
        return (double[][])this.distancias;
    }
    
    /**
     * Devuelve la distancia entre dos ciudad.
     * Este método proporciona un camino rápido de obtener distancias entre
     * ciudades porque accede a una matriz precalculada de distancias.
     * 
     * @param ciudad1 Primera ciudad (extremo).
     * @param ciudad2 Segunda ciudad (otro extremo).
     * @return Distancia entre ciudades.
     */
    public double getDistancia(final Ciudad ciudad1, final Ciudad ciudad2) {
        return this.distancias[ciudad1.getId() - 1][ciudad2.getId() - 1];
    }
    
    /**
     * Crea una matriz de distancia entre ciudades.
     * 
     * @param ciudades Ciudades a analizar.
     * @return Distancia entre ciudades.
     */
    private static double[][] CalculaDistancias(final Ciudad[] ciudades) {
        // Dado que la distancia de una ciudad a otra no depende de la ciudad
        // de partida, la matriz distancia es simétrica con diagonal nula.
        // Por tanto sólo hace falta calcular la mitad de los valores.
        double[][] distancias = new double[ciudades.length][ciudades.length];
        for (int i = 0; i < ciudades.length; i++) {
            for (int j = 0; j < ciudades.length; j++) {
                // Los índices en matrices empiezan en 0, pero en ciudades en 1.
                int id1 = ciudades[i].getId() - 1;
                int id2 = ciudades[j].getId() - 1;
                
                double dist;
                if (i < j)
                    dist = ciudades[i].getDistancia(ciudades[j]);
                else if (i == j)
                    dist = 0;
                else
                    dist = distancias[id2][id1];
                
                distancias[id1][id2] = dist;
            }
        }
        
        return distancias;
    }
}

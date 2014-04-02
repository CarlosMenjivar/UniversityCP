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
     * La primera dimension corresponde a cada una de las ciudades del vector
     * {@link Problema#ciudades} mientras que la segunda dimension contiene
     * la distancia de dicha ciudad con el resto de ciudades del vector en el
     * mismo orden.
     */
    private final double[][] distancias;
    
    /**
     * Crea una instancia del problema a partir de las ciudades que lo definen.
     * 
     * @param ciudades Ciudades que definen un problema.
     */
    public Problema(Ciudad[] ciudades) {
        this.ciudades = ciudades;
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
     * La expresión regular a cumple es: DIMENSION:\s*(\d+)
     */
    public static Problema FromStdIn() throws FormatMismatch {
        Scanner reader = new Scanner(System.in);
        
        // Primer obtiene el número de ciudades
        String numCiudadesRegex = "DIMENSION:\\s*(\\d+)";
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
    public Ciudad getCiudad(int index) {
        return this.ciudades[index];
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
     * Crea una matriz de distancia entre ciudades.
     * 
     * @param ciudades Ciudades a analizar.
     * @return Distancia entre ciudades.
     */
    private static double[][] CalculaDistancias(Ciudad[] ciudades) {
        double[][] distancias = new double[ciudades.length][ciudades.length];
        
        // TODO: Calcula la distancia de la mitad de las ciudades pues la del
        // resto ya habrá sido calcula anteriormente (es recíproco).
        for (int i = 0; i < ciudades.length; i++) {
            for (int j = 0; j < ciudades.length; j++) {
                distancias[i][j] = ciudades[i].getDistancia(ciudades[j]);
            }
        }
        
        return distancias;
    }
}

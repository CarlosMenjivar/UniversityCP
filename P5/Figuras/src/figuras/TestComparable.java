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
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package figuras;

import java.util.Arrays;
import java.util.Random;

/**
 * Conjunto de pruebas con la interfaz Comparable y métodos de ordenación
 * y búsqueda de mayor.
 * 
 * @author Benito Palacios Sánchez
 */
public class TestComparable {
    /**
     * Entrada del programa.
     * 
     * @param args El primer elemento es la probabilidad de que se genere un
     * círculo. El segundo el número de figuras a generar para realizar las 
     * pruebas y el tercero un parámetro de tamaño de las figuras creadas.
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Se requieren 3 argumentos.");
            return;
        }
        
        // Obtiene un vector aleatorio de figuras
        double prob   = Double.parseDouble(args[0]);
        int nFiguras  = Integer.parseInt(args[1]);
        int dimension = Integer.parseInt(args[2]);
        Figura[] figuras = generaArrayFiguras(prob, nFiguras, dimension);
        Figura[] copia = (Figura[])figuras.clone();
        
        // Obtiene la figura mayor de todas
        Figura mayor = ComparableAlgoritmos.mayor(figuras);
        System.out.println("La figura mayor es:");
        System.out.println(mayor);
        System.out.println();
        
        // Ordena el array
        ComparableAlgoritmos.ordena(figuras);
        System.out.println("El vector ordenado es:");
        for (Figura fig : figuras)
            System.out.println(fig);
        System.out.println();
        
        // Ordena el array de la otra forma
        Arrays.sort(copia);
        System.out.println("Otra forma de ordenarlos:");
        for (Figura fig : copia)
            System.out.println(fig);
    }
    
    /**
     * Genera un vector aleatorio de figuras.
     * Cuando se genera un círculo su radio estará comprendido entre 0 y 
     * dimension / 2.
     * Cuando se genera un rectángulo sus lados estarán comprendidos entre 0
     * y dimension
     * 
     * @param prob Probabilidad de que se genere un círculo en cada iteración.
     * @param nFiguras Tamaño del vector.
     * @param dimension Dimensión para crear las figuras.
     * @return Vector de figuras aleatorias.
     */
    private static Figura[] generaArrayFiguras(final double prob, 
            final int nFiguras, final int dimension) {
        Random random = new Random();
        Figura[] figuras = new Figura[nFiguras];
        
        for (int i = 0; i < nFiguras; i++) {
            double p = random.nextDouble();
            if (p < prob) {
                // Construir círculo de radio aleatorio entre 0 y dimension / 2
                figuras[i] = new Circulo(random.nextDouble() * dimension / 2);
            } else {
                // Construir rectángulo de ancho y altura aleatorio de 0 a dimension
                figuras[i] = new Rectangulo(
                        random.nextDouble() * dimension,
                        random.nextDouble() * dimension
                );
            }
        } // End for
        
        return figuras;
    }
}

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

import java.util.Random;

/**
 *
 * @author Benito Palacios Sánchez
 */
public class TestComparable {
    
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
    }
    
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

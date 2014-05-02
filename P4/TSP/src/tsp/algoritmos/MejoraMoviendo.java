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

package tsp.algoritmos;

import tsp.Ruta;

/**
 * Algoritmo de mejora de una ruta moviendo de lugar las ciudades.
 * 
 * @version 1.0
 * @author  Benito Palacios Sánchez
 */
public class MejoraMoviendo implements IMejoraRuta {
    private static final int MAX_ITERACIONES = 10;
    
    /**
     * Mejora la ruta realizando cambios de lugar de las ciudades.
     * 
     * @param ruta Ruta a mejorar.
     * @return Ruta mejorada, o si no se puede, ruta original.
     */
    @Override
    public Ruta mejoraRuta(final Ruta ruta) {
        Ruta rutaFinal = ruta;
        
        // Sobre la ruta aleatoria realiza toda las posibles mejoras.
        int i = 0;
        boolean fin = false;
        while (!fin && i < MAX_ITERACIONES) {
            // Pruebo a realizar una mejora
            Ruta rutaNueva = this.realizaMovimiento(rutaFinal);
            
            // Si se ha mejorado, es decir ha habido cambio.
            if (rutaNueva != rutaFinal) {
                rutaFinal = rutaNueva;
                i++;
            } else    // Si no terminar, ya no se puede mejorar más la ruta.
                fin = true;
        }
        
        return rutaFinal;
    }
    
    /**
     * Realiza una mejora sobre la ruta.
     * Para ello por cada posición busco la ciudad que reduciría el coste si
     * estuviera ahí. Al final realizo el cambio.
     * 
     * @param ruta Ruta a mejorar.
     * @return Ruta mejorada o en caso de no ser mejorada, ruta original.
     */
    private Ruta realizaMovimiento(final Ruta ruta) {
        // Si no hay mejora, devuelve la ruta original.
        double menorCoste = ruta.getCoste();
        Ruta mejorRuta    = ruta;
        
        // Por cada posición posible de cambio.
        for (int pos = 0; pos < ruta.getMaximasCiudades(); pos++) {
            // Por cada ciudad. Pruebo a cambiar la ciudad a esa posición.
            for (int i = 0; i < ruta.getMaximasCiudades(); i++) {
                // Saltarse a ciudad que ocupa dicha posición
                if (pos == i)
                    continue;
                
                // Muevo la ciudad a dicha posición
                Ruta rutaTemp = ruta.Clona();
                rutaTemp.mueve(i, pos);
                
                // Veo si el coste es menor
                double coste = rutaTemp.getCoste();
                if (coste < menorCoste) {
                    menorCoste = coste;
                    mejorRuta  = rutaTemp;
                }
            }   // Por cada ciudad
        } // Por cada posible posición
        
        return mejorRuta;
    }
}

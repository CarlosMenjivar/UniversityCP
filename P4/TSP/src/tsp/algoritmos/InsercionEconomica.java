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

import java.util.List;
import tsp.Ciudad;
import tsp.Ruta;

/**
 * Implementa el algoritmo de "Inserción económica".
 * La siguiente ciudad vendrá dada según genere el menor coste en la ruta.
 * 
 * @version 1.0
 * @author  Benito Palacios Sánchez
 */
public class InsercionEconomica implements IInsercion {
    
    @Override
    public void insertaSiguiente(final List<Ciudad> sinVisitar, final Ruta ruta) {
        // La siguiente ciudad podría ser añadida al principio o al final.
        // Pruebo en ambos sitios y escojo el que menor coste dé.
        double menorCoste  = Integer.MAX_VALUE;
        Ciudad mejorOpcion = null;
        int    posicion    = -1;
        
        for (int i = ruta.getPrimeraCiudad(); i <= ruta.getUltimaCiudad(); i++) {
            // Creo una ruta temporal con hueco para insertar ciudad en esta pos
            Ruta rutaPos = ruta.Clona();
            rutaPos.insertCiudad(null, i);
            
            // Por cada ciudad sin visitar, la establezco en dicha posición y
            // veo si el coste mejora
            for (Ciudad ciudad : sinVisitar) {
                rutaPos.setCiudad(ciudad, i);
                double coste = rutaPos.getCoste();
                
                // Si se da el caso, actualizar los límites
                if (coste < menorCoste) {
                    menorCoste  = coste;
                    mejorOpcion = ciudad;
                    posicion    = i;
                }
            } // Por cada ciudad
        } // Por cada posible posición
        
        // Finalmente, inserto la mejor ciudad.
        sinVisitar.remove(mejorOpcion);
        ruta.insertCiudad(mejorOpcion, posicion);
    }
}

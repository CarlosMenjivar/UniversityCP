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
        
        // Insertando la ciudad al principio de la ruta (si se puede)
        boolean mejorPrimero = true;
        if (ruta.getPrimeraCiudad() > 0) {
            int idx = ruta.getPrimeraCiudad() - 1;
            
            // Por cada ciudad, veo si genera un menor coste.
            for (Ciudad c : sinVisitar) {
                double coste = this.getCosteEnPosicion(c, idx, ruta);
                if (coste < menorCoste) {
                    mejorOpcion = c;
                    menorCoste  = coste;
                }
            }
        }
        
        // Insertando la ciudad al final de la ruta (si se puede)
        if (ruta.getUltimaCiudad() + 1 < ruta.getMaximasCiudades()) {
            int idx = ruta.getUltimaCiudad() + 1;
            
            // Por cada ciudad, veo si genera un menor coste.
            for (Ciudad c : sinVisitar) {
                double coste = this.getCosteEnPosicion(c, idx, ruta);
                if (coste < menorCoste) {
                    mejorOpcion  = c;
                    menorCoste   = coste;
                    mejorPrimero = false;   // Entonces es mejor al final.
                }
            }
        }
        
        // Finalmente, inserto la mejor ciudad.
        sinVisitar.remove(mejorOpcion);
        if (mejorPrimero)
            ruta.setCiudad(mejorOpcion, ruta.getPrimeraCiudad() - 1);
        else
            ruta.setCiudad(mejorOpcion, ruta.getUltimaCiudad() + 1);
    }
    
    /**
     * Devuelve el coste que tendría la ruta si se añade una ciudad en
     * una posición.
     * 
     * @param ciudad Ciudad a insertar en la ruta
     * @param posicion Posición donde se insertará dicha ciudad.
     * @param ruta Ruta base.
     * @return Coste de la ruta.
     */
    private double getCosteEnPosicion(final Ciudad ciudad, final int posicion,
           final Ruta ruta) {
        
        Ruta nuevaRuta = ruta.Clona();
        nuevaRuta.setCiudad(ciudad, posicion);
        return nuevaRuta.getCoste();
    }
}

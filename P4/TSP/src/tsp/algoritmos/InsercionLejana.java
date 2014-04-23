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
 *
 * @version 1.0
 * @author  Benito Palacios Sánchez
 */
public class InsercionLejana implements IInsercion {
    
    @Override
    public void insertaSiguiente(final List<Ciudad> sinVisitar, final Ruta ruta) {
        // Por cada ciudad calculo la posición donde insertada está más cerca
        // de otra ciudad. Cojo la ciudad que tenga dicha distancia máxima.
        double mayorDistancia = -1;
        Ciudad mejorOpcion    = null;
        int    mejorPosicion  = -1;
        
        Ciudad[] ciudadesRuta = ruta.getRuta();
        for (Ciudad ciudad : sinVisitar) {
            // Obtengo la menor distancia para esta ciudad y la posición en la
            // que dicha distancia se da
            int pos = this.getPosicionMinimaDistancia(ciudadesRuta, ciudad);
            double distancia = ciudad.getDistancia(ciudadesRuta[pos]);
            
            // Si esta distancia mínima es mayor a las otras guardarla
            if (distancia > mayorDistancia) {
                mayorDistancia = distancia;
                mejorOpcion    = ciudad;
                mejorPosicion  = pos;   // Esta posición es relativa a la primera
            }
        }
        
        // Finalmente, inserto la mejor ciudad.
        sinVisitar.remove(mejorOpcion);
        ruta.insertCiudad(mejorOpcion, mejorPosicion + ruta.getPrimeraCiudad());
    }
    
    private int getPosicionMinimaDistancia(final Ciudad[] ruta, final Ciudad ciudad) {
        double minimaDistancia = Double.MAX_VALUE;
        int posicion = -1;
        
        for (int i = 0; i < ruta.length; i++) {
            double distancia = ciudad.getDistancia(ruta[i]);
            if (distancia < minimaDistancia) {
                minimaDistancia = distancia;
                posicion = i;
            }
        } // Por cada posible posición  
        
        return posicion;
    }
}

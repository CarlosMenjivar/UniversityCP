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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tsp.Ciudad;
import tsp.Problema;
import tsp.Ruta;

/**
 * Algoritmo mejorado con diferentes estrategias de inserción.
 * Resuleve el problema TSP.
 * 
 * @version 1.0
 * @author Benito Palacios Sánchez
 */
public class EstrategiaInsercion implements ITspAlgoritmo {
    /** Algoritmo de inserción usado. */
    private final IInsercion insercion;
    
    /**
     * Crea una nueva instancia de este algoritmo con el algoritmo de inserción
     * dado.
     * 
     * @param insercion Algoritmo de inserción a usar.
     */
    public EstrategiaInsercion(final IInsercion insercion) {
        this.insercion = insercion;
    }
    
    @Override
    public Ruta calculaMejorRuta(final Problema problema) {
        // Ciudades sin visitar, para los algoritmos de inserción
        List<Ciudad> sinVisitar = new ArrayList<>();
        sinVisitar.addAll(Arrays.asList(problema.getCiudades()));
        
        // Genero la ruta inicial, y elimino esas ciudades de "sinVisitar".
        Ruta ruta = this.calculaRutaInicial(problema);
        sinVisitar.removeAll(Arrays.asList(ruta.getRuta()));
        
        // Mientras haya ciudades sin visitar, llamo al algoritmo de inserción
        while (sinVisitar.size() > 0)
            this.insercion.insertaSiguiente(sinVisitar, ruta);
        
        return ruta;
    }
    
    /**
     * Genera el recorrido inicial del que se parte.
     * Coge las tres ciudades que forman el triángulo más grande.
     * 
     * @param problema
     * @return 
     */
    private Ruta calculaRutaInicial(final Problema problema) {
        Ruta ruta = new Ruta(problema.getNumCiudades());
        
        // Ciudad más arriba
        double yMax = -1;
        for (Ciudad c : problema.getCiudades()) {
            if (c.getCoordY() > yMax) {
                yMax = c.getCoordY();
                ruta.setCiudad(c, 0);
            }
        }
        
        // Ciudad más a la izquierda
        double xMin = Double.MAX_VALUE;
        for (Ciudad c : problema.getCiudades()) {
            if (c.getCoordX() < xMin) {
                xMin = c.getCoordX();
                ruta.setCiudad(c, 1);
            }
        }
        
        // Ciudad más abajo
        double yMin = Double.MAX_VALUE;
        for (Ciudad c : problema.getCiudades()) {
            if (c.getCoordY() < yMin) {
                yMin = c.getCoordY();
                ruta.setCiudad(c, 2);
            }
        }
        
        return ruta;
    }
}

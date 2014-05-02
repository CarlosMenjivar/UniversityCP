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

import java.util.Arrays;
import java.util.List;
import tsp.Ciudad;
import tsp.Problema;
import tsp.Ruta;

/**
 * Implementación de un algoritmo de MonteCarlo para resolver el problema TSP.
 * Genera rutas aleatorias y escoge la mejor.
 * 
 * @version 1.0
 * @author  Benito Palacios Sánchez
 */
public class RutaAleatoria implements ITspAlgoritmo {
    /** Número de permutaciones, rutas aleatorias a generar */
    private final int intentos;
    
    /** Algoritmo para mejorar la ruta aleatoria */
    private final IMejoraRuta mejorar;
    
    /**
     * Crea una instancia del algoritmo de ruta aleatoria.
     * Las rutas generadas no son mejoradas.
     * 
     * @param intentos Número de rutas aleatorias a generar.
     */
    public RutaAleatoria(final int intentos) {
        this.intentos = intentos;
        
        // Por defecto no se realiza ninguna mejora sobre la ruta.
        // Algoritmo 4.
        this.mejorar = new IMejoraRuta() {
            @Override
            public Ruta mejoraRuta(final Ruta ruta) {
               return ruta; 
            }
        };
    }
    
    /**
     * Crea una instancia del algoritmo de ruta aleatoria mejorando.
     * 
     * @param intentos Número de rutas aleatorias a generar.
     * @param mejorar Algoritmo de mejora de la ruta aleatoria.
     */
    public RutaAleatoria(final int intentos, final IMejoraRuta mejorar) {
        this.intentos = intentos;
        this.mejorar  = mejorar;
    }
    
    @Override
    public Ruta calculaMejorRuta(final Problema problema) {
        Ruta mejorRuta    = null;
        double menorCoste = Double.MAX_VALUE;
        
        // Genero rutas aleatorias y escojo la mejor
        List<Ciudad> ciudadesRuta = Arrays.asList(problema.getCiudades());        
        for (int i = 0; i < this.intentos; i++) {
            // Genero la ruta aleatoria
            Ruta ruta = Ruta.Aleatoria(ciudadesRuta);
            ruta.setDistancias(problema.getDistancias());
            
            // Mejoro la ruta
            ruta = this.mejorar.mejoraRuta(ruta);
            
            // Compruebo si el coste es menor al mejor actual.
            double coste = ruta.getCoste();
            if (coste < menorCoste) {
                menorCoste = coste;
                mejorRuta  = ruta;
            }
        }
        
        return mejorRuta;
    }
}

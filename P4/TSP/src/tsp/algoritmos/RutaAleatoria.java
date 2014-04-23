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
import java.util.Random;
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
    private final int permutaciones;
    
    public RutaAleatoria(final int permutaciones) {
        this.permutaciones = permutaciones;
    }
    
    @Override
    public Ruta calculaMejorRuta(Problema problema) {
        Ruta mejorRuta    = null;
        double menorCoste = Double.MAX_VALUE;
        
        // Genero rutas aleatorias y escojo la mejor
        List<Ciudad> ciudadesRuta = Arrays.asList(problema.getCiudades());        
        for (int i = 0; i < this.permutaciones; i++) {
            // Genero la ruta aleatoria
            Ruta ruta = this.generaRuta(ciudadesRuta);
            
            // Compruebo si el coste es menor al mejor actual.
            double coste = ruta.getCoste();
            if (coste < menorCoste) {
                menorCoste = coste;
                mejorRuta  = ruta;
            }
        }
        
        return mejorRuta;
    }
    
    
    private Ruta generaRuta(List<Ciudad> ciudades) {
        Ruta ruta = new Ruta(ciudades.size());
        Random random = new Random();
        
        // Listas ir sacando ciudades y posiciones sin repetir.
        List<Ciudad>  ciuSinVisitar = new ArrayList(ciudades);
        List<Integer> posSinVisitar = new ArrayList(ciudades.size());

        // Genero la lista de posiciones, gracias a esto aseguro que no
        // establezco una ciudad en una posición ocupada por otra.
        for (int i = 0; i < ciudades.size(); i++)
            posSinVisitar.add(i);        
        
        // Escojo una ciudad al azar y la introduzco en una posición al azar
        while (ciuSinVisitar.size() > 0) {
            // Ciudad al azar
            int idx = random.nextInt(ciuSinVisitar.size());
            Ciudad ciudad = ciuSinVisitar.remove(idx);

            // Posición al azar
            idx = random.nextInt(posSinVisitar.size());
            int pos = posSinVisitar.remove(idx);

            // La inserto
            ruta.setCiudad(ciudad, pos, true);
        }
        
        return ruta;
    }
}

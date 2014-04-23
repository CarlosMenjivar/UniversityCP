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
 * Interfaz para implementar algoritmos de inserción.
 * Principal uso en {@link EstrategiaInsercion}.
 * 
 * @version 1.0
 * @author Benito Palacios Sánchez
 */
public interface IInsercion {
    
    /**
     * Inserta una ciudad en la ruta de forma óptima.
     * 
     * @param sinVisitar Lista de ciudades sin visitar todavía. Se actualizará.
     * @param ruta Ruta que se está generando.
     */
    void insertaSiguiente(final List<Ciudad> sinVisitar, final Ruta ruta);
}

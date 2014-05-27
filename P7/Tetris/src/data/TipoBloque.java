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
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */

package data;

/**
 * Tipo de bloque.
 * 
 * @author Benito Palacios Sánchez
 */
public enum TipoBloque {
    /** Es un bloque libre para ocupar. */
    VACIO(0),
    
    /** El bloque pertenece a una figura. */
    FIGURA(1),
    
    /** El bloque pertenece al escenario. */
    ESCENARIO(2),
    
    /** El bloque pertenece a los que se han fijado al final del escnario. */
    FIJO(3);
    
    private final int id;
    
    TipoBloque(int id) {
        this.id = id;
    }
    
    /**
     * Obtiene el tipo de bloque a partir del ID.
     * 
     * @param id ID del tipo.
     * @return Tipo de bloque o 'null' si no encontrado.
     */
    public static TipoBloque FromId(final int id) {
        for (TipoBloque tipo : TipoBloque.values())
            if (tipo.getId() == id)
                return tipo;
        
        return null;
    }
    
    /**
     * Obtiene el ID asociado.
     * 
     * @return ID asociado.
     */
    public int getId() {
        return this.id;
    }
}

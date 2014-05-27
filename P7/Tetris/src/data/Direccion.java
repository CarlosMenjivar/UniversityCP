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
 * Dirección de movimiento.
 * 
 * @author Benito Palacios Sánchez
 */
public enum Direccion {
    ABAJO,
    ARRIBA,
    IZQUIERDA,
    DERECHA;
    
    public Direccion getContrario() {
        switch (this) {
            case ABAJO: return ARRIBA;
            case ARRIBA: return ABAJO;
            case IZQUIERDA: return DERECHA;
            case DERECHA: return IZQUIERDA;
        }
        
        return ABAJO;
    }
}

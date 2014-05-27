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

import java.awt.Color;

/**
 * Representa un bloque del juego.
 * 
 * @author Benito Palacios Sánchez
 */
public class Bloque {
    private final TipoBloque tipo;
    private final Color color;

    /**
     * Crea un nuevo bloque a partir de sus parámetros.
     * 
     * @param tipo Tipo de bloque.
     * @param color Color del bloque.
     */
    public Bloque(final TipoBloque tipo, final Color color) {
        this.tipo = tipo;
        this.color = color;
    }

    /**
     * Obtiene el tipo de bloque.
     * 
     * @return Tipo de bloque.
     */
    public TipoBloque getTipo() {
        return tipo;
    }

    /**
     * Obtiene el color del bloque.
     * 
     * @return Color del bloque.
     */
    public Color getColor() {
        return color;
    }    
}

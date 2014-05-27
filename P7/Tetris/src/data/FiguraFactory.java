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
 * Creación de figuras predefinidas.
 * 
 * @author Benito Palacios Sánchez
 */
public class FiguraFactory {
    /**
     * Crea una figura con forma de 'L'.
     * 
     * @param posicion Posición de la figura.
     * @return Nueva figura.
     */
    public static Figura getL(final Punto posicion) {
        return new Figura(posicion, Color.GREEN, 0x0, 0x1, 0x1, 0x3);
    }
    
    /**
     * Crea una figura con forma de 'L' invertida.
     * 
     * @param posicion Posición de la figura.
     * @return Nueva figura.
     */
    public static Figura getLInversa(final Punto posicion) {
        return new Figura(posicion, Color.GREEN, 0x0, 0x2, 0x2, 0x3);
    }
    
    /**
     * Crea una figura con forma de cuadrado.
     * 
     * @param posicion Posición de la figura.
     * @return Nueva figura.
     */
    public static Figura getCuadrado(final Punto posicion) {
        return new Figura(posicion, Color.CYAN, 0x0, 0x0, 0x3, 0x3);
    }
    
    /**
     * Crea una figura con forma de 'Z'.
     * 
     * @param posicion Posición de la figura.
     * @return Nueva figura.
     */
    public static Figura getZ(final Punto posicion) {
        return new Figura(posicion, Color.MAGENTA, 0x0, 0x0, 0x3, 0x6);
    }
    
    /**
     * Crea una figura con forma de 'Z' invertida.
     * 
     * @param posicion Posición de la figura.
     * @return Nueva figura.
     */
    public static Figura getZInversa(final Punto posicion) {
        return new Figura(posicion, Color.MAGENTA, 0x0, 0x0, 0x6, 0x3);
    }
    
    /**
     * Crea una figura con forma de 'I'.
     * 
     * @param posicion Posición de la figura.
     * @return Nueva figura.
     */
    public static Figura getI(final Punto posicion) {
        return new Figura(posicion, Color.YELLOW, 0x1, 0x1, 0x1, 0x1);
    }
    
    /**
     * Crea una figura con forma de 'T' invertida.
     * 
     * @param posicion Posición de la figura.
     * @return Nueva figura.
     */
    public static Figura getTInversa(final Punto posicion) {
        return new Figura(posicion, Color.RED, 0x0, 0x0, 0x2, 0x7);
    }
    
    /**
     * Crea una figura de forma aleatoria.
     * 
     * @param posicion Posición de la figura.
     * @return Nueva figura.
     */
    public static Figura getAleatoria(final Punto posicion) {
        final int NUM_FIGURAS = 7;
        int random = (int)Math.round(Math.random() * (NUM_FIGURAS - 1));
        switch (random) {
            case 0: return getL(posicion);
            case 1: return getLInversa(posicion);
            case 2: return getCuadrado(posicion);
            case 3: return getZ(posicion);
            case 4: return getZInversa(posicion);
            case 5: return getI(posicion);
            case 6: return getTInversa(posicion);
        }
        
        return null;
    }
}

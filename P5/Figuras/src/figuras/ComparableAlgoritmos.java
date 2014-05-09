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

package figuras;

/**
 *
 * @author Benito Palacios Sánchez
 */
public class ComparableAlgoritmos {
    public static Figura mayor(final Figura[] figuras) {
        Figura mayor = null;
        for (Figura figura : figuras) {
            if (mayor == null || figura.compareTo(mayor) == 1)
                mayor = figura;
        }
        
        return mayor;
    }
        
    public static void ordena(final Figura[] figuras) {
        for (int i = 0; i < figuras.length - 1; i++) {
            int mayor = indiceDeMayor(figuras, i);
            
            Figura swap = figuras[i];
            figuras[i]  = figuras[mayor];
            figuras[mayor] = swap;
        }
    }
    
    private static int indiceDeMayor(final Figura[] figuras, final int inicio) {
        int indice = -1;
        for (int i = inicio; i < figuras.length; i++) {
            if (indice == -1 || figuras[i].compareTo(figuras[indice]) == 1)
                indice = i;
        }
        
        return indice;
    }
}

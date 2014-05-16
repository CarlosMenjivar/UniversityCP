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

package iotests;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Programa simple que calcula el tamaño de un fichero contando los bytes que
 * se pueden leer.
 *
 * @author Benito Palacios Sánchez
 */
public class CalculaTamano {
    public static void main(String[] args) {
        args = new String[] { "temp.dat" };
        
        if (args.length != 1) {
            System.out.println("USO: iotests/CalculaTamano fichero");
            return;
        }
        
        try {
            FileInputStream reader = new FileInputStream(args[0]);
            int count = 0;
            
            // Lee hasta encontrar el final
            while (reader.read() != -1)
                count++;
            
            System.out.println("El tamaño del fichero es " + count + " bytes");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

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
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Programa simple que escribe los valores del 1 al 10 de forma binaria en un
 * archivo.
 * 
 * @author Benito Palacios Sánchez
 */
public class EscribeArchivo {
    public static void main(String[] args) {
        try {
            // Escribo los datos en el fichero
            FileOutputStream writer = new FileOutputStream("temp.dat");
            for (int i = 1; i <= 10; i++)
                writer.write(i);
            writer.close();
            
            // Leo los datos del fichero
            FileInputStream reader = new FileInputStream("temp.dat");
            while (reader.available() > 0)
                System.out.print(reader.read() + " ");
            reader.close();
        } catch (IOException ex) {
            
        }
    }
}

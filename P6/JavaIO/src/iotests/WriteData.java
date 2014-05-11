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

import java.io.File;
import java.io.PrintWriter;

/**
 * Programa sencillo para escribir en un fichero de texto.
 * 
 * @author Benito Palacios Sánchez
 */
public class WriteData
{
    /**
     * Crea un fichero de texto con dos líneas de texto y números.
     * 
     * @param args Ninguno.
     * @throws java.io.IOException Error al intentar crear el fichero.
     */
    public static void main(String[] args) throws java.io.IOException {
        File file = new File("scores.txt");
        
        // Comprueba si el fichero ya existe, entonces sale.
        if (file.exists()) {
            System.out.println("El fichero ya existe");
            System.exit(0);
        }
        
        // Crear el fichero
        PrintWriter output = new PrintWriter(file);
        // Escribir salida formateada en el fichero
        output.print("Jacinto Campos ");
        output.println(90);
        output.print("Buenaventura Olmedo ");
        output.println(85);
        // Cerrar el fichero
        output.close();
    }
}
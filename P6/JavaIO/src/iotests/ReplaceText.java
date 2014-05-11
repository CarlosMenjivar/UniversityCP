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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Programa que reemplaza una cadena de texto por otra y lo escribe en un
 * fichero nuevo.
 * 
 * @author Benito Palacios Sánchez
 */
public class ReplaceText {
    
    /**
     * Reemplaza una cadena de texto por otra y escribe un nuevo archivo.
     * 
     * @param args Por orden: fichero original, fichero de salida, cadena
     * a reemplazar y cadena nueva que reemplazará.
     * @throws IOException En caso de no poder crear el nuevo fichero.
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            System.out.println("USO: ReplaceText original nueva strAnti strNuev");
            System.exit(1);
        }
        
        // Abre los archivos y crea los lectores / escritores
        File original  = new File(args[0]);
        Scanner reader = new Scanner(original);
        PrintWriter writer = new PrintWriter(args[1]);
        
        String strAntiguo = args[2];
        String strNuevo   = args[3];
        
        // Establece el delimitador como la cadena a reemplazar
        reader.useDelimiter(strAntiguo);
        
        // Si el archivo comienza con el delimitador el scanner lo saltará
        // automáticamente por eso hay que realizar la comprobación y en caso
        // de encontrarlo escribir la cadena de reemplazo.
        if (reader.findInLine("^" + strAntiguo) != null)
            writer.print(strNuevo);
        
        // Mientras siga existiendo la cadena a reemplazar...
        while (reader.hasNext()) {
            // Lee y escribe el texto hasta la cadena
            writer.print(reader.next());
            
            // En caso de que no se haya alcanzado el final del fichero
            // significa que se ha encontrado la cadena, así que se escribe
            // la nueva.
            if (reader.hasNextLine())
                writer.print(strNuevo);
        }
        
        // Cierra los archivos.
        reader.close();
        writer.close();
    }
}

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
import java.util.Scanner;

/**
 * Programa sencillo que lee un fichero de texto.
 * 
 * @author Benito Palacios Sánchez
 */
public class ReadData {

    /**
     * Abre el fichero de textocreado en WriteData y muestra su información.
     * 
     * @param args Ninguno
     * @throws java.io.IOException Error al abrir el archivo.
     */
    public static void main(String[] args) throws java.io.IOException {
        // Crear objeto File
        File file = new File("scores.txt");
        
        // Crear un Scanner para el File
        Scanner input = new Scanner(file);
        
        // Leer datos del fichero
        while (input.hasNext()) {
            String firstName = input.next();
            String lastName  = input.next();
            int score = input.nextInt();
            System.out.println(firstName + " " + lastName + " " + score);
        }
        
        // Cerrar el fichero
        input.close();
    }
}

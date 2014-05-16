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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Programa sencillo que convierte las instancias de la práctica 4 de ficheros
 * de texto a ficheros binarios.
 * 
 * @author Benito Palacios Sánchez
 */
public class TransformaABinarioTSP {
    private final static String NumCiudadesRegex = "DIMENSION\\s?:\\s*(\\d+)";
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("USO: iotests/TransformaABinarioTSP entrada salida");
            return;
        }
        
        try {
            // Lee el fichero de texto y va escribiendo el fichero binario
            Scanner reader = new Scanner(new File(args[0]));
            DataOutputStream writer = new DataOutputStream(
                    new FileOutputStream(args[1]));
            
            // Obtiene el número de ciudades
            reader.findInLine(NumCiudadesRegex);
            MatchResult result = reader.match();
            if (result.groupCount() != 1)
                return;
            int numCiudades = Integer.parseInt(result.group(1));
            writer.writeInt(numCiudades);
            
            // Por cada ciudad lee y escribe
            for (int i = 0; i < numCiudades; i++) {
                int id = reader.nextInt();
                writer.writeInt(id);
                
                double posX = reader.nextDouble();
                writer.writeDouble(posX);
                
                double posY = reader.nextDouble();
                writer.writeDouble(posY);
            }
            
            reader.close();
            writer.close();
            
            // Finalmente lee el nuevo archivo y lo muestra
            DataInputStream readerBin = new DataInputStream(
                    new FileInputStream(args[1]));
            
            numCiudades = readerBin.readInt();
            System.out.println("DIMENSIÓN: " + numCiudades);
            
            for (int i = 0; i < numCiudades; i++) {
                System.out.print(readerBin.readInt() + " ");    // ID
                System.out.print(readerBin.readDouble() + " "); // Pos X
                System.out.println(readerBin.readDouble());     // Pos Y
            }      
            
            readerBin.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

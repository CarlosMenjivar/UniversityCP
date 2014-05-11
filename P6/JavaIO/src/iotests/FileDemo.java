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

/**
 * Programa simple que muestra información de un fichero por la consola.
 * 
 * @author Benito Palacios Sánchez
 */
public class FileDemo
{  
    /**
     * Programa simple que abre este archivo y muestra su información.
     * 
     * @param args Ninguno.
     */
    public static void main(String args[]) {
        File f1 = new File("src/iotests/FileDemo.java");
        print("File Name: " + f1.getName());
        print("Path: " + f1.getPath());
        print("Abs Path: " + f1.getAbsolutePath());
        print("Parent: " + f1.getParent());
        print(f1.exists() ? "exists" : "does not exist");
        print(f1.canWrite() ? "is writeable" : "is not writeable");
        print(f1.canRead() ? "is readable" : "is not readable");
        print("is " + (f1.isDirectory() ? "" : "not" + " a directory"));
        print(f1.isFile() ? "is normal file" : "might be a named pipe");
        print(f1.isAbsolute() ? "is absolute" : "is not absolute");
        print("File last modified: " + f1.lastModified());
        print("File size: " + f1.length() + " Bytes");
    }
    
    /**
     * Muestra el mensaje por la salida estándar.
     * 
     * @param s Mensaje a mostrar.
     */
    private static void print(final String s) {
        System.out.println(s);
    }
}
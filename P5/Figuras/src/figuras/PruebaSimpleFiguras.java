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

import java.util.Date;
import java.util.Scanner;

/**
 * Clase para realizar pruebas sobre los métodos de las figuras.
 * 
 * @version 1.0
 * @author  Benito Palacios Sánchez
 */
public class PruebaSimpleFiguras {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Lee un círculo desde la entrada estándar
        System.out.print("Introduzca el radio del círculo: ");
        Circulo circulo = Circulo.fromScanner(scanner);
        System.out.println(circulo);
        System.out.println();
        
        // Lee un rectángulo desde la entrada estándar
        System.out.print("Introduzca ancho y alto del rectángulo: ");
        Rectangulo rectangulo = Rectangulo.fromScanner(scanner);
        System.out.println(rectangulo);
        System.out.println();
        
        // Imprimo datos sobre las figuras de nuevo
        imprime(circulo);
        System.out.println();
        
        imprime(rectangulo);
        System.out.println();
    }
    
    public static void imprime(Figura figura) {
        long tiempo = (new Date()).getTime() - figura.getFechaCreacion().getTime();
        
        System.out.println(figura.getClass().getSimpleName());
        System.out.println("Tiempo desde su creación: " + tiempo + " milisegundos");
        System.out.println("Área: " + figura.getArea());
        System.out.println("Perímetro: " + figura.getPerimetro());
    }
}

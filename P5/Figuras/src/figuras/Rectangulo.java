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

import java.util.Scanner;

/**
 * Representa un rectángulo geométrico.
 * 
 * @version 1.0
 * @author  Benito Palacios Sánchez
 */
public class Rectangulo extends Figura {
    /** Ancho del rectángulo */
    private double ancho;
    
    /** Alto del rectángulo */
    private double alto;
    
    /**
     * Crea una instancia con lados nulos.
     */
    public Rectangulo() {
        this.ancho = 0;
        this.alto  = 0;
    }

    /**
     * Crea una instancia con el tamaño especificado.
     * 
     * @param ancho Ancho del rectángulo.
     * @param alto Alto del rectángulo.
     */
    public Rectangulo(final double ancho, final double alto) {
        this.ancho = ancho;
        this.alto  = alto;
    }
    
    /**
     * Crea una nueva instancia leyendo sus parámetros usando el escaner.
     * 
     * @param scanner Escaner que se usará para leer los parámetros.
     * @return Nuevo rectángulo.
     */
    public static Rectangulo fromScanner(final Scanner scanner) {
        return new Rectangulo(scanner.nextDouble(), scanner.nextDouble());
    }
    
    /**
     * Devuelve el ancho del rectángulo.
     * 
     * @return Ancho del rectángulo.
     */
    public double getAncho() {
        return this.ancho;
    }
    
    /**
     * Establece el ancho del rectángulo.
     * 
     * @param value Nuevo ancho.
     */
    public void setAncho(final double value) {
        this.ancho = value;
    }
    
    /**
     * Devuelve el alto del rectángulo.
     * 
     * @return Alto del rectángulo.
     */
    public double getAlto() {
        return this.alto;
    }
    
    /**
     * Establece el alto del rectángulo.
     * 
     * @param value Nuevo alto.
     */
    public void setAlto(final double value) {
        this.alto = value;
    }
    
    @Override
    public double getArea() {
        return this.ancho * this.alto;
    }

    @Override
    public double getPerimetro() {
        return 2 * this.ancho + 2 * this.alto;
    }
    
    @Override
    public String toString() {
        return String.format("%s\nAncho: %.1f\nAlto: %.1f",
                super.toString(),
                this.ancho,
                this.alto
        );
    }
}

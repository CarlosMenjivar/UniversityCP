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
 * Representa un círculo geométrico.
 * 
 * @version 1.0
 * @author  Benito Palacios Sánchez
 */
public class Circulo extends Figura {
    /** Radio del círculo */
    private double radio;
    
    /**
     * Crea una instancia con radio nulo.
     */
    public Circulo() {
        this.radio = 0;
    }
    
    /**
     * Crea una instancia con el radio especificado.
     * 
     * @param radio Radio del círculo. 
     */
    public Circulo(final double radio) {
        this.radio = radio;
    }
    
    /**
     * Crea una instancia de circulo leyendo el radio usando el escaner.
     * 
     * @param scanner Desde donde leerá el radio.
     * @return Nuevo círculo.
     */
    public static Circulo fromScanner(Scanner scanner) {
        return new Circulo(scanner.nextDouble());
    }
    
    /**
     * Obtiene el radio del círculo
     * 
     * @return Radio del círculo
     */
    public double getRadio() {
        return this.radio;
    }
    
    /**
     * Establece el radio del círculo.
     * 
     * @param value Nuevo radio.
     */
    public void setRadio(final double value) {
        this.radio = value;
    }
    
    @Override
    public double getArea() {
        return Math.PI * this.radio * this.radio;
    }

    @Override
    public double getPerimetro() {
        return 2 * Math.PI * this.radio;
    }
    
    @Override
    public String toString() {
        return String.format("%s\nRadio: %.1f",
                super.toString(),
                this.radio
        );
    }
}

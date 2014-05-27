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
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */

package data;

import java.awt.Color;
import java.util.Arrays;

/**
 * Representa una figura del juego.
 * 
 * @author Benito Palacios Sánchez
 */
public class Figura {
    
    private static final int SIZE_X = 4;
    private static final int SIZE_Y = 4;
    
    private Bloque[][] figura;
    private Punto point;
    
    /**
     * Crea una nueva instancia de figura a partir de una defición del modelo.
     * 
     * @param point Posición de la figura.
     * @param color Color de los bloques de la figura.
     * @param filas Cada entero representa una fila. Cada bit representa si
     * hay un bloque o no.
     */
    public Figura(final Punto point, final Color color, int ... filas) {
        if (filas.length > SIZE_Y)
            throw new ArrayIndexOutOfBoundsException("Demasiadas filas");
        
        this.point  = point;
        this.figura = new Bloque[SIZE_Y][SIZE_X];
        
        // Por cada fila
        for (int y = 0; y < figura.length; y++) {
            // Por cada columna de la fila
            for (int x = 0; x < figura.length; x++) {
                if (y >= filas.length) {
                    this.figura[y][x] = new Bloque(TipoBloque.VACIO, Color.white);
                } else {
                    int valor = (filas[y] >> x) & 1;
                    TipoBloque tipo = (valor == 1) ? TipoBloque.FIGURA : TipoBloque.VACIO;
                    this.figura[y][x] = new Bloque(tipo, color);
                }
            }
        }
        
        // Desplaza la figura para asegurarnos de que está en el borde inferior.
        this.shift();
    }
    
    /**
     * Obtiene el número de columnas de la figura.
     * 
     * @return Número de columnas.
     */
    public int getNumColumns() {
        return SIZE_X;
    }
    
    /**
     * Obtiene el número de filas de la figura.
     * 
     * @return Número de filas.
     */
    public int getNumRows() {
        return SIZE_Y;
    }
    
    /**
     * Obtiene un bloque de la figura.
     * 
     * @param r Fila del bloque.
     * @param c Columna del bloque.
     * @return Bloque de la figura.
     */
    public Bloque getBloque(final int r, final int c) {
        if (r < 0 || r >= SIZE_Y)
            throw new ArrayIndexOutOfBoundsException();
        
        if (c < 0 || c >= SIZE_X)
            throw new ArrayIndexOutOfBoundsException();
        
        return this.figura[r][c];
    }
    
    /**
     * Obtiene la posición de la figura.
     * 
     * @return Posición de la figura.
     */
    public Punto getLocation() {
        return this.point;
    }
    
    /**
     * Mueve la figura la cantidad dada en el sentido dado.
     * 
     * @param direccion Dirección de movimiento.
     * @param cty Cantidad de movimiento.
     */
    public void move(final Direccion direccion, final int cty) {
        switch (direccion) {
            case ABAJO: this.point = this.point.offset(0, cty); break;
            case ARRIBA: this.point = this.point.offset(0, -cty); break;
            case DERECHA: this.point = this.point.offset(cty, 0); break;
            case IZQUIERDA: this.point = this.point.offset(-cty, 0); break;
        }
    }
    
    /**
     * Rota la figura en la dirección especificada.
     * 
     * @param direccion Dirección en la que rotar la figura.
     *  + ABAJO o DERECHA: en sentido de las agujas del reloj.
     *  + ARRIBA o IZQUIERDA: en sentido contrario a las agujas del reloj.
     */
    public void rotate(final Direccion direccion) {
        int a = 0;  // Como el ángulo es siempre 90º -> cos(90) = cos(-90) = 0;
        int b = 1;  // sen(90º) = 1 -> giro en sentido contrario al reloj.
        if (direccion == Direccion.ABAJO || direccion == Direccion.DERECHA)
            b = -1; // sen(-90º) = -1 -> giro en el sentdio del reloj.
        
        // Realiza la rotación para cada bloque de la figura y lo pone
        // en una nueva figura.
        Bloque[][] antigua = (Bloque[][])this.figura.clone();
        this.figura = new Bloque[SIZE_Y][SIZE_X];
        for (int x0 = 0; x0 < this.figura.length; x0++) {
            for (int y0 = 0; y0 < this.figura[x0].length; y0++) {
                Punto ant = new Punto(x0, y0);
                Punto rot = this.rotate(ant, a, b);
                int x1 = rot.getCoordX();
                int y1 = rot.getCoordY() + (this.figura[x0].length - 1);
                this.figura[x1][y1] = antigua[x0][y0];
            }
        }
        
        // Después de la rotación vuelvo a colocar la figura en el borde inferior.
        this.shift();
    }
    
    /**
     * Realiza la siguiente multiplicación matricial.
     * | x1 |   | a -b |   | x0 |
     * |    | = |      | * |    |
     * | y1 |   | b  a |   | y0 |
     * Donde:
     *   a = cos(alpha)
     *   b = sen(alpha)
     * 
     * @param punto Punto a rota (se rota con esas coordenadas).
     * @param a cos(alpha)
     * @param b sen(alpha)
     * @return Punto (vector) rotado.
     */
    private Punto rotate(final Punto punto, final int a, final int b) {       
        return new Punto(
                a * punto.getCoordX() - b * punto.getCoordY(),
                b * punto.getCoordX() + a * punto.getCoordY()
        );
    }
    
    /**
     * Obtiene el índice de la última fila que contiene bloques de la figura.
     * 
     * @return Índice de la última fila con bloques de la figura.
     */
    private int getBottonPoint() {
        int y = -1;
        
        // Comprueba cada fila hasta encontrar una que no sea nula
        for (int f = this.figura.length -1; f >= 0 && y == -1; f--) {
            // Comprueba cada bloque de la fila para ver si hay alguno lleno.
            boolean filaNula = true;
            for (int c = 0; c < this.figura[f].length && filaNula; c++) {
                if (this.figura[f][c].getTipo() != TipoBloque.VACIO)
                    filaNula = false;
            }
            
            // En caso de que no sea nula hemos terminado
            if (!filaNula)
                y = f;
        }
        
        return y;
    }
    
    /**
     * Desplaza una figura hacia el borde inferior.
     */
    private void shift() {
        int posDesp = this.getBottonPoint();
        this.shift(posDesp);
    }
    
    /**
     * Desplaza la figura hacia abajo partiendo de una posición.
     * 
     * @param pos Posición desde la que empezará el movimiento.
     */
    private void shift(int pos) {
        // Si no hay desde donde copiar o se va a copiar a sí mismo salir.
        if (pos == -1 || pos == this.figura.length - 1)
            return;
        
        int f;
        // Copio a partir de 'pos' hasta llegar al borde superior.
        for (f = this.figura.length - 1; pos >= 0; f--, pos--)
            System.arraycopy(this.figura[pos], 0, this.figura[f], 0, this.figura[f].length);
        
        // Las restantes filas son nulas
        for ( ; f >= 0; f--)
            Arrays.fill(this.figura[f], new Bloque(TipoBloque.VACIO, Color.yellow));
    }
}

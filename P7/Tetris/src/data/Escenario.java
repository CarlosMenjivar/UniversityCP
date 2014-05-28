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

/**
 * Representa el escenario del juego.
 * 
 * @author Benito Palacios Sánchez
 */
public class Escenario {
    
    private final int ancho;
    private final int alto;
    private final Bloque[][] escenario;
    
    private final Punto posInicio;
    private Figura figura;
    private Figura sigFigura;
    
    private int filasCompletas;
    
    public Escenario(final int ancho, final int alto) {
        this.ancho = ancho;
        this.alto  = alto;
        
        this.posInicio = new Punto(this.ancho / 2, 0);
        this.escenario = new Bloque[alto][ancho];
        this.inicializa();
    }
    
    public void inicializa() {
        this.figura    = FiguraFactory.getAleatoria(this.posInicio);
        this.sigFigura = FiguraFactory.getAleatoria(this.posInicio);
        this.filasCompletas = 0;
        
        Bloque bloqueEsc = new Bloque(TipoBloque.ESCENARIO, new Color(99, 81, 71));
        Bloque bloqueVac = new Bloque(TipoBloque.VACIO, Color.WHITE);
        
        for (int f = 0; f < this.alto; f++) {
            for (int c = 0; c < this.ancho; c++) {
                if (c < 2 || c >= ancho - 2)
                    this.escenario[f][c] = bloqueEsc;
                else if (f == this.alto - 1)
                    this.escenario[f][c] = bloqueEsc;
                else
                    this.escenario[f][c] = bloqueVac;
            }
        }
    }
    
    public int getAncho() {
        return this.ancho;
    }
    
    public int getAlto() {
        return this.alto;
    }
    
    public int getFilasCompletas() {
        return this.filasCompletas;
    }
    
    public Bloque getBloque(final int f, final int c) {
        if (f < 0 || f >= this.alto)
            throw new ArrayIndexOutOfBoundsException();
        
        if (c < 0 || c >= this.ancho)
            throw new ArrayIndexOutOfBoundsException();
        
        Punto figPos = this.figura.getLocation();
        int figAncho = this.figura.getNumColumns();
        int figAlto  = this.figura.getNumRows();
        if ((c >= figPos.getCoordX() && c < figPos.getCoordX() + figAncho) &&
                (f >= figPos.getCoordY() && f < figPos.getCoordY() + figAlto)) {
            
            Bloque bloque = this.figura.getBloque(f - figPos.getCoordY(), c - figPos.getCoordX());
            if (bloque.getTipo() != TipoBloque.VACIO)
                return bloque;
            else
                return this.escenario[f][c];
        } else {
            return this.escenario[f][c];
        }
    }
    
    public Figura getFiguraEnMovimiento() {
        return this.figura;
    }
    
    public Figura getFiguraSiguiente() {
        return this.sigFigura;
    }
    
    public boolean isPosicionFiguraValida() {
        int figX = this.figura.getLocation().getCoordX();
        int figY = this.figura.getLocation().getCoordY();
        
        for (int x = 0; x < this.figura.getNumColumns(); x++) {
            for (int y = 0; y < this.figura.getNumRows(); y++) {
                if (this.figura.getBloque(y, x).getTipo() != TipoBloque.FIGURA)
                    continue;
                
                if (this.escenario[y + figY][x + figX].getTipo() != TipoBloque.VACIO)
                    return false;
            }
        }
        
        return true;
    }
    
    public boolean isFiguraEnFondo() {
        int ultF = this.figura.getNumRows() - 1;
        int figY = this.figura.getLocation().getCoordY();
        int figX = this.figura.getLocation().getCoordX();
        
        for (int x = 0; x < this.figura.getNumColumns(); x++) {
            int y = ultF;
            if (this.figura.getBloque(ultF, x).getTipo() != TipoBloque.FIGURA) {
                for (y = ultF - 1; y >= 0; y--)
                    if (this.figura.getBloque(y, x).getTipo() == TipoBloque.FIGURA)
                        break;
            }
            
            if (y == -1)
                continue;
            
            if (this.escenario[y + figY + 1][x + figX].getTipo() == TipoBloque.FIJO ||
                this.escenario[y + figY + 1][x + figX].getTipo() == TipoBloque.ESCENARIO)
                return true;
        }
        
        return false;
    }
    
    public void fijaFigura() {
        // Copia los bloques de la figura como fijos
        int figX = this.figura.getLocation().getCoordX();
        int figY = this.figura.getLocation().getCoordY();
        for (int x = 0; x < this.figura.getNumColumns(); x++) {
            for (int y = 0; y < this.figura.getNumRows(); y++) {
                if (this.figura.getBloque(y, x).getTipo() != TipoBloque.FIGURA)
                    continue;
                
                this.escenario[figY + y][figX + x] = new Bloque(TipoBloque.FIJO, Color.gray);
            }
        }
        
        // Establece la siguiente figura y crea una nueva
        this.figura = this.sigFigura;
        this.sigFigura = FiguraFactory.getAleatoria(posInicio);

        // Quita las filas completas
        this.eliminaFilasCompletas();
    }
    
    private void eliminaFilasCompletas() {
        for (int y = this.alto - 1 - 1; y >= 0; y--) {
            // Comprueba si ya no hay más bloques fijos.
            if (this.estaVacia(y))
                return;
            
            // Comprueba si esta fila está completa
            if (this.estaCompleta(y)) {
                // La elimina
                this.desplaza(y);
                
                // Incrementa el contador
                this.filasCompletas++;
                
                // Incrementa el contador para comprobar la siguiente fila
                y++;
            }
        }
    }
    
    private boolean estaCompleta(final int f) {
        for (int x = 2; x < this.ancho - 2; x++)
            if (this.escenario[f][x].getTipo() != TipoBloque.FIJO)
                return false;
        
        return true;
    }
    
    private boolean estaVacia(final int f) {
        for (int x = 2; x < this.ancho - 2; x++)
            if (this.escenario[f][x].getTipo() != TipoBloque.VACIO)
                return false;
        
        return true;
    }
    
    private void desplaza(final int f) {
        for (int y = f; y > 0; y--)
            System.arraycopy(this.escenario[y - 1], 2, this.escenario[y], 2, this.ancho - 2 - 2);
    }
}

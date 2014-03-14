/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
package poligono;

import java.util.Scanner;

/**
 * Clase que representa un polígono convexo.
 *
 * @author  Benito Palacios Sánchez
 * @version 1.0, 12/03/2014
 */
public class Poligono
{
    private final Punto[] vertices; // Vértices del vector        NOPMD
    private int numPuntos;          // Número de puntos           NOPMD
    
    /**
     * Método principal del programa.
     * 
     * @param args Argumentos pasados por la línea de comandos
     */
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        
        System.out.print("Introduzca el número de puntos del polígono: ");
        final int totalPts = scanner.nextInt();
        
        final Poligono poligono = new Poligono(totalPts);   
        System.out.println("Introduzca los puntos del polígono en orden.");
        for (int i = 0; i < totalPts; i++) {
            System.out.print("[" + i + "] ");
            poligono.anadeVertice(Punto.fromScanner(scanner));
        }
        
        System.out.println("El área es: " + poligono.getPerimetro());
    }
    
    /**
     * Crea una instancia de polígono vacía.
     * 
     * @param totalPuntos Número de vértices del polígono.
     */
    public Poligono(final int totalPuntos) {
        this.numPuntos = 0;
        this.vertices = new Punto[totalPuntos];
    }
    
    /**
     * Devuelve el número de puntos actuales en el polígono.
     * 
     * @return El número de puntos del polígono
     */
    public int getNumPuntos() {
        return this.numPuntos;
    }
    
    /**
     * Obtiene un vértice a partir de su índice.
     * 
     * @param idx Índice del vértice a devolver
     * @return Vértice del polígono
     */
    public Punto getVertice(final int idx) {
        if (idx < 0 || idx >= this.vertices.length) {
            throw new IndexOutOfBoundsException();
        }
        
        return this.vertices[idx];
    }
        
    /**
     * Añade un vértice al polígono. Se añaden en orden.
     * 
     * @param vertice Siguiente vértice del polígono.
     */
    public void anadeVertice(final Punto vertice) {
        if (this.numPuntos == this.vertices.length) {
            return;
        }
        
        this.vertices[this.numPuntos] = new Punto(
                vertice.getCoordX(), vertice.getCoordY()
        );
        this.numPuntos++;
    }
    
    /**
     * Calcula el perímetro del polígono.
     * 
     * @return Perímetro del polígono.
     */
    public double getPerimetro() {
        double perimetro = 0;
        for (int i = 0; i < this.numPuntos; i++) {
            final Punto nextPt = (i == this.numPuntos - 1) ? 
                                    this.vertices[0] : this.vertices[i+1];
            perimetro += this.vertices[i].calculaDistancia(nextPt);
        }
        
        return perimetro;
    }
}

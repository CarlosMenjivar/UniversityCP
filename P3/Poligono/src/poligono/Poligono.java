/*
 * Copyright (C) 2014 Benito Palacios Sánchez
 *
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
    private final Punto[] vertices; // Vértices del poligono        NOPMD
    private int numVertices;        // Número de vertices           NOPMD
    
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
        
        System.out.println("El perímetro es: " + poligono.getPerimetro());
        System.out.println("El área es: " + poligono.getArea());
        
        // Crea un polígono a partir de un círculo
        System.out.println("-----------------------------");
        System.out.print("Introduzca un círculo: ");
        final Circulo circulo = Circulo.fromScanner(scanner);
        
        System.out.print("Introduzca el número de lados del polígono: ");
        final int numLados = scanner.nextInt();
        
        final Poligono poligono2 = new Poligono(numLados, circulo);
        System.out.println("El área del círculo es: "  + circulo.calculaArea());
        System.out.println("El área del polígono es: " + poligono2.getArea());
    }
    
    /**
     * Crea una instancia de polígono vacía.
     * 
     * @param totalPuntos Número de vértices del polígono.
     */
    public Poligono(final int totalPuntos) {
        this.numVertices = 0;
        this.vertices = new Punto[totalPuntos];
    }
    
    /**
     * Crea una instancia de poligono inscrito en el circulo dado.
     * 
     * @param totalVertices Numero de vertices del poligono.
     * @param circulo Circulo en el que estara inscrito el poligono.
     */
    public Poligono(final int totalVertices, final Circulo circulo) {
        final double ang   = 2.0 * Math.PI / totalVertices;
        final Punto origen = circulo.getOrigen();
        final double radio = circulo.getRadio();
        
        
        this.numVertices = totalVertices;
        this.vertices = new Punto[totalVertices];
        for (int i = 0; i < totalVertices; i++) {
            this.vertices[i] = new Punto(           // NOPMD
                origen.getCoordX() + radio * Math.cos(i * ang),
                origen.getCoordY() + radio * Math.sin(i * ang)
            );
        }
    }
    
    /**
     * Devuelve el número de puntos actuales en el polígono.
     * 
     * @return El número de puntos del polígono
     */
    public int getNumVertices() {
        return this.numVertices;
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
        if (this.numVertices == this.vertices.length) {
            return;
        }
        
        this.vertices[this.numVertices] = new Punto(
                vertice.getCoordX(), vertice.getCoordY()
        );
        this.numVertices++;
    }
    
    /**
     * Calcula el perímetro del polígono.
     * 
     * @return Perímetro del polígono.
     */
    public double getPerimetro() {
        double perimetro = 0;
        for (int i = 0; i < this.numVertices; i++) {
            final Punto nextPt = (i == this.numVertices - 1) ? 
                                    this.vertices[0] : this.vertices[i+1];
            perimetro += this.vertices[i].calculaDistancia(nextPt);
        }
        
        return perimetro;
    }
    
    /**
     * Calcula el área del polígono.
     * 
     * @return Área del polígono.
     */
    public double getArea() {        
        double area = 0;
        for (int i = 0; i < this.numVertices; i++) {
            final Punto ptInt = this.getPuntoInterior();
            final Punto ptSig = (i == this.numVertices - 1) ?
                                   this.vertices[0] : this.vertices[i+1];
            area += this.getAreaTriangulo(this.vertices[i], ptSig, ptInt);
        }
 
        // Redondea al tercer digito
        return Math.round(area * 1000) / 1000.0;
    }
    
    /*
     * Cálcula y devuelve el área de un triángulo a partir de sus vértices.
     */
    private double getAreaTriangulo(final Punto pt0, final Punto pt1, 
                                    final Punto pt2) {
        if (pt0.equals(pt1) || pt0.equals(pt2) || pt1.equals(pt2)) {
            return 0;
        }
        
        // Lados del triángulo
        final double s1 = pt0.calculaDistancia(pt1);
        final double s2 = pt1.calculaDistancia(pt2);
        final double s3 = pt2.calculaDistancia(pt0);
    
        final double t = (s1 + s2 + s3) / 2;
        return Math.sqrt(t * (t-s1) * (t-s2) * (t-s3));
    }
    
    private Punto getPuntoInterior() {
        return this.vertices[0];
    }
}

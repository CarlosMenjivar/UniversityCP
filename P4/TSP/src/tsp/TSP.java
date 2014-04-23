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

package tsp;

import org.omg.IOP.CodecPackage.FormatMismatch;
import tsp.algoritmos.*;

/**
 * Clase principal del programa donde se lee los argumentos y se ejecutan
 * los algoritmos.
 * 
 * @version 1.0
 * @author  Benito Palacios Sánchez
 */
public class TSP {
    /** Conjunto de algoritmos para resolver el problema soportados. */
    private static final int NumAlgoritmos = 4;
    
    /**
     * Método de entrada del programa.
     * 
     * @param args Los argumentos de la consola.
     */
    public static void main(final String[] args) {
        /*try {        
            // DEBUG:
            System.setIn(new java.io.FileInputStream("/home/benito/Documentos/Universidad/6/CP/Prácticas/P4/Tests/Instancias/a280.tsp"));
        } catch (java.io.FileNotFoundException ex) {
        }*/
        
        if (args.length == 0 || args[0].equals("help")) {
            System.out.println("Se necesitan argumentos extras.");
            muestraAyuda();
            return;
        }

        // Opciones por defecto
        boolean muestraSolucion = false;
        boolean muestraRuta     = false;
        boolean muestraCoste    = false;
        ITspAlgoritmo algoritmo = new VecinoMasCercano();

        // Lee los argumentos del programa.
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "solucion": muestraSolucion = true; break;
                case "ruta":     muestraRuta     = true; break;
                case "coste":    muestraCoste    = true; break;
                case "algo":
                    if (i + 1 >= args.length) {
                        System.out.println("Falta el número de algoritmo.");
                        muestraAyuda();
                        return;
                    }

                    // Obtiene el número del algoritmo
                    int numAlgo = Integer.parseInt(args[i + 1]);
                    if (numAlgo > NumAlgoritmos || numAlgo <= 0) {
                        System.out.println("Número de algoritmo inválido.");
                        muestraAyuda();
                        return;
                    }

                    // Crea una instancia del algoritmo
                    switch (numAlgo) {
                        case 2:
                            algoritmo = new EstrategiaInsercion(
                                                new InsercionEconomica()
                                        );
                            break;
                            
                        case 3:
                            algoritmo = new EstrategiaInsercion(
                                                new InsercionLejana()
                                        );
                            break;
                            
                        case 4:
                            algoritmo = new RutaAleatoria(10000);
                            break;
                    }
                    
                    i++;    // Salta el número de algoritmo.
                    break;
                    
                default:
                    System.out.println("Argumento no reconocido.");
                    muestraAyuda();
                    return;
            }
        }

        // Lee el problema
        Problema problema;
        try {
            problema = Problema.FromStdIn();
        } catch (FormatMismatch ex) {
            System.out.println(ex.getMessage());
            return;
        }  
        
        // Resuelve el problema
        Heuristica heuristica = new Heuristica(problema, algoritmo);
        heuristica.run();
        
        // Muestra el resultado
        Ruta ruta = heuristica.getRuta();
        if (muestraCoste)
            muestraCoste(ruta);
        
        if (muestraRuta)
            muestraRuta(ruta);
        
        if (muestraSolucion)
            muestraSolucion(ruta);
    }
    
    /**
     * Muestra los parámetros y el funcionamiento del programa.
     */
    private static void muestraAyuda() {
        System.out.println("USO: TSP [h] [solucion] [ruta] [coste] [algo X]");
        System.out.println();
        System.out.println("El problema se introducirá desde la entrada    ");
        System.out.println("estandar.                                      ");
        System.out.println();
        System.out.println("Opciones:");
        System.out.println("   h:        Muestra esta ayuda.               ");
        System.out.println("   solucion: Muestra las coordenadas de cada   ");
        System.out.println("              ciudad en orden según la ruta.   ");
        System.out.println("   ruta:     Muestra el ID de cada ciudad en   ");
        System.out.println("              orden según la ruta.             ");
        System.out.println("   coste:    Muestra el coste de la ruta.      ");
        System.out.println("   algo X:   Utiliza el número de algoritmo    ");
        System.out.println("              seleccionado.                    ");
        System.out.println("             0: VecinoMasCercano [por defecto] ");
        System.out.println("             1: InsercionEconomica             ");
        System.out.println("             2: InsercionLejana                ");
        System.out.println("             3: RutaAleatoria                  ");
        System.out.println("             4: MejoraAleatoria                ");
    }
        
    /**
     * Realiza una comparativa de cada algoritmo implementado comprobando
     * el coste de cada solución y el tiempo que tarda en encontrarla.
     * 
     * @param problema Problema a resolver.
     */
    private static void comparaAlgoritmos(Problema problema) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    
    /**
     * Muestra el coste de la ruta.
     * 
     * @param solucion Ruta encontrada.
     */
    private static void muestraCoste(Ruta solucion) {
        System.out.println(
                String.format("MEJOR SOLUCIÓN: %.2f", solucion.getCoste())
                );
    }
    
    /**
     * Muestra las coordenadas de cada ciudad en el orden en el que se
     * visitarán.
     * 
     * @param solucion Ruta encontrada.
     */
    private static void muestraSolucion(Ruta solucion) {
        for (Ciudad ciudad : solucion.getRuta())
            System.out.println(ciudad.getCoordX() + " " + ciudad.getCoordY());
    }
    
    /**
     * Muestra los identificadores de cada ciudad en el orden en el que se
     * visitarán.
     * 
     * @param solucion Ruta encontrada.
     */
    private static void muestraRuta(Ruta solucion) {
        for (Ciudad ciudad : solucion.getRuta())
            System.out.println(ciudad.getId());
    }
}

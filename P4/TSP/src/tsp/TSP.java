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

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
    private static final int NumAlgoritmos = 5;
    
    /** Número de intentos en algoritmo aleatorios 4. */
    private static final int NumIntentos4 = 10000;
    
    /** Número de intentos en algoritmo aleatorio 5 (es más lento). */
    private static final int NumIntentos5 = 10;
    
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
        boolean muestraTiempo   = false;
        boolean introduceOptimo = false;
        String  archivoOptimo   = null;
        ITspAlgoritmo algoritmo = new VecinoMasCercano();

        // Lee los argumentos del programa.
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "solucion": muestraSolucion = true; break;
                case "ruta":     muestraRuta     = true; break;
                case "coste":    muestraCoste    = true; break;
                case "tiempo":   muestraTiempo   = true; break;
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
                            algoritmo = new RutaAleatoria(NumIntentos4);
                            break;
                            
                        case 5:
                            algoritmo = new RutaAleatoria(
                                                NumIntentos5,
                                                new MejoraMoviendo()
                                        );
                            break;
                    }
                    
                    i++;    // Salta el número de algoritmo.
                    break;
                    
                case "optimo":
                    introduceOptimo = true;
                    archivoOptimo = args[++i];
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
        
        // Mide el tiempo
        long inicio = System.nanoTime();
        heuristica.run();
        long fin = System.nanoTime();
        
        // Muestra el resultado
        Ruta ruta = heuristica.getRuta();
        if (muestraCoste)
            muestraCoste(ruta);
        
        if (muestraRuta)
            muestraRuta(ruta);
        
        if (muestraSolucion)
            muestraSolucion(ruta);
        
        if (muestraTiempo) {
            System.out.println(String.format(
                    "Tiempo tardado: %.2f ms",
                    (fin - inicio) / 1000000.0
            ));
        }
        
        // Lee del archivo la ruta óptima.
        if (introduceOptimo) {
            try {
                System.out.println();
                System.out.println("Procesando y mostrando ruta óptima");
                Ruta rutaOptima = leeRutaOptima(archivoOptimo, problema);
                muestraCoste(rutaOptima);

                // Muestra la diferencia en coste
                double costeNormal = ruta.getCoste();
                double costeOptimo = rutaOptima.getCoste();
                double diff = (costeNormal - costeOptimo) / costeOptimo * 100;
                System.out.println(String.format(
                        "Nuestra ruta es: %.2f%% menos óptima.",
                        diff
                ));
            } catch (FileNotFoundException | EOFException ex) {
                System.out.println("Hubo un error: " + ex.getMessage());
            }
        }
    }
    
    /**
     * Muestra los parámetros y el funcionamiento del programa.
     */
    private static void muestraAyuda() {
        System.out.println("USO: TSP [h] [solucion] [ruta] [coste] [algo X]");
        System.out.println("         [optimo archivoConSolucion] [tiempo]  ");
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
        System.out.println("   optimo:   Introduce la solución óptima del  ");
        System.out.println("             problema para compararla.         ");
        System.out.println("   tiempo:   Muestra el tiempo en milisegundos ");
        System.out.println("             que la heurística toma en resolver");
        System.out.println("             el problema.                      ");
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

    /**
     * Lee la ruta óptima desde una fichero externo.
     * 
     * @param archivo Fichero con la ruta óptima.
     * @param problema Problema que resuelve la ruta.
     * @return Ruta óptima.
     * @throws FileNotFoundException El fichero no se puede encontrar.
     */
    private static Ruta leeRutaOptima(final String archivo, final Problema problema)
            throws FileNotFoundException, EOFException {
        Scanner scanner = new Scanner(new File(archivo));
        
        // Avanza hasta donde comienzan los números
        do ; while (scanner.hasNextLine() && !scanner.nextLine().startsWith("TOUR_SECTION"));
        
        // Si no se ha encontrado y se ha llegado al final del archivo excepción
        if (!scanner.hasNextLine())
            throw new java.io.EOFException("Imposible encontrar inicio de ruta");
        
        // Empezar añadir ciudades a la ruta por su ID.
        Ruta ruta = new Ruta(problema.getNumCiudades());
        
        // Mientras que el ID no sea "-1" seguir añadiendo
        int pos = 0;
        int idCiudad = scanner.nextInt();
        while (idCiudad != -1) {
            ruta.setCiudad(problema.getCiudadById(idCiudad), pos++);
            idCiudad = scanner.nextInt();
        }
        
        return ruta;
    }
}

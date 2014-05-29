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

package guitetris;

import data.Bloque;
import data.Figura;
import data.FiguraFactory;
import data.Punto;
import data.TipoBloque;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Timer;

/**
 * Muestra el tiempo de juego.
 * 
 * @author Benito Palacios Sánchez
 */
public class TiempoPanel extends javax.swing.JPanel {
    private static final int TamanoBloque = 4;
    private static final Punto PosicionHora = new Punto(4, 10);
    private static final Punto PosicionPuntosH = new Punto(27, 10);
    private static final Punto PosicionMinutos = new Punto(42, 10);
    private static final Punto PosicionPuntosM = new Punto(65, 10);
    private static final Punto PosicionSegundos = new Punto(80, 10);
    
    private final Timer timer;
    private EscenarioPanel escenarioPanel;
    private int tiempo = 0;
    
    /**
     * Crea el componente.
     */
    public TiempoPanel() {
        initComponents();
        
        // Cada 1000 ms aumenta la cantidad.
        this.timer = new Timer(1000, new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) { 
                tiempo++;
                repaint();
            }
        });
    }

    /**
     * Establece el componente que representa el escenario.
     * 
     * @param escenarioPanel Componente del escenario.
     */
    public void setEscenarioPanel(final EscenarioPanel escenarioPanel) {
        this.escenarioPanel = escenarioPanel;
    }
    
    /**
     * Reinicia el contador.
     */
    public void reset() {
        this.tiempo = 0;
        this.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (this.escenarioPanel == null)
            return;
        
        // Comprueba el estado del juego.
        if (!this.escenarioPanel.isRunning())
            this.timer.stop();
        else if (!this.timer.isRunning())
            this.timer.start();
        
        // Obtiene el tiempo pasado (milisegundos -> fecha).
        GregorianCalendar calen = new GregorianCalendar(0, 0, 0);
        calen.add(Calendar.SECOND, tiempo);
        
        // Pinta el tiempo
        this.paintFigura(g, FiguraFactory.getDosPuntos(PosicionPuntosH, Color.yellow));
        this.paintNumber(g, calen.get(Calendar.HOUR), 2, PosicionHora);
        this.paintFigura(g, FiguraFactory.getDosPuntos(PosicionPuntosM, Color.yellow));
        this.paintNumber(g, calen.get(Calendar.MINUTE), 2, PosicionMinutos);
        this.paintNumber(g, calen.get(Calendar.SECOND), 2, PosicionSegundos);
    }
    
    /**
     * Pinta un número.
     * 
     * @param g Utilidad de gráficos.
     * @param num Número a pintar.
     * @param len Número de cifras del número.
     * @param pos Posición del número.
     */
    private void paintNumber(Graphics g, int num, int len, Punto pos) {
        for (int i = 0; i < len; i++) {
            double factor = (int)Math.pow(10, len - (i + 1));
            int digit = (int)(num / factor);
            num %= factor;
            
            Punto figPos = pos.offset(TamanoBloque * Figura.GetInitSizeX() * i, 0);
            Figura figura = FiguraFactory.getNumber(figPos, Color.yellow, digit);
            this.paintFigura(g, figura);
        }
    }
    
    /**
     * Pinta una figura.
     * 
     * @param g Utilidad de gráficos.
     * @param fig Figura a pintar.
     */
    private void paintFigura(Graphics g, Figura fig) {
        Punto pos = fig.getLocation();
        for (int c = 0; c < fig.getNumColumns(); c++) {
            for (int f = 0; f < fig.getNumRows(); f++) {
                Bloque bloque = fig.getBloque(f, c);
                if (bloque.getTipo() == TipoBloque.VACIO)
                    continue;
                
                int x = pos.getCoordX() + c * TamanoBloque;
                int y = pos.getCoordY() + f * TamanoBloque;
                
                // Pinta el bloque
                g.setColor(bloque.getColor());
                g.fillRect(x, y, TamanoBloque, TamanoBloque);
                
               // Pinta el borde
                g.setColor(Color.BLACK);
                g.drawRect(x, y, TamanoBloque, TamanoBloque);  
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

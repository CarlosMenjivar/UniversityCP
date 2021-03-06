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
import data.Direccion;
import data.Escenario;
import data.TipoBloque;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Componente que muestra un escenario del juego.
 * 
 * @author Benito Palacios Sánchez
 */
public class EscenarioPanel extends javax.swing.JPanel {
    /** Color de fondo. */
    private static final Color BgColor = Color.WHITE;
    
    /** Tamaño en pixels de un bloque. */
    private static final int TamanoCelda = 10;
    
    /** Número de filas por nivel. */
    private static final int FilasPorNivel = 5;
    
    /** Número de puntos por fila. */
    private static final int PuntosPorFila = 7;
    
    /** Frame que lo contiene. */
    private final Frame frame;
    
    /** Timer para desplazar la figura. */
    private final Timer movimiento;
    
    /** El escenario actual. */
    private final Escenario escenario;
    
    /** El nivel actual. */
    private int nivel;
    
    /**
     * Crea un escenario vacío e inválido.
     * Constructor para evitar problemas con el diseñador.
     */
    public EscenarioPanel() {
        initComponents();
       
        this.escenario = null;
        this.movimiento = null;
        this.frame = null;
    }
    
    /**
     * Crea un escenario a partir del tamaño actual.
     * 
     * @param frame Frame que contiene este componente.
     */
    public EscenarioPanel(final Frame frame) {
        initComponents();
       
        this.escenario = new Escenario(
                this.getPreferredSize().width / TamanoCelda,
                this.getPreferredSize().height / TamanoCelda);
        this.nivel = 0;
        this.movimiento = new Timer(nivel2delay(this.nivel), movimientoAction);
        this.movimiento.start();
        this.frame = frame;
    }

    /**
     * Obtiene la cantidad de filas necesarias para subir de nivel.
     * 
     * @return Número de filas por nivel.
     */
    public static int GetFilasPorNivel() {
        return FilasPorNivel;
    }
    
    /**
     * Obtiene la cantidad de puntos por cada fila completada.
     * 
     * @return Número de puntos por fila.
     */
    public static int GetPuntosPorFilas() {
        return PuntosPorFila;
    }
    
    /**
     * Obtiene el escenario representado.
     * 
     * @return Escenario.
     */
    public Escenario getEscenario() {
        return this.escenario;
    }
    
    /**
     * Indica si el escenario está en pausa.
     * 
     * @return Indica si el escenario está en pausa.
     */
    public boolean isRunning() {
        return this.movimiento.isRunning();
    }
    
    /**
     * Reinicia el escenario (nueva partida).
     */
    public void reset() {
        this.escenario.inicializa();
        this.movimiento.start();
        this.frame.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.escenario == null)
            return;
        
        // Pinta el fondo
        g.setColor(BgColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        // Pinta cada bloque del escenario.
        for (int f = 0; f < this.escenario.getAlto(); f++) {
            for (int c = 0; c < this.escenario.getAncho(); c++) {
                Bloque bloque = this.escenario.getBloque(f, c);
                if (bloque.getTipo() == TipoBloque.VACIO)
                    continue;
                
                int x = c * TamanoCelda;
                int y = f * TamanoCelda;
                
                // Pinta el bloque
                g.setColor(bloque.getColor());
                g.fillRect(x, y, TamanoCelda, TamanoCelda);
                
               // Pinta el borde
                g.setColor(Color.BLACK);
                g.drawRect(x, y, TamanoCelda, TamanoCelda);                
            }
        }
    }
    
    /**
     * Convierte de nivel a delay del timer.
     * 
     * @param nivel Nivel del juego.
     * @return Delay en milisegundos.
     */
    private int nivel2delay(int nivel) {
        nivel = (nivel < 0) ? 0 : nivel;
        nivel = (nivel > 10) ? 10 : nivel;

        return 700 - 65 * nivel;
    }
    
    /**
     * Callback del timer. Mueve la figura hacia abajo.
     */
    private final AbstractAction movimientoAction = new AbstractAction() {   
        @Override
        public void actionPerformed(ActionEvent ae) {            
            mueveORota(Direccion.ABAJO, true);
        }
    };
    
    /**
     * Mueve o rota una figura.
     * 
     * @param direccion Dirección del movimiento.
     * @param moverORotar Determinar si la figura se mueve o se rota.
     */
    private void mueveORota(Direccion direccion, boolean moverORotar) {
        if (!this.movimiento.isRunning())
            return;

        // Muevo o roto
        if (moverORotar)
            escenario.getFiguraEnMovimiento().move(direccion, 1);
        else
            escenario.getFiguraEnMovimiento().rotate(direccion);
        
        // Compruebo si hay choque con el escenario
        if (!escenario.isPosicionFiguraValida()) {
            // Deshago el movimiento o rotación
            if (moverORotar)
                escenario.getFiguraEnMovimiento().move(direccion.getContrario(), 1);
            else
                escenario.getFiguraEnMovimiento().rotate(direccion.getContrario());
            this.frame.repaint();
            return;
        }
        
        // Compruebo si hay choque con el fondo
        if (escenario.isFiguraEnFondo()) {
            // Fijo la figura en el fondo
            escenario.fijaFigura();

            // Compruebo si se ha perdido porque la nueva figura no se
            // pueda mover
            if (escenario.isFiguraEnFondo()) {
                this.frame.repaint();
                JOptionPane.showMessageDialog(
                        null,
                        "GameOver",
                        "¡Has perdido!",
                        JOptionPane.WARNING_MESSAGE
                );
                this.movimiento.stop();
                return;
            }
            
            // Actualiza el nivel
            this.nivel = escenario.getFilasCompletas() / FilasPorNivel;
            this.movimiento.setDelay(this.nivel2delay(this.nivel));
        }

        // Actualizo el panel
        this.frame.repaint();    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(200, 400));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

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

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        this.requestFocus();
    }//GEN-LAST:event_formMouseEntered

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                this.mueveORota(Direccion.ABAJO, true);
                break;
                
            case KeyEvent.VK_UP:
                this.mueveORota(Direccion.ABAJO, false);
                break;
                
            case KeyEvent.VK_LEFT:
                this.mueveORota(Direccion.IZQUIERDA, true);
                break;
                
            case KeyEvent.VK_RIGHT:
                this.mueveORota(Direccion.DERECHA, true);
                break;
                
            case KeyEvent.VK_SPACE:
                if (this.movimiento.isRunning())
                    this.movimiento.stop();
                else
                    this.movimiento.start();
                this.frame.repaint();   // Necesario para el mensaje de pausa.
                break;
        }
    }//GEN-LAST:event_formKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

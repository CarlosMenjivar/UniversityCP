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
package guitetris;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Clase para reproducir archivos de audio (.wav).
 * 
 * @author http://stackoverflow.com/a/577926
 */
public class Audio {

    private static Thread CurrentClip;
    
    /**
     * Reproduce un archivo en bucle entre las posiciones dadas.
     * 
     * @param filePath Ruta al archivo.
     * @param start Frame de inicio de bucle.
     * @param end Frame final del bucle.
     */
    public static void PlayClipLoop(String filePath, int start, int end) {
        PlayClipLoop clip = new PlayClipLoop(filePath, start, end);
        CurrentClip = clip;
        clip.start();
    }
    
    /**
     * Para la reproducir del audio.
     */
    public static void Stop() {
        if (CurrentClip != null && CurrentClip.isAlive()) {
            CurrentClip.interrupt();
            CurrentClip = null;
        }
    }
    
    /**
     * Hebra encargada de reproducir en bucle un audio.
     */
    private static class PlayClipLoop extends Thread {
        
        private final File clipFile;
        private final int start;
        private final int end;
        
        private AudioListener listener;
        private Line line;
        
        /**
         * Crea una nueva instancia de la hebra.
         * 
         * @param filePath Ruta al archivo.
         * @param start Frame de inicio del bucle.
         * @param end Frame de fin de bucle.
         */
        public PlayClipLoop(String filePath, int start, int end) {
            this.clipFile = new File(filePath);
            this.start = start;
            this.end   = end;
        }
        
        @Override
        public void run() {
            this.listener = new AudioListener();
            try (AudioInputStream audioInStr = AudioSystem.getAudioInputStream(clipFile)) {
                try (Clip clip = AudioSystem.getClip()) {
                    this.line = clip;
                    clip.addLineListener(listener);
                    clip.open(audioInStr);
                    clip.setLoopPoints(start, end);
                    
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    listener.waitUntilDone();
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            } catch (IOException | UnsupportedAudioFileException | IllegalArgumentException ex) {
                System.err.println(ex.getMessage());
            }
        }
        
        @Override
        public void interrupt() {
            if (this.line != null && this.line.isOpen() && this.listener != null)
                this.listener.update(
                        new LineEvent(this.line, Type.STOP, AudioSystem.NOT_SPECIFIED)
                );
            super.interrupt();
        } 
    }
    
    /**
     * Callbacks para la reproducción de audio.
     */
    private static class AudioListener implements LineListener {

        private boolean done = false;

        @Override
        public synchronized void update(LineEvent event) {
            Type eventType = event.getType();
            if (eventType == Type.STOP || eventType == Type.CLOSE) {
                done = true;
                notifyAll();
            }
        }

        public synchronized void waitUntilDone() throws InterruptedException {
            while (!done) {
                wait();
            }
        }
    }
}

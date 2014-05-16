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

package iotests;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa el concepto de figura geométrica.
 * 
 * @version 1.0
 * @author  Benito Palacios Sánchez
 */
public abstract class Figura implements Comparable<Figura>, Serializable  {
    /** Representa la fecha cuando este objeto fue creado. */
    private final Date fechaCreacion;
    
    /**
     * Crea una instancia de la figura iniciando su hora de creación.
     */
    protected Figura() {
        this.fechaCreacion = new Date();
    }
    
    /**
     * Devuelve la fecha en la que la figura fue creada.
     * 
     * @return Fecha de creación.
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    @Override
    public String toString() {
        return String.format("%s creado el %s.\nArea: %.2f\nPerimetro: %.2f",
                this.getClass().getSimpleName(),
                this.fechaCreacion.toString(),
                this.getArea(),
                this.getPerimetro()
        );
    }
    
    /**
     * Obtiene el área de la figura.
     * 
     * @return Área de la figura.
     */
    public abstract double getArea();
    
    /**
     * Obtiene el perímetro de la figura.
     * 
     * @return Perímetro de la figura.
     */
    public abstract double getPerimetro();
    
    @Override
    public int compareTo(Figura obj) {
        Double miArea = this.getArea();
        return miArea.compareTo(obj.getArea());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio7a;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author cristina
 */
public class Profesor {

    //Atributos
    private String nombre;
    private String dni;
    private String puesto;
    private LocalDate fechaToma;
    private LocalDate fechaCese;
    private String telf;
    private boolean evaluador;
    private boolean coordinador;

    //Constructores
    public Profesor() {
    }

    public Profesor(String nombre, String dni, String puesto, LocalDate fechaToma, LocalDate fechaCese, String telf, boolean evaluador, boolean coordinador) {
        this.nombre = nombre;
        this.dni = dni;
        this.puesto = puesto;
        this.fechaToma = fechaToma;
        this.fechaCese = fechaCese;
        this.telf = telf;
        this.evaluador = evaluador;
        this.coordinador = coordinador;
    }

    //Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public LocalDate getFechaToma() {
        return fechaToma;
    }

    public void setFechaToma(LocalDate fechaToma) {
        this.fechaToma = fechaToma;
    }

    public LocalDate getFechaCese() {
        return fechaCese;
    }

    public void setFechaCese(LocalDate fechaCese) {
        this.fechaCese = fechaCese;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public boolean isEvaluador() {
        return evaluador;
    }

    public void setEvaluador(boolean evaluador) {
        this.evaluador = evaluador;
    }

    public boolean isCoordinador() {
        return coordinador;
    }

    public void setCoordinador(boolean coordinador) {
        this.coordinador = coordinador;
    }

    //Equals y hashCode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.dni);
        hash = 53 * hash + Objects.hashCode(this.puesto);
        hash = 53 * hash + Objects.hashCode(this.fechaToma);
        hash = 53 * hash + Objects.hashCode(this.fechaCese);
        hash = 53 * hash + Objects.hashCode(this.telf);
        hash = 53 * hash + (this.evaluador ? 1 : 0);
        hash = 53 * hash + (this.coordinador ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profesor other = (Profesor) obj;
        if (this.evaluador != other.evaluador) {
            return false;
        }
        if (this.coordinador != other.coordinador) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        if (!Objects.equals(this.puesto, other.puesto)) {
            return false;
        }
        if (!Objects.equals(this.telf, other.telf)) {
            return false;
        }
        if (!Objects.equals(this.fechaToma, other.fechaToma)) {
            return false;
        }
        return Objects.equals(this.fechaCese, other.fechaCese);
    }

    //ToString
    @Override
    public String toString() {
        return nombre + "," + dni + "," + puesto + "," + fechaToma + "," + fechaCese + "," + telf + "," + evaluador + "," + coordinador;
    }

}

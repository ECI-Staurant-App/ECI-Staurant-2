package edu.eci.arsw.ecistaurant.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity

public class Rol implements Serializable {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "nombre",unique = true,length = 20)
    private String nombre;

    public Rol() {
    }

    public Rol(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}

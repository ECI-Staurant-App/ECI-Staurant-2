package edu.eci.arsw.ecistaurant.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Mesa implements Serializable {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private int idMesa;
    @Column(name = "ubicacion",nullable = false,length = 40)
    private String ubicacion;
    @Column(name = "estaDisponible",nullable = false)
    private boolean estaDisponible;

    @JoinColumn(name = "carne")
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Mesa() {
    }

    public Mesa(String ubicacion, boolean estaDisponible, Usuario usuario) {
        this.ubicacion = ubicacion;
        this.estaDisponible = estaDisponible;
        this.usuario = usuario;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isEstaDisponible() {
        return estaDisponible;
    }

    public void setEstaDisponible(boolean estaDisponible) {
        this.estaDisponible = estaDisponible;
    }




}

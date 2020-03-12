package edu.eci.arsw.ecistaurant.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Platillo implements Serializable {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private int idPlatillo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "precio",nullable = false,length = 20)
    private int precio;

    @ManyToOne
    @JoinColumn(name = "fk_menu")
    private Menu menu;

    public Platillo() {
    }

    public Platillo(String nombre, int precio, Menu menu) {
        this.nombre = nombre;
        this.menu = menu;
        this.precio = precio;
    }

    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}

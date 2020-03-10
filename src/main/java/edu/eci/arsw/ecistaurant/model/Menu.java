package edu.eci.arsw.EciStaurant.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Menu implements Serializable {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private int idMenu;

    @Column(name = "nombre",nullable = false,length = 20)
    private String nombre;
    @Column(name = "precio",nullable = false,length = 20)
    private int precio;

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL)
    Set<Platillo> platillos= new HashSet<Platillo>();

    @ManyToOne
    @JoinColumn(name = "fk_restaurante")
    private Restaurante restaurante;

    public Menu(String nombre, int precio, Restaurante restaurante) {
        this.nombre = nombre;
        this.precio = precio;
        this.restaurante = restaurante;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Set<Platillo> getPlatillos() {
        return platillos;
    }

    public void setPlatillos(Set<Platillo> platillos) {
        this.platillos = platillos;
    }
}

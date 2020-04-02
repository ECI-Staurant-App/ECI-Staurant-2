package edu.eci.arsw.ecistaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
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
    @Column(name = "url",nullable = true)
    private String url;

    @ManyToOne
    @JoinColumn(name = "fk_restaurante")
    @JsonBackReference
    private Restaurante restaurante;

    public Menu(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public Menu() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

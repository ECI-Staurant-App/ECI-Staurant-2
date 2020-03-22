package edu.eci.arsw.ecistaurant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restaurante implements Serializable {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private int idRestaurante;
    @Column(name = "nombre",nullable = false)
    private String nombre;


    @OneToMany(mappedBy = "restaurante",cascade = CascadeType.ALL)
    Set<Pedido> pedidos= new HashSet<Pedido>();

    @OneToMany(mappedBy = "restaurante",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Menu> menus= new HashSet<Menu>();

    public Restaurante(String nombre) {
        this.nombre = nombre;
    }

    public Restaurante() {

    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}

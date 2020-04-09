package edu.eci.arsw.ecistaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Pedido implements Serializable {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @JsonIgnore
    private int idPedido;

    @ManyToOne
    @JoinColumn(name = "estudiante")
    private Usuario usuario;

    @Column
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "restaurante")
    @JsonBackReference
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "menu")
    private Menu menu;

    @Column
    private String estado;

    public Pedido() {
    }

    public Pedido(Usuario usuario, Restaurante restaurante, Menu menu, Date fecha,String estado) {
        this.usuario = usuario;
        this.restaurante = restaurante;
        this.menu = menu;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

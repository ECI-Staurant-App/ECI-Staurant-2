package edu.eci.arsw.ecistaurant.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Pedido implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int idPedido;

    @ManyToOne
    @JoinColumn(name = "fk_estudiante")
    private Usuario usuario;

    @Column
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "fk_restaurante")
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "fk_platillo")
    private Platillo platillo;

    public Pedido() {
    }

    public Pedido(Usuario usuario, Restaurante restaurante, Platillo platillo, Date fecha) {
        this.usuario = usuario;
        this.restaurante = restaurante;
        this.platillo = platillo;
        this.fecha = fecha;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public void setPlatillo(Platillo platillo) {
        this.platillo = platillo;
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

    public Platillo getPlatillo() {
        return platillo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

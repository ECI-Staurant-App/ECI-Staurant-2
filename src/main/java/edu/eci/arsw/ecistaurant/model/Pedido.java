package edu.eci.arsw.EciStaurant.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Pedido implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int idPedido;

    @ManyToOne
    @JoinColumn(name = "fk_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "fk_restaurante")
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "fk_platillo")
    private Platillo platillo;


    public Pedido(Estudiante estudiante, Restaurante restaurante, Platillo platillo) {
        this.estudiante = estudiante;
        this.restaurante = restaurante;
        this.platillo = platillo;
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

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Platillo getPlatillo() {
        return platillo;
    }
}

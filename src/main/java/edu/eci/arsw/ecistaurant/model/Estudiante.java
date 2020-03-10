package edu.eci.arsw.EciStaurant.model;


import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Estudiante implements Serializable {

    @Id
    private int carne;
    @Column(name = "nombre",nullable = false,length = 30)
    private String name;
    @Column(name = "saldo",length = 30)
    private int saldo;
    @Column(name = "email",nullable = false,length = 40)
    private String email;
    @Column(name="passwd",nullable = false)
    private String passwd;

    @OneToMany(mappedBy = "estudiante",cascade = CascadeType.ALL)
    Set<Pedido> pedidos= new HashSet<Pedido>();

    @ManyToOne
    @JoinColumn(name = "fk_restaurante")
    private Restaurante restaurante;


    public Estudiante(int carne, String name, int saldo, String email,String passwd) {
        this.carne = carne;
        this.name = name;
        this.saldo = saldo;
        this.email = email;
        this.passwd = passwd;

    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public int getCarne() {
        return carne;
    }

    public void setCarne(int carne) {
        this.carne = carne;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}

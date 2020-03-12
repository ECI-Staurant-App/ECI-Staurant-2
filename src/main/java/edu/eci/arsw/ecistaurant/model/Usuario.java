package edu.eci.arsw.ecistaurant.model;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Usuario implements Serializable {

    @Id
    private int carne;
    @Column(name = "nombre",nullable = false,length = 30)
    private String name;
    @Column(name = "saldo",length = 30)
    private int saldo;
    @Column(name = "email",unique = true,nullable = false,length = 40)
    private String email;
    @Column(name="passwd",nullable = false,length = 60)
    private String passwd;

    private Boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rol> roles;


    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
    Set<Pedido> pedidos= new HashSet<Pedido>();

    public Usuario(int carne, String name, int saldo, String email, String passwd) {
        this.carne = carne;
        this.name = name;
        this.saldo = saldo;
        this.email = email;
        this.passwd = passwd;
    }

    public Usuario() {

    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
}

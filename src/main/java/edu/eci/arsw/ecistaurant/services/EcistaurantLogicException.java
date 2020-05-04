package edu.eci.arsw.ecistaurant.services;

public class EcistaurantLogicException extends Exception {

    public static final String SALDO_INSUFICIENTE = "Saldo Insuficiente";

    public EcistaurantLogicException(){
        super();
    }

    public EcistaurantLogicException(String message) {
        super(message);
    }
}
package edu.eci.arsw.ecistaurant.persistence;

public class EcistaurantPersistenceException extends Exception {

    public static final String STUDENT_REGISTERED = "Student already registered";
    public static final String STUDENT_NOT_FOUND = "Student not found";
    public static final String PEDIDO_NOT_FOUND = "Pedido not found";
    public static final String RESTAURANT_REGISTERED = "Restaurant already registered";
    public static final String MENU_REGISTERED = "Menu already registered";
    public static final String MENU_NOT_FOUND = "Menu not found";
    public static final String RESTAURANT_NOT_FOUND = "Restaurant not found";

    public EcistaurantPersistenceException(){
        super();
    }

    public EcistaurantPersistenceException(String message) {
        super(message);
    }
}

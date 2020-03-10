package edu.eci.arsw.ecistaurant.persistence;

public class EcistaurantPersistenceException extends Exception {

    public static final String  STUDENT_REGISTERED = "Student already registered";
    public static final String STUDENT_NOT_FOUND = "Student not found";

    public EcistaurantPersistenceException(){
        super();
    }

    public EcistaurantPersistenceException(String message) {
        super(message);
    }
}

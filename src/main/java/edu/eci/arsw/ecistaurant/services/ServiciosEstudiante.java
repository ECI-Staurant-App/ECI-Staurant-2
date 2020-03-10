package edu.eci.arsw.ecistaurant.services;
import edu.eci.arsw.ecistaurant.model.Estudiante;

import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;

import java.util.List;


public interface ServiciosEstudiante {

    List<Estudiante> getAllStudents() throws EcistaurantPersistenceException;

    void saveStudent(Estudiante student) throws EcistaurantPersistenceException;

    Estudiante getStudentById(int carne) throws EcistaurantPersistenceException;
}

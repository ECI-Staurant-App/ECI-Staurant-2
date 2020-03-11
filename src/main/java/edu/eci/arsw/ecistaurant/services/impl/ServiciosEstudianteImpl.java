package edu.eci.arsw.ecistaurant.services.impl;

import edu.eci.arsw.ecistaurant.EciStaurantApplication;
import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.model.Platillo;
import edu.eci.arsw.ecistaurant.model.Restaurante;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;
import edu.eci.arsw.ecistaurant.persistence.PedidoRepository;
import edu.eci.arsw.ecistaurant.persistence.RestaurantRepository;
import edu.eci.arsw.ecistaurant.persistence.StudentRepository;
import edu.eci.arsw.ecistaurant.model.Estudiante;
import edu.eci.arsw.ecistaurant.services.ServiciosEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiciosEstudianteImpl implements ServiciosEstudiante {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public List<Estudiante> getAllStudents() throws EcistaurantPersistenceException {
        return studentRepository.findAll();
    }

    @Override
    public void saveStudent(Estudiante student) throws EcistaurantPersistenceException {
        Optional<Estudiante> optionalStudent = studentRepository.findByCarne(student.getCarne());
        if (optionalStudent.isPresent()){
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.STUDENT_REGISTERED);
        }else
        {
            studentRepository.save(student);
        }


    }

    @Override
    public Estudiante getStudentById(int carne) throws EcistaurantPersistenceException {
        Optional<Estudiante> optionalEstudiante = studentRepository.findByCarne(carne);
        if (!optionalEstudiante.isPresent())
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.STUDENT_NOT_FOUND);
        return optionalEstudiante.get();
    }

    @Override
    public Pedido realizarPedido(Pedido pedido) throws EcistaurantPersistenceException {

        pedido.setEstudiante(pedido.getEstudiante());
        pedido.setRestaurante(pedido.getRestaurante());
        pedido.setPlatillo(pedido.getPlatillo());
        pedido.setFecha(pedido.getFecha());
        return pedidoRepository.save(pedido);
    }



}

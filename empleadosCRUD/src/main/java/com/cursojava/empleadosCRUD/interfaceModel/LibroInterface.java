package com.cursojava.empleadosCRUD.interfaceModel;

import com.cursojava.empleadosCRUD.model.Libro;
import org.springframework.data.repository.CrudRepository;

public interface LibroInterface extends CrudRepository<Libro,Integer> {
}

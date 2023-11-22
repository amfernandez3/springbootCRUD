package com.cursojava.empleadosCRUD.interfaceModel;

import com.cursojava.empleadosCRUD.model.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoInterface extends CrudRepository<Empleado,Integer> {
}

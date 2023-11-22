package com.cursojava.empleadosCRUD.controler;

import com.cursojava.empleadosCRUD.interfaceModel.EmpleadoInterface;
import com.cursojava.empleadosCRUD.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/empleados")
@RestController
public class EmpleadoControler {

    private final EmpleadoInterface empleadoInterface;

    @Autowired
    public EmpleadoControler(EmpleadoInterface empleadoInterface) {
        this.empleadoInterface = empleadoInterface;
    }

    // Obtener todos los empleados
    @GetMapping("/listar")
    public List<Empleado> obtenerTodosEmpleados() {
        return (List<Empleado>) empleadoInterface.findAll();
    }

    // Obtener un empleado por ID
    @GetMapping("/{id}")
    public Optional<Empleado> obtenerEmpleadoPorId(@PathVariable int id) {
        return empleadoInterface.findById(id);
    }

    // Agregar un nuevo empleado
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarEmpleado(@RequestBody Empleado empleado) {
        empleadoInterface.save(empleado);
        return new ResponseEntity<>("Empleado agregado correctamente", HttpStatus.CREATED);
    }

    // Actualizar un empleado existente
    @PutMapping("/{id}")
    public void actualizarEmpleado(@PathVariable int id, @RequestBody Empleado empleado) {
        empleadoInterface.save(empleado);
    }

    // Eliminar un empleado por ID
    @DeleteMapping("/{id}")
    public void eliminarEmpleado(@PathVariable int id) {
        empleadoInterface.deleteById(id);
    }

    // Modificar el salario de un empleado por ID
    @PatchMapping("/modificarsalario/{id}")
    public ResponseEntity<String> modificarSalario(@PathVariable int id) {
        Optional<Empleado> optionalEmpleado = empleadoInterface.findById(id);

        if (optionalEmpleado.isPresent()) {
            Empleado empleado = optionalEmpleado.get();

            // Generar un nuevo salario aleatorio
            double nuevoSalario = Math.random() * 10000 + 50000;

            // Modificar el salario del empleado
            empleado.setSalario(nuevoSalario);

            // Guardar los cambios en la base de datos
            empleadoInterface.save(empleado);

            return new ResponseEntity<>("Salario modificado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}

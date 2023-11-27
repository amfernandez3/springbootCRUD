package com.cursojava.empleadosCRUD.controler;

import com.cursojava.empleadosCRUD.interfaceModel.LibroInterface;
import com.cursojava.empleadosCRUD.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/libros")
@RestController
public class LibroController {

    private final LibroInterface libroInterface;

    @Autowired
    public LibroController(LibroInterface libroInterface) {
        this.libroInterface = libroInterface;
    }

    // Obtener todos los libros
    @GetMapping("/listar")
    public List<Libro> obtenerTodosLibros() {
        Iterable<Libro> librosIterable = libroInterface.findAll();
        List<Libro> librosList = new ArrayList<>();
        librosIterable.forEach(librosList::add);
        return librosList;
    }

    // Obtener un libro por ISBN
    @GetMapping("/{isbn}")
    public Optional<Libro> obtenerLibroPorIsbn(@PathVariable int isbn) {
        return libroInterface.findById(isbn);
    }

    // Agregar un nuevo libro
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarLibro(@RequestBody Libro libro) {
        libroInterface.save(libro);
        return new ResponseEntity<>("Libro agregado correctamente", HttpStatus.CREATED);
    }

    // Agregar varios libros
    @PostMapping("/agregar-varios")
    public ResponseEntity<String> agregarVariosLibros(@RequestBody List<Libro> libros) {
        libroInterface.saveAll(libros);
        return new ResponseEntity<>("Libros agregados correctamente", HttpStatus.CREATED);
    }

    // Actualizar un libro existente
    @PutMapping("/{isbn}")
    public List actualizarLibro(@PathVariable int isbn, @RequestBody Libro libro) {
        libroInterface.save(libro);
        Iterable<Libro> librosIterable = libroInterface.findAll();
        List<Libro> librosList = new ArrayList<>();
        librosIterable.forEach(librosList::add);
        return librosList;
    }

    // Eliminar un libro por ISBN
    @DeleteMapping("/{isbn}")
    public List eliminarLibro(@PathVariable int isbn) {
        libroInterface.deleteById(isbn);
        Iterable<Libro> librosIterable = libroInterface.findAll();
        List<Libro> librosList = new ArrayList<>();
        librosIterable.forEach(librosList::add);
        return librosList;
    }

    // Nuevo endpoint para mostrar ISBN y autor
    @GetMapping("/mostrar/{isbn}")
    public ResponseEntity<String> mostrarIsbnYAutor(@PathVariable int isbn) {
        Optional<Libro> optionalLibro = libroInterface.findById(isbn);

        if (optionalLibro.isPresent()) {
            Libro libro = optionalLibro.get();
            return new ResponseEntity<>("ISBN: " + libro.getIsbn() + ", Autor: " + libro.getAutor(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Libro no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}

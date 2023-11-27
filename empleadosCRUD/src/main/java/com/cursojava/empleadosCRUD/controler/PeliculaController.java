package com.cursojava.empleadosCRUD.controler;


import com.cursojava.empleadosCRUD.interfaceModel.PeliculaInterface;
import com.cursojava.empleadosCRUD.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/peliculas")
@RestController
public class PeliculaController {

    private final PeliculaInterface peliculaInterface;

    @Autowired
    public PeliculaController(PeliculaInterface peliculaInterface) {
        this.peliculaInterface = peliculaInterface;
    }

    // Obtener todas las películas
    @GetMapping("/listar")
    public List<Pelicula> obtenerTodasPeliculas() {
        Iterable<Pelicula> peliculasIterable = peliculaInterface.findAll();
        List<Pelicula> peliculasList = new ArrayList<>();
        peliculasIterable.forEach(peliculasList::add);
        return peliculasList;
    }

    // Obtener una película por código
    @GetMapping("/{codigo}")
    public Optional<Pelicula> obtenerPeliculaPorCodigo(@PathVariable int codigo) {
        return peliculaInterface.findById(codigo);
    }

    // Agregar una nueva película
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarPelicula(@RequestBody Pelicula pelicula) {
        peliculaInterface.save(pelicula);
        return new ResponseEntity<>("Película agregada correctamente", HttpStatus.CREATED);
    }

    // Agregar varias películas
    @PostMapping("/agregar-varios")
    public ResponseEntity<String> agregarVariasPeliculas(@RequestBody List<Pelicula> peliculas) {
        peliculaInterface.saveAll(peliculas);
        return new ResponseEntity<>("Películas agregadas correctamente", HttpStatus.CREATED);
    }

    // Actualizar una película existente
    @PutMapping("/{codigo}")
    public void actualizarPelicula(@PathVariable int codigo, @RequestBody Pelicula pelicula) {
        peliculaInterface.save(pelicula);
    }

    // Eliminar una película por código
    @DeleteMapping("/{codigo}")
    public void eliminarPelicula(@PathVariable int codigo) {
        peliculaInterface.deleteById(codigo);
    }

    // Nuevo endpoint para mostrar código y autor
    @GetMapping("/mostrar/{codigo}")
    public ResponseEntity<String> mostrarCodigoYDirector(@PathVariable int codigo) {
        Optional<Pelicula> optionalPelicula = peliculaInterface.findById(codigo);

        if (optionalPelicula.isPresent()) {
            Pelicula pelicula = optionalPelicula.get();
            return new ResponseEntity<>("Código: " + pelicula.getCodigo() + ", Director: " + pelicula.getAutor(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Película no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    //***************************************************** Ejercicios películas
    // Nuevo endpoint para mostrar películas por director (se pasa el nombre por URL)
    @GetMapping("/por-director/{director}")
    public ResponseEntity<List<Pelicula>> mostrarPeliculasPorDirector(@PathVariable String director) {
        List<Pelicula> peliculasPorDirector = peliculaInterface.findByAutor(director);

        if (!peliculasPorDirector.isEmpty()) {
            return new ResponseEntity<>(peliculasPorDirector, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Buscar por autor empezado en M
     * @return
     */
    @GetMapping("/por-autor-m")
    public ResponseEntity<List<Pelicula>> mostrarPeliculasPorAutorQueComienzaConM() {
        List<Pelicula> peliculasPorAutorM = peliculaInterface.findByAutorStartingWith("M");

        if (!peliculasPorAutorM.isEmpty()) {
            return new ResponseEntity<>(peliculasPorAutorM, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Función que busca por autor/director presente en una lista
     * @return
     */
    @GetMapping("/por-director-ana-eva")
    public ResponseEntity<List<Pelicula>> mostrarPeliculasPorDirectorAnaOEva() {
        List<Pelicula> peliculasPorDirectores = peliculaInterface.findByAutorIn(List.of("Ana", "Eva"));

        if (!peliculasPorDirectores.isEmpty()) {
            return new ResponseEntity<>(peliculasPorDirectores, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Muestra solo los autores, en este caso se aplica DISTINCT
     * @return
     */
    @GetMapping("/autores")
    public ResponseEntity<List<String>> obtenerAutoresDePeliculas() {
        List<String> autores = peliculaInterface.findDistinctAutores();

        if (!autores.isEmpty()) {
            return new ResponseEntity<>(autores, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Muestra las peliculas incluídas entre el rango de precios definido
     * @return
     */
    @GetMapping("/por-precio")
    public ResponseEntity<List<Pelicula>> mostrarPeliculasPorRangoDePrecio() {
        List<Pelicula> peliculasPorPrecio = peliculaInterface.findByPrecioBetween(20, 55);

        if (!peliculasPorPrecio.isEmpty()) {
            return new ResponseEntity<>(peliculasPorPrecio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Funcion que devuelve peliculas cuyo precio es mayor que 50
     * @return
     */
    @GetMapping("/por-precio-superior-a-35")
    public ResponseEntity<List<Pelicula>> mostrarPeliculasPrecioSuperiorA35() {
        List<Pelicula> peliculasPrecioSuperiorA35 = peliculaInterface.findByPrecioGreaterThan(35);

        if (!peliculasPrecioSuperiorA35.isEmpty()) {
            return new ResponseEntity<>(peliculasPrecioSuperiorA35, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Permite buscar peliculas por cadenas de texto
     * @param keyword
     * @return
     */
    @GetMapping("/por-titulo-keyword/{keyword}")
    public ResponseEntity<List<Pelicula>> mostrarPeliculasPorTituloKeyword(@PathVariable String keyword) {
        List<Pelicula> peliculasPorTituloKeyword = peliculaInterface.findByTituloContainingIgnoreCase(keyword);

        if (!peliculasPorTituloKeyword.isEmpty()) {
            return new ResponseEntity<>(peliculasPorTituloKeyword, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

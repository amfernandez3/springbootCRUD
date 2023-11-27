package com.cursojava.empleadosCRUD.interfaceModel;

import com.cursojava.empleadosCRUD.model.Pelicula;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PeliculaInterface extends CrudRepository<Pelicula,Integer> {

    //Funciones Query para el ejercicio

    /**
     * Buscar por autor
     * @param autor
     * @return
     */
    List<Pelicula> findByAutor(String autor);

    /**
     * Funcion que buscar por autor según la cadena
     * @param autorPrefix
     * @return
     */
    List<Pelicula> findByAutorStartingWith(String autorPrefix);

    /**
     * Busca peliculas por director, donde director esté en una lista
     * @param autores
     * @return
     */
    List<Pelicula> findByAutorIn(List<String> autores);

    /**
     * Buscar películas y mostrar solo los directores, añadido DISTINCT
     * @return
     */
    @Query("SELECT DISTINCT p.autor FROM Pelicula p")
    List<String> findDistinctAutores();

    /**
     * Función que devuelve todos los datos de las películas cuyo precio esté en el rango
     * @param precioMin
     * @param precioMax
     * @return
     */
    List<Pelicula> findByPrecioBetween(double precioMin, double precioMax);

    /**
     * Función que busque peliculas cuyo valor sea superior a precioMin (50)
     * @param precioMin
     * @return
     */
    List<Pelicula> findByPrecioGreaterThan(double precioMin);

    /**
     * Funcion que busca peliculas cuyo titulo contenga una cadena concreta
     * @param tituloKeyword
     * @return
     */
    List<Pelicula> findByTituloContainingIgnoreCase(String tituloKeyword);

}

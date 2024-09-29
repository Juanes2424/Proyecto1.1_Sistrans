package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categorias (codigo, nombre, descripcion, caracteristicas_de_almacenamiento) VALUES(:codigo, :nombre, :descripcion, :caracteristicas_de_almacenamiento)", nativeQuery = true)
    void insertarCategoria(@Param("codigo") String codigo, @Param("nombre") String nombre,
            @Param("descripcion") String descripcion,
            @Param("caracteristicas_de_almacenamiento") String caracteristicasDeAlmacenamiento);
}

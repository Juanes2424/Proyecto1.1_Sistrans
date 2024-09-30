package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Sucursal;

public interface BodegaRepository extends JpaRepository<Sucursal, Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Bodega WHERE id = :id", nativeQuery = true)
    void borrarBodega(int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Bodega (superandes.nextval)", nativeQuery = true)
    void crearBodega(@Param(""));

}
package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Bodega;

public interface BodegaRepository extends JpaRepository<Bodega, String> {
        @Modifying
        @Transactional
        @Query(value = "DELETE FROM Bodega WHERE id =:id", nativeQuery = true)
        void borrarBodega(@Param("id") String id);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO Bodega (id, nombre, tamano_metros2, sucursal) " +
                        "VALUES (superandes_sequence.nextval, :nombre, :tamano_metros2, :sucursal)", nativeQuery = true)
        void insertarBodega(@Param("nombre") String nombre,
                        @Param("tamano_metros2") Integer tamano_metros2, @Param("sucursal") String sucursal);

}
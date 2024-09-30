package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, String> {
        @Modifying
        @Transactional
        @Query(value = "DELETE FROM Bodega WHERE id =:id", nativeQuery = true)
        void borrarBodega(@Param("id") int id);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO Sucursal (id, nombre, tamano_metros, direccion, telefono, codigo_ciudad) " +
                        "VALUES (NEXTVAL('superandes_sequence'), :nombre, :tamano_metros, :direccion, :telefono, :codigo_ciudad)", nativeQuery = true)
        void insertarSucursal(@Param("nombre") String nombre,
                        @Param("tamano_metros") int tamano_metros, @Param("direccion") String direccion,
                        @Param("telefono") long telefono,
                        @Param("codigo_ciudad") int codigo_ciudad);
}
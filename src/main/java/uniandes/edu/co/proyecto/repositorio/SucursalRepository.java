package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO Sucursal (id, nombre, tamano_metros, direccion, telefono, codigo_ciudad) " +
                        "VALUES (superandes.nextval, :id, :nombre, :tamanoMetros, :direccion, :telefono, :codigoCiudad)", nativeQuery = true)
        void insertarSucursal(Integer id, String nombre, int tamanoMetros, String direccion, long telefono,
                        int codigoCiudad);
}
package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;

import java.util.Collection;
import java.util.List;

public interface OrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, Integer> {

        @Query(value = "SELECT * FROM OrdenDeCompra", nativeQuery = true)
        Collection<OrdenDeCompra> obtenerTodasLasOrdenesDeCompra();

        @Query(value = "SELECT * FROM OrdenDeCompra WHERE id = :id", nativeQuery = true)
        OrdenDeCompra obtenerOrdenDeCompraPorId(@Param("id") Integer id);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO OrdenDeCompra (estado, fecha_creacion, fecha_entrega, sucursal, proveedor) VALUES(:estado, :fecha_creacion, :fecha_entrega, :sucursal, :proveedor)", nativeQuery = true)
        void insertarOrdenDeCompra(@Param("estado") String estado,
                        @Param("fecha_creacion") java.sql.Date fecha_creacion,
                        @Param("fecha_entrega") java.sql.Date fecha_entrega,
                        @Param("sucursal") Integer sucursalId,
                        @Param("proveedor") String proveedorNit);

        @Transactional
        @Query(value = "UPDATE OrdenDeCompra SET estado = :estado, fecha_entrega = :fecha_entrega WHERE id = :id", nativeQuery = true)
        void actualizarOrdenDeCompra(@Param("id") Integer id,
                        @Param("estado") String estado,
                        @Param("fecha_entrega") java.sql.Date fecha_entrega);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM OrdenDeCompra WHERE id = :id", nativeQuery = true)
        void eliminarOrdenDeCompra(@Param("id") Integer id);

        @Query(value = "SELECT * FROM OrdenDeCompra WHERE estado = :estado", nativeQuery = true)
        Collection<OrdenDeCompra> obtenerOrdenesDeCompraPorEstado(@Param("estado") String estado);
}

package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import oracle.sql.DATE;
import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;
import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.modelo.Proveedor;


public interface OrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO OrdenDeCommpra (id, estado, fecha_creacion, fecha_entrega) VALUES(superandes.nextval, :estado, :fecha_creacion, :fecha_entrega)", nativeQuery = true)
    void insertarOrdenDeCompra(@Param("estado") String estado, @Param("fecha_creacion") DATE fecha_creacion, @Param("fecha_entrega") DATE fecha_entrega, @Param("sucursal") Sucursal sucursal, @Param("proveedor") Proveedor proveedor);

    @Modifying
    @Transactional
    @Query(value = "UPDATE OrdenDeCompra O SET o.estado = 'anulada' WHERE id = :id AND o.estado != 'entregada'", nativeQuery = true)
    void cambiarAnulado(@Param("id") Integer id);

}
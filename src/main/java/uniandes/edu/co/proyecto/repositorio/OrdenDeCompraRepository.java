package uniandes.edu.co.proyecto.repositorio;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;
import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.modelo.Proveedor;

public interface OrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, Integer> {

    @Modifying
    @Transactional
    @Query(value ="INSERT INTO OrdenDeCommpra (id, estado, fecha_creacion, fecha_entrega, sucursal, proveedor) VALUES(superandes.nextval, :estado, :fecha_creacion, :fecha_entrega, :id_sucursal, :nit_proveedor)", nativeQuery = true)
    void insertarOrdenDeCompra(@Param("estado") String estado, @Param("fecha_creacion") Date fecha_creacion, @Param("fecha_entrega")
    Date fecha_entrega, @Param("sucursal") Sucursal sucursal, @Param("proveedor") Proveedor proveedor);
     
    @Modifying
    @Transactional
    @Query(value = "UPDATE OrdenDeCompra O SET o.estado = 'anulada' WHERE id = :id AND o.estado != 'entregada'", nativeQuery = true)
    void cambiarAnulado(@Param("id") Integer id);

    List<OrdenDeCompra> findAll();
}
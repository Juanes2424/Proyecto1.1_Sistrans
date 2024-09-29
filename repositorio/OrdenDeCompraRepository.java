package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, String> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE OrdenDeCompra O SET o.estado = 'anulada' WHERE id = :id AND o.estado != 'entregada'", nativeQuery = true)
    void cambiarAnulado(@Param("id") String id);
}
package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.modelo.Producto;

public interface BodegaRepository extends JpaRepository<Bodega, String> {
        @Modifying
        @Transactional
        @Query(value = "DELETE FROM Bodega WHERE id =:id", nativeQuery = true)
        void borrarBodega(@Param("id") String id);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO Bodega (id, nombre, tamano_metros2, sucursal) " +
                        "VALUES (sq.nextval, :nombre, :tamano_metros2, :sucursal)", nativeQuery = true)
        void insertarBodega(@Param("nombre") String nombre,
                        @Param("tamano_metros2") Integer tamano_metros2, @Param("sucursal") String sucursal);

                
        @Query(value = "SELECT pr.nombre AS Producto,\r\n"+ 
                "ib.total_existencias AS Cantidad_Actual,\r\n"+
                "ib.nivel_minimo_reorden AS Cantidad_Mínima,\r\n"+
                "ib.costo_promedio AS Costo_Promedio\r\n"+
                "FROM Producto pr\r\n"+
                "INNER JOIN InfoExtraBodega ib ON pr.codigo_barras = ib.codigo_producto\r\n"+
                "INNER JOIN Bodega b ON ib.bodega_id = b.id\r\n"+
                "INNER JOIN Sucursal s ON b.sucursal_id = s.id\r\n"+
                "WHERE s.id = :id_sucursal\r\n"+
                "AND b.id = :id_bodega;", nativeQuery = true)
        Collection<Producto> darInventarioBodega(@Param("id_sucursal") Integer id_sucursal,
        @Param("id_bodega") Integer id_bodega);

      
}
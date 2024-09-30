package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT * FROM Productos", nativeQuery = true)
    Collection<Producto> obtenerProductos();

    @Query(value = "SELECT * FROM Productos WHERE id = :id", nativeQuery = true)
    Producto obtenerProducto(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productos (codigo_barras, nombre, precio, categoria_codigo) VALUES(:codigoBarras, :nombre, :precio, :categoriaCodigo)", nativeQuery = true)
    void insertarProducto(@Param("codigoBarras") String codigoBarras, @Param("nombre") String nombre,
            @Param("precio") double precio, @Param("categoriaCodigo") String categoriaCodigo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET nombre = :nombre, precio = :precio, categoria_codigo = :categoriaCodigo WHERE id = :id", nativeQuery = true)
    void actualizarProducto(@Param("id") int id, @Param("codigoBarras") String codigoBarras,
            @Param("nombre") String nombre,
            @Param("precio") double precio, @Param("categoriaCodigo") String categoriaCodigo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productos WHERE id = :id", nativeQuery = true)
    void eliminarProducto(@Param("id") int id);
}

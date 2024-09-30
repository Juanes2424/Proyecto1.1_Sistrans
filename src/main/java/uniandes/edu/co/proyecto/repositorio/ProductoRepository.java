package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.proyecto.modelo.Producto;
import java.sql.*;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT * FROM Productos", nativeQuery = true)
    Collection<Producto> obtenerProductos();

    @Query(value = "SELECT * FROM Productos WHERE codigo_barras = :codigo_barras", nativeQuery = true)
    Producto obtenerProducto(@Param("codigo_barras") Integer codigoBarras);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productos (codigo_barras, nombre, precio_unitario, presentacion, cantidad_presentacion, unidad_medida_presentacion, cantidad_empaque, unidad_empaque, fecha_expiracion, categoria) VALUES(:codigo_barras, :nombre, :precio_unitario, :presentacion, :cantidad_presentacion, :unidad_medida_presentacion, :cantidad_empaque, :unidad_empaque, :fecha_expiracion, :categoria)", nativeQuery = true)
    void insertarProducto(@Param("codigo_barras") Integer codigoBarras,
            @Param("nombre") String nombre,
            @Param("precio_unitario") Integer precioUnitario,
            @Param("presentacion") String presentacion,
            @Param("cantidad_presentacion") Integer cantidadPresentacion,
            @Param("unidad_medida_presentacion") String unidadMedidaPresentacion,
            @Param("cantidad_empaque") Integer cantidadEmpaque,
            @Param("unidad_empaque") String unidadEmpaque,
            @Param("fecha_expiracion") Date fechaExpiracion,
            @Param("categoria") String categoriaCodigo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET nombre = :nombre, precio_unitario = :precio_unitario, presentacion = :presentacion, cantidad_presentacion = :cantidad_presentacion, unidad_medida_presentacion = :unidad_medida_presentacion, cantidad_empaque = :cantidad_empaque, unidad_empaque = :unidad_empaque, fecha_expiracion = :fecha_expiracion, categoria = :categoria WHERE codigo_barras = :codigo_barras", nativeQuery = true)
    void actualizarProducto(@Param("codigo_barras") Integer codigoBarras,
            @Param("nombre") String nombre,
            @Param("precio_unitario") Integer precioUnitario,
            @Param("presentacion") String presentacion,
            @Param("cantidad_presentacion") Integer cantidadPresentacion,
            @Param("unidad_medida_presentacion") String unidadMedidaPresentacion,
            @Param("cantidad_empaque") Integer cantidadEmpaque,
            @Param("unidad_empaque") String unidadEmpaque,
            @Param("fecha_expiracion") Date fechaExpiracion,
            @Param("categoria") String categoriaCodigo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productos WHERE codigo_barras = :codigo_barras", nativeQuery = true)
    void eliminarProducto(@Param("codigo_barras") Integer codigoBarras);
}

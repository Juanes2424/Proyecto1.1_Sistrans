package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.InfoExtraOrden;
import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;
import uniandes.edu.co.proyecto.modelo.Proveedor;
import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.repositorio.InfoExtraOrdenRepository;
import uniandes.edu.co.proyecto.repositorio.OrdenDeCompraRepository;
import uniandes.edu.co.proyecto.repositorio.ProveedorRepository;
import uniandes.edu.co.proyecto.repositorio.SucursalRepository;


import java.util.HashSet;


@RestController
public class OrdenDeCompraController {
    
    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private InfoExtraOrdenRepository infoExtraOrdenRepository; 
    @PostMapping("ordendecompra/new/save")
    public ResponseEntity<String> crearOrdenDeCompra(@RequestBody OrdenDeCompra ordenDeCompra) {
        try {
            
            ordenDeCompra.setEstado("vigente");
            ordenDeCompra.setDetalles(new HashSet<>());
            
           
            Sucursal sucursal = sucursalRepository.findById(ordenDeCompra.getSucursal().getId()).orElseThrow(() -> new IllegalArgumentException("Sucursal no encontrada"));
            Proveedor proveedor = proveedorRepository.findById(ordenDeCompra.getProveedor().getNit()).orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));
            
            
            ordenDeCompra.setSucursal(sucursal);
            ordenDeCompra.setProveedor(proveedor);
            
            
            OrdenDeCompra nuevaOrden = ordenDeCompraRepository.save(ordenDeCompra);
            
            // Guardar los detalles de la orden (si los hay)
            if (ordenDeCompra.getDetalles() != null) {
                for (InfoExtraOrden detalle : ordenDeCompra.getDetalles()) {
                    detalle.setOrdenDeCompra(nuevaOrden);
                    infoExtraOrdenRepository.save(detalle);
                }
            }

            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("ordendecompra/{id}/anulada")
    public ResponseEntity<String> anularOrdenDeCompra(@PathVariable Integer id) {
        try {
            ordenDeCompraRepository.cambiarAnulado(id);
            return new ResponseEntity<>("Orden de compra anulada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al anular la orden de compra: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

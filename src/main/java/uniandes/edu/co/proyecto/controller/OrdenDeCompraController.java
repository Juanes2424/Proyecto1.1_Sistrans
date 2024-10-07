package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;
import uniandes.edu.co.proyecto.repositorio.OrdenDeCompraRepository;

@RestController
@RequestMapping("/ordendecompra")
public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    @PostMapping("/new/save")

    public ResponseEntity<String> crearOrdenDeCompra(@RequestBody OrdenDeCompra ordenDeCompra) {
        try {
            ordenDeCompraRepository.insertarOrdenDeCompra(
                    ordenDeCompra.getEstado(),
                    ordenDeCompra.getFecha_creacion(),
                    ordenDeCompra.getFecha_entrega(),
                    ordenDeCompra.getSucursal().getId(),
                    ordenDeCompra.getProveedor().getNit()

            );
            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarOrdenDeCompra(@PathVariable Integer id,
            @RequestBody OrdenDeCompra ordenDeCompra) {
        try {
            ordenDeCompraRepository.actualizarOrdenDeCompra(
                    id,
                    ordenDeCompra.getEstado(),
                    ordenDeCompra.getFecha_entrega());
            return new ResponseEntity<>("Orden de compra actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la orden de compra", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ordencompra/list")
    public ResponseEntity<Collection<OrdenDeCompra>> listarOrdenesDeCompra() {
        try {
            Collection<OrdenDeCompra> ordenes = ordenDeCompraRepository.obtenerTodasLasOrdenesDeCompra();
            return new ResponseEntity<>(ordenes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

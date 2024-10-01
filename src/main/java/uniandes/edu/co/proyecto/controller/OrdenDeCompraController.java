package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;
import uniandes.edu.co.proyecto.repositorio.OrdenDeCompraRepository;

import java.util.List;

@RestController
@RequestMapping("/ordendecompra")
public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    @PostMapping("/new/save")

    public ResponseEntity<String> crearOrdenDeCompra(@RequestBody OrdenDeCompra ordenDeCompra) {
        try {
            ordenDeCompraRepository.save(ordenDeCompra); // Uso del método automático de JPA para insertar
            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra: ",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDeCompra> obtenerOrdenDeCompraPorId(@PathVariable Integer id) {
        try {
            OrdenDeCompra ordenDeCompra = ordenDeCompraRepository.findById(id).orElse(null);
            if (ordenDeCompra != null) {
                return new ResponseEntity<>(ordenDeCompra, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<OrdenDeCompra>> obtenerTodasLasOrdenesDeCompra() {
        try {
            List<OrdenDeCompra> ordenesDeCompra = ordenDeCompraRepository.findAll();
            return new ResponseEntity<>(ordenesDeCompra, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/edit")

    public ResponseEntity<String> actualizarOrdenDeCompra(@PathVariable Integer id,
            @RequestBody OrdenDeCompra ordenDeCompra) {
        try {
            OrdenDeCompra ordenExistente = ordenDeCompraRepository.findById(id).orElse(null);
            if (ordenExistente != null) {
                ordenExistente.setEstado(ordenDeCompra.getEstado());
                ordenExistente.setFecha_entrega(ordenDeCompra.getFecha_entrega());
                ordenDeCompraRepository.save(ordenExistente); // actualiza la orden de compra sin pasar por otras cosas
                return new ResponseEntity<>("Orden de compra actualizada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Orden de compra no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la orden de compra: ",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/delete")

    public ResponseEntity<String> eliminarOrdenDeCompra(@PathVariable Integer id) {
        try {
            OrdenDeCompra ordenExistente = ordenDeCompraRepository.findById(id).orElse(null);
            if (ordenExistente != null) {
                ordenDeCompraRepository.deleteById(id);
                return new ResponseEntity<>("Orden de compra eliminada exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Orden de compra no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la orden de compra: ",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrdenDeCompra>> obtenerOrdenesDeCompraPorEstado(@PathVariable String estado) {
        try {
            List<OrdenDeCompra> ordenesDeCompra = ordenDeCompraRepository.obtenerOrdenesDeCompraPorEstado(estado);
            return new ResponseEntity<>(ordenesDeCompra, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

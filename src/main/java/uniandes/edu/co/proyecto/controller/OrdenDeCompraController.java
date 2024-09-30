package uniandes.edu.co.proyecto.controller;

import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;
import uniandes.edu.co.proyecto.repositorio.OrdenDeCompraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class OrdenDeCompraController {
    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    @PostMapping("/ordendecompra/{id}/anulada")
    public ResponseEntity<String> anularOrdenDeCompra(@RequestBody Integer id) {

        try {
            ordenDeCompraRepository.cambiarAnulado(id);
            return new ResponseEntity<String>("Orden De Compra pasa a anulada", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error al pasar a anulada", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*
     * @PostMapping("/OrdenDeCompra/new/save")
     * public ResponseEntity<String> barGuardar(@RequestBody OrdenDeCompra orden) {
     * try {
     * ordenDeCompraRepository.insertarOrdenDeCompra(orden.getEstado(),
     * orden.getFecha_creacion().toDate(),
     * orden.getFecha_entrega(), orden.getSucursal().toString(),
     * orden.getProveedor());
     * return new ResponseEntity<>("Orden de compra creada exitosamente",
     * HttpStatus.CREATED);
     * } catch (Exception e) {
     * return new ResponseEntity<>("Error al crear la orden de compra",
     * HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     * }
     */
}
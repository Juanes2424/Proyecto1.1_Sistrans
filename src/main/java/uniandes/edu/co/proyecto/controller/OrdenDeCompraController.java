package uniandes.edu.co.proyecto.controller;

import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> anularOrdenDeCompra(@RequestBody String id) {

        try {
            ordenDeCompraRepository.cambiarAnulado(id);
            return new ResponseEntity<String>("Orden De Compra pasa a anulada", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error al pasar a anulada", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
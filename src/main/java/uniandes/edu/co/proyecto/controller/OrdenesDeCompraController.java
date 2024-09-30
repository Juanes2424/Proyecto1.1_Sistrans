package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;
import uniandes.edu.co.proyecto.repositorio.OrdenDeCompraRepository;

@RestController
public class OrdenesDeCompraController {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    @PostMapping("/OrdenDeCompra/new/save")
    public ResponseEntity<String> barGuardar(@RequestBody OrdenDeCompra orden) {
        try {
            ordenDeCompraRepository.insertarOrdenDeCompra(orden.getEstado(), orden.getFecha_creacion(), orden.getFecha_entrega(), orden.getSucursal(), orden.getProveedor());
            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import oracle.sql.DATE;

@Entity
@Table(name = "OrdenDeCompra")
public abstract class OrdenDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String estado;
    private DATE fecha_creacion;
    private DATE fecha_entrega;
    @ManyToOne
    @JoinColumn(name = "sucursal", referencedColumnName = "id")
    private Sucursal sucursal;
    @ManyToOne
    @JoinColumn(name = "proveedor", referencedColumnName = "nit")
    private Proveedor proveedor;

    public OrdenDeCompra(String estado, DATE fecha_creacion, DATE fecha_entrega, Sucursal sucursal,
            Proveedor proveedor) {
        this.estado = estado;
        this.fecha_creacion = fecha_creacion;
        this.fecha_entrega = fecha_entrega;
        this.sucursal = sucursal;
        this.proveedor = proveedor;
    }

    OrdenDeCompra() {
        ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DATE getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(DATE fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public DATE getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(DATE fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

}

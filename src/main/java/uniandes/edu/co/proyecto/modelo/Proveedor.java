package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Proveedor")
public abstract class Proveedor {

    @Id
    private Integer nit;
    private String nombre;
    private String direccion;
    private String nombre_contacto;
    private Integer telefono_contacto;

    public Proveedor(Integer nit, String nombre, String direccion, String nombre_contacto, Integer telefono_contacto) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nombre_contacto = nombre_contacto;
        this.telefono_contacto = telefono_contacto;
    }

    Proveedor() {
        ;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre_contacto() {
        return nombre_contacto;
    }

    public void setNombre_contacto(String nombre_contacto) {
        this.nombre_contacto = nombre_contacto;
    }

    public Integer getTelefono_contacto() {
        return telefono_contacto;
    }

    public void setTelefono_contacto(Integer telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }
}

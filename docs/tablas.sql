DROP TABLE InfoExtraOrden;
DROP TABLE RecepcionDeProducto;
DROP TABLE InfoExtraVenta;
DROP TABLE Venta;
DROP TABLE ProveedordeProducto;
DROP TABLE OrdenDeCompra;
DROP TABLE InfoExtraBodega;
DROP TABLE Bodega;
DROP TABLE Sucursal;
DROP TABLE Producto;
DROP TABLE Categoria;
DROP TABLE Proveedor;
DROP TABLE Cliente;
DROP TABLE Ciudad;



CREATE TABLE Cliente (
	cedula NUMBER(10 ,0) NOT NULL,
	nombre VARCHAR2(50) NOT NULL,

	PRIMARY KEY (cedula)
);

CREATE TABLE Ciudad (
	codigo NUMBER(7, 0) NOT NULL,
	nombre VARCHAR2(50) NOT NULL,

	PRIMARY KEY (codigo)
);

CREATE TABLE Proveedor (
nit NUMBER(15, 0) NOT NULL,
	nombre VARCHAR2(50) NOT NULL,
	direccion VARCHAR2(50) NOT NULL,
	nombre_contacto VARCHAR2(50) NOT NULL,
	telefono_contacto NUMBER(10, 0) NOT NULL,

	UNIQUE (nombre),
	PRIMARY KEY (nit)
);

CREATE TABLE Categoria (
	nombre VARCHAR2(50) NOT NULL,
	codigo VARCHAR2(50) NOT NULL, 
	descripcion VARCHAR2(150) NOT NULL,
	caracteristicas_de_almacenamiento VARCHAR2(100) NOT NULL,

	UNIQUE (nombre),
	PRIMARY KEY (codigo)
);

CREATE TABLE Producto (
	nombre VARCHAR2(50) NOT NULL,
	precio_unitario NUMBER(12,2) NOT NULL,
presentacion VARCHAR2(50) NOT NULL,
    	cantidad_presentacion NUMBER(7,2) NOT NULL,
unidad_medida_presentacion VARCHAR2(50) NOT NULL,
cantidad_empaque NUMBER(7, 2) NOT NULL,
unidad_empaque VARCHAR2(50) NOT NULL,
fecha_expiracion DATE NOT NULL,
codigo_barras NUMBER(7,0) NOT NULL,
categoria VARCHAR2(50) NOT NULL,

	PRIMARY KEY (codigo_barras),
	FOREIGN KEY (categoria) REFERENCES Categoria(codigo)
);

CREATE TABLE Sucursal (
	id VARCHAR2(50) NOT NULL,
	nombre VARCHAR2(50) NOT NULL,
	tamano_metros NUMBER(6, 0) NOT NULL,
	direccion VARCHAR2(50) NOT NULL,
	telefono NUMBER(10, 0) NOT NULL,
	codigo_ciudad NUMBER(7, 0) NOT NULL,

	UNIQUE (nombre),
	PRIMARY KEY (id),
	FOREIGN KEY (codigo_ciudad) REFERENCES Ciudad(codigo)
);

CREATE TABLE Bodega (
	id VARCHAR2(50) NOT NULL,
	nombre VARCHAR2(50) NOT NULL,
	tamano_metros2 NUMBER(6, 0) NOT NULL,
	sucursal VARCHAR2(50) NOT NULL,

	UNIQUE (nombre),
	PRIMARY KEY (id),
	FOREIGN KEY (sucursal) REFERENCES Sucursal(id)
);

CREATE TABLE InfoExtraBodega (
	id_bodega VARCHAR2(50) NOT NULL,
	codigo_producto NUMBER(7, 0) NOT NULL,
	nivel_minimo_reorden NUMBER(7,0) NOT NULL,
	capacidad_almacenamiento NUMBER(7, 0) NOT NULL,
	costo_bodega NUMBER(7, 0) NOT NULL,
	total_existencias NUMBER(7, 0) NOT NULL,
	costo_promedio NUMBER(12, 2) NOT NULL,

	PRIMARY KEY (id_bodega, codigo_producto),
	FOREIGN KEY (id_bodega) REFERENCES Bodega(id),
	FOREIGN KEY (codigo_producto) REFERENCES Producto(codigo_barras)
);

CREATE TABLE ProveedorDeProducto (
	codigo_barras NUMBER(7, 0) NOT NULL,
	nit_proveedor NUMBER(15, 0) NOT NULL,

	PRIMARY KEY (codigo_barras, nit_proveedor),
	FOREIGN KEY (codigo_barras) REFERENCES Producto(codigo_barras),
	FOREIGN KEY (nit_proveedor) REFERENCES Proveedor(nit)
);

CREATE TABLE OrdenDeCompra (
	id VARCHAR2(50) NOT NULL, 
	estado VARCHAR2(50) NOT NULL, 
	fecha_creacion DATE NOT NULL,
	fecha_entrega DATE NOT NULL,
	sucursal VARCHAR2(50) NOT NULL,
	proveedor NUMBER(15, 0) NOT NULL,

	PRIMARY KEY (id),
	FOREIGN KEY (sucursal) REFERENCES Sucursal(id),
	FOREIGN KEY (proveedor) REFERENCES Proveedor(nit)
);

CREATE TABLE Venta (
	id VARCHAR2(50) NOT NULL,
	fecha_venta DATE NOT NULL,
	cedula_cliente NUMBER(10, 0) NOT NULL,
	id_sucursal VARCHAR2(50) NOT NULL,

	PRIMARY KEY (id),
	FOREIGN KEY (cedula_cliente) REFERENCES Cliente(cedula),
	FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id)
);

CREATE TABLE InfoExtraVenta (
	id_venta VARCHAR2(50) NOT NULL,
	codigo_producto NUMBER(7, 0) NOT NULL,
	cantidad NUMBER(7, 0) NOT NULL,

	PRIMARY KEY (id_venta, codigo_producto),
	FOREIGN KEY (id_venta) REFERENCES Venta(id),
	FOREIGN KEY (codigo_producto) REFERENCES Producto(codigo_barras)
);

CREATE TABLE RecepcionDeProducto (
	id VARCHAR2(50) NOT NULL,
	fecha_recepcion DATE NOT NULL,
	id_orden_compra VARCHAR2(50) NOT NULL,
	id_bodega VARCHAR2(50) NOT NULL,

	PRIMARY KEY (id),
	FOREIGN KEY (id_orden_compra) REFERENCES OrdenDeCompra(id),
	FOREIGN KEY (id_bodega) REFERENCES Bodega(id)
);

CREATE TABLE InfoExtraOrden (
	codigo_barras_producto NUMBER(7, 0) NOT NULL,
	id_orden VARCHAR2(50) NOT NULL,
	cantidad NUMBER(7, 0) NOT NULL,
	costo_unitario_compra NUMBER(7, 0) NOT NULL,

	PRIMARY KEY (codigo_barras_producto, id_orden),
	FOREIGN KEY (codigo_barras_producto) REFERENCES Producto(codigo_barras),
	FOREIGN KEY (id_orden) REFERENCES OrdenDeCompra(id)
);



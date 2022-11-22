CREATE TABLE TipoServicio ( 
    IDTipoServicio number(10) DEFAULT NULL,
    TipoServicio varchar2(25) NOT NULL,
    PRIMARY KEY (IDtipoServicio)
); --Esta no --------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE TipoComision (
    IDtipoCom number(10) DEFAULT NULL,
    TipoCom varchar2(255) NOT NULL,
    Porcentaje number(10, 1) NOT NULL,
    CHECK (Porcentaje>0),
    PRIMARY KEY (IDtipoCom)
);--Esta no --------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE TipoImpuesto (
    IDtipoImp number(10) DEFAULT NULL,
    TipoImp varchar2(255) NOT NULL,
    Porcentaje number(3, 1) NOT NULL,
    PRIMARY KEY (IDtipoImp)
);--Esta no --------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE TipoPago (
    IDtipoPago number(10) DEFAULT NULL,
    TipoPago varchar2(25) NOT NULL,
    PRIMARY KEY (IDtipoPago)
);--Esta no --------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE TipoUsuario (
    IDtipoUs number(10) DEFAULT NULL,
    TipoUs varchar2(255) NOT NULL,
    PRIMARY KEY (IDtipoUs)
);--Esta no --------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE TipoVivienda (
    IDtipoViv number(10) DEFAULT NULL,
    Tipo varchar2(25) NOT NULL,
    PRIMARY KEY (IDtipoViv)
);--Esta no --------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Ubicacion (
    IDubicacion number(10) DEFAULT NULL,
    Pais varchar2(25) NOT NULL,
    Departamento varchar2(25) NOT NULL,
    Municipio varchar2(25) NOT NULL,
    PRIMARY KEY (IDubicacion)
);

CREATE TABLE Agencia (
    IDAgencia number(10) DEFAULT NULL,
    Nombre varchar2(25) NOT NULL,
    PRIMARY KEY (IDAgencia)
);

CREATE TABLE Usuario (
    IDusuario number(10) DEFAULT NULL,
    Nombre varchar2(25),
    Apellido varchar2(25),
    NombreUsuario varchar2(25) NOT NULL,
    Contrasena varchar2(32) NOT NULL,
    Correo varchar2(50) NOT NULL,
    Estado number(1) DEFAULT 1 NOT NULL,
    Fecha date DEFAULT SYSDATE NOT NULL,
    MaxPorOfrecer number(10, 2), --nullable
    Salario number(10, 2), --nullable
    IDtipoUsuario number(10) NOT NULL,
    IDubicacion number(10), --nullable
    IDAgencia number(10), --nullable
    CHECK (Salario>0 OR Salario=null),
    CHECK (MaxPorOfrecer>0 OR MaxPorOfrecer=null),
    UNIQUE (NombreUsuario),
    PRIMARY KEY (IDusuario),
    FOREIGN KEY (IDtipoUsuario) REFERENCES TipoUsuario (IDtipoUs) ON DELETE CASCADE,
    FOREIGN KEY (IDAgencia) REFERENCES Agencia (IDAgencia) ON DELETE CASCADE
);

CREATE TABLE Vivienda (
    IDvivienda number(10) DEFAULT NULL,
    Direccion varchar2(25) NOT NULL,
    CantHabitaciones number(10) NOT NULL,
    PrecioRentaMensual number(10, 2) NOT NULL,
    Fecha date DEFAULT SYSDATE NOT NULL,
    Estado number(1) DEFAULT 1 NOT NULL, --1 para disponible, 2 para en renta, 0 para eliminada
    Descripcion varchar2(500) DEFAULT NULL,
    IDubicacion number(10) NOT NULL,
    IDtipoViv number(10) NOT NULL,
    IDAgencia number(10) NOT NULL,
    CHECK (PrecioRentaMensual>0),
    PRIMARY KEY (IDvivienda),
    FOREIGN KEY (IDubicacion) REFERENCES Ubicacion (IDubicacion) ON DELETE CASCADE,
    FOREIGN KEY (IDtipoViv) REFERENCES TipoVivienda (IDtipoViv) ON DELETE CASCADE,
    FOREIGN KEY (IDAgencia) REFERENCES Agencia (IDAgencia) ON DELETE CASCADE
);

CREATE TABLE Comentario (
    IDcomentario number(10) DEFAULT NULL,
    Comentario varchar2(500) NOT NULL,
    Fecha date DEFAULT SYSDATE NOT NULL,
    IDvivienda number(10) NOT NULL,
    PRIMARY KEY (IDcomentario),
    FOREIGN KEY (IDvivienda) REFERENCES Vivienda (IDvivienda) ON DELETE CASCADE
);

CREATE TABLE Factura (
    IDfactura number(10) DEFAULT NULL,
    Correo varchar2(25) NOT NULL,
    TotalaPagar float(10) NOT NULL,
    FechaInicio date NOT NULL,
    FechaFin date NOT NULL,
    IDusuario number(10) NOT NULL,
    IDvivienda number(10) NOT NULL,
    IDServ number(10) NOT NULL,
    CHECK (TotalaPagar>0),
    PRIMARY KEY (IDfactura),
    FOREIGN KEY (IDusuario) REFERENCES Usuario (IDusuario) ON DELETE CASCADE,
    FOREIGN KEY (IDvivienda) REFERENCES Vivienda (IDvivienda) ON DELETE CASCADE
);

CREATE TABLE Factura_TipoComision (
    IDtipoCom number(10) NOT NULL,
    IDfactura number(10) NOT NULL,
    PRIMARY KEY (IDtipoCom, IDfactura),
    FOREIGN KEY (IDtipoCom) REFERENCES TipoComision (IDtipoCom) ON DELETE CASCADE,
    FOREIGN KEY (IDfactura) REFERENCES Factura (IDfactura) ON DELETE CASCADE
);--Esta no --------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Pago (
    IDfactura number(10) NOT NULL,
    IDtipoPago number(10) NOT NULL,
    Numero number(16), --nullable
    Monto number(30, 2) NOT NULL,
    NumeroAutorizacion number(25), --nullable
    CHECK (Monto>0),
    PRIMARY KEY (IDfactura, IDtipoPago),
    FOREIGN KEY (IDfactura) REFERENCES Factura (IDfactura) ON DELETE CASCADE,
    FOREIGN KEY (IDtipoPago) REFERENCES TipoPago (IDtipoPago) ON DELETE CASCADE
);

CREATE TABLE TipoImpuesto_Factura (
    IDtipoImp number(10) NOT NULL,
    IDfactura number(10) NOT NULL,
    PRIMARY KEY (IDtipoImp, IDfactura),
    FOREIGN KEY (IDfactura) REFERENCES Factura (IDfactura) ON DELETE CASCADE,
    FOREIGN KEY (IDtipoImp) REFERENCES TipoImpuesto (IDtipoImp) ON DELETE CASCADE
); --Esta no --------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE Servicio (
    IDfactura number(10) NOT NULL,
    IDtipoServicio number(10) NOT NULL,
    Costo number(10, 2),
    CHECK (Costo>0),
    PRIMARY KEY (IDtipoServicio, IDfactura),
    FOREIGN KEY (IDfactura) REFERENCES Factura (IDfactura) ON DELETE CASCADE,
    FOREIGN KEY (IDtipoServicio) REFERENCES TipoServicio (IDtipoServicio) ON DELETE CASCADE
);

CREATE TABLE Visita (
    IDusuario number(10) NOT NULL,
    IDvivienda number(10) NOT NULL,
    Fecha date DEFAULT SYSDATE NOT NULL,
    PRIMARY KEY (IDusuario, IDvivienda),
    FOREIGN KEY (IDusuario) REFERENCES Usuario (IDusuario) ON DELETE CASCADE,
    FOREIGN KEY (IDvivienda) REFERENCES Vivienda (IDvivienda) ON DELETE CASCADE
);--Esta no --------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE SolicitudRenta (
    IDusuario number(10) NOT NULL,
    IDvivienda number(10) NOT NULL,
    Fecha date DEFAULT SYSDATE NOT NULL,
    PRIMARY KEY (IDusuario, IDvivienda),
    FOREIGN KEY (IDusuario) REFERENCES Usuario (IDusuario) ON DELETE CASCADE,
    FOREIGN KEY (IDvivienda) REFERENCES Vivienda (IDvivienda) ON DELETE CASCADE
);--Esta no --------------------------------------------------------------------------------------------------------------------------------------------------------

DROP TABLE Servicio;
DROP TABLE TipoImpuesto_Factura;
DROP TABLE Pago;
DROP TABLE Factura_TipoComision;
DROP TABLE Factura;
DROP TABLE Comentario;
DROP TABLE Vivienda;
DROP TABLE Usuario;
DROP TABLE Agencia;
DROP TABLE Ubicacion;
DROP TABLE TipoVivienda;
DROP TABLE TipoUsuario;
DROP TABLE TipoPago;
DROP TABLE TipoImpuesto;
DROP TABLE TipoComision;
DROP TABLE TipoServicio;
DROP TABLE Visita;
DROP TABLE SolicitudRenta;
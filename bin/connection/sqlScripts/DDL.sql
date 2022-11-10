CREATE TABLE Servicio (
    IDservicio number(10) NOT NULL,
    TipoServicio varchar2(25) NOT NULL,
    Costo number(10, 2) NOT NULL,
    CHECK (Costo>0),
    PRIMARY KEY (IDservicio)
);

CREATE TABLE TipoComision (
    IDtipoCom number(10) NOT NULL,
    TipoCom varchar2(255) NOT NULL,
    Porcentaje number(10, 1) NOT NULL,
    CHECK (Porcentaje>0),
    PRIMARY KEY (IDtipoCom)
);

CREATE TABLE TipoDocumento (
    IDtipoDoc number(10) NOT NULL,
    TipoDoc varchar2(255) NOT NULL,
    PRIMARY KEY (IDtipoDoc)
);

CREATE TABLE TipoImpuesto (
    IDtipoImp number(10) NOT NULL,
    TipoImp varchar2(255) NOT NULL,
    Porcentaje number(3, 1) NOT NULL,
    PRIMARY KEY (IDtipoImp)
);

CREATE TABLE TipoPago (
    IDtipoPago number(10) NOT NULL,
    TipoPago varchar2(25) NOT NULL,
    Pago number(30, 2) NOT NULL,
    numeroAutorizacion number(25),
    PRIMARY KEY (IDtipoPago)
);

CREATE TABLE TipoUsuario (
    IDtipoUs number(10) NOT NULL,
    TipoUs varchar2(255) NOT NULL,
    PRIMARY KEY (IDtipoUs)
);

CREATE TABLE TipoVivienda (
    IDtipoViv number(10) NOT NULL,
    Tipo varchar2(25) NOT NULL,
    PRIMARY KEY (IDtipoViv)
);

CREATE TABLE Ubicacion (
    IDubicacion number(10) NOT NULL,
    Pais varchar2(25) NOT NULL,
    Departamento varchar2(25) NOT NULL,
    Municipio varchar2(25) NOT NULL,
    PRIMARY KEY (IDubicacion)
);

CREATE TABLE Agencia (
    IDAgencia number(10) NOT NULL,
    Nombre varchar2(25) NOT NULL,
    PRIMARY KEY (IDAgencia)
);

CREATE TABLE Usuario (
    IDusuario number(10) NOT NULL,
    Nombre varchar2(25) NOT NULL,
    Apellido varchar2(25) NOT NULL,
    NumeroDoc number(10) NOT NULL,
    NombreUsuario varchar2(25) NOT NULL,
    Contrasena varchar2(25) NOT NULL,
    Correo varchar2(25) NOT NULL,
    Salario number(10, 2), --nullable
    IDtipoUsuario number(10) NOT NULL,
    IDtipoDoc number(10) NOT NULL,
    IDAgencia number(10), --nullable
    CHECK (Salario>0),
    PRIMARY KEY (IDusuario),
    FOREIGN KEY (IDtipoUsuario) REFERENCES TipoUsuario (IDtipoUs) ON DELETE CASCADE,
    FOREIGN KEY (IDtipoDoc) REFERENCES TipoDocumento (IDtipoDoc) ON DELETE CASCADE,
    FOREIGN KEY (IDAgencia) REFERENCES Agencia (IDAgencia) ON DELETE CASCADE
);

CREATE TABLE Vivienda (
    IDvivienda number(10) NOT NULL,
    Direccion varchar2(25) NOT NULL,
    CantHabitaciones number(10) NOT NULL,
    PrecioRentaMensual number(10, 2) NOT NULL,
    Estado number(1) NOT NULL,
    Descripcion varchar2(500),
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
    IDcomentario number(10) NOT NULL,
    Comentario varchar2(500) NOT NULL,
    Fecha date NOT NULL,
    IDvivienda number(10) NOT NULL,
    PRIMARY KEY (IDcomentario),
    FOREIGN KEY (IDvivienda) REFERENCES Vivienda (IDvivienda) ON DELETE CASCADE
);

CREATE TABLE Pago (
    IDpago number(10) NOT NULL,
    TotalaPagar float(10) NOT NULL,
    FechaInicio date NOT NULL,
    FechaFin date NOT NULL,
    IDusuario number(10) NOT NULL,
    IDvivienda number(10) NOT NULL,
    IDServ number(10) NOT NULL,
    CHECK (TotalaPagar>0),
    PRIMARY KEY (IDpago),
    FOREIGN KEY (IDusuario) REFERENCES Usuario (IDusuario) ON DELETE CASCADE,
    FOREIGN KEY (IDvivienda) REFERENCES Vivienda (IDvivienda) ON DELETE CASCADE
);

CREATE TABLE Pago_TipoComision (
    IDtipoCom number(10) NOT NULL,
    IDpago number(10) NOT NULL,
    PRIMARY KEY (IDtipoCom, IDpago),
    FOREIGN KEY (IDtipoCom) REFERENCES TipoComision (IDtipoCom) ON DELETE CASCADE,
    FOREIGN KEY (IDpago) REFERENCES Pago (IDpago) ON DELETE CASCADE
);

CREATE TABLE Pago_TipoPago (
    IDpago number(10) NOT NULL,
    IDtipoPago number(10) NOT NULL,
    PRIMARY KEY (IDpago, IDtipoPago),
    FOREIGN KEY (IDpago) REFERENCES Pago (IDpago) ON DELETE CASCADE,
    FOREIGN KEY (IDtipoPago) REFERENCES TipoPago (IDtipoPago) ON DELETE CASCADE
);

CREATE TABLE TipoImpuestos_Pago (
    IDtipoImp number(10) NOT NULL,
    IDpago number(10) NOT NULL,
    PRIMARY KEY (IDtipoImp, IDpago),
    FOREIGN KEY (IDpago) REFERENCES Pago (IDpago) ON DELETE CASCADE,
    FOREIGN KEY (IDtipoImp) REFERENCES TipoImpuesto (IDtipoImp) ON DELETE CASCADE
);

CREATE TABLE Servicio_Pago (
    IDpago number(10) NOT NULL,
    IDservicio number(10) NOT NULL,
    PRIMARY KEY (IDservicio, IDpago),
    FOREIGN KEY (IDpago) REFERENCES Pago (IDpago) ON DELETE CASCADE,
    FOREIGN KEY (IDservicio) REFERENCES Servicio (IDservicio) ON DELETE CASCADE
);
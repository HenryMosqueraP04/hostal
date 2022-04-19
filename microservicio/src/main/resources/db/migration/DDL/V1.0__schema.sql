
-- tabla tipo_pago

CREATE TABLE tipo_pago(
    id INT NOT NULL auto_increment,
    nombre VARCHAR(250) NOT NULL UNIQUE,
    porcentaje_impuesto DECIMAL(2,1) NOT NULL DEFAULT 0.0,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

INSERT INTO tipo_pago(nombre, porcentaje_impuesto, estado) VALUES
('EFECTIVO', 1.0, 1),
('VIRTUAL', 2.0, 1);

-- tabla dia

CREATE TABLE dia(
    id INT NOT NULL auto_increment,
    nombre VARCHAR(250) NOT NULL UNIQUE,
    valor_por_hora DECIMAL(20,2) NOT NULL DEFAULT 0.00,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);


INSERT INTO dia(nombre, valor_por_hora, estado) VALUES
('MONDAY', 3000.00, 1),
('TUESDAY', 3000.00, 1),
('WEDNESDAY', 3000.00, 1),
('THURSDAY', 3000.00, 1),
('FRIDAY', 3000.00, 1),
('SATURDAY', 4500.00, 1),
('SUNDAY', 4500.00, 1);

--  tabla tipo habitacion

CREATE TABLE tipo_habitacion(
    id INT NOT NULL auto_increment,
    nombre VARCHAR(250) NOT NULL UNIQUE,
    valor DECIMAL(20,2) NOT NULL DEFAULT 0.00,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

INSERT INTO tipo_habitacion(nombre,valor,estado) VALUES
('PERSONAL',8500.00,1),
('FAMILIAR',15000.00,1);


-- tabla habitacion

CREATE TABLE habitacion(
    id INT NOT NULL auto_increment,
    nombre VARCHAR(250) NOT NULL,
    tipo_habitacion_id INT NOT NULL,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (tipo_habitacion_id) REFERENCES tipo_habitacion(id)
);

INSERT INTO habitacion(nombre, tipo_habitacion_id, estado) VALUES
('HABITACION PERSONAL',1,1),
('HABITACION FAMILIAR',2,1);

-- tabla usuario

CREATE TABLE usuario (
    id INT NOT NULL auto_increment,
    nombre_completo VARCHAR(250) NOT NULL,
    numero_documento VARCHAR(10) NOT NULL,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

INSERT INTO usuario(nombre_completo, numero_documento, estado) VALUES
('HENRY MOSQUERA', '1003815494', 1);

-- tabla reserva

CREATE TABLE reserva(
    id INT NOT NULL auto_increment,
    usuario_id INT NOT NULL,
    habitacion_id INT NOT NULL,
    tipo_pago_id INT NOT NULL,
    valor DECIMAL(20,2) NOT NULL DEFAULT 0.00,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    fecha_inicio DATETIME NOT NULL DEFAULT NOW(),
    fecha_fin DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (habitacion_id) REFERENCES habitacion(id),
    FOREIGN KEY (tipo_pago_id) REFERENCES tipo_pago(id)
);

INSERT INTO reserva(usuario_id,habitacion_id,tipo_pago_id,
valor, estado, fecha_inicio, fecha_fin) VALUES
(1,2,1,81305.00, 1, '2022-07-04 00:00:00', '2022-07-04 23:00:00');






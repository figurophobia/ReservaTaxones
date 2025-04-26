-- Creación de tablas para sistema de gestión de reservas naturales

-- Tabla de taxonomías
CREATE TABLE taxones (
    nombre VARCHAR(100) PRIMARY KEY,
    tipo VARCHAR(100),
    taxon_superior VARCHAR(100),
    FOREIGN KEY (taxon_superior) REFERENCES taxones(nombre)
);

-- Tabla unificada de áreas geográficas
CREATE TABLE area_geografica (
    nombre_reserva VARCHAR(100) PRIMARY KEY,
    extension INTEGER,
    altitud_nivel_bajo INTEGER,
    altitud_nivel_alto INTEGER,
    profundidad INTEGER,
    esAcuatica BOOLEAN,
    esTerrestre BOOLEAN
);

-- Tabla de países
CREATE TABLE pais (
    nombre VARCHAR(100),
    area VARCHAR(100),
    PRIMARY KEY (nombre, area),
    FOREIGN KEY (area) REFERENCES area_geografica(nombre_reserva)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Tabla de especies
CREATE TABLE especies (
    nombre_cientifico VARCHAR(150) PRIMARY KEY,
    nombre_comun VARCHAR(150),
    descripcion TEXT,
    area_geografica VARCHAR(100),
    nombre_taxon VARCHAR(100),
    FOREIGN KEY (area_geografica) REFERENCES area_geografica(nombre_reserva)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (nombre_taxon) REFERENCES taxones(nombre)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Tabla de ejemplares de cada especie
CREATE TABLE ejemplar (
    id INTEGER,
    nombre_cientifico_especie VARCHAR(150),
    mote VARCHAR(100),
    edad INTEGER,
    PRIMARY KEY (id, nombre_cientifico_especie),
    FOREIGN KEY (nombre_cientifico_especie) REFERENCES especies(nombre_cientifico)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Tabla de trabajadores
CREATE TABLE trabajadores (
    dni VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100),
    sueldo NUMERIC(10,2),
    horas INTEGER
);

-- Tabla de misiones
CREATE TABLE misiones (
    dni_trabajador VARCHAR(20),
    nombre_cientifico_especie VARCHAR(150),
    fecha_inicio DATE,
    fecha_fin DATE,
    descripcion TEXT,
    PRIMARY KEY (dni_trabajador, nombre_cientifico_especie, fecha_inicio),
    FOREIGN KEY (dni_trabajador) REFERENCES trabajadores(dni)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (nombre_cientifico_especie) REFERENCES especies(nombre_cientifico)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Tabla de relación trabajadores-misiones
CREATE TABLE trabajadores_misiones (
    trabajador VARCHAR(20),
    nombre_cientifico_especie VARCHAR(150),
    fecha_inicio DATE,
    PRIMARY KEY (trabajador, nombre_cientifico_especie, fecha_inicio),
    FOREIGN KEY (trabajador) REFERENCES trabajadores(dni)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (trabajador, nombre_cientifico_especie, fecha_inicio) 
        REFERENCES misiones(dni_trabajador, nombre_cientifico_especie, fecha_inicio)
        ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE alimento (
    id INTEGER PRIMARY KEY,
    tipo VARCHAR(50),
    nombre VARCHAR(50)
);

CREATE TABLE consumirAlimentos (
	nombre_especie VARCHAR(150),
	id_alimento INTEGER,
	cantidad INTEGER,
	frecuencia DECIMAL(2,2),
	PRIMARY KEY (nombre_especie, id_alimento),
	FOREIGN KEY (nombre_especie) REFERENCES ejemplar(nombre_cientifico_especie) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY (id_alimento) REFERENCES alimento(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE clinica_medica (
    nombre VARCHAR(100) PRIMARY KEY,
    ubicacion VARCHAR(100),
    num_empleados INTEGER
);

CREATE TABLE revisar (
	clinica VARCHAR(100),
	ejemplar INTEGER,
	especie_asociada VARCHAR(150),
	fecha_revision DATE,
	informe VARCHAR(200),
	
	PRIMARY KEY (clinica, ejemplar, especie_asociada),
	FOREIGN KEY (clinica) REFERENCES clinica_medica(nombre) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY (ejemplar) REFERENCES ejemplar(id) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREING KEY (especie_asociada) REFERENCES ejemplar(nombre_cientifico_especie) ON UPDATE CASCADE ON DELETE RESTRICT
);
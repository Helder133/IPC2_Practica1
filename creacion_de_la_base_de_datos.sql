CREATE DATABASE admin_eventos;

USE admin_eventos;

CREATE TABLE IF NOT EXISTS evento (
	codigo_de_evento VARCHAR (15) PRIMARY KEY,
	fecha_del_evento DATE NOT NULL,
	tipo_de_evento VARCHAR (10) CHECK (tipo_de_evento IN ('CHARLA','CONGRESO','TALLER','DEBATE')) NOT NULL,
	titulo_de_evento VARCHAR (150) NOT NULL,
	ubicacion VARCHAR (150) NOT NULL,
	cupo_maximo INT NOT NULL
);

CREATE TABLE IF NOT EXISTS participante (
	nombre VARCHAR (45) NOT NULL,
	tipo_de_participante VARCHAR (11) CHECK (tipo_de_participante IN ('ESTUDIANTE','PROFESIONAL','INVITADO')) NOT NULL,
	institucion_de_procedencia VARCHAR (150) NOT NULL,
	email VARCHAR (60) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS inscripcion (
	email_participante VARCHAR (60) NOT NULL UNIQUE,
	codigo_de_evento VARCHAR (15) NOT NULL UNIQUE,
	tipo_de_inscripcion VARCHAR (13) CHECK (tipo_de_inscripcion IN ('ASISTENTE','CONFERENCISTA','TALLERISTA','OTRO')) NOT NULL,
	constraint fk_compuesta PRIMARY KEY (email_participante, codigo_de_evento),
	CONSTRAINT fk_email_participante FOREIGN KEY (email_participante) REFERENCES participante (email),
	CONSTRAINT fk_codigo_de_evento FOREIGN KEY (codigo_de_evento) REFERENCES evento (codigo_de_evento)
);

CREATE TABLE IF NOT EXISTS pagar (
	email_participante VARCHAR (60) NOT NULL,
	codigo_de_evento VARCHAR (15) NOT NULL,
	metodo_de_pago VARCHAR (13) CHECK (metodo_de_pago IN ('EFECTIVO','TRANSFERENCIA','TARJETA')),
	monto INT NOT NULL,
	CONSTRAINT pk_compuesta_2 PRIMARY KEY (email_participante, codigo_de_evento),
	CONSTRAINT fk_compuesta FOREIGN KEY (email_participante, codigo_de_evento) REFERENCES inscripcion (email_participante, codigo_de_evento)
);

CREATE TABLE IF NOT EXISTS validar (
	email_participante VARCHAR (60) NOT NULL,
	codigo_de_evento VARCHAR (15) NOT NULL,
	CONSTRAINT pk_compuesto_3 PRIMARY KEY (email_participante, codigo_de_evento),
	CONSTRAINT fk_compuesto_2 FOREIGN KEY (email_participante, codigo_de_evento) REFERENCES pagar (email_participante, codigo_de_evento) 
);

CREATE TABLE IF NOT EXISTS actividad (
	codigo_de_activiad VARCHAR (15) PRIMARY KEY,
	codigo_de_evento VARCHAR (15) NOT NULL,
	tipo_de_actividad VARCHAR (7) CHECK (tipo_de_actividad IN('CHARLA','TALLER','DEBATE','OTRA')) NOT NULL,
	titulo_de_actividad VARCHAR (200) NOT NULL, 
	email_participante VARCHAR (60) NOT NULL,
	hora_de_inico TIME NOT NULL,
	hora_de_fin TIME NOT NULL,
	cupo_max INT NOT NULL,
	CONSTRAINT fk_email_participante_4 FOREIGN KEY (email_participante) REFERENCES participante (email),
	CONSTRAINT fk_codigo_de_evento_4 FOREIGN KEY (codigo_de_evento) REFERENCES evento (codigo_de_evento)
);

CREATE TABLE IF NOT EXISTS asistencia (
	email_participante VARCHAR (60) NOT NULL,
	codigo_de_actividad VARCHAR(15) NOT NULL,
	CONSTRAINT pk_compuesto PRIMARY KEY (email_participante, codigo_de_actividad),
	CONSTRAINT fk_email_participante_5 FOREIGN KEY (email_participante) REFERENCES participante (email),
	CONSTRAINT fk_codigo_de_actividad FOREIGN KEY (codigo_de_actividad) REFERENCES actividad (codigo_de_actividad)
);

ALTER TABLE evento MODIFY COLUMN costo_inscripcion DECIMAL(7,2) NOT NULL;

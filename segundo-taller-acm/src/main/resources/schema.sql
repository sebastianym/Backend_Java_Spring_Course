CREATE TABLE administrador (
    id_administrador integer NOT NULL,
    primer_nombre character varying(50) NOT NULL,
    segundo_nombre character varying(50),
    primer_apellido character varying(50) NOT NULL,
    segundo_apellido character varying(50),
    correo character varying(100) NOT NULL,
    telefono character varying(10) NOT NULL
);


CREATE TABLE administrador_general (
    id_administrador_general integer NOT NULL,
    correo character varying(100) NOT NULL,
    telefono character varying(10) NOT NULL,
    primer_nombre character varying(50) NOT NULL,
    segundo_nombre character varying(50),
    primer_apellido character varying(50) NOT NULL,
    segundo_apellido character varying(50)
);


CREATE TABLE cliente (
    id_cliente integer NOT NULL,
    primer_nombre character varying(50) NOT NULL,
    segundo_nombre character varying(50),
    primer_apellido character varying(50) NOT NULL,
    segundo_apellido character varying(50),
    cedula integer NOT NULL,
    direccion character varying(150) NOT NULL
);


CREATE TABLE empleado (
    id_empleado integer NOT NULL,
    correo character varying(100),
    telofono character varying(10) NOT NULL,
    primer_nombre character varying(50) NOT NULL,
    segundo_nombre character varying(50),
    primer_apellido character varying(50) NOT NULL,
    segundo_apellido character varying(50)
);


CREATE TABLE factura (
    id_factura integer NOT NULL,
    fk_id_reserva integer,
    fk_id_pago integer,
    fecha_emision integer,
    valor_total integer
);


CREATE TABLE habitacion (
    id_habitacion integer NOT NULL,
    numero_habitacion integer NOT NULL,
    fk_id_tipo_habitacion integer,
    precio_dia integer NOT NULL,
    disponible boolean,
    fk_id_hotel integer
);


CREATE TABLE hotel (
    id_hotel integer NOT NULL,
    nombre character varying(30) NOT NULL,
    ciudad character varying(20) NOT NULL,
    telefono character varying(10) NOT NULL,
    correo character varying(100) NOT NULL,
    direccion character varying(200) NOT NULL
);


CREATE TABLE pago (
    id_pago integer NOT NULL,
    fecha timestamp without time zone NOT NULL,
    fk_id_reserva integer,
    metodo_pago character varying(20),
    pago_total integer
);


CREATE TABLE reserva (
    id_reserva integer NOT NULL,
    fecha_inicio timestamp without time zone,
    fecha_final timestamp without time zone,
    cantidad_dias integer,
    estado boolean,
    fecha_reserva timestamp without time zone,
    fk_id_habitacion integer,
    fk_id_cliente integer
);


CREATE TABLE tipo_habitacion (
    id_tipo_habitacion integer NOT NULL,
    fk_id_hotel integer,
    nombre character varying(100),
    cantidad integer
);


CREATE TABLE usuario (
    id_usuario integer NOT NULL,
    nombre_usuario character varying(255) NOT NULL,
    contrasena character varying(255) NOT NULL,
    rol character varying(30) NOT NULL,
    fk_id_cliente integer,
    fk_id_empleado integer,
    fk_id_administrador_general integer,
    fk_id_administrador integer
);


ALTER TABLE ONLY administrador_general
    ADD CONSTRAINT administrador_general_pkey PRIMARY KEY (id_administrador_general);


ALTER TABLE ONLY administrador
    ADD CONSTRAINT administrador_pkey PRIMARY KEY (id_administrador);


ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_cedula_key UNIQUE (cedula);


ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente);


ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (id_empleado);


ALTER TABLE ONLY factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (id_factura);


ALTER TABLE ONLY habitacion
    ADD CONSTRAINT habitacion_numero_habitacion_key UNIQUE (numero_habitacion);


ALTER TABLE ONLY habitacion
    ADD CONSTRAINT habitacion_pkey PRIMARY KEY (id_habitacion);


ALTER TABLE ONLY hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id_hotel);


ALTER TABLE ONLY pago
    ADD CONSTRAINT pago_pkey PRIMARY KEY (id_pago);



ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_pkey PRIMARY KEY (id_reserva);


ALTER TABLE ONLY tipo_habitacion
    ADD CONSTRAINT tipo_habitacion_pkey PRIMARY KEY (id_tipo_habitacion);


ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_id_pago FOREIGN KEY (fk_id_pago) REFERENCES pago(id_pago);


ALTER TABLE ONLY factura
    ADD CONSTRAINT fk_id_reserva FOREIGN KEY (fk_id_reserva) REFERENCES reserva(id_reserva);


ALTER TABLE ONLY habitacion
    ADD CONSTRAINT fk_id_hotel FOREIGN KEY (fk_id_hotel) REFERENCES hotel(id_hotel);


ALTER TABLE ONLY habitacion
    ADD CONSTRAINT fk_id_tipo_habitacion FOREIGN KEY (fk_id_tipo_habitacion) REFERENCES tipo_habitacion(id_tipo_habitacion);


ALTER TABLE ONLY pago
    ADD CONSTRAINT fk_id_reserva FOREIGN KEY (fk_id_reserva) REFERENCES reserva(id_reserva);


ALTER TABLE ONLY reserva
    ADD CONSTRAINT fk_id_cliente FOREIGN KEY (fk_id_cliente) REFERENCES cliente(id_cliente);


ALTER TABLE ONLY reserva
    ADD CONSTRAINT fk_id_habitacion FOREIGN KEY (fk_id_habitacion) REFERENCES habitacion(id_habitacion);


ALTER TABLE ONLY tipo_habitacion
    ADD CONSTRAINT fk_id_hotel FOREIGN KEY (fk_id_hotel) REFERENCES hotel(id_hotel);


ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_id_administrador FOREIGN KEY (fk_id_administrador) REFERENCES administrador(id_administrador);


ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_id_administrador_general FOREIGN KEY (fk_id_administrador_general) REFERENCES administrador_general(id_administrador_general);


ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_id_cliente FOREIGN KEY (fk_id_cliente) REFERENCES cliente(id_cliente);


ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_id_empleado FOREIGN KEY (fk_id_empleado) REFERENCES empleado(id_empleado);

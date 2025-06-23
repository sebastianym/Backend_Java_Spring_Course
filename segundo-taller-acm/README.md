# Sistema de Gestión Hotelera - API REST

## Integrantes

- Santiago Baron Zuleta (20212020052)
- Laura Vanesa Beltran Suarez (20212020044)
- Sebastian Yepes Marta (20212020015)

Este proyecto implementa una API REST para un sistema de gestión de un hotel, permitiendo la administración de usuarios, habitaciones, reservas, pagos y facturación.

## Tecnologías Utilizadas

- Java 21
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Maven

## Cómo Ejecutar el Backend

1.  **Clonar el repositorio:**

    ```bash
    git clone <URL_DEL_REPOSITORIO>
    cd segundo-taller-acm
    ```

2.  **Configurar la base de datos:**

    El proyecto está configurado para conectarse a una base de datos PostgreSQL en Neon. La configuración se encuentra en `src/main/resources/application.properties`.

3.  **Ejecutar la aplicación:**

    Puedes ejecutar la aplicación usando el wrapper de Maven incluido:

    ```bash
    ./mvnw spring-boot:run
    ```

    La API estará disponible en `http://localhost:8080`.

## Endpoints de la API

A continuación se listan los endpoints disponibles en la API.

### Hoteles

-   `GET /api/hoteles`: Obtiene todos los hoteles.
-   `GET /api/hoteles/{id}`: Obtiene un hotel por su ID.
-   `POST /api/hoteles`: Crea un nuevo hotel.
-   `PUT /api/hoteles/{id}`: Actualiza un hotel existente.
-   `DELETE /api/hoteles/{id}`: Elimina un hotel.

### Tipos de Habitación

-   `GET /api/tipos-habitacion`: Obtiene todos los tipos de habitación.
-   `GET /api/tipos-habitacion/{id}`: Obtiene un tipo de habitación por su ID.
-   `POST /api/tipos-habitacion`: Crea un nuevo tipo de habitación.
-   `PUT /api/tipos-habitacion/{id}`: Actualiza un tipo de habitación existente.
-   `DELETE /api/tipos-habitacion/{id}`: Elimina un tipo de habitación.

### Habitaciones

-   `GET /api/habitaciones`: Obtiene todas las habitaciones.
-   `GET /api/habitaciones/{id}`: Obtiene una habitación por su ID.
-   `POST /api/habitaciones`: Crea una nueva habitación.
-   `PUT /api/habitaciones/{id}`: Actualiza una habitación existente.
-   `DELETE /api/habitaciones/{id}`: Elimina una habitación.

### Clientes

-   `GET /api/clientes`: Obtiene todos los clientes.
-   `GET /api/clientes/{id}`: Obtiene un cliente por su ID.
-   `POST /api/clientes`: Crea un nuevo cliente.
-   `PUT /api/clientes/{id}`: Actualiza un cliente existente.
-   `DELETE /api/clientes/{id}`: Elimina un cliente.

### Reservas

-   `GET /api/reservas`: Obtiene todas las reservas.
-   `GET /api/reservas/{id}`: Obtiene una reserva por su ID.
-   `POST /api/reservas`: Crea una nueva reserva.
-   `PUT /api/reservas/{id}`: Actualiza una reserva existente.
-   `DELETE /api/reservas/{id}`: Elimina una reserva.

### Pagos

-   `GET /api/pagos`: Obtiene todos los pagos.
-   `GET /api/pagos/{id}`: Obtiene un pago por su ID.
-   `POST /api/pagos`: Crea un nuevo pago.
-   `PUT /api/pagos/{id}`: Actualiza un pago existente.
-   `DELETE /api/pagos/{id}`: Elimina un pago.

### Facturas

-   `GET /api/facturas`: Obtiene todas las facturas.
-   `GET /api/facturas/{id}`: Obtiene una factura por su ID.
-   `POST /api/facturas`: Crea una nueva factura.
-   `PUT /api/facturas/{id}`: Actualiza una factura existente.
-   `DELETE /api/facturas/{id}`: Elimina una factura.

### Administradores

-   `GET /api/administradores`: Obtiene todos los administradores.
-   `GET /api/administradores/{id}`: Obtiene un administrador por su ID.
-   `POST /api/administradores`: Crea un nuevo administrador.
-   `PUT /api/administradores/{id}`: Actualiza un administrador existente.
-   `DELETE /api/administradores/{id}`: Elimina un administrador.

### Administradores Generales

-   `GET /api/administradores-generales`: Obtiene todos los administradores generales.
-   `GET /api/administradores-generales/{id}`: Obtiene un administrador general por su ID.
-   `POST /api/administradores-generales`: Crea un nuevo administrador general.
-   `PUT /api/administradores-generales/{id}`: Actualiza un administrador general existente.
-   `DELETE /api/administradores-generales/{id}`: Elimina un administrador general.

### Empleados

-   `GET /api/empleados`: Obtiene todos los empleados.
-   `GET /api/empleados/{id}`: Obtiene un empleado por su ID.
-   `POST /api/empleados`: Crea un nuevo empleado.
-   `PUT /api/empleados/{id}`: Actualiza un empleado existente.
-   `DELETE /api/empleados/{id}`: Elimina un empleado.

### Usuarios

-   `GET /api/usuarios`: Obtiene todos los usuarios.
-   `GET /api/usuarios/{id}`: Obtiene un usuario por su ID.
-   `POST /api/usuarios`: Crea un nuevo usuario.
-   `PUT /api/usuarios/{id}`: Actualiza un usuario existente.
-   `DELETE /api/usuarios/{id}`: Elimina un usuario.


# Flujo de Seeding para la API del Hotel

Esta guía describe el orden lógico para ejecutar las peticiones en Postman y poblar la base de datos con datos de ejemplo. Cada paso depende de que el anterior se haya completado con éxito, ya que se utilizan los IDs generados.

## 1. Crear un Hotel

Primero, necesitamos un hotel donde operará todo.

**Endpoint:** `POST /api/hoteles`

**Payload:**
```json
{
    "nombre": "Hotel Paraíso Tropical",
    "ciudad": "Cartagena",
    "telefono": "3123456789",
    "correo": "reservas@paraisotropical.com",
    "direccion": "Bocagrande, Av. San Martín #1-15"
}
```

> **Nota:** Guarda el `idHotel` de la respuesta (ej: `1`) para los siguientes pasos.

## 2. Crear un Tipo de Habitación

Ahora, definimos un tipo de habitación para nuestro hotel.

**Endpoint:** `POST /api/tipos-habitacion`

**Payload:**
```json
{
    "idHotel": 1, // Usa el id del hotel creado
    "nombre": "Suite Presidencial",
    "cantidad": 5
}
```

> **Nota:** Guarda el `idTipoHabitacion` de la respuesta (ej: `1`).

## 3. Crear una Habitación

Creamos una habitación específica del tipo anterior.

**Endpoint:** `POST /api/habitaciones`

**Payload:**
```json
{
    "numeroHabitacion": 101,
    "idTipoHabitacion": 1, // Usa el id del tipo de habitación creado
    "precioDia": 500000,
    "disponible": true,
    "idHotel": 1 // Usa el id del hotel creado
}
```

> **Nota:** Guarda el `idHabitacion` de la respuesta (ej: `1`).

## 4. Crear un Cliente

Registramos un nuevo cliente.

**Endpoint:** `POST /api/clientes`

**Payload:**
```json
{
    "primerNombre": "Juan",
    "primerApellido": "Pérez",
    "cedula": 12345678,
    "direccion": "Calle Falsa 123"
}
```

> **Nota:** Guarda el `idCliente` de la respuesta (ej: `1`).

## 5. Crear una Reserva

El cliente hace una reserva para la habitación que creamos.

**Endpoint:** `POST /api/reservas`

**Payload:**
```json
{
    "fechaInicio": "2025-12-20T15:00:00.000+00:00",
    "fechaFinal": "2025-12-23T12:00:00.000+00:00",
    "cantidadDias": 3,
    "estado": true,
    "idHabitacion": 1, // Usa el id de la habitación creada
    "idCliente": 1 // Usa el id del cliente creado
}
```

> **Nota:** Guarda el `idReserva` de la respuesta (ej: `1`).

## 6. Crear un Pago

El cliente paga la reserva.

**Endpoint:** `POST /api/pagos`

**Payload:**
```json
{
    "fecha": "2025-12-20T15:05:00.000+00:00",
    "idReserva": 1, // Usa el id de la reserva creada
    "metodoPago": "Tarjeta de Crédito",
    "pagoTotal": 1500000
}
```

> **Nota:** Guarda el `idPago` de la respuesta (ej: `1`).

## 7. Crear una Factura

Se genera la factura para la reserva y el pago.

**Endpoint:** `POST /api/facturas`

**Payload:**
```json
{
    "idReserva": 1, // Usa el id de la reserva creada
    "idPago": 1, // Usa el id del pago creado
    "fechaEmision": 1734711900, // Timestamp Unix
    "valorTotal": 1500000
}
```

## 8. Crear Roles de Usuario

Creamos los diferentes tipos de empleados/administradores.

### Empleado
**Endpoint:** `POST /api/empleados`
**Payload:**
```json
{
    "correo": "empleado@hotel.com",
    "telefono": "3001112233",
    "primerNombre": "Carlos",
    "primerApellido": "Gomez"
}
```

### Administrador
**Endpoint:** `POST /api/administradores`
**Payload:**
```json
{
    "primerNombre": "Ana",
    "primerApellido": "Martinez",
    "correo": "admin@hotel.com",
    "telefono": "3004445566"
}
```

> **Nota:** Guarda los IDs generados para el siguiente paso.

## 9. Crear Usuarios

Finalmente, creamos las cuentas de usuario y las asociamos a los roles o al cliente.

### Usuario para un Cliente
**Endpoint:** `POST /api/usuarios`
**Payload:**
```json
{
    "nombreUsuario": "juan.perez",
    "contrasena": "password123",
    "rol": "cliente",
    "idCliente": 1 // ID del cliente creado en el paso 4
}
```

### Usuario para un Empleado
**Endpoint:** `POST /api/usuarios`
**Payload:**
```json
{
    "nombreUsuario": "carlos.gomez",
    "contrasena": "password456",
    "rol": "empleado",
    "idEmpleado": 1 // ID del empleado creado en el paso 8
}
```
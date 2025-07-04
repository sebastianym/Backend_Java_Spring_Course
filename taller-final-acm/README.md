# Sistema de Gestión Hotelera - API REST

## Integrantes

- Santiago Baron Zuleta (20212020052)
- Laura Vanesa Beltran Suarez (20212020044)
- Sebastián Yepes Marta (20212020015)

Este proyecto implementa una API REST para un sistema de gestión de un hotel, permitiendo la administración de usuarios, habitaciones, reservas, pagos y facturación.

## Tecnologías Utilizadas

- Java 21
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Maven
- Spring Security con JWT

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

4.  **Inicializar la base de datos:**

    Antes de usar la API, debes inicializar la base de datos con datos de prueba:

    ```
    GET /setup
    ```

    Este endpoint limpia la base de datos y crea datos iniciales incluyendo usuarios, hoteles, habitaciones y más.

## Colecciones de Postman

El repositorio incluye varias colecciones de Postman para facilitar las pruebas de la API:

1. **Colecciones por partes:**
   - `Hotel_Management_API_Part1_Hoteles.postman_collection.json`: Endpoints relacionados con hoteles.
   - `Hotel_Management_API_Part2_Habitaciones.postman_collection.json`: Endpoints relacionados con habitaciones.
   - `Hotel_Management_API_Part3_Clientes.postman_collection.json`: Endpoints relacionados con clientes.
   - `Hotel_Management_API_Part4_Reservas.postman_collection.json`: Endpoints relacionados con reservas.

2. **Colección de flujo completo:**
   - `Hotel_Management_API_Part5_Flujo_Completo.postman_collection.json`: Contiene todas las solicitudes necesarias para probar el flujo completo de la aplicación, desde la inicialización de la base de datos hasta la creación de facturas.

Para utilizar estas colecciones:
1. Descarga los archivos JSON del repositorio
2. Abre Postman
3. Haz clic en "Import" y selecciona los archivos descargados
4. Las colecciones estarán disponibles en el panel izquierdo de Postman

## Autenticación

La API utiliza autenticación basada en JWT (JSON Web Tokens). Para acceder a los endpoints protegidos, primero debes obtener un token:

1.  **Iniciar sesión:**

    ```
    POST /api/auth/login
    ```

    Cuerpo de la solicitud:
    ```json
    {
        "username": "cliente",
        "password": "12345"
    }
    ```

    Respuesta:
    ```json
    {
        "token": "eyJhbGciOiJIUzUxMiJ9...",
        "type": "Bearer",
        "id": 1,
        "username": "cliente",
        "roles": ["CLIENTE"]
    }
    ```

2.  **Usar el token:**

    Incluye el token en el encabezado `Authorization` de tus solicitudes:

    ```
    Authorization: Bearer eyJhbGciOiJIUzUxMiJ9...
    ```

### Usuarios predefinidos

Después de ejecutar el endpoint `/setup`, se crean los siguientes usuarios:

| Usuario      | Contraseña | Rol                   |
|--------------|------------|----------------------|
| cliente      | 12345      | CLIENTE              |
| empleado     | 12345      | EMPLEADO             |
| admin        | 12345      | ADMINISTRADOR        |
| admingeneral | 12345      | ADMINISTRADOR_GENERAL |

## Endpoints de la API

A continuación se listan los endpoints disponibles en la API.

### Inicialización de datos

- `GET /setup`: Inicializa la base de datos con datos de prueba. Este endpoint limpia todos los datos existentes y crea nuevos registros para todas las entidades (hoteles, habitaciones, usuarios, etc.).

### Autenticación

- `POST /api/auth/login`: Inicia sesión y obtiene un token JWT.
- `POST /api/auth/register`: Registra un nuevo usuario.

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

## Flujo de Trabajo Recomendado

Para probar la API, se recomienda seguir este flujo de trabajo:

1. Inicializar la base de datos con `GET /setup`
2. Iniciar sesión con alguno de los usuarios predefinidos usando `POST /api/auth/login`
3. Usar el token JWT obtenido para acceder a los endpoints protegidos

La colección de Postman incluida en el repositorio contiene ejemplos de todas las solicitudes necesarias para probar la API.
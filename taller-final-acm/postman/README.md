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

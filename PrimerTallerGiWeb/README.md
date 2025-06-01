# 🛒 E-Commerce Demo - GIWeb

## 👥 Integrantes del equipo
- **Laura Vanesa Suarez Beltran** (20212020044)
- **Sebastian Yepes Marta** (20212020015)
- **Santiago Baron Zuleta** (20212020052)

---

## Credenciales
- "username": "mor_2314",
- "password": "83r5^_",

## 📋 Descripción y funcionamiento

Esta aplicación es un E-Commerce demo construido con **Spring Boot MVC** y **Thymeleaf** que consume la API pública [FakeStoreAPI](https://fakestoreapi.com/). Permite realizar login, visualizar productos y consultar carritos de usuario, todo con validaciones y experiencia visual.

### 🚦 Flujos principales

1. **Inicio de sesión**
   - El usuario accede a `/login` e ingresa sus credenciales.
   - Se valida que los campos no estén vacíos y se muestran mensajes de error claros si hay problemas.
   - Si las credenciales son correctas, se obtiene el ID del usuario y se inicia sesión.

2. **Visualización de productos**
   - Tras el login, se accede a `/products` donde se muestran los productos en cards, con diseño limpio y responsivo.
   - El usuario puede navegar a sus carritos o cerrar sesión desde aquí.

3. **Consulta de carritos**
   - Desde productos, el usuario accede a `/carts` donde ve sus carritos y puede desplegar los productos de cada uno, ver cantidades, subtotales y el total de pago.
   - El título muestra el nombre del usuario (por ejemplo: "Carritos de David Morrison").
   - Siempre que el usuario no tenga sesión, cualquier intento de acceder a `/products` o `/carts` lo redirige a `/login`.

4. **Cierre de sesión**
   - El usuario puede cerrar sesión desde cualquier página interna, lo que elimina la sesión y protege su información.

### ✅ Validaciones y mensajes de error
- **Campos vacíos**: Se resaltan los campos y se muestran mensajes debajo de cada input.
- **Credenciales incorrectas**: Aparece un snackbar rojo con mensaje debajo del botón de login.
- **Acceso no autorizado**: Si no hay sesión, cualquier intento de navegar a productos o carritos redirige a login.

---

## 🛠️ Construcción y consideraciones técnicas

- **Frameworks y tecnologías**: Java 21, Spring Boot, Spring MVC, Thymeleaf, Lombok, Bootstrap 5, consumo de REST API con RestTemplate.
- **Beans reutilizables**: RestTemplate se declara como Bean en una clase de configuración y se inyecta por constructor en los servicios.
- **Inyección de dependencias**: Se hace por constructor.
- **DTOs y models**: Uso de DTOs con Lombok para mapear datos de la API y modelos de dominio claros.
- **Validaciones**: Se usan anotaciones de validación (`@NotBlank`) y manejo de errores en el backend y frontend.
- **Seguridad**: El userId se guarda en sesión, nunca se expone en la URL. Acceso a rutas internas solo si hay sesión válida.
- **Cierre de sesión**: Invalida la sesión y protege la privacidad del usuario.


package com.giweb.supply.business.segundotalleracm.controller;

import com.giweb.supply.business.segundotalleracm.dto.SeedResponseDTO;
import com.giweb.supply.business.segundotalleracm.model.*;
import com.giweb.supply.business.segundotalleracm.repository.*;
import com.giweb.supply.business.segundotalleracm.security.enums.RolEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/setup")
@Slf4j
public class SetupController {

    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final AdministradorRepository administradorRepository;
    private final AdministradorGeneralRepository administradorGeneralRepository;
    private final UsuarioRepository usuarioRepository;
    private final HotelRepository hotelRepository;
    private final TipoHabitacionRepository tipoHabitacionRepository;
    private final HabitacionRepository habitacionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    public SetupController(
            ClienteRepository clienteRepository,
            EmpleadoRepository empleadoRepository,
            AdministradorRepository administradorRepository,
            AdministradorGeneralRepository administradorGeneralRepository,
            UsuarioRepository usuarioRepository,
            HotelRepository hotelRepository,
            TipoHabitacionRepository tipoHabitacionRepository,
            HabitacionRepository habitacionRepository,
            PasswordEncoder passwordEncoder,
            JdbcTemplate jdbcTemplate) {
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
        this.administradorRepository = administradorRepository;
        this.administradorGeneralRepository = administradorGeneralRepository;
        this.usuarioRepository = usuarioRepository;
        this.hotelRepository = hotelRepository;
        this.tipoHabitacionRepository = tipoHabitacionRepository;
        this.habitacionRepository = habitacionRepository;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    @Transactional
    public ResponseEntity<SeedResponseDTO> setup() {
        log.info("Inicializando base de datos con datos de prueba");
        
        try {
            clearExistingData();

            Map<String, List<Integer>> createdEntities = new HashMap<>();
            Map<String, String> credentials = new HashMap<>();
            
            List<Integer> clienteIds = createClientes();
            createdEntities.put("clientes", clienteIds);
            
            List<Integer> empleadoIds = createEmpleados();
            createdEntities.put("empleados", empleadoIds);
            
            List<Integer> adminIds = createAdministradores();
            createdEntities.put("administradores", adminIds);
            
            List<Integer> adminGeneralIds = createAdministradoresGenerales();
            createdEntities.put("administradoresGenerales", adminGeneralIds);
            
            createUsuarios(clienteIds, empleadoIds, adminIds, adminGeneralIds, credentials);
            
            List<Integer> hotelIds = createHoteles();
            createdEntities.put("hoteles", hotelIds);
            
            List<Integer> tipoHabitacionIds = createTiposHabitacion(hotelIds);
            createdEntities.put("tiposHabitacion", tipoHabitacionIds);
            
            List<Integer> habitacionIds = createHabitaciones(tipoHabitacionIds, hotelIds);
            createdEntities.put("habitaciones", habitacionIds);
            
            SeedResponseDTO response = SeedResponseDTO.builder()
                    .success(true)
                    .message("Base de datos inicializada correctamente")
                    .createdEntities(createdEntities)
                    .credentials(credentials)
                    .build();
                    
            log.info("Base de datos inicializada correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error al inicializar la base de datos", e);
            return ResponseEntity.ok(SeedResponseDTO.builder()
                    .success(false)
                    .message("Error al inicializar la base de datos: " + e.getMessage())
                    .build());
        }
    }
    
    @Transactional
    private void clearExistingData() {
        log.info("Limpiando datos existentes en la base de datos");
        
        jdbcTemplate.execute("SET CONSTRAINTS ALL DEFERRED");
        
        jdbcTemplate.execute("TRUNCATE TABLE factura CASCADE");
        jdbcTemplate.execute("TRUNCATE TABLE pago CASCADE");
        jdbcTemplate.execute("TRUNCATE TABLE reserva CASCADE");
        jdbcTemplate.execute("TRUNCATE TABLE habitacion CASCADE");
        jdbcTemplate.execute("TRUNCATE TABLE tipo_habitacion CASCADE");
        jdbcTemplate.execute("TRUNCATE TABLE hotel CASCADE");
        jdbcTemplate.execute("TRUNCATE TABLE usuario CASCADE");
        jdbcTemplate.execute("TRUNCATE TABLE cliente CASCADE");
        jdbcTemplate.execute("TRUNCATE TABLE empleado CASCADE");
        jdbcTemplate.execute("TRUNCATE TABLE administrador CASCADE");
        jdbcTemplate.execute("TRUNCATE TABLE administrador_general CASCADE");
        
        jdbcTemplate.execute("ALTER SEQUENCE IF EXISTS factura_id_factura_seq RESTART WITH 1");
        jdbcTemplate.execute("ALTER SEQUENCE IF EXISTS pago_id_pago_seq RESTART WITH 1");
        jdbcTemplate.execute("ALTER SEQUENCE IF EXISTS reserva_id_reserva_seq RESTART WITH 1");
        jdbcTemplate.execute("ALTER SEQUENCE IF EXISTS habitacion_id_habitacion_seq RESTART WITH 1");
        jdbcTemplate.execute("ALTER SEQUENCE IF EXISTS tipo_habitacion_id_tipo_habitacion_seq RESTART WITH 1");
        jdbcTemplate.execute("ALTER SEQUENCE IF EXISTS hotel_id_hotel_seq RESTART WITH 1");
        jdbcTemplate.execute("ALTER SEQUENCE IF EXISTS usuario_id_usuario_seq RESTART WITH 1");
        jdbcTemplate.execute("ALTER SEQUENCE IF EXISTS cliente_id_cliente_seq RESTART WITH 1");
        jdbcTemplate.execute("ALTER SEQUENCE IF EXISTS empleado_id_empleado_seq RESTART WITH 1");
        jdbcTemplate.execute("ALTER SEQUENCE IF EXISTS administrador_id_administrador_seq RESTART WITH 1");
        jdbcTemplate.execute("ALTER SEQUENCE IF EXISTS administrador_general_id_administrador_general_seq RESTART WITH 1");
        
        jdbcTemplate.execute("SET CONSTRAINTS ALL IMMEDIATE");
        
        log.info("Base de datos limpiada correctamente");
    }
    
    private List<Integer> createClientes() {
        List<Integer> ids = new ArrayList<>();
        
        Cliente cliente1 = new Cliente();
        cliente1.setPrimerNombre("Juan");
        cliente1.setSegundoNombre("Carlos");
        cliente1.setPrimerApellido("Pérez");
        cliente1.setSegundoApellido("Gómez");
        cliente1.setCedula(1001001001);
        cliente1.setDireccion("Calle 123 #45-67, Bogotá");
        Cliente savedCliente1 = clienteRepository.save(cliente1);
        ids.add(savedCliente1.getIdCliente());
        
        Cliente cliente2 = new Cliente();
        cliente2.setPrimerNombre("María");
        cliente2.setSegundoNombre("Fernanda");
        cliente2.setPrimerApellido("López");
        cliente2.setSegundoApellido("Rodríguez");
        cliente2.setCedula(1002002002);
        cliente2.setDireccion("Carrera 45 #12-34, Medellín");
        Cliente savedCliente2 = clienteRepository.save(cliente2);
        ids.add(savedCliente2.getIdCliente());
        
        return ids;
    }
    
    private List<Integer> createEmpleados() {
        List<Integer> ids = new ArrayList<>();
        
        Empleado empleado1 = new Empleado();
        empleado1.setPrimerNombre("Pedro");
        empleado1.setSegundoNombre("Pablo");
        empleado1.setPrimerApellido("García");
        empleado1.setSegundoApellido("Martínez");
        empleado1.setCorreo("pedro.garcia@hotel.com");
        empleado1.setTelefono("3001234567");
        Empleado savedEmpleado1 = empleadoRepository.save(empleado1);
        ids.add(savedEmpleado1.getIdEmpleado());
        
        Empleado empleado2 = new Empleado();
        empleado2.setPrimerNombre("Ana");
        empleado2.setSegundoNombre("María");
        empleado2.setPrimerApellido("Ramírez");
        empleado2.setSegundoApellido("Vargas");
        empleado2.setCorreo("ana.ramirez@hotel.com");
        empleado2.setTelefono("3009876543");
        Empleado savedEmpleado2 = empleadoRepository.save(empleado2);
        ids.add(savedEmpleado2.getIdEmpleado());
        
        return ids;
    }
    
    private List<Integer> createAdministradores() {
        List<Integer> ids = new ArrayList<>();
        
        Administrador admin1 = new Administrador();
        admin1.setPrimerNombre("Carlos");
        admin1.setSegundoNombre("Alberto");
        admin1.setPrimerApellido("Gómez");
        admin1.setSegundoApellido("Sánchez");
        admin1.setCorreo("carlos.gomez@hotel.com");
        admin1.setTelefono("3001112233");
        Administrador savedAdmin1 = administradorRepository.save(admin1);
        ids.add(savedAdmin1.getIdAdministrador());
        
        return ids;
    }
    
    private List<Integer> createAdministradoresGenerales() {
        List<Integer> ids = new ArrayList<>();
        
        AdministradorGeneral adminGeneral1 = new AdministradorGeneral();
        adminGeneral1.setPrimerNombre("Laura");
        adminGeneral1.setSegundoNombre("Patricia");
        adminGeneral1.setPrimerApellido("Hernández");
        adminGeneral1.setSegundoApellido("Díaz");
        adminGeneral1.setCorreo("laura.hernandez@hotel.com");
        adminGeneral1.setTelefono("3004445566");
        AdministradorGeneral savedAdminGeneral1 = administradorGeneralRepository.save(adminGeneral1);
        ids.add(savedAdminGeneral1.getIdAdministradorGeneral());
        
        return ids;
    }
    
    private void createUsuarios(
            List<Integer> clienteIds, 
            List<Integer> empleadoIds, 
            List<Integer> adminIds, 
            List<Integer> adminGeneralIds,
            Map<String, String> credentials) {
        
        String password = "12345";
        String encodedPassword = passwordEncoder.encode(password);
        
        Usuario usuarioCliente = new Usuario();
        usuarioCliente.setNombreUsuario("cliente");
        usuarioCliente.setContrasena(encodedPassword);
        usuarioCliente.setRol(RolEnum.CLIENTE.name());
        usuarioCliente.setCliente(clienteRepository.findById(clienteIds.get(0)).orElse(null));
        usuarioRepository.save(usuarioCliente);
        credentials.put("cliente", "usuario: cliente, contraseña: " + password);
        
        Usuario usuarioEmpleado = new Usuario();
        usuarioEmpleado.setNombreUsuario("empleado");
        usuarioEmpleado.setContrasena(encodedPassword);
        usuarioEmpleado.setRol(RolEnum.EMPLEADO.name());
        usuarioEmpleado.setEmpleado(empleadoRepository.findById(empleadoIds.get(0)).orElse(null));
        usuarioRepository.save(usuarioEmpleado);
        credentials.put("empleado", "usuario: empleado, contraseña: " + password);
        
        Usuario usuarioAdmin = new Usuario();
        usuarioAdmin.setNombreUsuario("admin");
        usuarioAdmin.setContrasena(encodedPassword);
        usuarioAdmin.setRol(RolEnum.ADMINISTRADOR.name());
        usuarioAdmin.setAdministrador(administradorRepository.findById(adminIds.get(0)).orElse(null));
        usuarioRepository.save(usuarioAdmin);
        credentials.put("administrador", "usuario: admin, contraseña: " + password);
        
        Usuario usuarioAdminGeneral = new Usuario();
        usuarioAdminGeneral.setNombreUsuario("admingeneral");
        usuarioAdminGeneral.setContrasena(encodedPassword);
        usuarioAdminGeneral.setRol(RolEnum.ADMINISTRADOR_GENERAL.name());
        usuarioAdminGeneral.setAdministradorGeneral(administradorGeneralRepository.findById(adminGeneralIds.get(0)).orElse(null));
        usuarioRepository.save(usuarioAdminGeneral);
        credentials.put("administradorGeneral", "usuario: admingeneral, contraseña: " + password);
    }
    
    private List<Integer> createHoteles() {
        List<Integer> ids = new ArrayList<>();
        
        Hotel hotel1 = new Hotel();
        hotel1.setNombre("Hotel Bogotá Plaza");
        hotel1.setCiudad("Bogotá");
        hotel1.setTelefono("6011234567");
        hotel1.setCorreo("info@bogotaplaza.com");
        hotel1.setDireccion("Calle 100 #15-20, Bogotá");
        Hotel savedHotel1 = hotelRepository.save(hotel1);
        ids.add(savedHotel1.getIdHotel());
        
        Hotel hotel2 = new Hotel();
        hotel2.setNombre("Hotel Medellín Royal");
        hotel2.setCiudad("Medellín");
        hotel2.setTelefono("6042345678");
        hotel2.setCorreo("info@medellinroyal.com");
        hotel2.setDireccion("Carrera 43A #1-50, Medellín");
        Hotel savedHotel2 = hotelRepository.save(hotel2);
        ids.add(savedHotel2.getIdHotel());
        
        return ids;
    }
    
    private List<Integer> createTiposHabitacion(List<Integer> hotelIds) {
        List<Integer> ids = new ArrayList<>();
        
        TipoHabitacion tipoHab1 = new TipoHabitacion();
        tipoHab1.setNombre("Estándar");
        tipoHab1.setCantidad(10);
        Hotel hotel1 = new Hotel();
        hotel1.setIdHotel(hotelIds.get(0));
        tipoHab1.setHotel(hotel1);
        TipoHabitacion savedTipoHab1 = tipoHabitacionRepository.save(tipoHab1);
        ids.add(savedTipoHab1.getIdTipoHabitacion());
        
        TipoHabitacion tipoHab2 = new TipoHabitacion();
        tipoHab2.setNombre("Suite");
        tipoHab2.setCantidad(5);
        tipoHab2.setHotel(hotel1);
        TipoHabitacion savedTipoHab2 = tipoHabitacionRepository.save(tipoHab2);
        ids.add(savedTipoHab2.getIdTipoHabitacion());
        
        TipoHabitacion tipoHab3 = new TipoHabitacion();
        tipoHab3.setNombre("Estándar");
        tipoHab3.setCantidad(15);
        Hotel hotel2 = new Hotel();
        hotel2.setIdHotel(hotelIds.get(1));
        tipoHab3.setHotel(hotel2);
        TipoHabitacion savedTipoHab3 = tipoHabitacionRepository.save(tipoHab3);
        ids.add(savedTipoHab3.getIdTipoHabitacion());
        
        TipoHabitacion tipoHab4 = new TipoHabitacion();
        tipoHab4.setNombre("Suite Ejecutiva");
        tipoHab4.setCantidad(3);
        tipoHab4.setHotel(hotel2);
        TipoHabitacion savedTipoHab4 = tipoHabitacionRepository.save(tipoHab4);
        ids.add(savedTipoHab4.getIdTipoHabitacion());
        
        return ids;
    }
    
    private List<Integer> createHabitaciones(List<Integer> tipoHabitacionIds, List<Integer> hotelIds) {
        List<Integer> ids = new ArrayList<>();
        
        Habitacion hab1 = new Habitacion();
        hab1.setNumeroHabitacion(101);
        hab1.setPrecioDia(120000);
        hab1.setDisponible(true);
        
        TipoHabitacion tipoHab1 = new TipoHabitacion();
        tipoHab1.setIdTipoHabitacion(tipoHabitacionIds.get(0));
        hab1.setTipoHabitacion(tipoHab1);
        
        Hotel hotel1 = new Hotel();
        hotel1.setIdHotel(hotelIds.get(0));
        hab1.setHotel(hotel1);
        
        Habitacion savedHab1 = habitacionRepository.save(hab1);
        ids.add(savedHab1.getIdHabitacion());
        
        Habitacion hab2 = new Habitacion();
        hab2.setNumeroHabitacion(201);
        hab2.setPrecioDia(250000);
        hab2.setDisponible(true);
        
        TipoHabitacion tipoHab2 = new TipoHabitacion();
        tipoHab2.setIdTipoHabitacion(tipoHabitacionIds.get(1));
        hab2.setTipoHabitacion(tipoHab2);
        hab2.setHotel(hotel1);
        
        Habitacion savedHab2 = habitacionRepository.save(hab2);
        ids.add(savedHab2.getIdHabitacion());
        
        Habitacion hab3 = new Habitacion();
        hab3.setNumeroHabitacion(301); 
        hab3.setPrecioDia(150000);
        hab3.setDisponible(true);
        
        TipoHabitacion tipoHab3 = new TipoHabitacion();
        tipoHab3.setIdTipoHabitacion(tipoHabitacionIds.get(2));
        hab3.setTipoHabitacion(tipoHab3);
        
        Hotel hotel2 = new Hotel();
        hotel2.setIdHotel(hotelIds.get(1));
        hab3.setHotel(hotel2);
        
        Habitacion savedHab3 = habitacionRepository.save(hab3);
        ids.add(savedHab3.getIdHabitacion());
        
        Habitacion hab4 = new Habitacion();
        hab4.setNumeroHabitacion(401); 
        hab4.setPrecioDia(350000);
        hab4.setDisponible(true);
        
        TipoHabitacion tipoHab4 = new TipoHabitacion();
        tipoHab4.setIdTipoHabitacion(tipoHabitacionIds.get(3));
        hab4.setTipoHabitacion(tipoHab4);
        hab4.setHotel(hotel2);
        
        Habitacion savedHab4 = habitacionRepository.save(hab4);
        ids.add(savedHab4.getIdHabitacion());
        
        return ids;
    }
}

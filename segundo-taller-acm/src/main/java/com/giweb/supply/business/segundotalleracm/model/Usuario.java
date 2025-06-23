package com.giweb.supply.business.segundotalleracm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", uniqueConstraints = {
    @UniqueConstraint(columnNames = "nombre_usuario")
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "rol", nullable = false, length = 30)
    private String rol;

    @OneToOne
    @JoinColumn(name = "fk_id_cliente")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "fk_id_empleado")
    private Empleado empleado;

    @OneToOne
    @JoinColumn(name = "fk_id_administrador_general")
    private AdministradorGeneral administradorGeneral;

    @OneToOne
    @JoinColumn(name = "fk_id_administrador")
    private Administrador administrador;
}

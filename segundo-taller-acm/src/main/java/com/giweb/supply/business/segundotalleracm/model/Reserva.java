package com.giweb.supply.business.segundotalleracm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Integer idReserva;

    @Column(name = "fecha_inicio")
    private Timestamp fechaInicio;

    @Column(name = "fecha_final")
    private Timestamp fechaFinal;

    @Column(name = "cantidad_dias")
    private Integer cantidadDias;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "fecha_reserva")
    private Timestamp fechaReserva;

    @ManyToOne
    @JoinColumn(name = "fk_id_habitacion")
    private Habitacion habitacion;

    @ManyToOne
    @JoinColumn(name = "fk_id_cliente")
    private Cliente cliente;
}

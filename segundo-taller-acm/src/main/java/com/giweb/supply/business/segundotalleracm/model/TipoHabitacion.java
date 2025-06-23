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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipo_habitacion")
public class TipoHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_habitacion")
    private Integer idTipoHabitacion;

    @ManyToOne
    @JoinColumn(name = "fk_id_hotel")
    private Hotel hotel;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "cantidad")
    private Integer cantidad;
}

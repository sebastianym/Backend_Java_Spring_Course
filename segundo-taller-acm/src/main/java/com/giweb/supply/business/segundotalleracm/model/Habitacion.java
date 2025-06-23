package com.giweb.supply.business.segundotalleracm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "habitacion")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private Integer idHabitacion;

    @Column(name = "numero_habitacion", nullable = false, unique = true)
    private Integer numeroHabitacion;

    @ManyToOne
    @JoinColumn(name = "fk_id_tipo_habitacion")
    private TipoHabitacion tipoHabitacion;

    @Column(name = "precio_dia", nullable = false)
    private Integer precioDia;

    @Column(name = "disponible")
    private Boolean disponible;

    @ManyToOne
    @JoinColumn(name = "fk_id_hotel")
    private Hotel hotel;
}

package com.giweb.supply.business.segundotalleracm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Integer idFactura;

    @ManyToOne
    @JoinColumn(name = "fk_id_reserva")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "fk_id_pago")
    private Pago pago;

    @Column(name = "fecha_emision")
    private Integer fechaEmision;

    @Column(name = "valor_total")
    private Integer valorTotal;
}

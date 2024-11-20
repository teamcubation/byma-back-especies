package com.byma.fondos_up_back.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "especie")
@Table(name = "especies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EspecieEntity {

    @Id
    @Column(unique = true)
    private Long idEspecie;
    private String codigoCVSA;
    private String denominacion;
    private int laminaMinima;
    private Double precio;
    private String cafci;
    private String cuentaDeEmision;
    private String estado;
    private long idEmisor;
    private long idGerente;
    private LocalDate vigencia;
    private LocalDate plazoDeLiquidacion;
    private String codigoCNV;
    private String isin;
    private String familiaDeFondos;
    private String observaciones;
    private boolean movimiento;
    private LocalDate fechaAlta;
}

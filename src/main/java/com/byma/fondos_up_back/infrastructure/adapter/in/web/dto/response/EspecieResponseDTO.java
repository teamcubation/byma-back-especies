package com.byma.fondos_up_back.infrastructure.adapter.in.web.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EspecieResponseDTO {
    private long idEspecie;
    private String codigoCVSA;
    private String denominacion;
    private int laminaMinima;
    private Double precio;
    private String cafdi;
    private String cuentaDeEmision;
    private String estado;
    private long idEmisor;
    private long idGerente;
    private LocalDateTime vigencia;
    private LocalDateTime plazoDeLiquidacion;
    private String codigoCNV;
    private String isin;
    private String familiaDeFondos;
    private String observaciones;
    private boolean movimiento;
    private LocalDateTime fechaAlta;
}

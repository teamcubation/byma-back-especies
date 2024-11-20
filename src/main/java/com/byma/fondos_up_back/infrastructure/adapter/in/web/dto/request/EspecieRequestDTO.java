package com.byma.fondos_up_back.infrastructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EspecieRequestDTO {
    @NotNull(message = "El id de la especie no puede ser null")
    private Long idEspecie;
    @NotNull(message = "El codigo CVSA no puede ser null")
    private String codigoCVSA;
    @NotNull(message = "La denominación no puede ser null")
    private String denominacion;
//    @Min(value = 0, message = "La lamina minima no puede ser negativa")
    private int laminaMinima;
//    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double precio;
    private String cafci;
    private String cuentaDeEmision;
    private String estado;
    private long idEmisor;
    private long idGerente;
    private LocalDate vigencia;
    @NotNull(message = "El plazo de liquidación no puede ser null")
    private LocalDate plazoDeLiquidacion;
    private String codigoCNV;
    private String isin;
    private String familiaDeFondos;
    private String observaciones;
    private boolean movimiento;
    private LocalDate fechaAlta;
}

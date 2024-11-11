package com.byma.fondos_up_back.infrastructure.adapter.out.persistance.mapper;

import com.byma.fondos_up_back.application.validation.Validador;
import com.byma.fondos_up_back.domain.model.Especie;
import com.byma.fondos_up_back.infrastructure.adapter.out.persistance.entity.EspecieEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EspecieMapper {

    public static EspecieEntity especieAEspecieEntity(Especie especie) {
        Validador.validadorParametrosNull(especie);
        return EspecieEntity.builder()
                .idEspecie(especie.getIdEspecie())
                .codigoCVSA(especie.getCodigoCVSA())
                .denominacion(especie.getDenominacion())
                .laminaMinima(especie.getLaminaMinima())
                .precio(especie.getPrecio())
                .cafdi(especie.getCafdi())
                .cuentaDeEmision(especie.getCuentaDeEmision())
                .estado(especie.getEstado())
                .idEmisor(especie.getIdEmisor())
                .idGerente(especie.getIdGerente())
                .vigencia(especie.getVigencia())
                .plazoDeLiquidacion(especie.getPlazoDeLiquidacion())
                .codigoCNV(especie.getCodigoCNV())
                .isin(especie.getIsin())
                .familiaDeFondos(especie.getFamiliaDeFondos())
                .observaciones(especie.getObservaciones())
                .movimiento(especie.isMovimiento())
                .fechaAlta(especie.getFechaAlta())
                .build();
    }


    public static Especie especieEntityAEspecie(EspecieEntity especieEntity) {
        Validador.validadorParametrosNull(especieEntity);
        return Especie.builder()
                .idEspecie(especieEntity.getIdEspecie())
                .codigoCVSA(especieEntity.getCodigoCVSA())
                .denominacion(especieEntity.getDenominacion())
                .laminaMinima(especieEntity.getLaminaMinima())
                .precio(especieEntity.getPrecio())
                .cafdi(especieEntity.getCafdi())
                .cuentaDeEmision(especieEntity.getCuentaDeEmision())
                .estado(especieEntity.getEstado())
                .idEmisor(especieEntity.getIdEmisor())
                .idGerente(especieEntity.getIdGerente())
                .vigencia(especieEntity.getVigencia())
                .plazoDeLiquidacion(especieEntity.getPlazoDeLiquidacion())
                .codigoCNV(especieEntity.getCodigoCNV())
                .isin(especieEntity.getIsin())
                .familiaDeFondos(especieEntity.getFamiliaDeFondos())
                .observaciones(especieEntity.getObservaciones())
                .movimiento(especieEntity.isMovimiento())
                .fechaAlta(especieEntity.getFechaAlta())
                .build();
    }
    public static List<Especie> especieEntitiesAEspecies(List<EspecieEntity> especieEntities) {
    return especieEntities.stream()
            .map(EspecieMapper::especieEntityAEspecie)
            .collect(Collectors.toList());
    }
}

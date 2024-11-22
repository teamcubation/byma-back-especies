package com.byma.fondos_up_back.infrastructure.adapter.out.persistance.mapper;

import com.byma.fondos_up_back.application.service.exception.ObjetoEnviadoNuloException;
import com.byma.fondos_up_back.util.validation.Validador;
import com.byma.fondos_up_back.domain.model.Especie;
import com.byma.fondos_up_back.infrastructure.adapter.out.persistance.entity.EspecieEntity;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EspecieMapper {

    public static EspecieEntity especieAEspecieEntity(Especie especie) throws ObjetoEnviadoNuloException {
        Validador.validarObjetoNotNull(especie);
        return EspecieEntity.builder()
                .idEspecie(especie.getIdEspecie())
                .codigoCVSA(especie.getCodigoCVSA())
                .denominacion(especie.getDenominacion())
                .laminaMinima(especie.getLaminaMinima())
                .precio(especie.getPrecio())
                .cafci(especie.getCafci())
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
                .idMoneda(especie.getIdMoneda())
                .fechaAlta(especie.getFechaAlta())
                .build();
    }


    public static Especie especieEntityAEspecie(EspecieEntity especieEntity) throws ObjetoEnviadoNuloException {
        Validador.validarObjetoNotNull(especieEntity);
        return Especie.builder()
                .idEspecie(especieEntity.getIdEspecie())
                .codigoCVSA(especieEntity.getCodigoCVSA())
                .denominacion(especieEntity.getDenominacion())
                .laminaMinima(especieEntity.getLaminaMinima())
                .precio(especieEntity.getPrecio())
                .cafci(especieEntity.getCafci())
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
                .idMoneda(especieEntity.getIdMoneda())
                .fechaAlta(especieEntity.getFechaAlta())
                .build();
    }

    public static List<Especie> especieEntitiesAEspecies(List<EspecieEntity> especieEntities) throws ObjetoEnviadoNuloException {
        List<Especie> especies = new ArrayList<>();

        for (EspecieEntity especieEntity : especieEntities) {
            especies.add(EspecieMapper.especieEntityAEspecie(especieEntity));
        }

        return especies;
    }
}

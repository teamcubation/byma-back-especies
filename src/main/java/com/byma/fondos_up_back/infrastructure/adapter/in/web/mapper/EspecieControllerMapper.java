package com.byma.fondos_up_back.infrastructure.adapter.in.web.mapper;

import com.byma.fondos_up_back.application.service.exception.ObjetoEnviadoNuloException;
import com.byma.fondos_up_back.domain.model.Especie;
import com.byma.fondos_up_back.infrastructure.adapter.in.web.dto.request.EspecieRequestDTO;
import com.byma.fondos_up_back.infrastructure.adapter.in.web.dto.response.EspecieResponseDTO;
import com.byma.fondos_up_back.infrastructure.adapter.out.persistance.entity.EspecieEntity;
import com.byma.fondos_up_back.infrastructure.adapter.out.persistance.mapper.EspecieMapper;

import java.util.ArrayList;
import java.util.List;

public class EspecieControllerMapper {

    public static Especie especieRequestDtoAEspecie(EspecieRequestDTO especieRequestDTO) {
        return Especie.builder()
                .idEspecie(especieRequestDTO.getIdEspecie())
                .codigoCVSA(especieRequestDTO.getCodigoCVSA())
                .denominacion(especieRequestDTO.getDenominacion())
                .laminaMinima(especieRequestDTO.getLaminaMinima())
                .precio(especieRequestDTO.getPrecio())
                .cafci(especieRequestDTO.getCafci())
                .cuentaDeEmision(especieRequestDTO.getCuentaDeEmision())
                .estado(especieRequestDTO.getEstado())
                .idEmisor(especieRequestDTO.getIdEmisor())
                .idGerente(especieRequestDTO.getIdGerente())
                .vigencia(especieRequestDTO.getVigencia())
                .plazoDeLiquidacion(especieRequestDTO.getPlazoDeLiquidacion())
                .codigoCNV(especieRequestDTO.getCodigoCNV())
                .isin(especieRequestDTO.getIsin())
                .familiaDeFondos(especieRequestDTO.getFamiliaDeFondos())
                .observaciones(especieRequestDTO.getObservaciones())
                .movimiento(especieRequestDTO.isMovimiento())
                .fechaAlta(especieRequestDTO.getFechaAlta())
                .build();
    }

    public static EspecieResponseDTO especieAEspecieResponseDTO(Especie especie) {
        return EspecieResponseDTO.builder()
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
                .movimiento(especie.isMovimiento())
                .fechaAlta(especie.getFechaAlta())
                .build();
    }

    public static List<EspecieResponseDTO> especiesAEspeciesResponseDTO(List<Especie> especies) {
        List<EspecieResponseDTO> especiesResponses = new ArrayList<>();

        for (Especie especie : especies) {
            especiesResponses.add(EspecieControllerMapper.especieAEspecieResponseDTO(especie));
        }

        return especiesResponses;
    }
}

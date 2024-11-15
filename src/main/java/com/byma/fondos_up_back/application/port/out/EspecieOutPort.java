package com.byma.fondos_up_back.application.port.out;

import com.byma.fondos_up_back.application.service.exception.AtributosNulosException;
import com.byma.fondos_up_back.application.service.exception.EspecieNoEncontradaException;
import com.byma.fondos_up_back.application.service.exception.ObjetoEnviadoNuloException;
import com.byma.fondos_up_back.domain.model.Especie;

import java.util.List;

public interface EspecieOutPort {

    Especie crear(Especie especie) throws ObjetoEnviadoNuloException;

    List<Especie> listarEspecies() throws ObjetoEnviadoNuloException;

    Especie obtenerPorId(Long idEspecie) throws AtributosNulosException, ObjetoEnviadoNuloException, EspecieNoEncontradaException;

    Especie actualizar(Especie especie) throws ObjetoEnviadoNuloException;

    void eliminar(Long idEspecie) throws ObjetoEnviadoNuloException, AtributosNulosException;

    boolean existeElID(Long idEspecie);
}

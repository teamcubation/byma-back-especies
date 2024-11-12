package com.byma.fondos_up_back.application.port.in;

import com.byma.fondos_up_back.application.service.exception.AtributosNulosException;
import com.byma.fondos_up_back.application.service.exception.EspecieConIdExistenteException;
import com.byma.fondos_up_back.application.service.exception.EspecieNoEncontradaException;
import com.byma.fondos_up_back.application.service.exception.ObjetoEnviadoNuloException;
import com.byma.fondos_up_back.domain.model.Especie;

import java.util.List;

public interface EspecieInPort {

    Especie crear(Especie especie) throws EspecieConIdExistenteException, AtributosNulosException, ObjetoEnviadoNuloException;

    List<Especie> listarEspecies() throws ObjetoEnviadoNuloException;

    Especie obtenerPorId(Long idEspecie) throws EspecieNoEncontradaException, AtributosNulosException, ObjetoEnviadoNuloException;

    Especie actualizar(Long idEspecie, Especie especie) throws AtributosNulosException, EspecieConIdExistenteException, ObjetoEnviadoNuloException, EspecieNoEncontradaException;

    void eliminar(Long idEspecie) throws AtributosNulosException, EspecieNoEncontradaException, ObjetoEnviadoNuloException;
}

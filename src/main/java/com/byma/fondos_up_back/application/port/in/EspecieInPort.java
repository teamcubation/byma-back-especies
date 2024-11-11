package com.byma.fondos_up_back.application.port.in;

import com.byma.fondos_up_back.application.service.exception.EspecieConIdExistenteException;
import com.byma.fondos_up_back.domain.model.Especie;

import java.util.List;

public interface EspecieInPort {

    Especie crear(Especie especie) throws EspecieConIdExistenteException;

    List<Especie> listarEspecies();

    Especie obtenerPorId(Long idEspecie);

    Especie actualizar(Long idEspecie, Especie especie);

    void eliminar(Long idEspecie);
}

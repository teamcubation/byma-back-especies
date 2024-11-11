package com.byma.fondos_up_back.application.port.out;

import com.byma.fondos_up_back.domain.model.Especie;

import java.util.List;

public interface EspecieOutPort {

    Especie crear(Especie especie);

    List<Especie> listarEspecies();

    Especie obtenerPorId(Long idEspecie);

    Especie actualizar(Especie especie);

    void eliminar(Long idEspecie);

    boolean existeElID(Long idEspecie);
}

package com.byma.fondos_up_back.application.service;

import com.byma.fondos_up_back.application.port.in.EspecieInPort;
import com.byma.fondos_up_back.application.port.out.EspecieOutPort;
import com.byma.fondos_up_back.application.service.exception.EspecieConIdExistenteException;
import com.byma.fondos_up_back.application.validation.Validador;
import com.byma.fondos_up_back.domain.model.Especie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EspecieService implements EspecieInPort {

    private final EspecieOutPort especieOutPort;

    public EspecieService(EspecieOutPort especieOutPort) {
        this.especieOutPort = especieOutPort;
    }

    @Override
    public Especie crear(Especie especie) throws EspecieConIdExistenteException {
        log.info("Crear especie: {}", especie);
        if (validarIdExistente(especie.getIdEspecie())) {
            throw new EspecieConIdExistenteException("Error al crear: ID existente");
        }
        return especieOutPort.crear(especie);
    }

    @Override
    public List<Especie> listarEspecies() {
        log.info("Obtener todos los especies");
        return especieOutPort.listarEspecies();
    }

    public Especie obtenerPorId(Long idEspecie) {
        log.info("Obtener especie por el id de la especie: {}", idEspecie);
        Validador.validadorParametrosNull(idEspecie);
        return especieOutPort.obtenerPorId(idEspecie);
    }

    @Override
    public Especie actualizar(Long idEspecie, Especie especie) {
        log.info("Actualizar especie con id: {}, datos a actualizar: {}",idEspecie, especie);
        Validador.validadorParametrosNull(idEspecie);
        especie.setIdEspecie(idEspecie);
        return especieOutPort.actualizar(especie);
    }

    @Override
    public void eliminar(Long idEspecie) {
        log.info("Eliminar especie de id {}", idEspecie);
        Validador.validadorParametrosNull(idEspecie);
        especieOutPort.eliminar(idEspecie);
    }
    private boolean validarIdExistente(long idEspecie) {
        return especieOutPort.existeElID(idEspecie);
    }
}

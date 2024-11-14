package com.byma.fondos_up_back.application.service;

import com.byma.fondos_up_back.application.port.in.EspecieInPort;
import com.byma.fondos_up_back.application.port.out.EspecieOutPort;
import com.byma.fondos_up_back.application.service.exception.AtributosNulosException;
import com.byma.fondos_up_back.application.service.exception.EspecieConIdExistenteException;
import com.byma.fondos_up_back.application.service.exception.EspecieNoEncontradaException;
import com.byma.fondos_up_back.application.service.exception.ObjetoEnviadoNuloException;
import com.byma.fondos_up_back.util.validation.Validador;
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
    public Especie crear(Especie especie) throws EspecieConIdExistenteException, ObjetoEnviadoNuloException, AtributosNulosException {
        Validador.validarEspecie(especie);
        validarEspecieNoExista(especie.getIdEspecie());
        return especieOutPort.crear(especie);
    }

    @Override
    public List<Especie> listarEspecies() throws ObjetoEnviadoNuloException {
        return especieOutPort.listarEspecies();
    }

    public Especie obtenerPorId(Long idEspecie) throws EspecieNoEncontradaException, AtributosNulosException, ObjetoEnviadoNuloException {
        Validador.validarIdNull(idEspecie);
        Especie especieEncontrada = especieOutPort.obtenerPorId(idEspecie);
        if (especieEncontrada == null) {
            throw new EspecieNoEncontradaException("La especie buscada por id no existe en el sistema.");
        }
        return especieEncontrada;
    }

    @Override
    public Especie actualizar(Long idEspecie, Especie especie) throws AtributosNulosException, EspecieConIdExistenteException, ObjetoEnviadoNuloException, EspecieNoEncontradaException {
        Validador.validarIdNull(idEspecie);
        if (!validarIdExistente(idEspecie)) {
            throw new EspecieNoEncontradaException("La especie buscada por id no existe en el sistema.");
        }
        Validador.validarEspecie(especie);
        if (!idEspecie.equals(especie.getIdEspecie())) {
            validarEspecieNoExista(especie.getIdEspecie());
        }
        especie.setIdEspecie(idEspecie);
        return especieOutPort.actualizar(especie);
    }

    @Override
    public void eliminar(Long idEspecie) throws AtributosNulosException, EspecieNoEncontradaException, ObjetoEnviadoNuloException {
        Validador.validarIdNull(idEspecie);
        if (!validarIdExistente(idEspecie)) {
            throw new EspecieNoEncontradaException("La especie buscada por id no existe en el sistema.");
        }
        especieOutPort.eliminar(idEspecie);
    }

    private boolean validarIdExistente(long idEspecie) {
        return especieOutPort.existeElID(idEspecie);
    }
    private void validarEspecieNoExista(long idEspecie) throws EspecieConIdExistenteException {
        if (validarIdExistente(idEspecie)) {
            throw new EspecieConIdExistenteException("Ya existe una especie con ese ID.");
        }
    }

}

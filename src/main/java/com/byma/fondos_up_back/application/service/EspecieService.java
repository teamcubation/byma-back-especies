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
        Validador.validarObjetoNotNull(especie);
        Validador.validarAtributosNulos(especie);
        if (validarIdExistente(especie.getIdEspecie())) {
            throw new EspecieConIdExistenteException("Error al crear: ID existente");
        }
        return especieOutPort.crear(especie);
    }

    @Override
    public List<Especie> listarEspecies() throws ObjetoEnviadoNuloException {
        return especieOutPort.listarEspecies();
    }

    public Especie obtenerPorId(Long idEspecie) throws EspecieNoEncontradaException, AtributosNulosException, ObjetoEnviadoNuloException {
        Validador.validarIdNull(idEspecie);
        Especie especieEncontrada = especieOutPort.obtenerPorId(idEspecie);
        if (Validador.validarEspecieNull(especieEncontrada)) {
            throw new EspecieNoEncontradaException("La especie buscada por id no existe en el sistema.");
        }
        return especieEncontrada;
    }

    @Override
    public Especie actualizar(Long idEspecie, Especie especie) throws AtributosNulosException, EspecieConIdExistenteException, ObjetoEnviadoNuloException, EspecieNoEncontradaException {
        Validador.validarIdNull(idEspecie);
        if (noExisteIdEspecie(idEspecie)) {
            throw new EspecieNoEncontradaException("La especie buscada por id no existe en el sistema.");
        }
        Validador.validarObjetoNotNull(especie);
        Validador.validarAtributosNulos(especie);
        if (!idEspecie.equals(especie.getIdEspecie())) {
            if (validarIdExistente(especie.getIdEspecie())) {
                throw new EspecieConIdExistenteException("Ya existe una especie con ese ID.");
            }
        }
        especie.setIdEspecie(idEspecie);
        return especieOutPort.actualizar(especie);
    }

    @Override
    public void eliminar(Long idEspecie) throws AtributosNulosException, EspecieNoEncontradaException, ObjetoEnviadoNuloException {
        Validador.validarIdNull(idEspecie);
        if (noExisteIdEspecie(idEspecie)) {
            throw new EspecieNoEncontradaException("La especie buscada por id no existe en el sistema.");
        }
        especieOutPort.eliminar(idEspecie);
    }

    private boolean validarIdExistente(long idEspecie) {
        return especieOutPort.existeElID(idEspecie);
    }

    private boolean noExisteIdEspecie(Long idEspecie) {
        return !especieOutPort.existeElID(idEspecie);
    }

}

package com.byma.fondos_up_back.infrastructure.adapter.out.persistance;

import com.byma.fondos_up_back.application.port.out.EspecieOutPort;
import com.byma.fondos_up_back.application.validation.Validador;
import com.byma.fondos_up_back.domain.model.Especie;
import com.byma.fondos_up_back.infrastructure.adapter.out.persistance.mapper.EspecieMapper;
import com.byma.fondos_up_back.infrastructure.adapter.out.persistance.repository.EspecieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EspecieOutAdapter implements EspecieOutPort {

    @Autowired
    private EspecieRepository especieRepository;
    @Override
    public Especie crear(Especie especie) {
        Validador.validadorParametrosNull(especie);
        return EspecieMapper.especieEntityAEspecie(especieRepository.save(EspecieMapper.especieAEspecieEntity(especie)));
    }

    @Override
    public List<Especie> listarEspecies() {
        return EspecieMapper.especieEntitiesAEspecies(especieRepository.findAll());
    }

    @Override
    public Especie obtenerPorId(Long idEspecie) {
        Validador.validadorParametrosNull(idEspecie);
        return EspecieMapper.especieEntityAEspecie(especieRepository.findById(idEspecie).orElse(null));
    }

    @Override
    public Especie actualizar(Especie especie) {
        Validador.validadorParametrosNull(especie);
        return EspecieMapper.especieEntityAEspecie(especieRepository.save(EspecieMapper.especieAEspecieEntity(especie)));
    }

    @Override
    public void eliminar(Long idEspecie) {
        Validador.validadorParametrosNull(idEspecie);
        especieRepository.deleteById(idEspecie);
    }
    @Override
    public boolean existeElID(Long idEspecie){
        return especieRepository.existsById(idEspecie);
    }
}

package com.byma.fondos_up_back.adapter;

import com.byma.fondos_up_back.application.service.exception.AtributosNulosException;
import com.byma.fondos_up_back.application.service.exception.EspecieNoEncontradaException;
import com.byma.fondos_up_back.application.service.exception.ObjetoEnviadoNuloException;
import com.byma.fondos_up_back.domain.model.Especie;
import com.byma.fondos_up_back.infrastructure.adapter.out.persistance.EspecieOutAdapter;
import com.byma.fondos_up_back.infrastructure.adapter.out.persistance.entity.EspecieEntity;
import com.byma.fondos_up_back.infrastructure.adapter.out.persistance.repository.EspecieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdapterTest {
    public static final long ID2_ESPECIE = 2L;
    public static final String CODIGO_CVSA = "DEF456";
    public static final long ID1_ESPECIE = 1L;
    public static final String ESPECIE_DE_PRUEBA = "Especie de prueba";
    public static final LocalDate DATE_1 = LocalDate.now();
    
    @InjectMocks
    private EspecieOutAdapter especieOutAdapter;

    @Mock
    private EspecieRepository especieRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    private Especie mockEspecie1() {
        return Especie.builder()
                .idEspecie(ID1_ESPECIE)
                .codigoCVSA(CODIGO_CVSA)
                .denominacion(ESPECIE_DE_PRUEBA)
                .plazoDeLiquidacion(DATE_1)
                .build();

    }

    private Especie mockEspecie2() {
        return Especie.builder()
                .idEspecie(ID2_ESPECIE)
                .codigoCVSA(CODIGO_CVSA)
                .denominacion(ESPECIE_DE_PRUEBA)
                .plazoDeLiquidacion(DATE_1)
                .build();
    }

    private EspecieEntity mockEspecieEntity1() {
        return EspecieEntity.builder()
                .idEspecie(ID1_ESPECIE)
                .codigoCVSA(CODIGO_CVSA)
                .denominacion(ESPECIE_DE_PRUEBA)
                .plazoDeLiquidacion(DATE_1)
                .build();
    }

    @Test
    void whenSaveEspecie_thenReturnEspecie() throws Exception {
        EspecieEntity especieEntity = mockEspecieEntity1();
        Especie especie = mockEspecie1();
        when(especieRepository.save(any(EspecieEntity.class))).thenReturn(especieEntity);
        Especie result = especieOutAdapter.crear(especie);
        assertEquals(especie, result);
    }

    @Test
    void whenSaveNullEspecie_thenThrowObjetoEnviadoNuloException() {
        Exception exception = assertThrows(ObjetoEnviadoNuloException.class, () -> {
            especieOutAdapter.crear(null);
        });
        assertEquals("El objeto enviado no puede ser nulo.", exception.getMessage());
    }

    @Test
    void whenUpdateEspecie_thenReturnEspecie() throws Exception {
        EspecieEntity especieEntity = mockEspecieEntity1();
        Especie especie = mockEspecie1();
        when(especieRepository.save(any(EspecieEntity.class))).thenReturn(especieEntity);
        Especie result = especieOutAdapter.actualizar(especie);
        assertEquals(especie, result);
    }

    @Test
    void whenUpdateNullEspecie_thenThrowObjetoEnviadoNuloException() {
        Exception exception = assertThrows(ObjetoEnviadoNuloException.class, () -> {
            especieOutAdapter.actualizar(null);
        });
        assertEquals("El objeto enviado no puede ser nulo.", exception.getMessage());
    }

    @Test
    void whenGetEspecie_thenReturnEspecie() throws Exception {
        EspecieEntity especieEntity = mockEspecieEntity1();
        Especie especie = mockEspecie1();
        when(especieRepository.findById(ID1_ESPECIE)).thenReturn(java.util.Optional.of(especieEntity));
        Especie result = especieOutAdapter.obtenerPorId(ID1_ESPECIE);
        assertEquals(especie, result);
    }

    @Test
    void whenGetEspecie_thenReturnEspecieNoEncontradaException() {
        when(especieRepository.findById(ID1_ESPECIE)).thenReturn(Optional.empty());
        assertThrows(EspecieNoEncontradaException.class, () -> {
            especieOutAdapter.obtenerPorId(ID1_ESPECIE);
        });
    }

    @Test
    void whenGetEspecie_thenReturnAtributosNulosException() {
        when(especieRepository.findById(ID1_ESPECIE)).thenReturn(Optional.empty());
        assertThrows(AtributosNulosException.class, () -> {
            especieOutAdapter.obtenerPorId(null);
        });
    }
    @Test
    void whenGetAllEspecie_thenReturnEspecie() throws Exception {
        EspecieEntity especieEntity1 = mockEspecieEntity1();
        EspecieEntity especieEntity2 = mockEspecieEntity1();
        when(especieRepository.findAll()).thenReturn(java.util.List.of(especieEntity1, especieEntity2));
        java.util.List<Especie> result = especieOutAdapter.listarEspecies();
        assertEquals(2, result.size());
    }

    @Test
    void whenDeleteEspecie_thenDoNothing() throws Exception {
        especieOutAdapter.eliminar(ID1_ESPECIE);
        verify(especieRepository, times(1)).deleteById(ID1_ESPECIE);
    }
    @Test
    void whenDeleteEspecie_thenReturnAtributosNulosException() {
        assertThrows(AtributosNulosException.class, () -> {
            especieOutAdapter.eliminar(null);
        });
    }
    @Test
    void whenVerifyIfIDExists_thenReturnTrue() throws Exception {
        when(especieRepository.existsById(ID1_ESPECIE)).thenReturn(true);
        boolean result = especieOutAdapter.existeElID(ID1_ESPECIE);
        assertTrue(result);
    }
    @Test
    void whenVerifyIfIDExists_thenReturnFalse() throws Exception {
        when(especieRepository.existsById(ID1_ESPECIE)).thenReturn(false);
        boolean result = especieOutAdapter.existeElID(ID1_ESPECIE);
        assertFalse(result);
    }
}

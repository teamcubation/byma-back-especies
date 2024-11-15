package com.byma.fondos_up_back.application.service;

import com.byma.fondos_up_back.application.port.out.EspecieOutPort;
import com.byma.fondos_up_back.application.service.exception.AtributosNulosException;
import com.byma.fondos_up_back.application.service.exception.EspecieConIdExistenteException;
import com.byma.fondos_up_back.application.service.exception.EspecieNoEncontradaException;
import com.byma.fondos_up_back.application.service.exception.ObjetoEnviadoNuloException;
import com.byma.fondos_up_back.domain.model.Especie;
import com.byma.fondos_up_back.infrastructure.adapter.out.persistance.repository.EspecieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class EspecieServiceTest {
    @InjectMocks
    private EspecieService especieService;

    @Mock
    private EspecieOutPort especieOutPort;

    private static final Long ID_ESPECIE_1 = 1L;
    private static final Long ID_ESPECIE_2 = 2L;
    private static final Long ID_ESPECIE_3 = 3L;
    private static final String CODIGO_CVSA_1 = "CVSA1";
    private static final String CODIGO_CVSA_2 = "CVSA2";
    private static final String CODIGO_CVSA_3 = "CVSA3";
    private static final String DENOMINACION_1 = "Denominacion1";
    private static final String DENOMINACION_2 = "Denominacion2";
    private static final String DENOMINACION_3 = "Denominacion3";
    private static final int LAMINA_MINIMA_1 = 2;
    private static final int LAMINA_MINIMA_2 = 25;
    private static final int LAMINA_MINIMA_3 = 30;
    private static final Double PRECIO_1 = 1000.5;
    private static final Double PRECIO_2 = 59999.3;
    private static final Double PRECIO_3 = 59999.0;
    private static final String CAFCI_1 = "CAFCI1";
    private static final String CAFCI_2 = "CAFCI2";
    private static final String CAFCI_3 = "CAFCI3";
    private static final String CUENTA_EMISION_1 = "Cuenta emision1";
    private static final String CUENTA_EMISION_2 = "Cuenta emision2";
    private static final String CUENTA_EMISION_3 = "Cuenta emision3";
    private static final String ESTADO_1 = "ACTIVO";
    private static final String ESTADO_2 = "INACTIVO";
    private static final long ID_EMISOR_1 = 1L;
    private static final long ID_EMISOR_2 = 2L;
    private static final long ID_EMISOR_3 = 3L;
    private static final long ID_GERENTE_1 = 2L;
    private static final long ID_GERENTE_2 = 3L;
    private static final long ID_GERENTE_3 = 4L;
    private static final LocalDateTime VIGENCIA_1 = LocalDateTime.now().plusDays(100);
    private static final LocalDateTime VIGENCIA_2 = LocalDateTime.now().plusDays(100);
    private static final LocalDateTime VIGENCIA_3 = LocalDateTime.now().plusDays(50);
    private static final LocalDateTime PLAZO_LIQUIDACION_1 = LocalDateTime.now().plusDays(5);
    private static final LocalDateTime PLAZO_LIQUIDACION_2 = LocalDateTime.now().plusDays(25);
    private static final LocalDateTime PLAZO_LIQUIDACION_3 = LocalDateTime.now().plusDays(7);
    private static final String CODIGO_CNV_1 = "CNV1";
    private static final String CODIGO_CNV_2 = "CNV2";
    private static final String CODIGO_CNV_3 = "CNV3";
    private static final String ISIN_1 = "ISIN1";
    private static final String ISIN_2 = "ISIN2";
    private static final String ISIN_3 = "ISIN3";
    private static final String FAMILIA_FONDOS_1 = "Familia Fondos 1";
    private static final String FAMILIA_FONDOS_2 = "Familia Fondos2";
    private static final String FAMILIA_FONDOS_3 = "Familia Fondos3";
    private static final String OBSERVACIONES_1 = "Observaciones1";
    private static final String OBSERVACIONES_2 = "Observaciones2";
    private static final String OBSERVACIONES_3 = "Observaciones3";
    private static final boolean MOVIMIENTO_1 = true;
    private static final boolean MOVIMIENTO_2 = false;
    private static final LocalDateTime FECHA_ALTA_1 = LocalDateTime.now();
    private static final LocalDateTime FECHA_ALTA_2 = LocalDateTime.now();
    private static final LocalDateTime FECHA_ALTA_3 = LocalDateTime.now();

    private Especie especie1;
    private Especie especie2;
    private Especie especie3;

    private List<Especie> especies = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    especie1 = Especie.builder()
            .idEspecie(ID_ESPECIE_1)
            .codigoCVSA(CODIGO_CVSA_1)
            .denominacion(DENOMINACION_1)
            .laminaMinima(LAMINA_MINIMA_1)
            .precio(PRECIO_1)
            .cafci(CAFCI_1)
            .cuentaDeEmision(CUENTA_EMISION_1)
            .estado(ESTADO_1)
            .idEmisor(ID_EMISOR_1)
            .idGerente(ID_GERENTE_1)
            .vigencia(VIGENCIA_1)
            .plazoDeLiquidacion(PLAZO_LIQUIDACION_1)
            .codigoCNV(CODIGO_CNV_1)
            .isin(ISIN_1)
            .familiaDeFondos(FAMILIA_FONDOS_1)
            .observaciones(OBSERVACIONES_1)
            .movimiento(MOVIMIENTO_1)
            .fechaAlta(FECHA_ALTA_1)
            .build();

    especie2 = Especie.builder()
            .idEspecie(ID_ESPECIE_2)
            .codigoCVSA(CODIGO_CVSA_2)
            .denominacion(DENOMINACION_2)
            .laminaMinima(LAMINA_MINIMA_2)
            .precio(PRECIO_2)
            .cafci(CAFCI_2)
            .cuentaDeEmision(CUENTA_EMISION_2)
            .estado(ESTADO_2)
            .idEmisor(ID_EMISOR_2)
            .idGerente(ID_GERENTE_2)
            .vigencia(VIGENCIA_2)
            .plazoDeLiquidacion(PLAZO_LIQUIDACION_2)
            .codigoCNV(CODIGO_CNV_2)
            .isin(ISIN_2)
            .familiaDeFondos(FAMILIA_FONDOS_2)
            .observaciones(OBSERVACIONES_2)
            .movimiento(MOVIMIENTO_2)
            .fechaAlta(FECHA_ALTA_2)
            .build();

        especie3 = Especie.builder()
                .idEspecie(ID_ESPECIE_3)
                .codigoCVSA(CODIGO_CVSA_3)
                .denominacion(DENOMINACION_3)
                .laminaMinima(LAMINA_MINIMA_3)
                .precio(PRECIO_3)
                .cafci(CAFCI_3)
                .cuentaDeEmision(CUENTA_EMISION_3)
                .estado(ESTADO_1)
                .idEmisor(ID_EMISOR_3)
                .idGerente(ID_GERENTE_3)
                .vigencia(VIGENCIA_3)
                .plazoDeLiquidacion(PLAZO_LIQUIDACION_3)
                .codigoCNV(CODIGO_CNV_3)
                .isin(ISIN_3)
                .familiaDeFondos(FAMILIA_FONDOS_3)
                .observaciones(OBSERVACIONES_3)
                .movimiento(MOVIMIENTO_1)
                .fechaAlta(FECHA_ALTA_3)
                .build();

        especies.add(especie1);
        especies.add(especie2);
        especies.add(especie3);
    }

    @Test
    void deberiaRetornarEspecie_cuandoSeCreaUnaEspecie() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieConIdExistenteException {
        when(especieOutPort.crear(especie1)).thenReturn(especie1);

        Especie especieCreada = especieService.crear(especie1);
        assertNotNull(especieCreada);
        assertEquals(especie1.getCodigoCVSA(), especieCreada.getCodigoCVSA());
        assertEquals(especie1.getCodigoCVSA(), especieCreada.getCodigoCVSA());
        assertEquals(especie1.getDenominacion(), especieCreada.getDenominacion());
        assertEquals(especie1.getLaminaMinima(), especieCreada.getLaminaMinima());
        assertEquals(especie1.getPrecio(), especieCreada.getPrecio());
        assertEquals(especie1.getCafci(), especieCreada.getCafci());
        assertEquals(especie1.getCuentaDeEmision(), especieCreada.getCuentaDeEmision());
        assertEquals(especie1.getEstado(), especieCreada.getEstado());
        assertEquals(especie1.getIdEmisor(), especieCreada.getIdEmisor());
        assertEquals(especie1.getIdGerente(), especieCreada.getIdGerente());
        assertEquals(especie1.getVigencia(), especieCreada.getVigencia());
        assertEquals(especie1.getPlazoDeLiquidacion(), especieCreada.getPlazoDeLiquidacion());
        assertEquals(especie1.getCodigoCNV(), especieCreada.getCodigoCNV());
        assertEquals(especie1.getIsin(), especieCreada.getIsin());
        assertEquals(especie1.getObservaciones(), especieCreada.getObservaciones());
        assertEquals(especie1.isMovimiento(), especieCreada.isMovimiento());
        assertEquals(especie1.getFechaAlta(), especieCreada.getFechaAlta());
    }

    @Test
    void deberiaLanzarObjetoEnviadoNuloException_cuandoSeQuiereCrearUnaEspecieNula() {
        assertThrows(ObjetoEnviadoNuloException.class, () -> especieService.crear(null));
    }

    @Test
    void deberiaLanzarAtributosNulosException_cuandoSeCreaUnEspecieConCodigoCVSAIgualCero() {
        Especie especie = Especie.builder()
                .idEspecie(ID_ESPECIE_1)
                .codigoCVSA(null)
                .denominacion(DENOMINACION_1)
                .laminaMinima(LAMINA_MINIMA_1)
                .precio(PRECIO_1)
                .cafci(CAFCI_1)
                .cuentaDeEmision(CUENTA_EMISION_1)
                .estado(ESTADO_1)
                .idEmisor(ID_EMISOR_1)
                .idGerente(ID_GERENTE_1)
                .vigencia(VIGENCIA_1)
                .plazoDeLiquidacion(PLAZO_LIQUIDACION_1)
                .codigoCNV(CODIGO_CNV_1)
                .isin(ISIN_1)
                .familiaDeFondos(FAMILIA_FONDOS_1)
                .observaciones(OBSERVACIONES_1)
                .movimiento(MOVIMIENTO_1)
                .fechaAlta(FECHA_ALTA_2)
                .build();

        assertThrows(AtributosNulosException.class, () -> especieService.crear(especie));
    }

    @Test
    void deberiaLanzarAtributosNulosException_cuandoSeCreaUnEspecieConDenominacionNula() {
        Especie especie = Especie.builder()
                .idEspecie(ID_ESPECIE_1)
                .codigoCVSA(CODIGO_CVSA_1)
                .denominacion(null)
                .laminaMinima(LAMINA_MINIMA_1)
                .precio(PRECIO_1)
                .cafci(CAFCI_1)
                .cuentaDeEmision(CUENTA_EMISION_1)
                .estado(ESTADO_1)
                .idEmisor(ID_EMISOR_1)
                .idGerente(ID_GERENTE_1)
                .vigencia(VIGENCIA_1)
                .plazoDeLiquidacion(PLAZO_LIQUIDACION_1)
                .codigoCNV(CODIGO_CNV_1)
                .isin(ISIN_1)
                .familiaDeFondos(FAMILIA_FONDOS_1)
                .observaciones(OBSERVACIONES_1)
                .movimiento(MOVIMIENTO_1)
                .fechaAlta(FECHA_ALTA_1)
                .build();

        assertThrows(AtributosNulosException.class, () -> especieService.crear(especie));
    }

    @Test
    void deberiaLanzarAtributosNulosException_cuandoSeCreaUnEspecieConPlazoLiquidaciónNulo() {
        Especie especie = Especie.builder()
                .idEspecie(ID_ESPECIE_1)
                .codigoCVSA(CODIGO_CVSA_1)
                .denominacion(DENOMINACION_1)
                .laminaMinima(LAMINA_MINIMA_1)
                .precio(PRECIO_1)
                .cafci(CAFCI_1)
                .cuentaDeEmision(CUENTA_EMISION_1)
                .estado(ESTADO_1)
                .idEmisor(ID_EMISOR_1)
                .idGerente(ID_GERENTE_1)
                .vigencia(VIGENCIA_1)
                .plazoDeLiquidacion(null)
                .codigoCNV(CODIGO_CNV_1)
                .isin(ISIN_1)
                .familiaDeFondos(FAMILIA_FONDOS_1)
                .observaciones(OBSERVACIONES_1)
                .movimiento(MOVIMIENTO_1)
                .fechaAlta(FECHA_ALTA_1)
                .build();

        assertThrows(AtributosNulosException.class, () -> especieService.crear(especie));
    }

    @Test
    void deberiaRetornarUnaListaDeEspeciesTamanioTres_cuandoSePideListarEspecies() throws ObjetoEnviadoNuloException {
        when(especieOutPort.listarEspecies()).thenReturn(especies);

        List<Especie> listaDeEspecie = especieService.listarEspecies();

        assertEquals(especies, listaDeEspecie);
        assertEquals(3, listaDeEspecie.size());
    }

    @Test
    void deberiaRetornarUnaListaVacia_cuandoSePideListarEspeciesYNoHayNadaGuardado() throws ObjetoEnviadoNuloException {
        when(especieOutPort.listarEspecies()).thenReturn(Collections.emptyList());

        List<Especie> listaDeEspecie = especieService.listarEspecies();

        assertEquals(Collections.emptyList(), listaDeEspecie);
        assertEquals(0, listaDeEspecie.size());
    }

    @Test
    void deberiaRetornarUnaEspecie_cuandoSeBuscaPorId() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieNoEncontradaException {
        when(especieOutPort.obtenerPorId(ID_ESPECIE_1)).thenReturn(especie1);

        Especie especieEncontrada = especieService.obtenerPorId(ID_ESPECIE_1);
        assertEquals(especie1, especieEncontrada);
    }

    @Test
    void deberiaLanzarEspecieNoEncontradaException_cuandoSeBuscaUnIdQueNoEstaGuardado() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieNoEncontradaException {
        when(especieOutPort.obtenerPorId(ID_ESPECIE_1)).thenReturn(null);

        assertThrows(EspecieNoEncontradaException.class, () -> especieService.obtenerPorId(ID_ESPECIE_1));
    }

    @Test
    void deberiaRetornarUnaEspecieActualizada_cuandoSeActualizaUnaEspecieConIdConocido() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieConIdExistenteException, EspecieNoEncontradaException {
        Especie especieActualizada = Especie.builder()
                .idEspecie(ID_ESPECIE_1)
                .codigoCVSA(CODIGO_CVSA_3)
                .denominacion(DENOMINACION_3)
                .laminaMinima(LAMINA_MINIMA_3)
                .precio(PRECIO_3)
                .cafci(CAFCI_3)
                .cuentaDeEmision(CUENTA_EMISION_3)
                .estado(ESTADO_2)
                .idEmisor(ID_EMISOR_3)
                .idGerente(ID_GERENTE_3)
                .vigencia(VIGENCIA_3)
                .plazoDeLiquidacion(PLAZO_LIQUIDACION_3)
                .codigoCNV(CODIGO_CNV_3)
                .isin(ISIN_3)
                .familiaDeFondos(FAMILIA_FONDOS_3)
                .observaciones(OBSERVACIONES_3)
                .movimiento(MOVIMIENTO_2)
                .fechaAlta(FECHA_ALTA_3)
                .build();

        when(especieOutPort.existeElID(ID_ESPECIE_1)).thenReturn(true);
        when(especieOutPort.actualizar(especie1)).thenReturn(especieActualizada);

        Especie especieObtenida = especieService.actualizar(ID_ESPECIE_1, especie1);

        assertEquals(especieActualizada, especieObtenida);
    }

    @Test
    void deberiaRetornarUnaEspecieActualizada() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieConIdExistenteException, EspecieNoEncontradaException {
        Especie especieActualizada = Especie.builder()
                .idEspecie(ID_ESPECIE_1)
                .codigoCVSA(CODIGO_CVSA_3)
                .denominacion(DENOMINACION_3)
                .laminaMinima(LAMINA_MINIMA_3)
                .precio(PRECIO_3)
                .cafci(CAFCI_3)
                .cuentaDeEmision(CUENTA_EMISION_3)
                .estado(ESTADO_2)
                .idEmisor(ID_EMISOR_3)
                .idGerente(ID_GERENTE_3)
                .vigencia(VIGENCIA_3)
                .plazoDeLiquidacion(PLAZO_LIQUIDACION_3)
                .codigoCNV(CODIGO_CNV_3)
                .isin(ISIN_3)
                .familiaDeFondos(FAMILIA_FONDOS_3)
                .observaciones(OBSERVACIONES_3)
                .movimiento(MOVIMIENTO_2)
                .fechaAlta(FECHA_ALTA_3)
                .build();

        when(especieOutPort.existeElID(ID_ESPECIE_1)).thenReturn(true);
        when(especieOutPort.actualizar(especie1)).thenReturn(especieActualizada);

        Especie especieObtenida = especieService.actualizar(ID_ESPECIE_1, especie1);

        assertEquals(especieActualizada, especieObtenida);
    }

    @Test
    void deberiaEliminarUnaEspecie_cuandoSeEliminaUnaEspecieConIdConocido() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieNoEncontradaException {
        when(especieOutPort.existeElID(ID_ESPECIE_1)).thenReturn(true);

        doNothing().when(especieOutPort).eliminar(ID_ESPECIE_1);
        especieService.eliminar(ID_ESPECIE_1);

        verify(especieOutPort,times(1)).eliminar(ID_ESPECIE_1);
    }

    @Test
    void deberiaLanzarEspecieNoEncontradaException_cuandoSeEliminaUnaEspecieConIdDesconocido() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieNoEncontradaException {
        when(especieOutPort.existeElID(ID_ESPECIE_1)).thenReturn(false);

        doNothing().when(especieOutPort).eliminar(ID_ESPECIE_1);

        assertThrows(EspecieNoEncontradaException.class, () -> especieService.eliminar(ID_ESPECIE_1));
    }
}
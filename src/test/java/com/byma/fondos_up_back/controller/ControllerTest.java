package com.byma.fondos_up_back.controller;

import com.byma.fondos_up_back.application.port.in.EspecieInPort;
import com.byma.fondos_up_back.application.service.exception.AtributosNulosException;
import com.byma.fondos_up_back.application.service.exception.EspecieConIdExistenteException;
import com.byma.fondos_up_back.application.service.exception.EspecieNoEncontradaException;
import com.byma.fondos_up_back.application.service.exception.ObjetoEnviadoNuloException;
import com.byma.fondos_up_back.domain.model.Especie;
import com.byma.fondos_up_back.exception_handler.GlobalExceptionHandler;
import com.byma.fondos_up_back.infrastructure.adapter.in.web.controller.impl.EspecieController;
import com.byma.fondos_up_back.infrastructure.adapter.in.web.dto.request.EspecieRequestDTO;
import com.byma.fondos_up_back.infrastructure.adapter.in.web.dto.response.EspecieResponseDTO;
import com.byma.fondos_up_back.infrastructure.adapter.in.web.mapper.EspecieControllerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControllerTest {
    public static final long ID0_ESPECIE = 0L;
    public static final long ID2_ESPECIE = 2L;
    public static final String CODIGO_CVSA = "DEF456";
    public static final long ID1_ESPECIE = 1L;
    public static final String ESPECIE_DE_PRUEBA = "Especie de prueba";
    public static final LocalDate DATE_1 = LocalDate.now();

    @InjectMocks
    private EspecieController especieController;

    @Mock
    private EspecieInPort especieInPort;

    private MockMvc mockMvc;

    private List<Especie> especies = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(especieController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
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
    private EspecieRequestDTO mockEspecieRequestDTO() {
        return EspecieRequestDTO.builder()
                //.idEspecie(ID1_ESPECIE)
                .codigoCVSA(CODIGO_CVSA)
                .denominacion(ESPECIE_DE_PRUEBA)
                .plazoDeLiquidacion(DATE_1)
                .build();
    }
    private EspecieResponseDTO mockEspecieResponseDTO() {
        return EspecieResponseDTO.builder()
                .idEspecie(ID1_ESPECIE)
                .codigoCVSA(CODIGO_CVSA)
                .denominacion(ESPECIE_DE_PRUEBA)
                .plazoDeLiquidacion(DATE_1)
                .build();
    }
    private EspecieResponseDTO mockEspecieResponseDTO2() {
        return EspecieResponseDTO.builder()
                //.idEspecie(ID2_ESPECIE)
                .codigoCVSA(CODIGO_CVSA)
                .denominacion(ESPECIE_DE_PRUEBA)
                .plazoDeLiquidacion(DATE_1)
                .build();
    }
    private List<EspecieResponseDTO> mockEspecieResponseDTOList() {
        List<EspecieResponseDTO> especieResponseDTOList = new ArrayList<>();
        especieResponseDTOList.add(mockEspecieResponseDTO());
        especieResponseDTOList.add(mockEspecieResponseDTO2());
        return especieResponseDTOList;
    }
    private List<Especie> mockEspecieList() {
        especies.add(mockEspecie1());
        especies.add(mockEspecie2());
        return especies;
    }
    @Test
    void whenCreateEspecie_thenReturnEspecie() throws Exception {
        EspecieRequestDTO especieRequestDTO = mockEspecieRequestDTO();
        EspecieResponseDTO especieResponseDTO = mockEspecieResponseDTO();
        Especie especieMapeada = EspecieControllerMapper.especieRequestDtoAEspecie(especieRequestDTO);
        when(especieInPort.crear(especieMapeada)).thenReturn(mockEspecie1());
        ResponseEntity<EspecieResponseDTO> result = especieController.crear(especieRequestDTO);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(especieResponseDTO, result.getBody());
        verify(especieInPort, times(1)).crear(especieMapeada);
    }
    @Test
    void whenCreateEspecie_thenReturnAtributosNulosException() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieConIdExistenteException {
        EspecieRequestDTO especieRequestDTO = mockEspecieRequestDTO();
        especieRequestDTO.setDenominacion(null);
        doThrow(new AtributosNulosException("El atributo denominación no puede ser nulo"))
                .when(especieInPort).crear(any(Especie.class));
        Exception exception = assertThrows(AtributosNulosException.class, () -> {
            especieController.crear(especieRequestDTO);
        });
        assertInstanceOf(AtributosNulosException.class, exception);
        assertEquals("El atributo denominación no puede ser nulo", exception.getMessage());
    }
    @Test
    void whenCreateEspecie_thenReturnEspecieConIdExistenteException() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieConIdExistenteException {
        EspecieRequestDTO especieRequestDTO = mockEspecieRequestDTO();
        doThrow(new EspecieConIdExistenteException("El id de la especie ya existe"))
                .when(especieInPort).crear(any(Especie.class));
        Exception exception = assertThrows(EspecieConIdExistenteException.class, () -> {
            especieController.crear(especieRequestDTO);
        });
        assertInstanceOf(EspecieConIdExistenteException.class, exception);
        assertEquals("El id de la especie ya existe", exception.getMessage());
    }
    @Test
    void whenCreateEspecie_thenReturnObjetoEnviadoNuloException() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieConIdExistenteException {
        doThrow(new ObjetoEnviadoNuloException("El objeto enviado no puede ser nulo."))
                .when(especieInPort).crear(any(Especie.class));
        Exception exception = assertThrows(ObjetoEnviadoNuloException.class, () -> {
            especieController.crear(null);
        });
        assertInstanceOf(ObjetoEnviadoNuloException.class, exception);
    }

    @Test
    void whenUpdateEspecie_thenReturnEspecie() throws Exception {
        Especie especie = mockEspecie1();
        EspecieRequestDTO especieRequestDTO = mockEspecieRequestDTO();
        EspecieResponseDTO especieResponseDTO = mockEspecieResponseDTO();
        Especie especieMapeada = EspecieControllerMapper.especieRequestDtoAEspecie(especieRequestDTO);
        when(especieInPort.actualizar(ID1_ESPECIE, especieMapeada)).thenReturn(especie);
        ResponseEntity<EspecieResponseDTO> result = especieController.actualizar(ID1_ESPECIE, especieRequestDTO);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(especieResponseDTO, result.getBody());
        verify(especieInPort, times(1)).actualizar(ID1_ESPECIE, especieMapeada);
    }
    @Test
    void whenUpdateEspecie_thenReturnAtributosNulosException() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieConIdExistenteException, EspecieNoEncontradaException {
        EspecieRequestDTO especieRequestDTO = mockEspecieRequestDTO();
        especieRequestDTO.setDenominacion(null);
        doThrow(new AtributosNulosException("El atributo denominación no puede ser nulo"))
                .when(especieInPort).actualizar(anyLong(), any(Especie.class));
        Exception exception = assertThrows(AtributosNulosException.class, () -> {
            especieController.actualizar(ID1_ESPECIE, especieRequestDTO);
        });
        assertInstanceOf(AtributosNulosException.class, exception);
        assertEquals("El atributo denominación no puede ser nulo", exception.getMessage());
    }
    @Test
    void whenUpdateEspecie_thenReturnEspecieNoEncontradaException() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieConIdExistenteException, EspecieNoEncontradaException {
        doThrow(new EspecieNoEncontradaException("Especie no encontrada"))
                .when(especieInPort).actualizar(anyLong(), any(Especie.class));
        Exception exception = assertThrows(EspecieNoEncontradaException.class, () -> {
            especieController.actualizar(ID1_ESPECIE, mockEspecieRequestDTO());
        });
        assertInstanceOf(EspecieNoEncontradaException.class, exception);
        assertEquals("Especie no encontrada", exception.getMessage());
    }
    @Test
    void whenUpdateEspecie_thenReturnObjetoEnviadoNuloException() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieConIdExistenteException, EspecieNoEncontradaException {
        doThrow(new ObjetoEnviadoNuloException("El objeto enviado no puede ser nulo."))
                .when(especieInPort).actualizar(anyLong(), any(Especie.class));
        Exception exception = assertThrows(ObjetoEnviadoNuloException.class, () -> {
            especieController.actualizar(ID1_ESPECIE, null);
        });
        assertInstanceOf(ObjetoEnviadoNuloException.class, exception);
    }
    @Test
    void whenUpdateEspecie_thenReturnEspecieConIdExistenteException() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieConIdExistenteException, EspecieNoEncontradaException {
        doThrow(new EspecieConIdExistenteException("El id de la especie ya existe"))
                .when(especieInPort).actualizar(anyLong(), any(Especie.class));
        Exception exception = assertThrows(EspecieConIdExistenteException.class, () -> {
            especieController.actualizar(ID1_ESPECIE, mockEspecieRequestDTO());
        });
        assertInstanceOf(EspecieConIdExistenteException.class, exception);
        assertEquals("El id de la especie ya existe", exception.getMessage());
    }

    @Test
    void whenGetEspecies_thenReturn2Especies() throws Exception {
        when(especieInPort.listarEspecies()).thenReturn(mockEspecieList());
        ResponseEntity<List<EspecieResponseDTO>> result = especieController.listarEspecies();
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(especies.size(), Objects.requireNonNull(result.getBody()).size());
        verify(especieInPort, times(1)).listarEspecies();
    }

    @Test
    void whenGetEspecie_thenReturnEspecie() throws Exception {
        when(especieInPort.obtenerPorId(ID1_ESPECIE)).thenReturn(mockEspecie1());
        ResponseEntity<EspecieResponseDTO> result = especieController.obtenerPorIdEspecie(ID1_ESPECIE);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(mockEspecieResponseDTO(), result.getBody());
        verify(especieInPort, times(1)).obtenerPorId(ID1_ESPECIE);
    }
    @Test
    void whenGetEspecie_thenReturnEspecieNoEncontradaException() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieNoEncontradaException {
        doThrow(new EspecieNoEncontradaException("Especie no encontrada"))
                .when(especieInPort).obtenerPorId(anyLong());
        Exception exception = assertThrows(EspecieNoEncontradaException.class, () -> {
            especieController.obtenerPorIdEspecie(ID1_ESPECIE);
        });
        assertInstanceOf(EspecieNoEncontradaException.class, exception);
        assertEquals("Especie no encontrada", exception.getMessage());
    }
    @Test
    void whenDeleteEspecie_thenReturnNoContent() throws Exception {
        doNothing().when(especieInPort).eliminar(ID1_ESPECIE);
        ResponseEntity<Void> result = especieController.eliminar(ID1_ESPECIE);
        verify(especieInPort, times(1)).eliminar(ID1_ESPECIE);
        assertEquals(200, result.getStatusCodeValue());
        assertNull(result.getBody());
    }
    @Test
    void whenDeleteEspecie_thenReturnEspecieNoEncontradaException() throws ObjetoEnviadoNuloException, AtributosNulosException, EspecieNoEncontradaException {
        doThrow(new EspecieNoEncontradaException("Especie no encontrada"))
                .when(especieInPort).eliminar(anyLong());
        Exception exception = assertThrows(EspecieNoEncontradaException.class, () -> {
            especieController.eliminar(ID1_ESPECIE);
        });
        assertInstanceOf(EspecieNoEncontradaException.class, exception);
        assertEquals("Especie no encontrada", exception.getMessage());
    }
}

package com.byma.fondos_up_back.infrastructure.adapter.in.web.controller;

import com.byma.fondos_up_back.infrastructure.adapter.in.web.dto.request.EspecieRequestDTO;
import com.byma.fondos_up_back.infrastructure.adapter.in.web.dto.response.EspecieResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Especie", description = "Api para gestionar especies")

public interface ApiEspecie {

    @Operation(summary = "Crear especie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Especie creada exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<EspecieResponseDTO> crear(@RequestBody @Valid EspecieRequestDTO especieRequestDTO);

    @Operation(summary = "Obtener todas las especies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especies encontradas"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<List<EspecieResponseDTO>> listarEspecies();

//    @Operation(summary = "Obtener una especie por ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Especie encontrada"),
//            @ApiResponse(responseCode = "404", description = "Error: Especie no encontrada"),
//            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
//    })
//    ResponseEntity<EspecieResponseDTO> obtenerPorIdOrganizacionespecie(@PathVariable Long idOrganizacionespecie);

//    @Operation(summary = "Actualizar una especie")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Especie actualizada exitosamente"),
//            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
//    })
//    ResponseEntity<EspecieResponseDTO> actualizar(@PathVariable Long idOrganizacionespecie, @RequestBody @Valid EspecieRequestDTO especieRequestDTO);

    @Operation(summary = "Eliminar una especie por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Especie eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Error: Especie no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<Void> eliminar(@PathVariable Long idEspecie);
}

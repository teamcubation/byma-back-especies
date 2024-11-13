package com.byma.fondos_up_back.exception_handler;

import com.byma.fondos_up_back.application.service.exception.AtributosNulosException;
import com.byma.fondos_up_back.application.service.exception.EspecieConIdExistenteException;
import com.byma.fondos_up_back.application.service.exception.EspecieNoEncontradaException;
import com.byma.fondos_up_back.application.service.exception.ObjetoEnviadoNuloException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ObjetoEnviadoNuloException.class)
    public ErrorMessageResponse handleObjetoEnviadoNuloException(Exception exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EspecieNoEncontradaException.class)
    public ErrorMessageResponse handleEspecieNoEncontradaException(Exception exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AtributosNulosException.class)
    public ErrorMessageResponse handleAtributosNulosException(Exception exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EspecieConIdExistenteException.class)
    public ErrorMessageResponse handleEspecieConIdExistenteException(Exception exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorMessageResponse handleException(Exception exception, HttpServletRequest request) {
        return this.createErrorMessageResponse(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        ErrorMessageResponse errorMessageResponse = ConstruirErrorMessageResponseDeValidacion(e, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageResponse);
    }

    private ErrorMessageResponse ConstruirErrorMessageResponseDeValidacion(MethodArgumentNotValidException e, HttpServletRequest request) {
        BindingResult result = e.getBindingResult();
        return ErrorMessageResponse.builder()
                .exception(e.getClass().getName())
                .message("Error")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .details(result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList())).build();
    }

    private ErrorMessageResponse createErrorMessageResponse(Exception exception, HttpServletRequest request, HttpStatus status) {
        return ErrorMessageResponse.builder()
                .exception(exception.getClass().getName())
                .message(exception.getMessage())
                .status(status.value())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .build();
    }

}

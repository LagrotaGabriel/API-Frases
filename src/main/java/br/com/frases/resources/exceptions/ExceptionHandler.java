package br.com.frases.resources.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/** Classe cujo objetivo é realizar a manipulação de exceções
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 06/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/API-Frases/blob/master/src/main/java/br/com/frases/resources/exceptions/ExceptionHandler.java */
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFoundException(ObjectNotFoundException objectNotFoundException,
                                                                 HttpServletRequest httpServletRequest){

        StandartError error = new StandartError(LocalDateTime.now(), 400, objectNotFoundException.getMessage(),
                httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandartError> nullPointerException(NullPointerException nullPointerException,
                                                              HttpServletRequest httpServletRequest){

        StandartError error = new StandartError(LocalDateTime.now(), 400, nullPointerException.getMessage(),
                httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandartError> nullPointerException(HttpServletRequest httpServletRequest){

        StandartError error = new StandartError(
                LocalDateTime.now(), 400, "Falha de violação de dados", httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        StandartError error = new StandartError(
                LocalDateTime.now(), 400, "Falha de violação de dados", "/api/phrase");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}

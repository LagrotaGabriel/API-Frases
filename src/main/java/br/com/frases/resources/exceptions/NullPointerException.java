package br.com.frases.resources.exceptions;

/** Exceção personalizada para casos de NullPointer
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 06/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/API-Frases/blob/master/src/main/java/br/com/frases/resources/exceptions/NullPointerException.java */
public class NullPointerException extends RuntimeException{

    public NullPointerException(String message) {
        super(message);
    }

}

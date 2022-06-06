package br.com.frases.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

/** Classe contendo todos os atributos e métodos do objeto PhraseDTO
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 06/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/API-Frases/blob/master/src/main/java/br/com/frases/models/dto/PhraseDTO.java */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PhraseDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "data", access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime timeStamp;

    @JsonProperty(value = "frase", access = JsonProperty.Access.READ_WRITE, required = true)
    private String phrase;

}

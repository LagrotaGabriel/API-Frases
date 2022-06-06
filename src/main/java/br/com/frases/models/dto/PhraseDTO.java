package br.com.frases.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

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

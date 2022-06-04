package br.com.frases.models.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PhraseDTO {

    private Long id;
    private LocalDateTime timeStamp;
    private String phrase;

}

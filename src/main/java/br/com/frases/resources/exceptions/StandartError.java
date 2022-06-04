package br.com.frases.resources.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StandartError {

    private LocalDateTime localDateTime;
    private Integer status;
    private String error;
    private String path;

}

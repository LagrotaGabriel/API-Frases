package br.com.frases.models.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/** Classe contendo todos os atributos e métodos do objeto PhraseEntity
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 06/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/API-Frases/blob/master/src/main/java/br/com/frases/models/entity/PhraseEntity.java  */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@SequenceGenerator(allocationSize = 1, sequenceName = "sq_phrase", name="phrase")
@Table(name = "TB_PHRASE")
public class PhraseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "phrase")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "timeStamp")
    private LocalDateTime timeStamp;

    @NotNull
    @Column(name = "phrase")
    private String phrase;

}

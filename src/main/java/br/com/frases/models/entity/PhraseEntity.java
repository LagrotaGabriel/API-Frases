package br.com.frases.models.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

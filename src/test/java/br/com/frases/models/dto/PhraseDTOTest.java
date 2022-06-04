package br.com.frases.models.dto;

import br.com.frases.mocks.PhraseDTOBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class PhraseDTOTest {

    PhraseDTO phraseDTO;

    @Test
    @DisplayName("Should test PhraseDTO Setter methods")
    public void shouldTestPhraseDTOSetters(){

        Assertions.assertEquals("PhraseDTO(id=1, timeStamp=2022-06-04T02:06:57.260990900, phrase=Frase de " +
                "teste)", PhraseDTOBuilder.builder().withStaticTimeStamp().build().toString());


    }

    @Test
    @DisplayName("Should test PhraseDTO AllArgsConstructor method")
    public void shouldTestPhraseDTOAllArgsConstructor(){

        phraseDTO = new PhraseDTO(1L, LocalDateTime.parse("2022-06-04T02:06:57.260990900"), "Frase de teste");

        Assertions.assertEquals("PhraseDTO(id=1, timeStamp=2022-06-04T02:06:57.260990900, phrase=Frase de " +
                "teste)", phraseDTO.toString());


    }
}

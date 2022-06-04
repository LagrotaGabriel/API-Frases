package br.com.frases.models.entity;

import br.com.frases.mocks.PhraseEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class PhraseEntityTest {

    PhraseEntity phraseEntity;

    @Test
    @DisplayName("Should test phrase entity setters")
    public void shouldTestPhraseEntitySetters(){

        Assertions.assertEquals("PhraseEntity(id=1, timeStamp=2022-06-04T02:06:57.260990900, phrase=Frase de " +
                "teste)", PhraseEntityBuilder.builder().withStaticTimeStamp().build().toString());

    }

    @Test
    @DisplayName("Should test PhraseEntity AllArgsConstructor")
    public void shouldTestPhraseEntityAllArgsConstructor(){

        phraseEntity = new PhraseEntity(1L, LocalDateTime.parse("2022-06-04T02:06:57.260990900"), "Frase de teste");

        Assertions.assertEquals("PhraseEntity(id=1, timeStamp=2022-06-04T02:06:57.260990900, phrase=Frase de " +
                "teste)", phraseEntity.toString());


    }


}

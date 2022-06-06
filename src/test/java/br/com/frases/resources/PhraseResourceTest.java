package br.com.frases.resources;

import br.com.frases.mocks.PhraseDTOBuilder;
import br.com.frases.models.dto.PhraseDTO;
import br.com.frases.services.PhraseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PhraseResourceTest {

    @InjectMocks
    PhraseResource phraseResource;

    @Mock
    PhraseService phraseService;

    @Test
    @DisplayName("Should test PhraseResource FindAll method")
    public void shouldTestPhraseResourceFindAllMethod(){

        List<PhraseDTO> phraseDTOS = new ArrayList<>();
        phraseDTOS.add(PhraseDTOBuilder.builder().withStaticTimeStamp().build());

        Mockito.when(phraseService.findAllValidation()).thenReturn(phraseDTOS);

        Assertions.assertEquals("<200 OK OK,[PhraseDTO(id=1, timeStamp=2022-06-04T02:06:57.260990900, phrase=" +
                "Frase de teste)],[]>", phraseResource.findAll().toString());

    }

    @Test
    @DisplayName("Should test PhraseResource findById method")
    public void shouldTestPhraseResourceFindByIdMethod(){

        Mockito.when(phraseService.findByIdConverter(Mockito.any()))
                .thenReturn(PhraseDTOBuilder.builder().withStaticTimeStamp().build());

        Assertions.assertEquals("<200 OK OK,PhraseDTO(id=1, timeStamp=2022-06-04T02:06:57.260990900, " +
                "phrase=Frase de teste),[]>", phraseResource.findById(1L).toString());

    }

    @Test
    @DisplayName("Should test PhraseResourceCreateMethod")
    public void shouldTestPhraseResourceCreateMethod(){

        Mockito.when(phraseService.createValidation(Mockito.any()))
                .thenReturn(PhraseDTOBuilder.builder().withStaticTimeStamp().build());

        Assertions.assertEquals("<201 CREATED Created,PhraseDTO(id=1, timeStamp=2022-06-04T02:06:57.260990900, " +
                "phrase=Frase de teste),[]>", phraseResource.create(PhraseDTOBuilder.builder().withStaticTimeStamp()
                .build()).toString());

    }

}

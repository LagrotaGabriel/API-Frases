package br.com.frases.services;

import br.com.frases.config.ModelMapperConfig;
import br.com.frases.mocks.PhraseEntityBuilder;
import br.com.frases.models.entity.PhraseEntity;
import br.com.frases.resources.exceptions.ObjectNotFoundException;
import br.com.frases.services.dao.PhraseDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PhraseServiceTest {

    @InjectMocks
    PhraseService phraseService;

    @Mock
    PhraseDAOImpl phraseDAO;

    @Mock
    ModelMapperConfig modelMapper;

    @Test
    @DisplayName("Should test PhraseService FindAllValidation method with success")
    public void shouldTestPhraseServiceFindAllValidationMethodWithSuccess(){

        List<PhraseEntity> phraseEntities = new ArrayList<>();
        phraseEntities.add(PhraseEntityBuilder.builder().withStaticTimeStamp().build());

        Mockito.when(phraseDAO.findAll()).thenReturn(phraseEntities);
        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());

        Assertions.assertEquals("[PhraseDTO(id=1, timeStamp=2022-06-04T02:06:57.260990900, phrase=Frase de " +
                "teste)]", phraseService.findAllValidation().toString());

    }

    @Test
    @DisplayName("Should test PhraseService FindAllValidation method with ObjectNotFoundException throw")
    public void shouldTestPhraseServiceFindAllValidationMethodWithExceptionThrow(){

        try{
            Mockito.when(phraseDAO.findAll()).thenReturn(new ArrayList<>());
            phraseService.findAllValidation();
            Assertions.fail();
        }
        catch(ObjectNotFoundException e){
            Assertions.assertEquals("Não existe nenhuma frase cadastrada", e.getMessage());
        }

    }

    @Test
    @DisplayName("Should test PhraseService findByIdConverter method with success")
    public void shouldTestPhraseServiceFindByIdConverterWithSuccess(){

        Mockito.when(modelMapper.mapper())
                .thenReturn(new ModelMapper());
        Mockito.when(phraseDAO.findById(Mockito.any()))
                .thenReturn(PhraseEntityBuilder.builder().withStaticTimeStamp().build());

        Assertions.assertEquals("PhraseDTO(id=1, timeStamp=2022-06-04T02:06:57.260990900, phrase=Frase de " +
                "teste)", phraseService.findByIdConverter(1L).toString());

    }

    @Test
    @DisplayName("Should test PhraseService findByIdConverter method with ObjectNotFoundException throw")
    public void shouldTestPhraseServiceFindByIdConverterWithObjectNotFoundExceptionThrow(){

        try{
            Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());
            Mockito.when(phraseDAO.findById(Mockito.any())).thenThrow(ObjectNotFoundException.class);
            phraseService.findByIdConverter(1L);
            Assertions.fail();
        }
        catch(ObjectNotFoundException e){
            Assertions.assertNull(e.getMessage());
        }

    }

}

package br.com.frases.services;

import br.com.frases.config.ModelMapperConfig;
import br.com.frases.mocks.PhraseDTOBuilder;
import br.com.frases.mocks.PhraseEntityBuilder;
import br.com.frases.models.entity.PhraseEntity;
import br.com.frases.resources.exceptions.NullPointerException;
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

    @Test
    @DisplayName("Should test PhraseService createValidation method with success")
    public void shouldTestPhraseServiceCreateValidationWithSuccess(){

        Mockito.when(modelMapper.mapper())
                .thenReturn(new ModelMapper());
        Mockito.when(phraseDAO.create(Mockito.any()))
                .thenReturn(PhraseEntityBuilder.builder().withStaticTimeStamp().build());

        Assertions.assertEquals("PhraseDTO(id=1, timeStamp=2022-06-04T02:06:57.260990900, phrase=Frase de teste)",
                phraseService.createValidation(PhraseDTOBuilder.builder().withStaticTimeStamp().build()).toString());

    }

    @Test
    @DisplayName("Should test PhraseService createValidation method with NullPointerException throw")
    public void shouldTestPhraseServiceCreateValidationWithNullPointerExceptionThrow(){

        try {
            phraseService.createValidation(PhraseDTOBuilder.builder().withNullPhrase().withStaticTimeStamp().build());
            Assertions.fail();
        }
        catch (NullPointerException e){
            Assertions.assertEquals("A frase precisa estar preenchida", e.getMessage());
        }

    }

    @Test
    @DisplayName("Should test PhraseService updateValidation method with success")
    public void shouldTestPhraseServiceUpdateValidationWithSuccess(){

        Mockito.when(modelMapper.mapper())
                .thenReturn(new ModelMapper());

        Mockito.when(phraseDAO.update(Mockito.any(), Mockito.any()))
                .thenReturn(PhraseEntityBuilder.builder().withStaticTimeStamp().build());

        Assertions.assertEquals("PhraseDTO(id=1, timeStamp=2022-06-04T02:06:57.260990900, phrase=Frase de teste)",
                phraseService.updateValidation(1L, PhraseDTOBuilder.builder().withStaticTimeStamp().build()).toString());

    }

    @Test
    @DisplayName("Should test PhraseService updateValidation method with null id throwing a NullPointerException")
    public void shouldTestPhraseServiceUpdateValidationWithNullId(){

        try {
            phraseService.updateValidation(null, PhraseDTOBuilder.builder().withStaticTimeStamp().build());
            Assertions.fail();
        }
        catch (NullPointerException e){
            Assertions.assertEquals("ID não informado", e.getMessage());
        }

    }

    @Test
    @DisplayName("Should test PhraseService updateValidation method with null phrase throwing a NullPointerException")
    public void shouldTestPhraseServiceUpdateValidationWithNullPhrase(){

        try {
            phraseService.updateValidation(1L, PhraseDTOBuilder.builder().withNullPhrase().build());
            Assertions.fail();
        }
        catch (NullPointerException e){
            Assertions.assertEquals("A frase precisa estar preenchida", e.getMessage());
        }

    }

    @Test
    @DisplayName("Should test PhraseService deleteValidation method with success")
    public void shouldTestPhraseServiceDeleteValidationMethodWithSuccess(){
        Mockito.when(phraseDAO.delete(Mockito.any())).thenReturn(true);
        Assertions.assertTrue(phraseService.deleteValidation(1L));
    }

    @Test
    @DisplayName("Should test PhraseService deleteValidation method with NullPointerException throw")
    public void shouldTestPhraseServiceDeleteValidationMethodWithNullPointerExceptionThrow(){

        try{
            phraseService.deleteValidation(null);
            Assertions.fail();
        }
        catch (NullPointerException e){
            Assertions.assertEquals("Campo de preenchimento de id vazio", e.getMessage());
        }

    }

}

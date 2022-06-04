package br.com.frases.services.dao;

import br.com.frases.config.ModelMapperConfig;
import br.com.frases.mocks.PhraseDTOBuilder;
import br.com.frases.mocks.PhraseEntityBuilder;
import br.com.frases.repositories.PhraseRepository;
import br.com.frases.resources.exceptions.ObjectNotFoundException;
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
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PhraseDAOImplTest {

    @InjectMocks
    PhraseDAOImpl impl;

    @Mock
    PhraseRepository phraseRepository;

    @Mock
    ModelMapperConfig modelMapper;

    @Test
    @DisplayName("Should test PhraseDAOImpl creation method")
    public void shouldTestCreationMethod(){

        Mockito.when(phraseRepository.save(Mockito.any()))
                .thenReturn(PhraseEntityBuilder.builder().withStaticTimeStamp().build());

        Mockito.when(modelMapper.mapper()).thenReturn(new ModelMapper());

        Assertions.assertEquals("PhraseEntity(id=1, timeStamp=2022-06-04T02:06:57.260990900, phrase=Frase de " +
                "teste)", impl.create(PhraseDTOBuilder.builder().withStaticTimeStamp().build()).toString());

    }

    @Test
    @DisplayName("Should test PhraseDAOImpl findAll method")
    public void shouldTestFindAllMethod(){

        Mockito.when(phraseRepository.findAll()).thenReturn(new ArrayList<>());

        Assertions.assertEquals("[]", impl.findAll().toString());

    }

    @Test
    @DisplayName("Should test PhraseDAOImpl findById method with success")
    public void shouldTestFindByIdWithSuccess(){

        Mockito.when(phraseRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(PhraseEntityBuilder.builder().withStaticTimeStamp().build()));

        Assertions.assertEquals("PhraseEntity(id=1, timeStamp=2022-06-04T02:06:57.260990900, phrase=Frase de " +
                "teste)", impl.findById(1L).toString());

    }

    @Test
    @DisplayName("Should test PhraseDAOImpl findById method with exception")
    public void shouldTestFindByIdWithException(){

        try{
            Mockito.when(phraseRepository.findById(Mockito.any()))
                    .thenReturn(Optional.empty());
            impl.findById(1L);
            Assertions.fail();
        }
        catch (ObjectNotFoundException e){
            Assertions.assertEquals("Frase não encontrada", e.getMessage());
        }

    }

    @Test
    @DisplayName("Should test PhraseDAOImpl update method with success")
    public void shouldTestUpdateWithSuccess(){

        Mockito.when(phraseRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(PhraseEntityBuilder.builder().withStaticTimeStamp().build()));

        Assertions.assertEquals("PhraseEntity(id=1, timeStamp=2022-06-04T02:06:57.260990900, phrase=Frase de " +
                "teste)", impl.update(1L, PhraseDTOBuilder.builder().withStaticTimeStamp().build()).toString());

    }

    @Test
    @DisplayName("Should test PhraseDAOImpl update method with exception")
    public void shouldTestUpdateWithException(){

        try{
            Mockito.when(phraseRepository.findById(Mockito.any())).thenReturn(Optional.empty());
            impl.update(1L, PhraseDTOBuilder.builder().withStaticTimeStamp().build());
            Assertions.fail();
        }
        catch (ObjectNotFoundException e){
            Assertions.assertEquals("Id não encontrado", e.getMessage());
        }

    }

    @Test
    @DisplayName("Should test PhraseDAOImpl deleteById method with success")
    public void shouldTestDeleteByIdWithSuccess(){

        Mockito.when(phraseRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(PhraseEntityBuilder.builder().withStaticTimeStamp().build()));

        Mockito.doNothing().when(phraseRepository).deleteById(Mockito.any());

        Assertions.assertTrue(impl.delete(1L));

    }

    @Test
    @DisplayName("Should test PhraseDAOImpl deleteById method with exception")
    public void shouldTestDeleteByIdWithException(){

        try{
            Mockito.when(phraseRepository.findById(Mockito.any())).thenReturn(Optional.empty());
            impl.delete(1L);
            Assertions.fail();
        }
        catch(ObjectNotFoundException e){
            Assertions.assertEquals("Id não encontrado", e.getMessage());
        }

    }








}

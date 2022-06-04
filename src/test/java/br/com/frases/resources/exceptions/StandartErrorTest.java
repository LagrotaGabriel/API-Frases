package br.com.frases.resources.exceptions;

import br.com.frases.mocks.StandartErrorBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class StandartErrorTest {

    StandartError standartError;

    @Test
    @DisplayName("Should test StandartError setter methods")
    public void shouldTestStandartErrorSetterMethods(){

        Assertions.assertEquals("StandartError(localDateTime=2022-06-04T02:06:57.260990900, status=400, error=Erro de " +
                "teste, path=/test)", StandartErrorBuilder.builder().withStaticTimeStamp().build().toString());

    }

    @Test
    @DisplayName("Should test StandartError AllArgsConstructor")
    public void shouldTestStandartErrorAllArgsConstructor(){

        standartError = new StandartError(LocalDateTime.parse("2022-06-04T02:06:57.260990900"), 400,
                "Erro de teste", "/test");

        Assertions.assertEquals("StandartError(localDateTime=2022-06-04T02:06:57.260990900, status=400, error=Erro de " +
                "teste, path=/test)", standartError.toString());
    }

}

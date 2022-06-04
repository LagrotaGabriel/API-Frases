package br.com.frases.mocks;

import br.com.frases.resources.exceptions.StandartError;

import java.time.LocalDateTime;

public class StandartErrorBuilder {

    private StandartError standartError;
    private StandartErrorBuilder(){};

    public static StandartErrorBuilder builder(){

        StandartErrorBuilder builder = new StandartErrorBuilder();
        builder.standartError = new StandartError();

        builder.standartError.setLocalDateTime(LocalDateTime.now());
        builder.standartError.setStatus(400);
        builder.standartError.setError("Erro de teste");
        builder.standartError.setPath("/test");

        return builder;

    }

    public StandartErrorBuilder withStaticTimeStamp(){
        standartError.setLocalDateTime(LocalDateTime.parse("2022-06-04T02:06:57.260990900"));
        return this;
    }

    public StandartError build(){
        return standartError;
    }
}

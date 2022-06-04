package br.com.frases.mocks;

import br.com.frases.models.entity.PhraseEntity;

import java.time.LocalDateTime;

public class PhraseEntityBuilder {

    private PhraseEntity phraseEntity;
    private PhraseEntityBuilder(){};

    public static PhraseEntityBuilder builder(){

        PhraseEntityBuilder builder = new PhraseEntityBuilder();
        builder.phraseEntity = new PhraseEntity();

        builder.phraseEntity.setId(1L);
        builder.phraseEntity.setTimeStamp(LocalDateTime.now());
        builder.phraseEntity.setPhrase("Frase de teste");

        return builder;
    }

    public PhraseEntityBuilder withStaticTimeStamp(){
        phraseEntity.setTimeStamp(LocalDateTime.parse("2022-06-04T02:06:57.260990900"));
        return this;
    }

    public PhraseEntity build(){
        return phraseEntity;
    }

}

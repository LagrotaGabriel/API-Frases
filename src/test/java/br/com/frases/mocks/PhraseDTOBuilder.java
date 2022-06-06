package br.com.frases.mocks;

import br.com.frases.models.dto.PhraseDTO;

import java.time.LocalDateTime;

public class PhraseDTOBuilder {

    private PhraseDTO phraseDTO;
    private PhraseDTOBuilder(){};

    public static PhraseDTOBuilder builder(){

        PhraseDTOBuilder builder = new PhraseDTOBuilder();
        builder.phraseDTO = new PhraseDTO();

        builder.phraseDTO.setId(1L);
        builder.phraseDTO.setTimeStamp(LocalDateTime.now());
        builder.phraseDTO.setPhrase("Frase de teste");

        return builder;

    }

    public PhraseDTOBuilder withStaticTimeStamp(){
        phraseDTO.setTimeStamp(LocalDateTime.parse("2022-06-04T02:06:57.260990900"));
        return this;
    }

    public PhraseDTOBuilder withNullPhrase(){
        phraseDTO.setPhrase(null);
        return this;
    }

    public PhraseDTO build(){
        return phraseDTO;
    }
}

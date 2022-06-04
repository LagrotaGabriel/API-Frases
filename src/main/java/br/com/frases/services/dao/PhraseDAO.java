package br.com.frases.services.dao;

import br.com.frases.models.dto.PhraseDTO;
import br.com.frases.models.entity.PhraseEntity;

import java.util.List;

public interface PhraseDAO {

    PhraseEntity create(PhraseDTO phraseDTO);
    List<PhraseEntity> findAll();
    PhraseEntity findById(Long id);
    PhraseEntity update(PhraseDTO phraseDTO);
    Boolean delete(PhraseDTO phraseDTO);

}

package br.com.frases.services.dao;

import br.com.frases.models.dto.PhraseDTO;
import br.com.frases.models.entity.PhraseEntity;

import java.util.List;

/** Interface cujo objetivo é fornecer os métodos para tratamento de persistência no banco de dados do projeto
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 06/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/API-Frases/blob/master/src/main/java/br/com/frases/services/dao/PhraseDAO.java */
public interface PhraseDAO {

    PhraseEntity create(PhraseDTO phraseDTO);
    List<PhraseEntity> findAll();
    PhraseEntity findById(Long id);
    PhraseEntity update(Long id, PhraseDTO phraseDTO);
    Boolean delete(Long id);

}

package br.com.frases.services.dao;

import br.com.frases.config.ModelMapperConfig;
import br.com.frases.models.dto.PhraseDTO;
import br.com.frases.models.entity.PhraseEntity;
import br.com.frases.repositories.PhraseRepository;
import br.com.frases.resources.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/** Classe de serviço cujo objetivo é realizar a implementação do CRUD do projeto. Tem acesso direto ao banco de dados
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 06/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/API-Frases/blob/master/src/main/java/br/com/frases/services/dao/PhraseDAOImpl.java */
@Service
public class PhraseDAOImpl implements PhraseDAO{

    @Autowired
    PhraseRepository phraseRepository;

    @Autowired
    ModelMapperConfig modelMapper;

    /** Método que objetiva persistir uma nova frase no banco de dados
     ** @param phraseDTO Recebe um objeto do tipo PhraseDTO
     ** @return Retorna um objeto do tipo PhraseEntity, após persistência */
    @Override
    public PhraseEntity create(PhraseDTO phraseDTO) {
        return phraseRepository.save(modelMapper.mapper().map(phraseDTO, PhraseEntity.class));
    }

    /** Método que objetiva buscar todas as frases no banco de dados
     ** @return Retorna uma lista contendo todas as frases salvas no banco de dados */
    @Override
    public List<PhraseEntity> findAll() {
        return phraseRepository.findAll();
    }

    /** Método que objetiva procurar no banco de dados uma frase pelo id
     ** @param id Recebe um id do tipo Long
     ** @return Retorna um objeto do tipo PhraseEntity */
    @Override
    public PhraseEntity findById(Long id) {
        Optional<PhraseEntity> phraseEntity = phraseRepository.findById(id);
        return phraseEntity.orElseThrow(() -> new ObjectNotFoundException("Frase não encontrada"));
    }

    /** Método que objetiva atualizar uma frase no banco de dados
     ** @param id Recebe um id do tipo Long
     ** @param phraseDTO Recebe um objeto do tipo PhraseDTO
     ** @return Retorna um objeto do tipo PhraseEntity */
    @Override
    public PhraseEntity update(Long id, PhraseDTO phraseDTO) {

        Optional<PhraseEntity> phraseEntity = phraseRepository.findById(id);

        if(phraseEntity.isPresent()) {
            phraseEntity.ifPresent(entity -> entity.setPhrase(phraseDTO.getPhrase()));
            phraseRepository.save(phraseEntity.get());
        }

        return phraseEntity.orElseThrow(() -> new ObjectNotFoundException("Id não encontrado"));

    }

    /** Método que objetiva deletar uma frase no banco de dados
     ** @param id Recebe um id do tipo Long
     ** @return Retorna um Booleano informando o sucesso da operação */
    @Override
    public Boolean delete(Long id) {

        Optional<PhraseEntity> phraseEntity = phraseRepository.findById(id);
        if(phraseEntity.isPresent()){
            phraseRepository.deleteById(id);
            return true;
        }
        else{
            throw new ObjectNotFoundException("Id não encontrado");
        }

    }

}

package br.com.frases.services;

import br.com.frases.config.ModelMapperConfig;
import br.com.frases.models.dto.PhraseDTO;
import br.com.frases.models.entity.PhraseEntity;
import br.com.frases.resources.exceptions.NullPointerException;
import br.com.frases.resources.exceptions.ObjectNotFoundException;
import br.com.frases.services.dao.PhraseDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/** Classe cujo objetivo é realizar as validações e conexões entre a camada de Controller e a camada de persistência
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 06/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/API-Frases/blob/master/src/main/java/br/com/frases/services/PhraseService.java */
@Service
public class PhraseService {

    @Autowired
    PhraseDAOImpl phraseDAO;

    @Autowired
    ModelMapperConfig modelMapper;

    /** Método que objetiva realizar as validações pertinentes à criação de uma nova frase
     ** @param phraseDTO Recebe um objeto do tipo PhraseDTO
     ** @return Retorna um objeto do tipo PhraseDTO */
    public PhraseDTO createValidation(PhraseDTO phraseDTO){

        if(phraseDTO.getPhrase() != null && !phraseDTO.getPhrase().equals("")) {
            phraseDTO.setTimeStamp(LocalDateTime.now());
            return modelMapper.mapper().map(phraseDAO.create(phraseDTO), PhraseDTO.class);
        }
        throw new NullPointerException("A frase precisa estar preenchida");

    }

    /** Método que objetiva realizar as validações pertinentes à busca de todas as frases cadastradas
     ** @return Retorna uma lista de objetos do tipo PhraseDTO */
    public List<PhraseDTO> findAllValidation(){

        List<PhraseEntity> phraseEntities = phraseDAO.findAll();

        if(!phraseDAO.findAll().isEmpty()) return phraseEntities.stream()
                .map(x -> modelMapper.mapper().map(x, PhraseDTO.class)).collect(Collectors.toList());

        throw new ObjectNotFoundException("Não existe nenhuma frase cadastrada");

    }

    /** Método que objetiva realizar as validações pertinentes à busca de uma frase cadastrada pelo seu ID
     ** @param id Recebe um id do tipo Long
     ** @return Retorna um objeto do tipo PhraseDTO
     ** @throws ObjectNotFoundException Exceção propagada da camada de persistência, que informa caso o id na qual foi
     ** passado pelo parâmetro por parte do usuário, não resultou em nenhum resultado */
    public PhraseDTO findByIdConverter(Long id) throws ObjectNotFoundException{

        return modelMapper.mapper().map(phraseDAO.findById(id), PhraseDTO.class);

    }

    /** Método que objetiva realizar as validações pertinentes à atualização de uma frase cadastrada no banco de dados
     ** @param id Recebe um id do tipo Long
     ** @param phraseDTO Recebe um objeto do tipo PhraseDTO
     ** @return Retorna um objeto do tipo PhraseDTO */
    public PhraseDTO updateValidation(Long id, PhraseDTO phraseDTO){

        if(phraseDTO.getPhrase() != null && !phraseDTO.getPhrase().equals("") && id != null) {
            return modelMapper.mapper().map(phraseDAO.update(id, phraseDTO), PhraseDTO.class);
        }
        else if(id == null){
            throw new NullPointerException("ID não informado");
        }
        else {
            throw new NullPointerException("A frase precisa estar preenchida");
        }

    }

    /** Método que objetiva realizar as validações pertinentes ao delete de uma frase cadastrada no banco de dados
     ** @param id Recebe um id do tipo Long
     ** @return Retorna um Booleano */
    public Boolean deleteValidation(Long id){

        if (id != null) return phraseDAO.delete(id);
        throw new NullPointerException("Campo de preenchimento de id vazio");

    }

}

package br.com.frases.services;

import br.com.frases.config.ModelMapperConfig;
import br.com.frases.models.dto.PhraseDTO;
import br.com.frases.models.entity.PhraseEntity;
import br.com.frases.resources.exceptions.NullPointerException;
import br.com.frases.resources.exceptions.ObjectNotFoundException;
import br.com.frases.services.dao.PhraseDAO;
import br.com.frases.services.dao.PhraseDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhraseService {

    @Autowired
    PhraseDAOImpl phraseDAO;

    @Autowired
    ModelMapperConfig modelMapper;

    public List<PhraseDTO> findAllValidation(){

        List<PhraseEntity> phraseEntities = phraseDAO.findAll();

        if(!phraseDAO.findAll().isEmpty()) return phraseEntities.stream()
                .map(x -> modelMapper.mapper().map(x, PhraseDTO.class)).collect(Collectors.toList());

        throw new ObjectNotFoundException("Não existe nenhuma frase cadastrada");

    }

    public PhraseDTO findByIdConverter(Long id) throws ObjectNotFoundException{

        return modelMapper.mapper().map(phraseDAO.findById(id), PhraseDTO.class);

    }

    public PhraseDTO createValidation(PhraseDTO phraseDTO){

        if(phraseDTO.getPhrase() != null && !phraseDTO.getPhrase().equals("")) {
            phraseDTO.setTimeStamp(LocalDateTime.now());
            return modelMapper.mapper().map(phraseDAO.create(phraseDTO), PhraseDTO.class);
        }
        throw new NullPointerException("A frase precisa estar preenchida");

    }

}

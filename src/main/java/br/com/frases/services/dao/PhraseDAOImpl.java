package br.com.frases.services.dao;

import br.com.frases.config.ModelMapperConfig;
import br.com.frases.models.dto.PhraseDTO;
import br.com.frases.models.entity.PhraseEntity;
import br.com.frases.repositories.PhraseRepository;
import br.com.frases.resources.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PhraseDAOImpl implements PhraseDAO{

    @Autowired
    PhraseRepository phraseRepository;

    @Autowired
    ModelMapperConfig modelMapper;

    @Override
    public PhraseEntity create(PhraseDTO phraseDTO) {
        return phraseRepository.save(modelMapper.mapper().map(phraseDTO, PhraseEntity.class));
    }

    @Override
    public List<PhraseEntity> findAll() {
        return phraseRepository.findAll();
    }

    @Override
    public PhraseEntity findById(Long id) {
        Optional<PhraseEntity> phraseEntity = phraseRepository.findById(id);
        return phraseEntity.orElseThrow(() -> new ObjectNotFoundException("Frase não encontrada"));
    }

    @Override
    public PhraseEntity update(Long id, PhraseDTO phraseDTO) {

        Optional<PhraseEntity> phraseEntity = phraseRepository.findById(id);

        if(phraseEntity.isPresent()) {
            phraseEntity.ifPresent(entity -> entity.setPhrase(phraseDTO.getPhrase()));
            phraseRepository.save(phraseEntity.get());
        }

        return phraseEntity.orElseThrow(() -> new ObjectNotFoundException("Id não encontrado"));

    }

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

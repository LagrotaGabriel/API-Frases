package br.com.frases.repositories;

import br.com.frases.models.entity.PhraseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhraseRepository extends JpaRepository<PhraseEntity, Long> {
}

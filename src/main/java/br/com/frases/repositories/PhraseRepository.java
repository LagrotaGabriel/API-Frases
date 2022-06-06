package br.com.frases.repositories;

import br.com.frases.models.entity.PhraseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Classe cujo objetivo é realizar a comunicação com o banco de dados através do JPA
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 06/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/API-Frases/blob/master/src/main/java/br/com/frases/repositories/PhraseRepository.java */
@Repository
public interface PhraseRepository extends JpaRepository<PhraseEntity, Long> {
}

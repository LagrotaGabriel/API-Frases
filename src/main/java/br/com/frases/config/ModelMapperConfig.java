package br.com.frases.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Classe cujo objetivo é realizar a configuração do ModelMapper
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 06/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/API-Frases/blob/master/src/main/java/br/com/frases/config/ModelMapperConfig.java */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

}

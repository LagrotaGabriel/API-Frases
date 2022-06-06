package br.com.frases.resources;

import br.com.frases.models.dto.PhraseDTO;
import br.com.frases.resources.exceptions.ObjectNotFoundException;
import br.com.frases.services.PhraseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/** Classe cujo objetivo é fornecer os endpoints para comunicação com a camada de serviços através do JSON
 ** @author Gabriel Lagrota
 ** @version 1.0.0
 ** @since 06/06/2022
 ** @email gabriellagrota23@gmail.com
 ** @github https://github.com/LagrotaGabriel/API-Frases/blob/master/src/main/java/br/com/frases/resources/PhraseResource.java */
@RestController
@RequestMapping("api/phrase")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@Api(value = "O aplicativo tem como intuito realizar o cadastro, consulta, alteração e delete de frases")
public class PhraseResource {

    @Autowired
    PhraseService phraseService;

    /** Método que objetiva retornar uma lista contendo todas as frases salvas no banco de dados da API
     ** @return retorna uma lista de contendo todos os objetos do tipo PhraseDTO do banco de dados da API */
    @ApiOperation(
            value = "Buscar todas as frases",
            notes = "Busca todas as frases cadastradas no banco de dados da API",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    @ApiResponses(value = {
        @ApiResponse(code=200, message = "Frases encontradas com sucesso", response = PhraseDTO.class),
        @ApiResponse(code=401, message = "Acesso não autorizado"),
        @ApiResponse(code=404, message = "Frases não encontradas", response = ObjectNotFoundException.class),
        @ApiResponse(code=500, message = "Erro de comunicação com a API")
    })
    @GetMapping
    public ResponseEntity<List<PhraseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(phraseService.findAllValidation());
    }

    /** Método que objetiva buscar uma frase no banco de dados através do id inserido pelo usuário no path
     ** @param id identificação da frase na qual o usuário deseja buscar
     ** @return Retorna um objeto do tipo PhraseDTO, caso seja encontrado no banco de dados após a busca pelo id */
    @ApiOperation(
            value = "Busca por ID",
            notes = "Busca uma frase no banco de dados do projeto com base no id passado pelo usuário",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Frase encontrada com sucesso", response = PhraseDTO.class),
            @ApiResponse(code=401, message = "Acesso não autorizado"),
            @ApiResponse(code=404, message = "Frase não encontrada", response = ObjectNotFoundException.class),
            @ApiResponse(code=500, message = "Falha de comunicação com a API")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PhraseDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(phraseService.findByIdConverter(id));
    }

    /** Método que objetiva o cadastrar uma nova frase no banco de dados através do JSON passado pelo usuário no
     ** corpo da requisição. Este objeto é validado na camada de serviços
     ** @param phraseDTO Recebe no corpo da requisição json, um objeto do tipo PhraseDTO
     ** @return Retorna no corpo da response, o objeto do tipo JSON cadastrado */
    @ApiOperation(
            value = "Cadastrar nova frase",
            notes = "Salva uma nova frase no banco de dados da API",
            produces = MediaType.APPLICATION_JSON,
            consumes = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Frase criada com sucesso", response = PhraseDTO.class),
            @ApiResponse(code = 201, message = "Frase criada com sucesso", response = PhraseDTO.class),
            @ApiResponse(code = 400, message = "Falha de violação de dados", response = HttpMessageNotReadableException.class),
            @ApiResponse(code = 401, message = "Acesso não autorizado"),
            @ApiResponse(code = 500, message = "Falha de comunicação com a API")
    })
    @PostMapping
    public ResponseEntity<PhraseDTO> create(@RequestBody PhraseDTO phraseDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(phraseService.createValidation(phraseDTO));
    }

    /** Método que objetiva atualizar uma frase no banco de dados, de acordo com o id da frase que o usuário deseja
     ** atualizar e de acordo com o corpo da requisição passado pelo usuário, que deve conter os atributos do objeto
     ** atualizados
     ** @param id  Recebe pelo path, passado pelo usuário, um id do tipo Long
     ** @param phraseDTO  Recebe no corpo da requisição, um objeto do tipo JSON que é convertido para o tipo PhraseDTO
     ** @return Retorna no corpo da requisição o objeto atualizado, do tipo PhraseDTO */
    @ApiOperation(
            value = "Atualiza frase por id",
            notes = "Atualiza uma frase no banco de dados da API com base no id e no valor passado pelo usuário",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Frase atualizada com sucesso", response = PhraseDTO.class),
            @ApiResponse(code = 201, message = "Frase atualizada com sucesso", response = PhraseDTO.class),
            @ApiResponse(code = 400, message = "Falha de violação de dados", response = HttpMessageNotReadableException.class),
            @ApiResponse(code = 401, message = "Acesso não autorizado"),
            @ApiResponse(code = 404, message = "Usuário não encontrado"),
            @ApiResponse(code = 500, message = "Falha de comunicação com a API")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PhraseDTO> update(@PathVariable Long id, @RequestBody PhraseDTO phraseDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(phraseService.updateValidation(id, phraseDTO));
    }

    /** Método que objetiva deletar uma frase no banco de dados, de acordo com o id passado no path pelo usuário
     ** @param id Recebe um id do tipo Long
     ** @return Retorna o status da requisição, que pode ser OK ou NOT_FOUND, de acordo com o processamento na camada de
     ** persistência e de serviços */
    @ApiOperation(
            value = "Deleta uma frase",
            notes = "Deleta uma frase do banco de dados com base no id passado pelo usuário através do path",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Frase deletada com sucesso", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Falha de violação de dados", response = HttpMessageNotReadableException.class),
            @ApiResponse(code = 401, message = "Acesso não autorizado"),
            @ApiResponse(code = 404, message = "Frase não encontrada", response = ObjectNotFoundException.class),
            @ApiResponse(code = 500, message = "Falha de comunicação com a API")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if(phraseService.deleteValidation(id)) return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}

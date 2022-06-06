package br.com.frases.resources;

import br.com.frases.config.ModelMapperConfig;
import br.com.frases.models.dto.PhraseDTO;
import br.com.frases.resources.exceptions.ObjectNotFoundException;
import br.com.frases.services.PhraseService;
import br.com.frases.services.dao.PhraseDAOImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("api/phrase")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
@Api(value = "O aplicativo tem como intuito realizar o cadastro, consulta, alteração e delete de frases")
public class PhraseResource {

    @Autowired
    PhraseDAOImpl phraseDAO;

    @Autowired
    PhraseService phraseService;

    @Autowired
    ModelMapperConfig modelMapper;

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

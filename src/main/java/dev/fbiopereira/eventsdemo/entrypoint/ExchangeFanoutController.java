package dev.fbiopereira.eventsdemo.entrypoint;


import dev.fbiopereira.eventsdemo.core.usecase.CreateExchangeFanoutUseCase;
import dev.fbiopereira.eventsdemo.entrypoint.mapper.ExchangeFanoutCreateRequestMapper;
import dev.fbiopereira.eventsdemo.entrypoint.request.ExchangeFanoutCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/exchanges")
@Tag(name = "Exchange Fanout", description = "Operações para gerenciar as Exchanges do tipo Fanout")
public class ExchangeFanoutController {


    private final CreateExchangeFanoutUseCase createExchangeFanoutUseCase;
    private final ExchangeFanoutCreateRequestMapper exchangeFanoutCreateRequestMapper;

    public ExchangeFanoutController(
            CreateExchangeFanoutUseCase createExchangeFanoutUseCase,
            ExchangeFanoutCreateRequestMapper exchangeFanoutCreateRequestMapper) {
        this.createExchangeFanoutUseCase = createExchangeFanoutUseCase;
        this.exchangeFanoutCreateRequestMapper = exchangeFanoutCreateRequestMapper;
    }


    @PostMapping("/fanout")
    @Operation(
            summary = "Criar exchange fanout",
            description = "Cria uma nova exchange do tipo fanout no RabbitMQ com os parâmetros especificados e salva as configurações no MongoDB incluindo o esquema CloudEvent 1.0"
    )
    @ApiResponse(responseCode = "201", description = "Exchange criada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)))
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos ou esquema CloudEvent inválido")
    @ApiResponse(responseCode = "500", description = "Erro ao criar a exchange")
    public ResponseEntity<Map<String, Object>> createFanoutExchange(@RequestBody ExchangeFanoutCreateRequest request) {

        var exchangeFanout = exchangeFanoutCreateRequestMapper.toExchangeFanout(request);
        createExchangeFanoutUseCase.createExchangeFanout(exchangeFanout);
        return ResponseEntity.ok().build();
    }



}

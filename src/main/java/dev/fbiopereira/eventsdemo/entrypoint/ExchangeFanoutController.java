package dev.fbiopereira.eventsdemo.entrypoint;


import dev.fbiopereira.eventsdemo.core.usecase.CreateExchangeFanoutQueueUseCase;
import dev.fbiopereira.eventsdemo.core.usecase.CreateExchangeFanoutUseCase;
import dev.fbiopereira.eventsdemo.entrypoint.mapper.ExchangeFanoutCreateRequestMapper;
import dev.fbiopereira.eventsdemo.entrypoint.mapper.ExchangeFanoutQueueCreateRequestMapper;
import dev.fbiopereira.eventsdemo.entrypoint.request.ExchangeFanoutCreateRequest;
import dev.fbiopereira.eventsdemo.entrypoint.request.ExchangeFanoutQueueCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
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
    private final ExchangeFanoutQueueCreateRequestMapper exchangeFanoutQueueCreateRequestMapper;
    private final CreateExchangeFanoutQueueUseCase createExchangeFanoutQueueUseCase;

    public ExchangeFanoutController(CreateExchangeFanoutUseCase createExchangeFanoutUseCase,
                                   ExchangeFanoutCreateRequestMapper exchangeFanoutCreateRequestMapper,
                                   ExchangeFanoutQueueCreateRequestMapper exchangeFanoutQueueCreateRequestMapper,
                                   CreateExchangeFanoutQueueUseCase createExchangeFanoutQueueUseCase) {
        this.createExchangeFanoutUseCase = createExchangeFanoutUseCase;
        this.exchangeFanoutCreateRequestMapper = exchangeFanoutCreateRequestMapper;
        this.exchangeFanoutQueueCreateRequestMapper = exchangeFanoutQueueCreateRequestMapper;
        this.createExchangeFanoutQueueUseCase = createExchangeFanoutQueueUseCase;
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
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/fanoutqueue")
    @Operation(
            summary = "Criar fila associada a exchange fanout",
            description = "Cria uma nova fila no RabbitMQ e a associa a uma exchange fanout existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Fila criada e associada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar a fila ou associação")
    })
    public ResponseEntity<String> createFanoutQueue(@RequestBody ExchangeFanoutQueueCreateRequest request) {

        var exchangeFanoutQueue = exchangeFanoutQueueCreateRequestMapper.toExchangeFanoutQueue(request);
        createExchangeFanoutQueueUseCase.createExchangeFanoutQueue(exchangeFanoutQueue);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


}

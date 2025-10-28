package dev.fbiopereira.eventsdemo.entrypoint;

import dev.fbiopereira.eventsdemo.core.usecase.ProduceFanoutMessageUseCase;
import dev.fbiopereira.eventsdemo.entrypoint.mapper.ExchangeFanoutMessageRequestMapper;
import dev.fbiopereira.eventsdemo.entrypoint.request.ExchangeFanoutMessageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/corpevents")
@Tag(name = "Eventos Corporativos", description = "Operações para enviar eventos corporativos")
public class ExchangeFanoutMessageController {

    private final ProduceFanoutMessageUseCase produceFanoutMessageUseCase;
    private final ExchangeFanoutMessageRequestMapper exchangeFanoutMessageRequestMapper;

    public ExchangeFanoutMessageController(ProduceFanoutMessageUseCase produceFanoutMessageUseCase,
                                          ExchangeFanoutMessageRequestMapper exchangeFanoutMessageRequestMapper) {
        this.produceFanoutMessageUseCase = produceFanoutMessageUseCase;
        this.exchangeFanoutMessageRequestMapper = exchangeFanoutMessageRequestMapper;
    }


    @PostMapping("/event")
    @Operation(
            summary = "Enviar um evento corporativo",
            description = "Emvia uma mensagem para o broker de mensagens"
    )
    @ApiResponse(responseCode = "201", description = "Mensagem enviada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)))
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    @ApiResponse(responseCode = "500", description = "Erro ao enviar a mensagem")
    public ResponseEntity<Map<String, Object>> sendCorporateEvent(@RequestBody ExchangeFanoutMessageRequest request) {

        var exchangeFanoutMessage = exchangeFanoutMessageRequestMapper.toExchangeFanoutMessage(request);
        produceFanoutMessageUseCase.sendMessage(exchangeFanoutMessage);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}

package dev.fbiopereira.eventsdemo.fanoutexchange.rest.controller;

import dev.fbiopereira.eventsdemo.fanoutexchange.document.EventDocument;
import dev.fbiopereira.eventsdemo.fanoutexchange.repository.EventRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Events", description = "Operações para consultar eventos enviados")
public class EventController {

    private final EventRepository eventRepository;

    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os eventos",
            description = "Retorna uma lista de todos os eventos enviados para as exchanges"
    )
    @ApiResponse(responseCode = "200", description = "Lista de eventos retornada com sucesso")
    public ResponseEntity<List<EventDocument>> getAllEvents() {
        List<EventDocument> events = eventRepository.findAll();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar evento por ID",
            description = "Retorna um evento específico pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Evento encontrado"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    })
    public ResponseEntity<EventDocument> getEventById(
            @Parameter(description = "ID do evento") @PathVariable String id) {
        Optional<EventDocument> event = eventRepository.findById(id);
        return event.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/exchange/{exchangeName}")
    @Operation(
            summary = "Listar eventos por exchange",
            description = "Retorna todos os eventos enviados para uma exchange específica"
    )
    @ApiResponse(responseCode = "200", description = "Lista de eventos da exchange retornada com sucesso")
    public ResponseEntity<List<EventDocument>> getEventsByExchange(
            @Parameter(description = "Nome da exchange") @PathVariable String exchangeName) {
        List<EventDocument> events = eventRepository.findByExchangeName(exchangeName);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/status/{status}")
    @Operation(
            summary = "Listar eventos por status",
            description = "Retorna todos os eventos com um status específico (RECEIVED, SENT, ERROR)"
    )
    @ApiResponse(responseCode = "200", description = "Lista de eventos com o status especificado")
    public ResponseEntity<List<EventDocument>> getEventsByStatus(
            @Parameter(description = "Status do evento (RECEIVED, SENT, ERROR)") @PathVariable String status) {
        List<EventDocument> events = eventRepository.findByStatus(status.toUpperCase());
        return ResponseEntity.ok(events);
    }
}
